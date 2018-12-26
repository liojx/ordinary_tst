package studiii.zlsj_test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author liaosijun
 * @since 2018年12月21日 下午5:02:04
 */
public class FtpUtil {

	private String ip = "www.baidu.com";
	
	private Integer port = 21;
	
	private String userName = "lsj";
	
	private String password ="abc";
	
	private String path = "/void";
	
	/**
	 * 不能把ftp 设置为全局的了，并发会出问题
	 * @param f
	 */
	public void upload(File f){
		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.connect(ip,port);
			ftp.login(userName, password);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();      
			if (!FTPReply.isPositiveCompletion(reply)) {      
				ftp.disconnect();      
			}
			ftp.changeWorkingDirectory(path);
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileInputStream input = null;
		try{
			if(f == null){
				System.out.println("文件参数为null");
				return;
			}
			if(f.exists()){
				input = new FileInputStream(f);
				boolean ret = ftp.storeFile(f.getName(),input);
				if(ret){
					System.out.println("文件[" + f.getName() + "]已经上传到目录[" + path + "]中" );
				}else{
					System.out.println("文件[" + f.getName() + "]上传失败");
				}
			}else{
				System.out.println("需要上传的文件[" + f.getName() + "]不存在！");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			    try {
			    	if(input != null){
			    		input.close();
			    	}
			    	if(ftp!=null){  
			    		ftp.logout();
			    		ftp.disconnect();  
			    	}  
				} catch (IOException e) {
					e.printStackTrace();
			   } 
		}
	}
}
