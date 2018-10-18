package studiii.zlsj_test.cdBankPeriod.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteCount {

	public static void main(String[] args) {
		String path ="d:/temp/";
		String filename = "lengthuu.xml";
		File file = new File(path+filename);
		try {
			FileInputStream fis = new FileInputStream(file);
			 InputStreamReader isr = new InputStreamReader(fis, "GBK");
			int b=0;
			while((isr.read())>0){
				b++;
			}
			System.out.println(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
