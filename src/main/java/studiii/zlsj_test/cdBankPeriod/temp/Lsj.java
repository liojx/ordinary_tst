package studiii.zlsj_test.cdBankPeriod.temp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Lsj {
	
	private static File file = new File("d:\\temp\\lsj.properties");
	
	private static Properties prop = new Properties();
	
	private static String dest_path = "";
	
	private static String xls_path = "";
	
	private static String dir_data_file_name = "";
	
	private static String file_data_file_name = "";
	
	private static String dir_src_path = "";
	
	private static String filesize = "";
	
	private static String log_path ="";
	
	private static StringBuilder sb = new StringBuilder();
	
	static ArrayList<ONe> dirlist = new ArrayList<ONe>();
	
	static ArrayList<ONe> leaflist = new ArrayList<ONe>();
	static String fname ="";
	static int level = 1;
	
	static ArrayList<ONe> mululist = new ArrayList<ONe>();
	
	static ArrayList<String> srcList = new ArrayList<String>(); 
		
	static {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			dest_path =  prop.getProperty("dest_path");
			xls_path = prop.getProperty("xls_path");
			dir_data_file_name = prop.getProperty("dir_data_file_name");
			file_data_file_name = prop.getProperty("file_data_file_name");
			filesize = prop.getProperty("filesize");
			dir_src_path =  prop.getProperty("dir_src_path");
			log_path = prop.getProperty("log_path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	static ArrayList<ONe> read_dir_data(String str) throws Exception {
		ArrayList<ONe> arr = new ArrayList<ONe>();
		FileInputStream fis = new FileInputStream(new File(xls_path + str));
		Workbook wk = null;
		wk = Workbook.getWorkbook(fis);
		Sheet sh1 = wk.getSheet(0);
		int colnums = sh1.getColumns();
		int rows = sh1.getRows();
		
		for(int i=1;i<rows;i++){
			ONe one = new ONe();
			for(int j=0;j<colnums;j++){
				Cell c = sh1.getCell(j, i);
				String cont = c.getContents();
				switch(j){
					case 0:one.setId(cont);break;
					case 1:one.setName(cont);break;
					case 2:one.setParentId(cont);break;
					case 3:one.setLevels(cont);break;
					case 4:one.setInputDate(cont);
					default:break;
				}
			}
			arr.add(one);
		}
		wk.close();
		return arr;
	}
	
	public static void main(String[] args) {
		try {
			int c = 0;
			for(int i = 1;i<=Integer.parseInt(filesize) ;i++){
				dirlist = Lsj.read_dir_data(dir_data_file_name +i+".xls");
//				for(ONe o : dirlist){
//					//System.out.println(++c +"  Ŀ¼���� ^^^^^^^^^^" + o.getName()  );
//				}
				
				for(ONe o: dirlist){
					String path = huidiaodaye(o);
					o.setPath(path);
					mululist.add(o);
				}
				level = 1;fname = "";
			}
			leaflist = Lsj.read_dir_data(file_data_file_name);
			String p = dir_src_path;
			File f = new File(p);
			listFiles(f);
			System.out.println(srcList.size());
			int v =0;
			Iterator it = leaflist.iterator();
			while(it.hasNext()){
				ONe o = (ONe) it.next();
				for(ONe m : mululist){
					if(o.getParentId().equals(m.getId())){
//						String idate = o.getInputDate();
//						if(idate!=null)
//						idate = idate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
						o.setPath(m.getPath() +"\\"+o.getName());
						//o.setKuohuName(o.getName()+"("+idate); ����Ҫƴ��
						String kn = o.getName().substring(0,o.getName().lastIndexOf("."));
						o.setKuohuName(kn);
					}
				}
			}
			Iterator it2 = leaflist.iterator();
			int jjj = 0;
			int xc = 0;
			while(it2.hasNext()){
				ONe o = (ONe) it2.next();
				String l1 = (++v + " ��Ҫ�������ļ�-----"+(++xc)+"-----"+o.getPath() +"    "+o.getKuohuName());
				sb.append(l1 + "\r");
				for(String s : srcList){
					if(s!=null&& o.getPath()!=null && s.indexOf(o.getKuohuName()) >-1){
						String fp = o.getPath();
						if(fp!=null&&fp.contains("(")){
							fp = fp.substring(0, fp.lastIndexOf("("));
						}
						String sufx = s.substring(s.lastIndexOf("."));
						fp = fp + sufx;
						String l  = ++jjj+ "  ���ڿ����������� "+s+">>>>>>>>>>>>>>"+fp;
						System.out.println(l);
						sb.append(l + "\r");
						copy(s,fp);
						break;
					}else{
						
					}
				}
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 String l = e.toString();
			 sb.append(l + "\r");
		}
		finally{
			try {
				log(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static int kt = 0;
	static String  huidiaodaye(ONe o) throws IOException{
		String fileName = "";
		int currl =  Integer.parseInt(o.getLevels());
		if(currl == level){
			fname = fname.contains("\\")?fname.substring(0,fname.lastIndexOf("\\")):fname;
		}else if(currl > level){
			//fileName = fname   +"/" +o.getName();
		}
		fileName = fname  + "\\"+ o.getName();
		if(currl >= currl){
			level = currl;
		}
		fname = fileName;
		String path = dest_path +  fileName  ;
		File file = new File(path);
		String l = ++kt + "  Ŀ¼·��*************" + path;
		sb.append(l + "\r");
		System.out.println(l);
		
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}
	
	public static void  copy(String src_file,String dest_file) throws Exception{
		BufferedInputStream buff_inStream = null;
		BufferedOutputStream buff_outStream = null;
		try {
			
			buff_inStream = new BufferedInputStream(new FileInputStream(src_file));
			File outFile = new File(dest_file);
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
	}
	
	static int ki = 0;
	public static File[]  listFiles(File f) throws Exception{
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(int i=0;i<files.length;i++){
				if(files[i].isDirectory()){
					Lsj.listFiles(files[i]);
				}else{
					String fp = files[i].getAbsolutePath();
					String l = ++ki + "  �г�����Ŀ¼��·��------"+fp;
					sb.append(l + "\r");
					System.out.println(l);
					srcList.add(fp);
					Lsj.listFiles(files[i]);
				}
			}
		}
		return null;
	}
	
	static void log(String s) throws Exception {
		InputStreamReader reader = null;
		OutputStreamWriter writer = null;
		try {
			ByteArrayInputStream br = new ByteArrayInputStream(s.getBytes());
			reader = new InputStreamReader(br);
			String outPath = log_path + "out.log";
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
	}
}
