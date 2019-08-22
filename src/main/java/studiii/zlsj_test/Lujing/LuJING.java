package studiii.zlsj_test.Lujing;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/8/2 14:54
 */
public class LuJING {

	public static void main(String[] args) {
		System.out.println(LuJING.class.getResource("/").getPath());
		System.out.println(System.getProperty("java.class.path"));


		String jarWholePath = LuJING.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.toString());
		}
		String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
		System.out.println(jarPath);
	}

}