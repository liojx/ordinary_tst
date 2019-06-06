package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.*;

/**
 * 
 * @author DEV029688
 *         InputStram是所有字节输入流的抽象类，其子类有ByteArrayInputStram,FileInputStream
 *         ,FilterInputStream等
 */
public class IOStreamOperTest {

	private static ByteArrayInputStream byteArray;

	private static FileInputStream fileInput;

	private static FilterInputStream filterInput;

	private String inFilePath = "D:/temp/iooper.txt";

	private String outFilePath = "D:/temp/text.txt";
	
	private String inFilePath2 = "D:/temp/inputstream.txt";
	
	private String inFilePath3 = "D:/temp/";
	
	private String fileName = "Socket2Http";
	
	private String suffix = ".java";
	
	private Integer length = 10;

	public void operByteArray() {
		try {
			FileInputStream fileIn = new FileInputStream(inFilePath);
			/*System.out.println("is available()-->"+fileIn.available());
			System.out.println("read once time-->"+fileIn.read());
			System.out.println("Now,surplus -->"+fileIn.available());
			System.out.println("read second time-->"+fileIn.read());
			System.out.println("Now,surplus -->"+fileIn.available());*/
			FileOutputStream fileOut = new FileOutputStream(outFilePath);
			FileInputStream inin = new FileInputStream(inFilePath2);
			
			byte [] b = new byte[fileIn.available()];
			System.out.println(b.length);
			//System.out.println(fileIn.read(b,0,200));
			//System.out.println(fileIn.read(b,0,100));
			//fileOut.write(fileIn.read(b,0,100));
			
			byte [] by = new byte[30];
			//System.out.println(inin.read(by));
			//System.out.println(inin.read(by,0,78));
			//inin.close();
			int c = 0;
			//int buf = inin.read(by, 10, 10);
			while((c=inin.read())>0){
				System.out.println(c);
				fileOut.write(c);
			}
			//System.out.println(buf);
			//fileOut.write(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 反编译出来每行前面有注释，除掉这些注释
	 */
	public void removeUnusedCode () {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inFilePath3+fileName+suffix)),"UTF-8"));
			String linestr = "";
			String returnstr = "";
			while((linestr = reader.readLine())!=null){
				returnstr +=linestr.substring(length)+"\n";
			}
			System.out.println(returnstr);
			writer = new BufferedWriter(new FileWriter(inFilePath3+fileName+"_2"+suffix));
			writer.write(returnstr);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(writer!=null)
				writer.close();
				if(reader!=null)
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
//		IOStreamOperTest t = new IOStreamOperTest();
//		t.operByteArray();
		new IOStreamOperTest().removeUnusedCode();
	}
}
