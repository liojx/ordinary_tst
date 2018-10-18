package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.File;

public class ListFileOrDir {

	public void list() {
		File dir = new File("D:\\WorkSpace_Out\\zWebwork\\WebRoot\\WEB-INF\\lib");

		File[] children = dir.listFiles();

		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i].getName());
		}
	}
	static int j = 0;  //总共文件
	static int k = 0;
	static int l = 0;
	static void cnt(File f){
		File[] children = null;
		if(f!=null){
			if(f.isDirectory()){
				children = f.listFiles();
				for (int i = 0; i < children.length; i++) {
					cnt(children[i]);
//					System.out.println(children[i].getPath() );
					if(children[i].isFile()) j++;
					if(children[i].isFile() && !children[i].getName().endsWith(".java")) k++;
					if(children[i].isFile() && children[i].getName().endsWith(".class")) l++;
				}
			}
		}
	}
	public static void main(String[] args) {
//		System.out.println("sfksfjsdfsfdsdf".charAt(4));
//		System.out.println("sfksfjsdfsfdsdf".substring(1));
//		ListFileOrDir d = new ListFileOrDir ();
//		d.list();
		String s = "C:\\doctorwork_lsj\\eclipse-workspace\\clinic-web\\target\\classes\\writable";
		String p = "D:\\电话银行二期\\程序版本\\Webagentout\\target\\classes";
		File file = new File(s);
		cnt(file);
		System.out.println("总共文件个数，不包含文件夹："+j);
		System.out.println("Java文件个数："+k);
		System.out.println("class文件个数："+l);
	}
}
