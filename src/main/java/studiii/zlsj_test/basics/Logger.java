package studiii.zlsj_test.basics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liaosijun
 * @since 2018年9月30日 下午3:53:00
 */
public class Logger {
	
	static final String fileName = "File.log";
	static final String path = "C:\\Users\\Lsj\\Desktop\\";
	static File f = createFile();
	
	static File createFile() {
		File file = null;
		file = new File(path + fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	static void log(String txt) {
		FileOutputStream out;
		try {
			out = new  FileOutputStream(f,true);
			out.write(txt.getBytes());
			out.write("\n".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
