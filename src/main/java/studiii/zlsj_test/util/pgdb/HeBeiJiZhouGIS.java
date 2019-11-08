package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liaosijun
 * @Time: 2019/10/10 10:32
 */
public class HeBeiJiZhouGIS {

	/**
	 * step 1 ： 把临时表xx_line 的数据导入到share_dev中
	 * @param args
	 */
	private void dealLine(){
		Connection conn = PgGisDataInitUtils.getConn();
		int size = 2000;  // 每次处理2000条
		int page = 0;
		String sql_t = "select count(*) from xx_line";
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
		// 分页处理，如果数据量大的话，是有必要的
		int loopcnt = total/size == 0 ? total/size : total/size + 1;
		if (total < size) {
			loopcnt = 1;
		}
		while (loopcnt-- > 0) {
			String querySql = "select * from xx_line";
			String int_sql = "insert into share_dev (id,type_id,name,platform_code,create_by,update_by) VALUES (?,?,?,'GISSERVICE','admin','admin')";
			PreparedStatement prest = null;
			ResultSet rs = null;
			Statement st = null;
			try {
				List<ShareDev> list = new ArrayList<ShareDev>();
				st = conn.createStatement();
				prest = conn.prepareStatement(querySql);
				rs = prest.executeQuery();
				while (rs.next()) {
					ShareDev dev = new ShareDev();
					dev.setId(rs.getLong("id"));
					dev.setCaliber(rs.getString("gj") == null ? 0 : Integer.parseInt(rs.getString("gj")));
					list.add(dev);
				}
				prest = conn.prepareStatement(int_sql);
				conn.setAutoCommit(false);
				for (ShareDev p : list){
					String name = "";
					int caliber = p.getCaliber();
					long type_id = 0L;
					if (caliber >= 900) {
						name = "DN900（含）以上管段";
						type_id = 14;
					} else if ( caliber >= 600 && caliber < 900){
						name = "DN600-DN900管段";
						type_id = 12;
					} else if ( caliber >= 400 && caliber < 600) {
						name = "DN400-DN600管段";
						type_id = 11;
					} else if (caliber >= 200 && caliber < 400) {
						name = "DN200-DN400管段";
						type_id = 10;
					} else if (caliber >= 100 && caliber < 200) {
						name = "DN100-DN200管段";
						type_id = 8;
					} else {
						name = "DN100（不含）以下管段";
						type_id = 9;
					}

					prest.setLong(1,p.getId());
					prest.setLong(2,type_id);
					prest.setString(3,name);
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) rs.close();
					if (st != null) st.close();
					if (prest != null) prest.close();
					if (conn != null) conn.close();
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
	 * step 2 : 把临时表xx_point 的管点数据 导入到share_dev表中
	 */
	private void dealPoint(){
		Connection conn = PgGisDataInitUtils.getConn();
		int size = 2000;  // 每次处理2000条
		int page = 0;
		String sql_t = "select count(*) from xx_point";
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
		if (total < size) {
			loopcnt = 1;
		}
		while (loopcnt-- > 0) {
			String sql = "select * from (SELECT a.*,b.id typeid,b.name FROM xx_point a left join share_dev_type b on a.类别 = b.name and b.limb_leaf=2) as tx order by gid limit " + size + " offset " + page * size;
			String int_sql = "insert into share_dev (id,type_id,name,platform_code,create_by,update_by) VALUES (?,?,?,'GISSERVICE','admin','admin')";
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
					long id = rs.getLong("gid");
					pointNow.setId(id);
					String name = rs.getString("name");
					pointNow.setName(name);
					long type_id = rs.getLong("typeid");
					pointNow.setTypeId(type_id);
					list.add(pointNow);
				}
				for (PointNow p : list){
					prest.setLong(1,p.getId());
					prest.setLong(2,p.getTypeId());
					prest.setString(3,  p.getName());
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
			} catch (SQLException e) {
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
	 * step 3 ： 把水管数据导入到gis_dev_ext中去
	 *
	 */
	public void dealLineData(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 13";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = PgGisDataInitUtils.getConn();
		String fields = null;
		PreparedStatement prest = null;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				fields = rst.getString(1);
			}
			String[] abc = fields.split(",");
			String sql2 = "SELECT " + fields + "  from  (select id,qdbh start_code, zdbh end_code, cz material, gj caliber,qdms start_depth, zdms end_depth, mslx cover_mode,azrq install_date, chr mapping_man,geom  from xx_line ) a left join (select id dev_id, type_id,name from share_dev) b on a.id = b.dev_id";
			rst = stt.executeQuery(sql2);
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
			if (total < size) {
				loopcnt = 1;
			}
			String int_sql = "insert into gis_dev_ext (dev_id,name,code,caliber,material, geom,tpl_type_id,data_info,create_by,update_by) values (?,?,?,?,?,?,?,?,'admin','admin')";
			prest = conn.prepareStatement(int_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					Long id = (Long) map.get("dev_id");
					if(id == null){
						continue;
					}
					String name = (String) map.get("name");
					String code = (String) map.get("start_code") + "-" + (String) map.get("end_code");
					int caliber = Integer.parseInt(String.valueOf(map.get("caliber")));
					String material = (String) map.get("material");
					Object geom = map.get("geom");
					prest.setLong(1, id);
					prest.setString(2, name);
					prest.setString(3, code);
					prest.setInt(4, caliber);
					prest.setString(5, material);
					prest.setObject(6,geom);
					prest.setLong(7,13);

					// 封装data_info 数据
					if(map.containsKey("geom")) {
						map.remove("geom"); // 删除geom
					}
					String jsonStr = JSONObject.toJSONString(map,                             SerializerFeature.WriteMapNullValue);  // 长一点，记住null的字段不省略
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");

					prest.setObject(8,jsonObject);
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}
			System.out.println("把管网数据导入到gis_dev_ext成功！");
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
	 *  step 4 : 处理点的数据到gis_dev_ext
	 */
	public void dealPointData(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 1";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = PgGisDataInitUtils.getConn();
		String fields = null;
		PreparedStatement prest = null;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				fields = rst.getString(1);
			}
			String[] abc = fields.split(",");
			String sql2 = "SELECT " + fields + ",type_id  from  (select 管点编 code, x坐标 x, y坐标 y, 地面高 ground_height, 埋深 depth, 规格 spec, 材质 material, 井规格 well_spec, 道路名 addr, 备注 remark, 测绘人 mapping_man,geom,gid  from xx_point ) a left join (select id dev_id, type_id,name from share_dev) b on a.gid = b.dev_id";
			rst = stt.executeQuery(sql2);
			ArrayList<HashMap<String,Object>> list = new ArrayList<>();
			while (rst.next()) {
				int len = abc.length;
				int i = 0;
				HashMap map = new HashMap();
				while(len-- > 0) {
					map.put(abc[i], rst.getObject(abc[i++]));
				}
				map.put("type_id", rst.getLong("type_id"));
				list.add(map);
			}

			int size = 1000;
			int total = list.size();
			int loopcnt = total/size == 0 ? total/size : total/size + 1;
			if (total < size) {
				loopcnt = 1;
			}
			String int_sql = "insert into gis_dev_ext (dev_id,name,code,material, geom,tpl_type_id,data_info,create_by,update_by) values (?,?,?,?,?,?,?,'admin','admin')";
			prest = conn.prepareStatement(int_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					Long id = (Long) map.get("dev_id");
					if(id == null){
						continue;
					}
					String name = (String) map.get("name");
					String code = (String) map.get("code");
					String material =  map.get("material") == null ? "" : String.valueOf(map.get("material"));
					Object geom = map.get("geom");
					Long typeId = Long.parseLong(String.valueOf(map.get("type_id")));
					prest.setLong(1, id);
					prest.setString(2, name);
					prest.setString(3, code);
					prest.setString(4, material);
					prest.setObject(5,geom);
					prest.setLong(6,typeId);

					// 封装data_info 数据
					if(map.containsKey("geom")) {
						map.remove("geom"); // 删除geom
					}
					if(map.containsKey("type_id")) {
						map.remove("type_id");
					}
					String jsonStr = JSONObject.toJSONString(map,SerializerFeature.WriteMapNullValue);  // 长一点，记住null的字段不省略
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");

					prest.setObject(7,jsonObject);
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}
			System.out.println("把管点数据导入到gis_dev_ext成功！");
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
		HeBeiJiZhouGIS heBeiJiZhouGIS = new HeBeiJiZhouGIS();
//		heBeiJiZhouGIS.dealLine();
//		heBeiJiZhouGIS.dealPoint();
		heBeiJiZhouGIS.dealLineData();
//		heBeiJiZhouGIS.dealPointData();
	}
}
