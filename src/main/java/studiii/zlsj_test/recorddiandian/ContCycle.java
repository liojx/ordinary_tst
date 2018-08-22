package studiii.zlsj_test.recorddiandian;

import java.io.*;
import java.util.*;

public class ContCycle {

	static final String FILE_PATH = "C:\\Users\\Lsj\\git\\clinic-web";

	static final String ANNO_PATH = "C:\\doctorwork_lsj\\文档资料\\used-annotation";

	File rootFile = new File(FILE_PATH);

	List<String> files = new ArrayList<String>();

	Set<String> list = new HashSet<String>();

	Set<String> allList = new HashSet<String>();

	File[] list(File f) {
		File[] filelist = null;
		if(f.isDirectory()) {
			filelist = f.listFiles();
			for(File sf : filelist) {
				if(sf.isDirectory() && !sf.isHidden()) { //如果是目录并且不是隐藏目录，回调
					list(sf);
				}else {
					if(sf.getAbsolutePath().endsWith(".java")) //如果是java文件才加进来
						files.add(sf.getAbsolutePath());
				}
			}
		}else {
			files.add(f.getAbsolutePath());
		}
		return filelist;
	}

	Set<String> deal(String filePath){
		try {
			Reader reader = new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.trim();
				if(line.startsWith("@") || line.indexOf("(@") >-1) {
					int start = line.indexOf("@");
					int end = line.indexOf(" ");
					if(end > start)
						line = line.substring(start,end);

					if(line.indexOf("(") >-1) {
						if(line.indexOf("(@")>-1) {
							line = line.substring(line.indexOf("(@"));
							line = line.substring(1,line.indexOf(" "));
							list.add(line);
							continue;
						}
						line = line.substring(line.indexOf("@"),line.indexOf("("));
					}
					list.add(line);
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	void listANNO() throws IOException {
		File file = new File(ANNO_PATH);
		File[] filelist = file.listFiles();
		for(File f : filelist) {
			Reader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.trim();
				allList.add(line);
			}
		}
	}
	public static void main(String[] args) {
		ContCycle cc = new ContCycle();
		cc.list(cc.rootFile);
		Set set = null;
		for(String str : cc.files) {
			set = cc.deal(str);
		}


		try {
			cc.listANNO();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<String> it = cc.allList.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
