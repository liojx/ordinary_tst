package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountLines {
	static String path = "D:/workspace_project3rd/bocd-csr/";
	
	static List<String> allFilePath =  new ArrayList<String>();
	
	static List<String> removeType = new ArrayList<String>();
	
	static List<String> canStatType = new ArrayList<String>();
	
	static String directory = "bocd-csr";
	
	
	
	static{
		removeType.add(".classpath");//算了，难得移除
		//直接添加需要统计的吧
		canStatType.add(".java");
		canStatType.add(".bat");
		canStatType.add(".txt");
		canStatType.add(".properties");
		canStatType.add(".ftl");
		canStatType.add(".jsp");
		canStatType.add(".html");
		canStatType.add(".js");
		canStatType.add(".css");
		canStatType.add(".htm");
		canStatType.add(".htc");
		canStatType.add(".ini");
		canStatType.add(".xml");
	}
	

	
	static Integer oneFileLines(File file){
		Integer line = 0;
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str = null;
				while((str = br.readLine()) !=null){
					line ++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return line;
	}
	static void getFile(File file){
		if(file.exists()){
			if(file.isDirectory()){
				File[] fileList = file.listFiles();
				for(File f : fileList){
					if(f.isFile()){
						for(String x : canStatType){
							try{
								if(x.equals(f.getName().substring(f.getName().lastIndexOf(".")))){
									allFilePath.add(f.getAbsolutePath());
								}
							}catch(Exception e){
								System.out.println(f.getName()+"不統計");
							}
						}
					}else{
						if(!".svn".equals(f.getName()) || !".git".equals(f.getName()))
						getFile(f);
					}
				}
			}
		}
	}
	static List<String> getFileType(){
		List<String> list = new ArrayList<String>();
		for(String s: allFilePath){
			try{
				if(list.contains(s.substring(s.lastIndexOf(".")))){
					continue;
				}else{
					list.add(s.substring(s.lastIndexOf(".")));
				}
			}catch(Exception e){
				System.out.println("e--"+s);
			}
		}
		return list;
	}
	
	static Integer getLines(){
		Integer l = 0;
		File  file = new File(path);
		getFile(file);
		for(String p : allFilePath){
			try{
				File fi = new File(p);
				int lineNum = oneFileLines(fi);
				String log = p+"\t\t\t\t\t"+lineNum+"\n";
				l+=lineNum;
				wirte(log);
			}catch(Exception e){
				System.out.println(" baocuo=="+p);
			}
		}
		return l;
	}
	
	
	static void wirte(String s){
		
	}
	public static void main(String[] args) {
//		int s = CountLines.oneFileLines(new File("D:/workspace_project3rd/bocd-csr/lib/ant.jar"));
//		System.out.println(s);
		getFile(new File("C:\\doctorwork_lsj\\eclipse-workspace\\clinic-web"));
//		for(String s : allFilePath){
//			System.out.println(s);
//		}
//		System.out.println(allFilePath.size());
//		System.out.println(".xml".substring(".xml".lastIndexOf(".")));
//		for(String s : CountLines.getFileType()){
//			System.out.println(s);
//		}
//		System.out.println(getLines());
	}
}
