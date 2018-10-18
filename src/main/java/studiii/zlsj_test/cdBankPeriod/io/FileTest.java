package studiii.zlsj_test.cdBankPeriod.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileTest {
	
	public static void main(String[] args) {
		FileTest.copyFile();
	}
	public static boolean copyFile() {
		File oldPathFile  = new File("/home/callapp/test.txt");
		String newPathFile = "/home/callcsr/temp/xd.txt";
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {

			int bytesum = 0;
			int byteread = 0;
			if (oldPathFile.exists()) { // 文件存在??
				inStream = new FileInputStream(oldPathFile); // 读入原文??
				fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节?? 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
				return true;
			} else {
				System.out.println("文件=" + oldPathFile + "不存在!计划改名对应的文件为="
						+ newPathFile);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("复制文件:" + oldPathFile + " 到目标文件:" + newPathFile
					+ " 操作失败!");
			System.err.println("复制单个文件操作出错");
		} finally {
			fs = null;
			inStream = null;
		}
		return false;
	}
}
