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
			if (oldPathFile.exists()) { // �ļ�����??
				inStream = new FileInputStream(oldPathFile); // ����ԭ��??
				fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ�?? �ļ���С
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
				return true;
			} else {
				System.out.println("�ļ�=" + oldPathFile + "������!�ƻ�������Ӧ���ļ�Ϊ="
						+ newPathFile);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����ļ�:" + oldPathFile + " ��Ŀ���ļ�:" + newPathFile
					+ " ����ʧ��!");
			System.err.println("���Ƶ����ļ���������");
		} finally {
			fs = null;
			inStream = null;
		}
		return false;
	}
}
