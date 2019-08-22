package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @Description: 初始化数据到pg数据库中
 * @Author: liaosijun
 * @Time: 2019/6/6 15:36
 */
public class PgGisDataInitUtils {

	private final static String host_url = "jdbc:postgresql://192.168.80.106:5432/gis?characterEncoding=UTF-8";
	private final static String user = "postgres";
	private final static String pwd = "123456";

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
	 * 存放线的数据到gis_dev_ext
	 */
	public void dealLineData(){
		String colSql = "select string_agg(field_name,',') from dev.gis_dev_tpl_attr where type_id=29";
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
				fields = fields.replace("id","dev_id");
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
			String int_sql = "insert into dev.gis_dev_ext  (dev_id,data_info) values (?,?)";
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
					Long id = (Long) map.get("dev_id");
					if(id == null){
						continue;
					}
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

	/**
	 * 处理点的数据到gis_dev_ext
	 */
	public void dealPoint() throws Exception {
		Map<Long,String> typeMap = new HashMap<>(); // key 存typeId, value存typeId 对应的属性字段
		String typeIdSql = "select distinct p_id from dev.share_dev_type where id in (select type_id from dev.gis_dev_point group by type_id)";

		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
//		rst = stt.executeQuery(typeIdSql);
//		List<Long> typeIDs = new ArrayList<>();
//		while(rst.next()){
//			typeIDs.add(rst.getLong(1));
//		}
//
//		String fieldsSql = "select string_agg(field_name,',') from dev.gis_dev_tpl_attr where type_id=?";
//		prest = conn.prepareStatement(fieldsSql);
//		for (Long l : typeIDs){
//			prest.setLong(1,l);
//			rst = prest.executeQuery(fieldsSql);
//			while (rst.next()){
//				typeMap.put(l,rst.getString(1));
//			}
//		}

		// 偷懒，类型都是一样

		String sl = "select b.name,b.dev_id,b.geom,a.code,a.x,a.y,a.dmgc,a.jdms,a.jglx,a.jggg,a.jgcz,a.ysdm,a.kcdw," +
				"a.qsdw,a.jdjb,a.pxjdh,a.pj,a.dlm,a.bz,a.dhxzb,a.dhyzb,a.fzlx,a.tsdh from " +
				"js_point_copy1 a left join gis_dev_point b on a.code = b.code";
		String[] abc = "name,dev_id,geom,code,x,y,dmgc,jdms,jglx,jggg,jgcz,ysdm,kcdw,qsdw,jdjb,pxjdh,pj,dlm,bz,dhxzb,dhyzb,fzlx,tsdh".split(",");
		stt = conn.createStatement();
		rst = stt.executeQuery(sl);
		ArrayList<HashMap<String,Object>> list = new ArrayList<>();
		while (rst.next()) {
			int len = abc.length;
			int i = 0;
			HashMap map = new HashMap();
			while(len-- > 0) {

				map.put(abc[i], rst.getObject(abc[i++]));
			}
			list.add(map);
		}


			int size = 1000;
			int total = list.size();
			int loopcnt = total/size == 0 ? total/size : total/size + 1;
			String int_sql = "insert into gis_dev_ext  (dev_id,data_info) values (?,?)";
			prest = conn.prepareStatement(int_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					String jsonStr  = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
//					String jsonStr = new ObjectMapper().writeValueAsString(map);
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");
					Long id = (Long) map.get("dev_id");
					if(id == null){
						continue;
					}
					prest.setLong(1, id);
					prest.setObject(2, jsonObject);
					prest.addBatch();
				}
				 prest.executeBatch();
				 conn.commit();
				step ++;
			}
	}

	void updateData(){
//		String sql = "select id, data_info from gis_dev_ext where caliber is not null";
		String sql = getSqlFromxxxxml();
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		List list = Lists.newArrayList();
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(sql);
			while (rst.next()) {
				Length l = new Length();
				Object datainfo =  rst.getObject("datainfo");
//				Object datainfo =  rst.getObject("data_info");
//				Long id = rst.getLong("id");
				Long id = rst.getLong("devid");
				l.setObj(datainfo);
				l.setId(id);
				list.add(l);
			}

			String sql2 = "update gis_dev_ext set data_info = ? where caliber is not null and id = ?";
			prest = conn.prepareStatement(sql2);
			int i =0;
			conn.setAutoCommit(false);
//			for (Object objjj : list) {
//				Length obj = (Length)objjj;
//				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
//				jb.remove("length");
//				System.out.println("================>" + obj.getObj().toString());
//				PGobject jsonObject = new PGobject();
//				jsonObject.setValue(JSONObject.toJSONString(jb));
//				jsonObject.setType("jsonb");
//				prest.setObject(1, jsonObject);
//				prest.setLong(2,obj.getId());
//				prest.addBatch();
//				System.out.println("-------->" + jb.toString() + (i++));
//			}

			long today = new Date().getTime();
			long oneDay = 24 * 3600 * 1000;
			long second = 1000;
			int ix = 1;
			int bx = 40;
			// 增加data_info 一个字段
			for (Object objjj : list) {

				Length obj = (Length)objjj;
				if(Objects.isNull(obj.getObj())) {
					continue;
				}
				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
				Date d = new Date(today - (oneDay * ix++));
				Date dt = new Date(today - (oneDay * ix++) + second * bx ++);
				jb.fluentPut("install_date", d);
				jb.fluentPut("fix_time",  dt);
				PGobject jsonObject = new PGobject();
				jsonObject.setValue(JSONObject.toJSONString(jb));
				jsonObject.setType("jsonb");
				prest.setObject(1, jsonObject);
				prest.setLong(2,obj.getId());
				prest.addBatch();
				System.out.println("-------->" + jb.toString() + (i++));
			}
			// 并发死锁，如果没有设置autocommit false
			prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			try{
//				if(prest!= null){
//					prest.close();
//				}
//				if(rst != null){
//					rst.close();
//				}
//				if(stt != null){
//					stt.close();
//				}
//				if(conn != null){
//					conn.close();
//				}
//			}catch (Exception e){
//				e.printStackTrace();
//			}
		}


	}

	public static String  getSqlFromxxxxml() {
		String fileName = "/temp.sql";
		File sqlFile = new File(PgGisDataInitUtils.class.getResource(fileName).getFile());
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(sqlFile));
			String str = null;
			while((str = reader.readLine()) != null){
				sb.append(str + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return String.valueOf(sb);
	}

	public static void main(String[] args) {
		PgGisDataInitUtils p = new PgGisDataInitUtils();
		try {
//			p.dealLine();
//			p.dealLineData();
//			p.dealPoint();
			p.updateData();
		}catch (Exception e){
			e.printStackTrace();
		}
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

	private class Length{
		private Long id;
		private Object obj;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}
	}
}