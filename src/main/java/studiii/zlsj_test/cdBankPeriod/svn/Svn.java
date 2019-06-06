package studiii.zlsj_test.cdBankPeriod.svn;

import studiii.zlsj_test.cdBankPeriod.dbutil.DBtool;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class Svn {
	/**
	 * ��������������ļ�
	 */
	private static int detectDays = 30;
	
	/**
	 * �������ļ�
	 */
	private static File outFile = new File("C:/Users/dev032990/Desktop/outlog.log");
	/**
	 * ������Ŀ¼
	 */
	private static String detectDirectory = null;
	
	/**
	 * ���Ŀ¼·��
	 */
	private static List<String> directoryPathList = new ArrayList<String>();
	
	/**
	 *����ļ�·�� 
	 */
	private static List<String> filePathList = new ArrayList<String>();
	
	/**
	 * ����ļ�����
	 */
	private static List<FileVo> fileVoList = new ArrayList<FileVo>();
	
	/**
	 * ������ݿ����еľ���·��
	 */
	private static List<String> dbFilePathList = new ArrayList<String>();
	
	public static FileOutputStream outStream = null;
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	static {
		String direcotry = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("****************�����뱻��̽��Ŀ¼************");
		String str = scan.next();
		direcotry = str.replace("\\", "/");
		detectDirectory =  direcotry + "/";
	}
	
	/**
	 * ��ȡ�ļ�·��
	 * @date 2017-11-23 ����09:59:30
	 */
	public static void getAllFile(File rootFile){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File[] filelist = null;
		try{
			if(rootFile.isDirectory()){
				filelist = rootFile.listFiles();
				for(int i = 0;i<filelist.length;i++){
					File f = filelist[i];
					if(f.isFile()){
						FileVo  vo = new FileVo();
						vo.setDetectDirectory(detectDirectory);
						vo.setFilePath(f.getAbsolutePath());
						vo.setLastModifyTime(new Date(f.lastModified()));
						vo.setFileDirectoryPath(f.getParent());
						vo.setExists("1");
						vo.setSaveTime(new Date());
						vo.setType("1");
						vo.setSize(f.length());
						fileVoList.add(vo);
//						filePathList.add(f.getAbsolutePath());
//						System.out.println(f.getAbsolutePath() + "   "+sdf.format(new Date(f.lastModified())));
//						System.out.println(f.getAbsolutePath() + " "+f.getParent());
//						System.out.println("�ļ��Ĵ�С��"+f.length());
					}else{
						if(!("target".equals(f.getName())&&f.getAbsolutePath().indexOf("csr\target")>0) && !".svn".equals(f.getName())){//�������ų�csr�µ�target������.svn�ļ���
							getAllFile(f);
						}else{
							System.out.println("δͳ�Ƶ��ļ�  " +f.getAbsolutePath());
						}
					}
				}
				FileVo  vo = new FileVo();
				vo.setDetectDirectory(detectDirectory);
				vo.setFilePath(rootFile.getAbsolutePath());
				vo.setLastModifyTime(new Date(rootFile.lastModified()));
				vo.setExists("1");
				vo.setSaveTime(new Date());
				vo.setType("2");
				fileVoList.add(vo);
//				System.out.println(rootFile.getAbsolutePath());
//				directoryPathList.add(rootFile.getAbsolutePath());
			}else{
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ����еļ�¼
	 */
	public static void getDbFilePath(){
		Connection conn = DBtool.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String d_p = detectDirectory.replace("/", "\\");
		d_p = d_p.substring(0,d_p.length()-1);
		try{
			
			String sql = "select * from t_file where instr(file_path, ?)>0 ";
			pst  = conn.prepareStatement(sql);
			pst.setString(1, d_p);
			rs = pst.executeQuery();
			while(rs.next()){
				dbFilePathList.add(rs.getString("file_Path"));
			}
		}catch(Exception e){
			System.out.println( e.getMessage());
		}finally{
			try {
					if(rs!=null)rs.close();
					if(pst!=null)pst.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * ����
	 */
	static void batchInsert(List<FileVo> list){
		Connection conn = DBtool.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			String sql = "insert into t_file (detect_directory,file_path,modify_time,save_time,file_directory_path,size_,type_,exist_) values" +
					"(?,?,?,?,?,?,?,?)";
			pst  = conn.prepareStatement(sql);
			if(list != null && list.size()>0){
				for(int i=0;i < list.size();i++){
					FileVo vo = list.get(i);
					pst.setString(1, vo.getDetectDirectory());
					pst.setString(2, vo.getFilePath());
					pst.setTimestamp(3,new Timestamp(vo.getLastModifyTime().getTime()));
					pst.setTimestamp(4,new Timestamp(vo.getSaveTime().getTime()));
					pst.setString(5, vo.getFileDirectoryPath());
					pst.setLong(6, vo.getSize() == null ? 0:vo.getSize());
					pst.setString(7, vo.getType());
					pst.setString(8, vo.getExists());
					pst.addBatch();
				}
			}
			pst.executeBatch();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println( e.getMessage());
		}finally{
			try {
					if(rs!=null)rs.close();
					if(pst!=null)pst.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * ����
	 */
	static void save(){
		getDbFilePath();
		getAllFile(new File(detectDirectory));
			if(dbFilePathList!=null && dbFilePathList.size() >0){
				List<String> a = new ArrayList<String>();
				System.arraycopy(dbFilePathList, 0, a, 0, dbFilePathList.size());
				for(String dd:a){
					System.out.println(dd);
				}
				 //= dbFilePathList.removeAll(fileVoList);
//				for
				for(String db : dbFilePathList){
					for(int m = 0;m<fileVoList.size();m++){
						FileVo vo = fileVoList.get(m);
						if(vo!=null&&vo.getFilePath()!=null&&vo.getFilePath().equals(db)){
							System.out.println("���ݿ���ڵ��ļ���"+db);
							fileVoList.remove(m);
							m--;
							break;
						}else{
//							System.out.println("�Ѿ�ɾ���ģ�"+db);
						}
					}
				}
				batchInsert(fileVoList);
			}else{
				batchInsert(fileVoList);
			}
			System.out.println("���ݿ�["+detectDirectory+"]��Ŀ¼�����У�"+(dbFilePathList!=null?dbFilePathList.size():0)+"���ļ�");
			System.out.println("�ܹ����룺"+fileVoList.size()+"���ļ�");
	}
	
	/**
	 * ����������ļ��޸�ʱ��仯
	 */
	static void modifyTimeChangeFile(){
		Connection conn = DBtool.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if(detectDirectory!=null ){
			detectDirectory = detectDirectory.substring(0, detectDirectory.length()-1);
			detectDirectory = detectDirectory.replace("/", "\\");
		}
		try{
			String sql = "select * from t_file where type_ = 1 and modify_time between sysdate - " + detectDays +" and sysdate  and instr(file_path,'"+detectDirectory+"')>0";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int i = 0;
			while(rs.next()){
				String file_path = rs.getString("file_path");
				Timestamp modify = rs.getTimestamp("modify_time");
				if(file_path.indexOf(".class")>-1 || file_path.indexOf(".metadata")>-1)continue;
				System.out.println("���"+detectDays+"���޸����ڱ仯���ļ�·����"+file_path+"\t����޸�ʱ�䣺"+sdf.format(modify));
				write_("���"+detectDays+"���޸����ڱ仯���ļ�·����"+file_path+"\t����޸�ʱ�䣺"+sdf.format(modify)+"\n");
				i++;
			}
			System.out.println("���"+detectDays+"���޸���"+i+"���ļ�");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pst!=null)pst.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static FileOutputStream getOutStream(){
		try{
			if(outStream == null){
				outStream = new FileOutputStream(outFile,true); //׷�Ӳ�����
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return outStream;
	} 
	static void write_(String s) {
		FileOutputStream buff_outStream = null;
		try {
			if (outFile.exists()){
				outFile.createNewFile();
			}
			buff_outStream = Svn.getOutStream();
			byte b[] =  s.getBytes();
			buff_outStream.write(b, 0, b.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	/**
	 * @date 2017-11-23 ����09:11:11
	 */
	public static void main(String[] args) {
		Long start = new Date().getTime();
		Svn.save();
		Long end = new Date().getTime();
		System.out.println("��ʱ��"+(end - start )/1000+"s");
//		outFile.delete();
//		modifyTimeChangeFile();
	}

}
