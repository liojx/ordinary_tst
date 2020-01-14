package studiii.zlsj_test.util.pgdb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: liaosijun
 * @Time: 2019/11/22 16:53
 */
public class UpdateUtil {

	// 更新data_info
	public static void up() {
		Connection conn = PgGisDataInitUtils.getConn();
		String sql ="select data_info from gis_dev_ext where caliber is not null";
		Statement stt = null;
		ResultSet rst = null;
		PreparedStatement prest = null;
		List<PGobject> lineList = Lists.newArrayList();
		try {
			stt = conn.createStatement();
			rst = stt.executeQuery(sql);
			while (rst.next()) {
				lineList.add((PGobject) rst.getObject(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> list = Lists.newArrayList();
		lineList.forEach(pGobject -> {
			JSONObject j = JSONObject.parseObject(JSONObject.toJSONString(pGobject));
			String vals = (String)j.get("value");
			JSONObject valObj = JSONObject.parseObject(vals);

			Map<String,Object> map = Maps.newHashMap();
			for (Map.Entry entry : valObj.entrySet()) {
				String key = (String) entry.getKey();
				Object val = entry.getValue();
				if ("dlm".equals(entry.getKey())) {
					map.put("address", val);
				} else if ("dmgc".equals(key)) {
					map.put("height", val);
				} else if ("qsdw".equals(key)) {
					map.put("belongTo", val);
				} else if ("kcdw".equals(key)) {
					map.put("surveyCompany", val);
				} else if ("jdms".equals(key)) {
					map.put("depth", val);
				} else if ("dev_id".equals(key)) {
					map.put("devId", val);
				} else if ("qdbm".equals(key)) {
					map.put("startCode", val);
				} else  if ("zdbm".equals(key)) {
					map.put("endCode", val);
				} else if ("inputstaff". equals(key)) {
					map.put("inputStaff",val);
				} else if ("pipe_length".equals(key)) {
					map.put("pipeLength", val);
				}
				map.put("surveyDate",new Date());
				map.put("remark","摆正位置");
				map.put("belongTo","广安");
				list.add(map);
			}
		});

		System.out.println(list);
	}

	public static void main(String[] args) {
		UpdateUtil.up();
	}
}
