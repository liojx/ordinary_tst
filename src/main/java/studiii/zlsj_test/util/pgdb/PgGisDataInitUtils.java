package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;
import studiii.zlsj_test.cdBankPeriod.tools.Jxl;
import studiii.zlsj_test.transaction.bean.Obj;

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
//	private final static String host_url = "jdbc:postgresql://192.168.80.70:5432/gis?characterEncoding=GBK";
//	private final static String host_url = "jdbc:postgresql://223.247.199.63:5432/gis?characterEncoding=GBK";
//	private final static String host_url = "jdbc:postgresql://192.168.80.106:5432/gis?characterEncoding=GBK";
	private final static String user = "postgres";
//	private final static String pwd = "jdrxaaa1233*";     // 广安
//	private final static String pwd = "postgres";
//	private final static String pwd = "123456";
	private final static String pwd = "JDgis_pg0911"; // 冀州

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
//				if (jb.containsKey("kcrq")) {
//					jb.put("kcrq", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}
//				jb.remove("geom");
				jb.remove("dev_id");
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

	public int selectAllCnt(){
		String colSql = "select count(1) from gis_dev_ext";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = getConn();
		int total = 0;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				total = rst.getInt(1);
			}

			System.out.println("total ......................  ........... " + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rst.close();
				stt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}

	public int selectAllPointCnt(){
		String colSql = "select count(1) from gis_dev_ext where caliber is null";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = getConn();
		int total = 0;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				total = rst.getInt(1);
			}

			System.out.println("total ......................  ........... " + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rst.close();
				stt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}


	public int selectAllLineCnt(){
		String colSql = "select count(1) from gis_dev_ext where caliber is not null";
		Statement stt = null;
		ResultSet rst = null;
		Connection conn = getConn();
		int total = 0;
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(colSql);
			while (rst.next()) {
				total = rst.getInt(1);
			}

			System.out.println("total ......................  ........... " + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rst.close();
				stt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}

	private List<Length> selectAll() {
		String sql = "select dev_id,  name,geom, data_info ,belong_to,material from gis_dev_ext";
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
				Object datainfo = rst.getObject("data_info");
				String devId = rst.getString("dev_id");
				String code = rst.getString("geom");
				Long belongto = rst.getLong("belong_to");
				String name = rst.getString("name");
				String me = rst.getString("material");
				l.setObj(datainfo);
				l.setDevId(devId);
				l.setCode(code);
				l.setId(belongto);
				l.setName(name);
				l.setMer(me);

				list.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Length> selectAllPoint() {
		String sql = "select dev_id,  name,geom, data_info ,belong_to,material from gis_dev_ext where caliber is null";
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
				Object datainfo = rst.getObject("data_info");
				String devId = rst.getString("dev_id");
				String code = rst.getString("geom");
				Long belongto = rst.getLong("belong_to");
				String name = rst.getString("name");
				String me = rst.getString("material");
				l.setObj(datainfo);
				l.setDevId(devId);
				l.setCode(code);
				l.setId(belongto);
				l.setName(name);
				l.setMer(me);

				list.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Length> selectAllLine() {
		String sql = "select dev_id,  name,geom, data_info ,belong_to,material from gis_dev_ext where caliber is not null";
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
				Object datainfo = rst.getObject("data_info");
				String devId = rst.getString("dev_id");
				String code = rst.getString("geom");
				Long belongto = rst.getLong("belong_to");
				String name = rst.getString("name");
				String me = rst.getString("material");
				l.setObj(datainfo);
				l.setDevId(devId);
				l.setCode(code);
				l.setId(belongto);
				l.setName(name);
				l.setMer(me);

				list.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Length> selectAll2() {
		String sql = "select dev_id,  name,geom, data_info ,belong_to from gis_dev_ext";
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
				Object datainfo = rst.getObject("data_info");
				String devId = rst.getString("dev_id");
				String code = rst.getString("geom");
				Long belongto = rst.getLong("belong_to");
				String name = rst.getString("name");
				l.setObj(datainfo);
				l.setDevId(devId);
				l.setCode(code);
				l.setId(belongto);
				l.setName(name);
				list.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	void updateDataInFo(List<Length> subList){
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		try {
			String sql2 = "update gis_dev_ext set data_info = ? where dev_id = ?";
			prest = conn.prepareStatement(sql2);
			stt = conn.createStatement();
			int i = 0;
			conn.setAutoCommit(false);
			for (Length obj : subList) {
				String sqll = "SELECT  ROUND((st_length( \'" + obj.getCode() + "\' ))::NUMERIC,3) ";
				ResultSet resultSet = stt.executeQuery(sqll);
				double xxx = 0L;
				while (resultSet.next()) {
					xxx = resultSet.getDouble(1);
				}

				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
//				jb.put("pipe_length", xxx);
//				long belongTo = obj.getId();
//				if (144 == belongTo | 1 == belongTo) {
//					jb.put("belong_to", "广安");
//				} else if (141 == belongTo | 5 == belongTo) {
//					jb.put("belong_to", "邻水");
//				} else if (136 == belongTo | 2 == belongTo) {
//					jb.put("belong_to", "岳池");
//				}else if (143 == belongTo | 4 == belongTo) {
//					jb.put("belong_to", "武胜");
//				}else if (140 == belongTo | 3 == belongTo ) {
//					jb.put("belong_to", "华蓥");
//				}else if (142 == belongTo | 6 == belongTo) {
					jb.put("belong_to", "冀州");
//				}

				if (jb.containsKey("addr")) {
					Object o = jb.get("addr");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("address", v);
					jb.remove("addr");
				}
				if (jb.containsKey("ground_height")) {
					Object o = jb.get("ground_height");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("groundHeight", v);
					jb.remove("ground_height");
				}


				if (jb.containsKey("mapping_man")) {
					Object o = jb.get("mapping_man");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("inputstaff", v);
					jb.remove("mapping_man");
				}

				if (jb.containsKey("jdjb")) {
					Object o = jb.get("jdjb");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("precisionRank", v);
					jb.remove("jdjb");
				}

				if (jb.containsKey("jdms")) {
					Object o = jb.get("jdms");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("depth", v);
					jb.remove("jdms");
				}

				if (jb.containsKey("cover_mode")) {
					Object o = jb.get("cover_mode");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("buryType", v);
					jb.remove("cover_mode");
				}

				if (jb.containsKey("jgcz")) {
					Object o = jb.get("jgcz");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("material", v);
					jb.remove("jgcz");
				}
				if (jb.containsKey("start_depth")) {
					Object o = jb.get("start_depth");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("startDepth", v);
					jb.remove("start_depth");
				}
				if (jb.containsKey("end_depth")) {
					Object o = jb.get("end_depth");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("endDepth", v);
					jb.remove("end_depth");
				}


				if (jb.containsKey("jggg")) {
					Object o = jb.get("jggg");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("spec", v);
					jb.remove("jggg");
				}

				if (jb.containsKey("jglx")) {
					Object o = jb.get("jglx");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("manholeType", v);
					jb.remove("jglx");
				}


				if (jb.containsKey("kcdw")) {
					Object o = jb.get("kcdw");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("surveyCompany", v);
					jb.remove("kcdw");
				}

				if (jb.containsKey("qsdw")) {
					Object o = jb.get("qsdw");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("belong_to", v);
					jb.remove("qsdw");
				}

				if (jb.containsKey("ysdm")) {
					jb.remove("ysdm");
				}
				if (jb.containsKey("dev_id")) {
					jb.remove("dev_id");
				}

				if (jb.containsKey("kcrq")) {
					Object o = jb.get("kcrq");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("surveyDate", v);
					jb.remove("kcrq");
				}

				if (jb.containsKey("mer")) {
					Object o = jb.get("mer");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("material", v);
					jb.remove("mer");
				}

				if (jb.containsKey("qdbm")) {
					Object o = jb.get("qdbm");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("startCode", v);
					jb.remove("qdbm");
				}

				if (jb.containsKey("zdbm")) {
					Object o = jb.get("zdbm");
					String v = "";
					if (Objects.nonNull(o)) {
						v = String.valueOf(o);
					}
					jb.put("endCode", v);
					jb.remove("zdbm");
				}

				if(jb.containsKey("mer_type_code")) {
					jb.remove("mer_type_code");
				}


				PGobject jsonObject = new PGobject();
				jsonObject.setValue(JSONObject.toJSONString(jb, SerializerFeature.WriteMapNullValue)); // 不省略为null的字段
				jsonObject.setType("jsonb");
				prest.setObject(1, jsonObject);
				prest.setString(2,String.valueOf(obj.getDevId()));
				prest.addBatch();
				System.out.println("-------->" + jb.toString() + (i++));
			}
			// 并发死锁，如果没有设置autocommit false
			prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	void updateDataInFo2(List<Length> subList){
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		try {
			String sql2 = "update gis_dev_ext set data_info = ? where dev_id = ?";
			prest = conn.prepareStatement(sql2);
			stt = conn.createStatement();
			int i = 0;
			conn.setAutoCommit(false);
			List<Map<String, String>> maps = Jxl.get2();
			for (Length obj : subList) {
				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
				String devId = obj.getDevId();
				for (Map<String, String> xm : maps) {
					String dev_id = xm.get("dev_id");
					String startCode = xm.get("startCode");
					String endCode = xm.get("endCode");
					if (devId.equals(dev_id)) {
						jb.put("startCode", startCode);
						jb.put("endCode", endCode);
						jb.remove("code");
						break;
					}
				}
				PGobject jsonObject = new PGobject();
				jsonObject.setValue(JSONObject.toJSONString(jb, SerializerFeature.WriteMapNullValue)); // 不省略为null的字段
				jsonObject.setType("jsonb");
				prest.setObject(1, jsonObject);
				prest.setString(2,String.valueOf(obj.getDevId()));
				prest.addBatch();
				System.out.println("-------->" + jb.toString() + (i++));
			}
			// 并发死锁，如果没有设置autocommit false
			prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	static int js = 0;
	void updateDataInFo3(List<Length> subList){
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		try {
			String sql2 = "update gis_dev_ext set data_info = ? where dev_id = ?";
			prest = conn.prepareStatement(sql2);
			stt = conn.createStatement();
			int i = 0;
			conn.setAutoCommit(false);
			for (Length obj : subList) {
				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
						jb.put("precisionRank","物探");
						jb.put("surveyCompany","正元地理信息有限责任公司四川分公司");
						if (Objects.isNull(jb.get("surveyDate"))) {
							jb.put("surveyDate", null);
						}
				if (jb.containsKey("startCode") && jb.containsKey("endCode")) {
					jb.put("startDepth",null);
					jb.put("endDepth", null);
					jb.put("buryType",null);
				}
//				if (jb.containsKey("startCode") && jb.containsKey("endCode")) {
//
//					String sqll = "SELECT  ROUND((st_length( \'" + obj.getCode() + "\' ))::NUMERIC,3) ";
//					ResultSet resultSet = stt.executeQuery(sqll);
//					double xxx = 0L;
//					while (resultSet.next()) {
//						xxx = resultSet.getDouble(1);
//					}
//					jb.put("pipe_length", xxx);
//				} else {
//					jb.remove("pipe_length");
//				}
//				long belongTo = obj.getId();
//				if (144 == belongTo | 1 == belongTo) {
//					jb.put("belong_to", "广安");
//				} else if (141 == belongTo | 5 == belongTo) {
//					jb.put("belong_to", "邻水");
//				} else if (136 == belongTo | 2 == belongTo) {
//					jb.put("belong_to", "岳池");
//				}else if (143 == belongTo | 4 == belongTo) {
//					jb.put("belong_to", "武胜");
//				}else if (140 == belongTo | 3 == belongTo ) {
//					jb.put("belong_to", "华蓥");
//				}else if (142 == belongTo | 6 == belongTo) {
//					jb.put("belong_to", "前锋");
//				}
//				String name = obj.getName();
//				jb.put("name", name);
//				String mer = obj.getMer();
//				jb.put("material", mer);
//				Double d = null;
//				if (jb.containsKey("depth")) {
//					Object depth = jb.get("depth");
//					String depthS = "";
//					if ("0E-11".equals(depth) || "".equals(depth)) {
//						depthS = "0";
//					} else {
//						depthS = String.valueOf(depth);
//					}
//					d = Double.parseDouble(depthS);
//					jb.put("depth", d);
//				}
				PGobject jsonObject = new PGobject();
				jsonObject.setValue(JSONObject.toJSONString(jb, SerializerFeature.WriteMapNullValue)); // 不省略为null的字段
				jsonObject.setType("jsonb");
				prest.setObject(1, jsonObject);
				prest.setString(2,String.valueOf(obj.getDevId()));
				prest.addBatch();
				System.out.println("-------->" + jb.toString() + (js++));
			}
			// 并发死锁，如果没有设置autocommit false
			prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	private void updateExt() {
		int total = selectAllCnt();
		List<Length> list = selectAll();
		int PAGE_SIZE = 1000;
		int loopcnt = total / PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1;
		if (total < PAGE_SIZE) {
			loopcnt = 1;
		}
		int step = 0;
		while (loopcnt-- > 0) {
			List<Length> subList = list.subList(step * PAGE_SIZE,
					(step + 1) * PAGE_SIZE > total ? total : (step + 1) * PAGE_SIZE);
			try {
				updateDataInFo(subList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			step++;
		}
	}

	/** excle 里面的某个字段更新到jsonb的对应字段，只支持postgis 9.5*/
	void updateData2(){
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		try {

//			List<Map<String,String>> excelList = Jxl.get();
			String sql = "select id, st_x(geom) from gis_dev_ext where  belong_to = 153 and caliber is null";

			stt = conn.createStatement();
			rst = stt.executeQuery(sql);
			List<Map<Long, String>> ll = Lists.newArrayList();
			while (rst.next()) {
				Long l = rst.getLong(1);
				String xy = rst.getString(2);
				Map<Long, String> m = Maps.newHashMap();
				m.put(l, xy);
				ll.add(m);
			}

			String sql2 = "UPDATE gis_dev_ext set data_info = jsonb_set(data_info, '{x}', ? :: jsonb,false) :: jsonb where  belong_to = 153 " +
					"and  caliber is null  and id = ?";
			prest = conn.prepareStatement(sql2);
			int i =0;
			conn.setAutoCommit(false);
			for (Map<Long, String> stringMap : ll) {

				Long id = stringMap.keySet().iterator().next();
				String s = stringMap.get(id);
				prest.setString(1, s);
				prest.setLong(2, id);
				prest.addBatch();
			}

			// 并发死锁，如果没有设置autocommit false
//			 prest.executeBatch();
			prest.execute();
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



	public void updateLngLat() {
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		Connection conn = getConn();
		List<Length> lengths = selectAllPoint();
		try {
			String sql2 = "update share_dev set lng = ? , lat = ? where id = ?";
			prest = conn.prepareStatement(sql2);
			stt = conn.createStatement();
			conn.setAutoCommit(false);
			int i = 0;
			for (Length obj : lengths) {
				String devId = obj.getDevId();
				JSONObject jb = JSONObject.parseObject(obj.getObj().toString());
				Object x = jb.get("x");
				String xx = String.valueOf(x);
				Object y = jb.get("y");
				String yy = String.valueOf(y);
				try {
					if (Double.parseDouble(xx) < 100 && Double.parseDouble(yy) < 200) {
						continue;
					}
				} catch (Exception e) {
					System.out.println(xx + "," + yy+"," + devId);
				}
				prest.setString(1, xx);
				prest.setString(2, yy);
				prest.setString(3,devId);
				prest.addBatch();

				System.out.println("-------->i=" + (i++) +"," + devId + "x="+xx +",y="+yy);
			}
			// 并发死锁，如果没有设置autocommit false
			prest.executeBatch();
			conn.commit();
			System.out.println("complete...");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
//			p.updateDataInFo();
			p.updateExt();     // 这个方法是更新 data_info 的数据
//			p.updateLngLat();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}