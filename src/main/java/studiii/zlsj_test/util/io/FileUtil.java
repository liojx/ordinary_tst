package studiii.zlsj_test.util.io;

import java.io.*;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/10 13:01
 */
public class FileUtil {

	public final static String filePath = "c:\\Users\\liao_\\Desktop\\12.txt";

	public final static String charSetUTF8 = "UTF-8";

	public final static String charSetGBK = "GBK";


	public static String fileToString(String filePath){
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, charSetGBK);
			reader = new BufferedReader(isr);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line + "\\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("result -----> \n" + sb.toString());
		return sb.toString();
	}
}