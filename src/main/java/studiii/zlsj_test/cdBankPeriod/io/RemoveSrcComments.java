package studiii.zlsj_test.cdBankPeriod.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class RemoveSrcComments {

	/**
	 * @date 2017-5-5 下午05:54:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveSrcComments.deal();
	}
	
	public static String path = "d:/temp/";
	
	public static String path2 = "d:/temp/deal/";
	
	public static String name = "BasicDataSourceFactory";
	
	public static String suffix = ".java";
	
	public static void deal() {
		path = path + name + suffix;
		path2 = path2 + name + suffix;
		try {
		File file = new File(path);
		FileInputStream is;
				is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String line = "";
		String xmlStr = "";
		while ((line = reader.readLine()) != null) {
			int index = line.lastIndexOf("/");
			if(index>1){
				line = line.substring(index+1);
			}
			System.out.println(line);
			xmlStr += (line + "\n");
		}
		File file2 = new File(path2);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
