package studiii.zlsj_test.cdBankPeriod.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReaderIO {
	
	private static String readerPathKey = "reader.io";
	
	private static String x_path =  "d:/temp/fff.txt";
	
	private static String f_path = "d:/temp/out.txt";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		prop();
		readerRead();
	}
	
	public static void readerRead() {
		File file = new File(x_path);
		File file2 = new File(f_path);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter wr = new BufferedWriter( new FileWriter(file2));
			String line = "";
			String[] sr = null;
			while((line = br.readLine()) != null){
				sr = line.split("\\s+");
			}
			for(int i = 0;i<sr.length;i++){
				System.out.println(sr[i]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void prop() {
		Properties prop = new Properties();
		InputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			/**
			 * absolute path: D:\huro\workspace_new\lsj\src\
			 * relative path: src\
			 */
			File file = new File("D:/huro/workspace_new/lsj/src/leoxj.properties");
			File file_ = new File("src/leoxj.properties");
			System.out.println(file.getName());
			System.out.println(file.getPath());
			System.out.println(file.getAbsoluteFile());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getCanonicalFile());
			System.out.println(file.getCanonicalPath());
			System.out.println(file_.getName());
			System.out.println(file_.getPath());
			System.out.println(file_.getAbsoluteFile());
			System.out.println(file_.getAbsolutePath());
			System.out.println(file_.getCanonicalFile());
			System.out.println(file_.getCanonicalPath());
			inStream = new FileInputStream(file_);
			prop.load(inStream);
			String readerPath = (String)prop.get(readerPathKey);
			File operFile = new File(readerPath + "/" + "temp1.txt");
			FileReader fileReader = new FileReader(operFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			outStream = new FileOutputStream(readerPath+"hur.txt");
			int len = 0;
			while(bufferReader.read() != -1){
				String str = bufferReader.readLine()+"\n";
				len += str.getBytes().length;
				System.out.println(len);
				outStream.write(str.getBytes());
			}
			System.out.println(len);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(inStream!=null)
				inStream.close();
				if(outStream!=null)
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
