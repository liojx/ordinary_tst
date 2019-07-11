package studiii.zlsj_test.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description: 文件大小输出
 * @Author: liaosijun
 * @Time: 2019/7/10 9:26
 */
public class FileSizeCount {

	static String fileName = "d:/temp/nohup.out";

	static File file = null;

	static {
		try {
			file = new File(fileName);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	static int count(File file) {
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] by = new byte[1024];
			int cnt = 0;
			while((cnt = fis.read(by,0, by.length)) > -1){
				System.out.println(" cnt == " + cnt);
				count += cnt;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("文件大小为： " + count + " 字节");
		System.out.println("文件大小为： " + count + " B");
		System.out.println("文件大小为： " + Double.parseDouble(String.valueOf(count))/1024 + " KB");
		System.out.println("文件大小为： " + Double.parseDouble(String.valueOf(count))/1024/1024 + " MB");
		return count;
	}

	public static void main(String[] args) {
		int count = count(file);
		System.out.println("count == " + count + " bytes");

	}
}