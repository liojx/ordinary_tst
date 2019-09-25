package studiii.zlsj_test.util.io;

import java.io.*;
import java.util.Date;


/**
 * 把CV来的一段好，放到txt文本中，然后去掉所有空格。
 */
public class MoveSpace {

	public static final String fileName = "d:/Temp/spaceTxt.txt";




	public static void  rw_speed_bufferedinStream_bufferedoutStream(String s){
		BufferedOutputStream buff_outStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(s.getBytes("GBK"));
			String outPath = fileName;
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			buff_outStream = new BufferedOutputStream(new FileOutputStream(outFile, true));
			int len;
			byte b [] = new byte[1024];
			while((len = byteArrayInputStream.read(b))!=-1){
				buff_outStream.write(b,0,len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(byteArrayInputStream!=null)
					byteArrayInputStream.close();
				if(buff_outStream!=null)
					buff_outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static String fileToString(String filePath){
		File file = new File(filePath);
		BufferedReader reader;
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"GBK");
			reader = new BufferedReader(isr);
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("result -----> \n" + sb.toString());
		return sb.toString();
	}

	public static void main(String[] args) {
		String s  = MoveSpace.fileToString(fileName);
		s += "\r\n\n";
		MoveSpace.rw_speed_bufferedinStream_bufferedoutStream(s);
	}
}
