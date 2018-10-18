package studiii.zlsj_test.cdBankPeriod.svn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileVo {
	
	/**
	 * ����̽��Ŀ¼
	 */
	private String detectDirectory;
	
	/**
	 * �ļ�����Ŀ¼������·��
	 */
	private String filePath;
	
	/**
	 * �ļ�Ŀ¼����·��
	 */
	private String fileDirectoryPath;
	
	/**
	 * ����޸�ʱ��
	 */
	private Date lastModifyTime;
	
	/**
	 * ��¼�ļ�ʱ��
	 */
	private Date saveTime;
	
	/**
	 * �ļ���С����λ���ֽڣ�
	 */
	private Long size;
	
	/**
	 * �ļ����� 1-�ļ� 2-Ŀ¼
	 */
	private String type;
	
	
	/**
	 * �Ƿ����  1-���� 0-�Ѿ�ɾ��
	 */
	private String exists;


	/**
	 * @return the detectDirectory
	 */
	public String getDetectDirectory() {
		return detectDirectory;
	}


	/**
	 * @param detectDirectory the detectDirectory to set
	 */
	public void setDetectDirectory(String detectDirectory) {
		this.detectDirectory = detectDirectory;
	}


	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return the fileDirectoryPath
	 */
	public String getFileDirectoryPath() {
		return fileDirectoryPath;
	}


	/**
	 * @param fileDirectoryPath the fileDirectoryPath to set
	 */
	public void setFileDirectoryPath(String fileDirectoryPath) {
		this.fileDirectoryPath = fileDirectoryPath;
	}


	/**
	 * @return the lastModifyTime
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}


	/**
	 * @param lastModifyTime the lastModifyTime to set
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}


	/**
	 * @return the saveTime
	 */
	public Date getSaveTime() {
		return saveTime;
	}


	/**
	 * @param saveTime the saveTime to set
	 */
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}


	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the exists
	 */
	public String getExists() {
		return exists;
	}


	/**
	 * @param exists the exists to set
	 */
	public void setExists(String exists) {
		this.exists = exists;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "����̽��Ŀ¼��"+this.detectDirectory +"\t" 
		+"�ļ�����·����"+this.filePath +"\t"
		+"�ļ�����޸�ʱ�䣺"+sdf.format(this.lastModifyTime)+"\t"
		+"�ļ�����ʱ��:"+sdf.format(this.saveTime)+"\t"
		+"�ļ��ĸ�Ŀ¼����·����"+this.fileDirectoryPath+"\t"
		+"�ļ��Ĵ�С��"+this.size+"\t"
		+"�ļ������ͣ�"+this.type+"\t"
		+"�ļ��Ƿ���ڣ�"+this.exists;
	}
}
