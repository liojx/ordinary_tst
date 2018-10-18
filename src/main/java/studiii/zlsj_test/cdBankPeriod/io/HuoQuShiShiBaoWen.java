package studiii.zlsj_test.cdBankPeriod.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class HuoQuShiShiBaoWen {
	/**
	 * csr|app|cronjob|callout 测试服务器
	 */
//	static String csr_host = "15.0.20.49";
	static String csr_host = "15.0.20.44";
	
	/**
	 * ivr 测试服务器
	 */
//	static String ivr_host = "15.0.20.51";
	static String ivr_host = "15.0.20.187";
	
	/**
	 * 存放上传的xml文件
	 */
	static String[] app_xml = {"cfx201C.xml","cfx2092.xml","cfx200Y.xml"}; 
	
	/**
	 * csr war服务器路径
	 */
	static String csr_server_path = "/u01/soft/tomcat-csr/webapps/";
	
	/**
	 * ivr war 路径
	 */
//	static String ivr_server_path = "/opt/Tomcat/AppServer/apache-tomcat-6.0.18b/webapps/";
	static String ivr_server_path = "/opt/Tomcat/AppServer7.2/apache-tomcat-8.0.47/webapps/";
	
	/**
	 * csr war本第路径
	 */
//	static String csr_local_path = "D:/call_center/csr/trunk/target/";
	static String csr_local_path = "D:/版本/2017年11月28日版本/phonebank20171128/20171128/";
//	static String csr_local_path = "D:/XMR2017001A/trunk/csr/target/";
	
//	static String ivr_local_path = "C:/Users/dev032990/Desktop/";
//	static String ivr_local_path = "C:/Users/dev032990/Desktop/IVR war EP/";
	static String ivr_local_path = "C:/Users/dev032990/Desktop/IVR war EP - 2/";
	/**
	 * csr目录备份位置
	 */
	static String csr_bak_path = "/back/csr/";
	/**
	 * csr 服务器用户名
	 */
	static String csr_username = "callcsr";
	
	/**
	 * ivr 服务器登录名
	 */
//	static String ivr_username = "callvp";
	static String ivr_username = "root";
	
	/**
	 * ivr 登录密码
	 */
//	static String ivr_password = "callvp";
	static String ivr_password = "root01";
	
	/**
	 * csr 服务器密码
	 */
	static String csr_password = "callcsr";
	
	/**
	 * sftp 端口
	 */
	static int port = 22;
	
	/**
	 * csr sftp
	 */
	static ChannelSftp csr_sftp = null;
	
	/**
	 * ivr_sftp
	 */
	static ChannelSftp ivr_sftp = null;
	
	/**
	 * ivr war 
	 */
	public static String ivr_war = "CompanyCustom.war";
	/**
	 * csr war包名字
	 */
	static String csr_war = "csr.war";
	
	/**
	 *UnifyApp的username 
	 */
	static String app_username  = "callapp";
	
	/**
	 * UnifyApp的密码
	 */
	static String app_password = "callapp";
	
	/**
	 *UnifyApp sftp 
	 */
	static ChannelSftp app_sftp = null;
	
	/**
	 *UnifyApp 测试服务器部署路径 
	 */
	static String app_server_path = "/u01/soft/tomcat-app/webapps/UnifyApp/";
	
	/**
	 *UnifyApp 本地存放路径 
	 */
//	static String app_local_path = "D:/workspace_63_for_Dev_In/UnifyApp/WebRoot/";
	static String app_local_path = "D:/版本/2017年11月28日版本/phonebank20171128/20171128/APP-V1.1.38/call_center/APP/trunk/UnifyApp/WebRoot/";
	
	/**
	 * UnifyApp备份的目录
	 */
	static String app_bak_path = "/u01/soft/tomcat-app/verbak/";
	
	static List<String> fileList = null;
	
	static String detectDirectory = "";
	//UnifyApp/WebRoot下的文件
	static LinkedHashMap<String,String> allFile = new LinkedHashMap<String,String>();
	
	static ChannelSftp conn(String username,String host,int port,String password,int type){
		ChannelSftp sftp = null;
		JSch jsch = new JSch();
		try {
			jsch.getSession(username, host, port);
			Session session = jsch.getSession(username, host, port);
			session.setPassword(password);
			Properties p = new Properties();
			p.put("userauth.gssapi-with-mic", "no");
			p.put("StrictHostKeyChecking", "no");
			session.setConfig(p);
			session.connect();
			if(type == 3){
//				FileSystemOptions
//				SftpFileSystemConfigBuilder
			}
			Channel c = session.openChannel("sftp");
			sftp = (ChannelSftp)c;
			switch (type) {
			case 1:csr_sftp = sftp;break;
			case 2:app_sftp = sftp;break;
			case 3:ivr_sftp =sftp;break;
			default:break;
			}
			
		} catch (JSchException e) {
			e.printStackTrace();
		}
		
		return sftp;
	}
	
	static void download(String directory,String downloadfile,String savefile,ChannelSftp sftp){
		try {
//			sftp.connect();
			sftp.cd(directory);
			File file = new File(savefile);
			if(!file.exists())file.createNewFile();
			sftp.get(downloadfile,new FileOutputStream(file));
			System.out.println("从"+directory+"下载到"+savefile+"成功！");
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void upload(String directory,String uploadfile,ChannelSftp sftp){
		try {
//			sftp.connect();
			sftp.cd(directory);
			System.out.println("上传的文件路径:"+uploadfile);
			File file = new File(uploadfile);
			if(!file.exists())file.createNewFile();
			sftp.put(new FileInputStream(file),file.getName());
			System.out.println("从"+uploadfile+"上传到"+directory+"成功");
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static List<String> getFileList(){
		if(fileList == null){
			fileList = new ArrayList<String>(); 
		}
		return fileList;
	}
	static List<String> getFile(File file){
		List<String> pathList = HuoQuShiShiBaoWen.getFileList();
		
		if(file.exists()){
			if(file.isDirectory()){
				File[] fileL = file.listFiles();
				for(File f : fileL){
					if(f.isDirectory()){
						getFile(f);
					}
					pathList.add(f.getAbsolutePath());
				}
			}else{
				pathList.add(file.getAbsolutePath());
			}
		}
		return pathList;
	}
	
	//备份csr的war包和解压目录文件，并上传本地打的war包
	static void csr(){
		csr_local_path = detectDirectory;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		ChannelSftp sftp = conn(csr_username,csr_host,port,csr_password,1);
		System.out.println("csr----"+"csr_username="+csr_username+" csr_host="+csr_host+" csr_password="+csr_password);
		try {
			sftp.connect();
			sftp.cd(csr_server_path);
			try{
			//备份
				sftp.rename(csr_server_path+csr_war, csr_server_path + csr_war+ "."+sdf.format(new Date()));
				sftp.rename(csr_server_path+"csr", csr_bak_path + "csr"+ "."+sdf.format(new Date()));
				System.out.println("备份成功");
			}catch(Exception e){
				e.printStackTrace();
			}
			//上传
			upload(csr_server_path, csr_local_path+csr_war, csr_sftp);
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
		
	}
	
	//获得每个需要上传的xml的路径
	static List<String> getXmlFile(String[] xml) {
		File f = new File(app_local_path);
		LinkedHashMap<String, String> all = getAllFile(f);
		Set<String> set = all.keySet();
		Iterator<String> it = set.iterator();
		List<String> path = new ArrayList<String>();
		if (xml != null && xml.length > 0) {
			while (it.hasNext()) {
				String str = it.next();
				for (String s : xml) {
					if (s.equals(str)) {
						path.add(all.get(str));
					}
				}
			}
		}

		return path;
	}

	//获得app下所有文件的路径
	static LinkedHashMap<String,String> getAllFile(File rootFile){
		File[] filelist = null;
		try{
			if(rootFile.isDirectory()){
				filelist = rootFile.listFiles();
				for(int i = 0;i<filelist.length;i++){
					File f = filelist[i];
					if(f.isFile()){
						allFile.put(f.getName(), f.getAbsolutePath());
					}
					getAllFile(f);
				}
			}else{
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return allFile;
	}
	//把上面app_xml数组中的文件传到指定文件，并把已有的这些文件备份
	static void unifyapp(){
		
		try{
			app_local_path = detectDirectory;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			List<String> list = getXmlFile(app_xml);
			List<String> serverPath = new ArrayList<String>();
			ChannelSftp sftp = conn(app_username,csr_host,port,app_password,2);
			System.out.println("UnifyApp----"+"app_username="+app_username+" csr_host="+csr_host+" app_password="+app_password);
			sftp.connect();
			sftp.cd(app_bak_path);
			for(int i = 0;i<list.size();i++){
				String filep = list.get(i).toString();
				if(filep.indexOf(app_local_path.replace("/", "\\")) == 0){
					serverPath.add(app_server_path+(filep.substring(app_local_path.length()).replace("\\", "/")));
				}
			}
			//备份到app_bak_path里面
			for(String s : serverPath){
				String name = s.substring(s.lastIndexOf("/")+1);
				String server_directory = s.substring(0,s.lastIndexOf("/"));
				String local = "d:/temp/";
				download(server_directory, s, local+name, sftp);
				upload(app_bak_path, local+name, sftp);
				sftp.cd(app_bak_path);
				try{
					sftp.rename(app_bak_path+name, app_bak_path+name+"_"+sdf.format(new Date()));
				}catch(Exception ex){
					new Exception(app_bak_path+name+"_"+sdf.format(new Date()) + "已经存在,改名不成功！").printStackTrace();
				}
			}
			System.out.println("备份成功");
			//上传版本
			for(int i = 0;i<serverPath.size();i++){
				String s = serverPath.get(i);
				upload(s.substring(0,s.lastIndexOf("/")), list.get(i), sftp);
			}
			System.out.println("上传成功");
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void ivr(){
		ivr_local_path = detectDirectory;
		ChannelSftp sftp = conn(ivr_username,ivr_host,port,ivr_password,3);
		System.out.println("ivr_host = " +ivr_host + "  ivr_username = "+ivr_username);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try{
			sftp.connect();
			sftp.cd(ivr_server_path);
		
			//备份
			sftp.rename(ivr_server_path+ivr_war, ivr_server_path + ivr_war+ "."+sdf.format(new Date()));
			sftp.rename(ivr_server_path+ivr_war.substring(0,ivr_war.indexOf(".")), ivr_server_path + ivr_war.substring(0,ivr_war.indexOf("."))+ "."+sdf.format(new Date()));
			System.out.println("备份成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("备份失败！");
		}
			//上传
			upload(ivr_server_path, ivr_local_path+ivr_war, ivr_sftp);
//			System.exit(0);
	}
	
	static void xmlRespSave(){
		
	}
	
	static {
		String directory = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("****************请输入上传文件的目录************");
		String str = scan.nextLine();
		directory = str.replace("\\", "/");
		detectDirectory =  directory + "/";
	}
	
	public static void main(String[] args) {
//		csr();
//		unifyapp();
		List<String> x = new ArrayList<String>();
//		x.add("AcctActive.war");
//		x.add("CompanyCustom.war");
//		x.add("ConfrenceFlow.war");
//		x.add("CreditCardCustom.war");
//		x.add("InformationServises.war");
//		x.add("limit_cancels.war");
//		x.add("Lost_CD.war");
		x.add("PersonCustom.war");
		for(String s : x){
			HuoQuShiShiBaoWen.ivr_war = s;
			System.out.println("s=="+s);
			ivr();
		}
		System.exit(0);
	}
}
