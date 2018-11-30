package studiii.zlsj_test.basics;

import java.io.File;

/**
 * @author liaosijun
 * @since 2018年10月17日 上午10:50:07
 */
public class FmtStrUtil {
	
	private static String path = "‪c:/tmmp/"; // 存放txt文件路径
	
	private static String fileName = "fmt";
	
	private static String suffix = ".txt";
	
	private static void xSpaceLine() {
		String p = path + fileName + suffix;
		System.out.println(p);
		File file = new File("‪c:/tmmp/fmt.txt");
		System.out.println(file.exists());
		System.out.println("中户人民共和国十大元帅");
	}
	
	public static void main(String[] args) {
		xSpaceLine();
	}
}
