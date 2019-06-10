package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSONObject;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 初始化数据到pg数据库中
 * @Author: liaosijun
 * @Time: 2019/6/6 15:36
 */
public class PgGisDataInitUtils {

	private final static String host_url = "jdbc:postgresql://192.168.10.244:5432/gis-test?characterEncoding=UTF-8";
	private final static String user = "postgres";
	private final static String pwd = "postgres";

	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(host_url, user, pwd);
//			Statement st = conn.createStatement();
//			String sql = "select * from dev.gis_dict_detail";
//			ResultSet rs = st.executeQuery(sql);
//			while (rs.next()) {
//				System.out.print(rs.getLong(1) + "\t");
//				System.out.print(rs.getLong(2) + "\t");
//				System.out.print(rs.getString(3) + "\t");
//				System.out.print(rs.getString(4) + "\t");
//				System.out.println("\n");
//			}
//			rs.close();
//			st.close();
//			conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {

		}
		return conn;
	}

	/**
	 * 处理管网数据, 存放到gis_dev_line的数据
	 */
	private void dealLine(){
		/**
		 * 我们表对应数据字段5个： 设备模板ID、管网名称、管网编号、口径、空间信息
		 */
		Connection conn = getConn();
		int size = 1000;  // 每次处理1000条
		int page = 0;
		String sql_t = "select count(*) from water_pipe";
		int total = 0;
		Statement stt = null;
		ResultSet rst = null;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(sql_t);
			while (rst.next()) {
				total = rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rst.close();
				stt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		int loopcnt = total/size == 0 ? total/size : total/size + 1;
		while (loopcnt-- > 0) {
			String sql = "SELECT gid,  featid, gczj, geom FROM water_pipe order by gid limit " + size + " offset " + page * size;
			String int_sql = "insert into dev.gis_dev_line (tpl_id,name,code,caliber,geom,id) VALUES (1, ?,?,?,?,?)";
			PreparedStatement prest = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				List<PointNow> list = new ArrayList<PointNow>();
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				prest = conn.prepareStatement(int_sql);
				conn.setAutoCommit(false);
				while (rs.next()) {
					PointNow pointNow = new PointNow();
					String code = rs.getString(2);
					int kj = rs.getInt(3);
					int id = rs.getInt(1);
					PGgeometry geom = (PGgeometry)rs.getObject(4);
					pointNow.setGeom(geom);
					pointNow.setKj(kj);
					pointNow.setCode(code);
					pointNow.setId(id);
					list.add(pointNow);
				}
				for (PointNow p : list){
					prest.setString(1,new String("输配水管".getBytes("UTF-8")));
					prest.setString(2,p.getCode());
					prest.setInt(3,p.getKj());
					prest.setObject(4,p.getGeom());
					prest.setInt(5,p.getId());
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					st.close();
					prest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			page ++;
			System.out.println("导入 " + page  + " page");
		}
		System.out.println("completed");
	}

	/**
	 * 存放数据到gis_dev_data
	 */
	public void dealLineData(){
		String colSql = "select string_agg(field_name,',') from dev.gis_tpl_attr where tpl_id = 1";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = getConn();
		String fields = null;
		PreparedStatement prest = null;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				fields = rst.getString(1);
			}
			if (!StringUtils.isEmpty(fields)){
				fields = fields.replace("geom","a.geom");
			}
			String[] abc = fields.split(",");
			String sql2 = "SELECT " + fields + " from water_pipe a left join dev.gis_dev_line b on a.gid = b.id";
			rst = stt.executeQuery(sql2);
			ArrayList<HashMap<String,Object>> list = new ArrayList<>();
			while (rst.next()) {
				int len = abc.length;
				int i = 0;
				HashMap map = new HashMap();
				while(len-- > 0) {
					if(abc[i].equals("a.geom")){
						abc[i] = "geom";
					}
					map.put(abc[i], rst.getObject(abc[i++]));
				}
				list.add(map);
			}

			int size = 1000;
			int total = list.size();
			int loopcnt = total/size == 0 ? total/size : total/size + 1;
			String int_sql = "insert into dev.gis_dev_data  (dev_id,data_info) values (?,?)";
			prest = conn.prepareStatement(int_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					String jsonStr = JSONObject.toJSONString(map);
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");
					long id = (long) map.get("id");
					prest.setLong(1, id);
					prest.setObject(2, jsonObject);
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rst.close();
				stt.close();
				prest.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		PgGisDataInitUtils p = new PgGisDataInitUtils();
//		p.dealLine();
		p.dealLineData();
	}

	private class PointNow{
		private Integer id;
		private String name;
		private String code;
		private Integer kj;
		private PGgeometry geom;


		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getKj() {
			return kj;
		}

		public void setKj(Integer kj) {
			this.kj = kj;
		}

		public PGgeometry getGeom() {
			return geom;
		}

		public void setGeom(PGgeometry geom) {
			this.geom = geom;
		}
	}
}