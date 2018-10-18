package studiii.zlsj_test.cdBankPeriod.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;


public class InStreamIO {
	
	private static File file = new File("src/leoxj.properties");
	
	private static Properties prop = new Properties();
	
	static String rootPath = "";
	
	static String infile = "";
	
	static String outfile = "";
	
	static String infile2 = "";
	
	static String outfile2 = "";
	
	static {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			rootPath = (String)prop.get("reader.io");
			infile = prop.getProperty("infile");
			outfile = prop.getProperty("outfile");
			infile2 = prop.getProperty("infile2");
			outfile2 = prop.getProperty("outfile2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void ioRead () {
		FileInputStream operFis = null ;
		FileOutputStream fos = null ;
		try {
			operFis = new FileInputStream(rootPath +"/"+infile);
			File writtedFile = new File(rootPath + "/" +outfile);
			if(!writtedFile.exists()){
				writtedFile.createNewFile();
			}
			fos = new FileOutputStream(writtedFile);
			long start = new Date().getTime();
			int len = 0;
			byte[] b = new byte[10000];
			while((len = operFis.read(b))!=-1){
//				fos.write(len); //441000ms  just read 
				fos.write(b,0,len); // 544ms--b=1024   299ms--b=2048   183ms--b=4096  109ms--b=10000
			}
			long end = new Date().getTime();
			
			int cost = (int)((end-start));
			System.out.println("71,428,274 write cost "+ cost +" ms");
			/*int one= operFis.read(); //48byte ???
			int two = operFis.read(new byte[102400]);
			int three = operFis.read(new byte[102400], 0, 86566);
			System.out.println(one);
			System.out.println(two);
			System.out.println(three);
			*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(operFis!=null)
				operFis.close();
				if(fos!=null)
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 性能测试，取/back/app/log/UnifyApp_2015-12-08.log (更名为infile2.log) 大小为216M，进行读写操作，求出耗时测试
	 * 以下4个方法都是类似的，主要对FileInputStream/FileOutputSteam、BufferedInputStream/BufferedOutputStream、
	 * InputStreamReader/OutputStreamWriter和BufferedReader/BufferedWriter这4对输入输出的效率测试。
	 * 主要对类的选择。
	 * FileInputStream--FileOutputStream
	 */
	
	public static long rw_speed_InputStream_OutputStream(){
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		long start = new Date().getTime();
		try {
			inStream = new FileInputStream(rootPath + "/" + infile2);
			String outPath = rootPath + "/" + outfile2;
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			outStream = new FileOutputStream(outFile);
			int len = 0;
			byte b []= new byte[1024];
			while((len = inStream.read())!=-1){
				outStream.write(len);
			}
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
		long end = new Date().getTime();
		return end - start ;
	}
	/**
	 *BufferedReader--BufferedWritter 
	 */
	public static long  rw_speed_bufferedReader_bufferedWriter(){
		BufferedReader reader = null;
		BufferedWriter writer = null;
		long start = new Date().getTime();
		System.out.println(start);
		try {
			
			reader = new BufferedReader(new FileReader(rootPath + "/" + infile2));
			String outPath = rootPath + "/" + outfile2;
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			writer = new BufferedWriter(new FileWriter(outFile));
			String str = null;
//			while((str = reader.readLine()) != null){
//				str += "\n";
//				writer.write(str);
//			}
			int len = 0 ; 
			char c [] = new char[1024];
			while((len = reader.read(c)) != -1){
				writer.write(c,0,len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null)
					reader.close();
				if(writer!=null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = new Date().getTime();
		System.out.println(end);
		return end - start ;
	}
	/**
	 *BufferedInputStream--BufferedOutputStream
	 */
	public static long  rw_speed_bufferedinStream_bufferedoutStream(){
		BufferedInputStream buff_inStream = null;
		BufferedOutputStream buff_outStream = null;
		long start = new Date().getTime();
		System.out.println(start);
		try {
			
			buff_inStream = new BufferedInputStream(new FileInputStream(rootPath + "/" + infile2));
			String outPath = rootPath + "/" + outfile2;
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			buff_outStream = new BufferedOutputStream(new FileOutputStream(outFile));
			int len = 0;
			byte b [] = new byte[1024];
			while((len = buff_inStream.read(b))!=-1){
				buff_outStream.write(b,0,len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(buff_inStream!=null)
					buff_inStream.close();
				if(buff_outStream!=null)
					buff_outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = new Date().getTime();
		System.out.println(end);
		return end - start ;
	}
	
	/**
	 * Reader-- Writter 
	 */
	public static long  rw_speed_reader_writer(){
		InputStreamReader reader = null;
		OutputStreamWriter writer = null;
		long start = new Date().getTime();
		System.out.println(start);
		try {
			reader = new InputStreamReader(new FileInputStream(rootPath + "/" + infile2));
			String outPath = rootPath + "/" + outfile2;
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			writer = new OutputStreamWriter(new FileOutputStream(outFile));
			int len = 0;
			char c []= new char[1024];
			while((len = reader.read(c))!=-1){
				writer.write(c,0,len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null)
					reader.close();
				if(writer!=null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = new Date().getTime();
		System.out.println(end);
		return end - start ;
	}
	
	/**
	 * 下面方法主要是对读写的方法进行选择，看看不同的读写有没有效率的不同
	 */
	public static long read_write_style(){
		long start = new Date().getTime();
		System.out.println("Start Time :" + start);
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			inStream = new FileInputStream(rootPath + "/" + infile2);
			String outPath = rootPath + "/" + "output.log";
			File outFile = new File(outPath);
			if(!outFile.exists())outFile.createNewFile();
			outStream = new FileOutputStream(outFile);
			int count = 0;
			int last_len = 0;
			/*
			 * 方法1 int read() 这个方法是实现了InputStream 的abstract int read()方法。
			 * 22290 ms 这个速度慢 （3M的文件）
			 */
			int len = 0;
			/*while((len = inStream.read())!=-1){
				outStream.write(len);
			}*/
			/**
			 * 方法2 int read(byte[] b,int start,int length) 
			 * b缓冲的数值是1024的话，耗费53ms,很快，这个文件只有3M
			 * b=2048,cost 30ms,very fast,我再找个更大的文件来看差距，这个文件只有3M，下面整个216M的
			 * b=1024,cost 2434ms,也比一个字节一个字节读耗费半小时，不知道快了N倍啊。
			 * b=2048,cost 1028ms,1秒钟，这速度，真的叫秒杀啊。
			 * b=8,	  cost 377705ms,约等于6分钟
			 */
			int par = 4096;
			byte[] b = new byte[par];
			while((len = inStream.read(b))!=-1){
				outStream.write(b,0,len);
				count++;
				last_len = len;
			}
			System.out.println("last_len:"+last_len);
			System.out.println("count-1 ="+(count-1));
			System.out.println("par*(count-1)+len="+(par*(count-1)+last_len));
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
		long end = new Date().getTime();
		System.out.println("End Time : " + end);
		return end-start;
		
	}
	
	public static void main(String[] args) {
//		ioRead();
		long in_out_stream_time = rw_speed_InputStream_OutputStream();
		System.out.println(in_out_stream_time); //1883295ms 大约31分钟，用的是read() 和 write(int len); 如果按1024字节的读写，仅需2233ms
		long reader_writer_time = rw_speed_reader_writer();
		System.out.println(reader_writer_time); //29379  29秒钟;如果按1024 char 读写，2025ms 2秒钟
		long in_out_buffered_time = rw_speed_bufferedinStream_bufferedoutStream();
		System.out.println(in_out_buffered_time); //11192  11秒钟; 如果按1024字节的读写，仅需407ms
		long reader_writer_buffered_time = rw_speed_bufferedReader_bufferedWriter();
		System.out.println(reader_writer_buffered_time); //4043   4秒钟，这个差距太大了。 如果按1024char 读写，需要2307ms
//		System.out.println("Spent Time : "+read_write_style()); //
		/**
		 * 文件总大小：227,382,011 字节
		 */
//		long totalSize = 227382011L;
//		int cache = 1024;
		/**
		 * 当 b = 8,整数倍除后，余3个，用read_write_style() 中 read (byte[]) 时，多读了5个
		 * b = 1024,余数为763，用read_write_style() 中 read (byte[]) 时，多读了227,382,272 - 227,382,011 刚好等于 1024-763=261个字节
		 * b = 2048,余数为763，用read_write_style() 中 read (byte[]) 时，多读了227,383,296 - 227,382,011 刚好等于 2048-763=1285个字节
		 */
//		System.out.println(totalSize % cache);
	}
}
