package studiii.zlsj_test.cdBankPeriod.tools;

import java.io.File;
/**
 * 删除.svn的目录
 * @author DEV029688
 *
 */
public class DelSVNFolder {
	private static Integer j =0;
	private static File[]  fileArray = new File[5000];
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File( "D:\\temp");
		/*File files_1 []  = file.listFiles();
		for(int i=0;i<files_1.length;i++){
			if("liaosijun4".equalsIgnoreCase(files_1[i].getName())){
				boolean f = files_1[i].delete();
				System.out.println(f);
			}
		}*/
		File files_1 []  = file.listFiles();
		for(int i=0;i<files_1.length;i++){
			DelSVNFolder.listFiles(files_1[i]);
		}
		for(int cc = 0;cc<fileArray.length;cc++){
			DelSVNFolder.deleteFiles(fileArray[cc]);
		}
	}
	
	public static File[]  listFiles(File f){
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(int i=0;i<files.length;i++){
				if(files[i].isDirectory()){
					System.out.println(files[i].getParent()+"\\"+files[i].getName());
					DelSVNFolder.listFiles(files[i]);
					if(files[i].getName().equals(".svn")){
						System.out.println(files[i].getParent()+"\\"+files[i].getName());
						//System.out.println(files[i].delete());
						fileArray[j++] = files[i];
						//System.out.println(j);
					}
				}
			}
		}
		return fileArray;
	}
	
	public static void deleteFiles(File f){
		if(f!=null){
			if(f.isDirectory()){
				File[] files = f.listFiles();
				for(int i=0;i<files.length;i++){
					if(files[i].isDirectory()){
						//deleteFiles(files[i]);
						System.out.println(files[i].getParent()+"\\"+files[i].getName());
						//files[i].delete();
					}else{
						System.out.println(files[i].getParent()+"\\"+files[i].getName());
						//files[i].delete();
					}
				}
				//System.out.println(f.getParent()+"\\"+f.getName());
				//f.delete();
			}else{
				//无
				System.out.println("======="+f.getParent()+"\\"+f.getName());
			}
		}
	}
}
