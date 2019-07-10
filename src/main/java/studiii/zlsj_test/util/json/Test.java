package studiii.zlsj_test.util.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/7/5 13:41
 */
public class Test {

	private final static String filePath = "d:/temp/json_read_test.txt";

	private static File file;

	static {
		file = new File(filePath);
		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		List<Long> list = getArrary();
		int i = 0;
		for(Long l : list) {
			System.out.println(l);
			i ++ ;
		}
		System.out.println("i====" + i);
	}

	static String getContent() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		return String.valueOf(sb);
	}

	static List<Long> getArrary() {
		try {
			String srx = getContent();
			System.out.println("str ==== " + srx);
			JSONObject jsonObject = JSONObject.parseObject(srx);
			JSONArray jsonArray = jsonObject.getJSONArray("features");
			List<Long> list = Lists.newArrayList();
			jsonArray.forEach(feature -> {
				System.out.println(JSONObject.toJSONString(feature));
				Object attributes = ((JSONObject) feature).get("attributes");
				Object dev_id = ((JSONObject)attributes).get("dev_id");
				System.out.println("dev_id = " + dev_id);
				list.add(Long.parseLong(String.valueOf(dev_id)));
			});
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}