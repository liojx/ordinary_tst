package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;
import studiii.zlsj_test.cdBankPeriod.tools.Jxl;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Description: 初始化数据到pg数据库中
 * @Author: liaosijun
 * @Time: 2019/6/6 15:36
 */
public class PgGisDataInitUtils {

	private final static String host_url = "jdbc:postgresql://119.3.141.56:5432/gis?characterEncoding=GBK";
//	private final static String host_url = "jdbc:postgresql://192.168.80.70:5432/test?characterEncoding=GBK";
	private final static String user = "postgres";
	private final static String pwd = "JDgis_pg0911";
//	private final static String pwd = "postgres";

	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(host_url, user, pwd);
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
		int size = 2000;  // 每次处理1000条
		int page = 0;
		String sql_t = "select count(*) from jz_line";
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
			String int_sql = "insert into share_dev (id,type_id,name,platform_code,create_by,update_by) VALUES (?,?,?,?,?,?)";
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
					prest.setString(4,"GISSERVICE");
					prest.setString(5,"admin");
					prest.setString(6,"admin");
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
	 * 存放线的数据到gis_dev_ext
	 */
	public void dealLineData(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 13";
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
			}

			String[] abc = fields.split(",");

			String sql2 = "SELECT " + fields + "  from  (select id,qdbh start_code, zdbh end_code, cz material, gj caliber,qdms start_depth, zdms end_depth, mslx cover_mode,azrq install_date, chr mapping_man,geom  from xx_line_copy1 ) a left join (select id dev_id, type_id,name from share_dev) b on a.id = b.dev_id";
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
			String int_sql = "insert into gis_dev_ext  (dev_id,caliber,material, geom,data_info,tpl_type_id) values (?,?,?,?,?,?)";
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
					String name = (String) map.get("name");
//					String code = (String) map.get("start_code") + "-" + (String) map.get("end_code");
					int caliber = Integer.parseInt(String.valueOf(map.get("caliber")));
					String material = (String) map.get("material");
//					Long tplId = Long.parseLong(String.valueOf(map.get("tpl_type_id")));
					Object geom = map.get("geom");
					prest.setLong(1, id);
//					prest.setString(2, name);
//					prest.setString(3, code);
					prest.setInt(2, caliber);
					prest.setString(3, material);
					prest.setObject(4,geom);
//					prest.setLong(7,tplId);
					prest.setObject(5,jsonObject);
					prest.setLong(6, -1);
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
	 * 更新 gis_dev_ext 中 data_info 的所有数据
	 * 河北冀州
	 */
	public void updatedealPIPEData(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 13";
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
			if (fields.contains("geom,")) { // 取消geom字段
				fields = fields.replace("geom,","");
			} else if (fields.contains(",geom")) {
				fields = fields.replace(",geom", "");
			}
			if  (fields.contains("name,")) { // 取消name字段
				fields = fields.replace("name,","");
			} else if (fields.contains(",name")) {
				fields = fields.replace(",name","");
			}
			String[] abc = fields.split(",");
			String sql2 = "SELECT " + fields + " , id from  (select id,qdbh start_code, zdbh end_code, cz material, gj caliber,qdms start_depth, zdms end_depth, mslx cover_mode,azrq install_date, chr mapping_man from xx_line_copy1 ) a left join (select id dev_id from share_dev) b on a.id = b.dev_id";
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
			List<String> LogList = Lists.newArrayList();
			int size = 1000;
			int total = list.size();
			int loopcnt = total/size == 0 ? total/size : total/size + 1;
			if (total < size) loopcnt = 1;
			String upd_sql = "update gis_dev_ext set data_info = ? where dev_id = ?";
			prest = conn.prepareStatement(upd_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					String jsonStr = JSONObject.toJSONString(map,                             SerializerFeature.WriteMapNullValue);  // 长一点，记住null的字段不省略
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");
					Long id = (Long) map.get("dev_id");
					if(id == null){
						continue;
					}
					prest.setObject(1,jsonObject);
					prest.setLong(2, id);
					LogList.add(id + "========" + JSONObject.toJSONString(jsonObject));
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}
			int ii = 0;
			for (String s : LogList){
				System.out.println(++ii + "-->" + s);
			};
			System.out.println("complete ......................  ........... ");
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
	 * 更新data_info管点数据
	 * 河北冀州
	 */
	public void updatedealPOINTData(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 1 ";
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
			if (fields.contains("geom,")) {
				fields = fields.replace("geom,","");
			} else if (fields.contains(",geom")) {
				fields = fields.replace(",geom", "");
			}

			if  (fields.contains("name,")) {
				fields = fields.replace("name,","");
			} else if (fields.contains(",name")) {
				fields = fields.replace(",name","");
			}
			String[] abc = "code,x,y,ground_height,depth,spec,material,well_spec,addr,remark,mapping_man,dev_id".split(",");
			String sql2 = "select 管点编 code, x坐标 x, y坐标 y, 地面高 ground_height, 埋深 depth, 规格 spec, 材质 material, 井规格 well_spec, 道路名 addr, 备注 remark, 测绘人 mapping_man,gid dev_id from xx_point";
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
			List<String> LogList = Lists.newArrayList();
			int size = 1000;
			int total = list.size();
			int loopcnt = total/size == 0 ? total/size : total/size + 1;
			if (total < size) loopcnt = 1;
			String upd_sql = "update gis_dev_ext set data_info = ? where dev_id = ?";
			prest = conn.prepareStatement(upd_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					String jsonStr = JSONObject.toJSONString(map,                             SerializerFeature.WriteMapNullValue);  // 长一点，记住null的字段不省略
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");
					Long id = Long.parseLong(String.valueOf(map.get("dev_id")));
					if(id == null){
						continue;
					}
					prest.setObject(1,jsonObject);
					prest.setLong(2, id);
					LogList.add(id + "========" + JSONObject.toJSONString(jsonObject));
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}

			int ii = 0;
			for (String s : LogList){
				System.out.println(++ii + "-->" + s);
			};
			System.out.println("complete ......................  ........... ");
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
		String typeIdSql = "select distinct p_id from share_dev_type where id not in (8,9,10,11,12,14) and limb_leaf = 2";

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

//		String sl = "select b.name,b.dev_id,b.geom,a.code,a.x,a.y,a.dmgc,a.jdms,a.jglx,a.jggg,a.jgcz,a.ysdm,a.kcdw," +
//				"a.qsdw,a.jdjb,a.pxjdh,a.pj,a.dlm,a.bz,a.dhxzb,a.dhyzb,a.fzlx,a.tsdh from " +
//				"js_point_copy1 a left join gis_dev_point b on a.code = b.code";
		String sl = "select 管点编 code, x坐标 x, y坐标 y, 地面高 ground_height, 埋深 depth, 规格 spec, 材质 material, 井规格 well_spec, 道路名 addr, 备注 remark, 测绘人 mapping_man,geom,gid dev_id from xx_point";
		String[] abc = "code,x,y,ground_height,depth,spec,material,well_spec,addr,remark,mapping_man,geom,dev_id".split(",");
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
			String int_sql = "insert into gis_dev_ext  (dev_id,material, geom,data_info,tpl_type_id) values (?,?,?,?,0)";
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
					Long id = Long.parseLong(String.valueOf(map.get("dev_id")));
					if(id == null){
						continue;
					}
					prest.setLong(1, id);
//					prest.setObject(2, jsonObject);

					String name = (String) map.get("name");
//					int caliber = Integer.parseInt(String.valueOf(map.get("caliber")));
					String material = (String) map.get("material");
					Object geom = map.get("geom");
					prest.setString(2, material == null ? "" : material);
					prest.setObject(3,geom);
					prest.setObject(4,jsonObject);
					prest.addBatch();
				}
				 prest.executeBatch();
				 conn.commit();
				step ++;
			}
	}

	void updateData(){
		String sql = "select id, data_info from gis_dev_ext where caliber is not null";
//		String sql = getSqlFromxxxxml();
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

			String sql2 = "update gis_dev_ext set data_info = ? where dev_id = ?";
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
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jb.fluentPut("install_date", sf.format(d));
				jb.fluentPut("fix_time",  sd.format(dt));
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


	void updateData1(){
		String sql = "select id,name, data_info from gis_dev_ext";
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		List<Length> list = Lists.newArrayList();
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(sql);
			while (rst.next()) {
				Length l = new Length();
				Object datainfo =  rst.getObject("data_info");
				Long id = rst.getLong("id");
				String name = rst.getString("name");
				l.setObj(datainfo);
				l.setId(id);
				l.setName(name);
				list.add(l);
			}

			String sql2 = "update gis_dev_ext set data_info = ? where id = ?";
			prest = conn.prepareStatement(sql2);
			int i =0;
			conn.setAutoCommit(false);
			for (Length obj : list) {
				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
				jb.fluentPut("name", obj.getName());
				System.out.println("================>" + jb.toString());
				PGobject jsonObject = new PGobject();
				jsonObject.setValue(JSONObject.toJSONString(jb, SerializerFeature.WriteMapNullValue)); // 不省略为null的字段
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
			try{
				if(prest!= null){
					prest.close();
				}
				if(rst != null){
					rst.close();
				}
				if(stt != null){
					stt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}


	}

	/** excle 里面的某个字段更新到jsonb的对应字段，只支持postgis 9.5*/
	void updateData2(){
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		try {

			List<Map<String,String>> excelList = Jxl.get();

			String sql2 = "UPDATE gis_dev_ext set data_info = jsonb_set(data_info, '{depth}', ? :: jsonb,false) :: jsonb where code = ? ";
			prest = conn.prepareStatement(sql2);
			int i =0;
			conn.setAutoCommit(false);
			for (Map<String, String> stringMap : excelList) {
				String code = stringMap.get("code");
				String val = stringMap.get("val");
				System.out.println("code = " + code + "-------" + "val = " + val + " ||| " + i++);
				prest.setString(1, val);
				prest.setString(2, code);
				prest.addBatch();
			}

			// 并发死锁，如果没有设置autocommit false
			 prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(prest!= null){
					prest.close();
				}
				if(rst != null){
					rst.close();
				}
				if(stt != null){
					stt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}


	}


	/**
	 * 河北冀州，处理管网数据, 存放到gis_dev_line的数据
	 */
	private void dealLine2(){
		/**
		 * 我们表对应数据字段5个： 设备模板ID、管网名称、管网编号、口径、空间信息
		 */
		Connection conn = getConn();
		int size = 2000;  // 每次处理1000条
		int page = 0;
		String sql_t = "select count(*) from xx_line_copy1";
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
			String querySql = "select * from xx_line_copy1";
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
	 * 河北冀州
	 * 把水管数据存进 gis_dev_ext
	 */
	public void dealLineData2(){
		String colSql = "select string_agg(field_name,',') from gis_dev_tpl_attr where type_id = 13";
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
			String[] abc = fields.split(",");
			String sql2 = "SELECT " + fields + "  from  (select id,qdbh start_code, zdbh end_code, cz material, gj caliber,qdms start_depth, zdms end_depth, mslx cover_mode,azrq install_date, chr mapping_man,geom  from xx_line_copy1 ) a left join (select id dev_id, type_id,name from share_dev) b on a.id = b.dev_id";
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
			String int_sql = "insert into gis_dev_ext (dev_id,name,code,caliber,material, geom,data_info,tpl_type_id,create_by,update_by) values (?,?,?,?,?,?,?,13,'admin','admin')";
			prest = conn.prepareStatement(int_sql);
			conn.setAutoCommit(false);
			int step = 0;
			while (loopcnt-- > 0) {
				List<HashMap<String,Object>> subList = (List<HashMap<String, Object>>) list.subList(step*size,(step+1) * size > total ? total : (step+1) * size);
				for (HashMap<String,Object> map : subList){
					Long dev_id = Long.parseLong(String.valueOf(map.get("dev_id")));
					String name = String.valueOf(map.get("name"));
					String code = (String) map.get("start_code") + "-" + (String) map.get("end_code");
					int caliber = Integer.parseInt(String.valueOf(map.get("caliber") == null ? 0 : map.get("caliber")));
					String material = (String) map.get("material");
					Object geom = map.get("geom");
					JSONObject a = new JSONObject();
					a.putAll(map);
					a.remove("geom"); // 删除geom
					String jsonStr = JSONObject.toJSONString(a, SerializerFeature.WriteMapNullValue);
					PGobject jsonObject = new PGobject();
					jsonObject.setValue(jsonStr);
					jsonObject.setType("json");

					prest.setLong(1, dev_id);
					prest.setString(2, name);
					prest.setString(3, code);
					prest.setInt(4, caliber);
					prest.setString(5, material);
					prest.setObject(6,geom);
					prest.setObject(7,jsonObject);
					prest.addBatch();
				}
				prest.executeBatch();
				conn.commit();
				step ++;
			}
			System.out.println("导入成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rst != null) rst.close();
				if (stt != null) stt.close();
				if (prest != null) prest.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		PgGisDataInitUtils p = new PgGisDataInitUtils();
		try {
//			p.dealLine();
//			p.dealLineData();
//			p.dealPoint();
//			p.updateData();
//			p.updateData1();
//			p.updateData2();
//			p.updatedealPIPEData();
//			p.updatedealPOINTData();
//			p.dealLine2();
			p.dealLineData2();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private class PointNow{
		private Long id;
		private Long typeId;

		public Long getTypeId() {
			return typeId;
		}

		public void setTypeId(Long typeId) {
			this.typeId = typeId;
		}

		private String name;
		private String code;
		private Integer kj;
		private PGgeometry geom;


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private String name;

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