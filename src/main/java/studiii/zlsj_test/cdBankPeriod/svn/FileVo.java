package studiii.zlsj_test.cdBankPeriod.svn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileVo {

	/**
	 * 被侦探的目录
	 */
	private String detectDirectory;

	/**
	 * 文件（非目录）绝对路径
	 */
	private String filePath;

	/**
	 * 文件目录绝对路径
	 */
	private String fileDirectoryPath;

	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;

	/**
	 * 记录文件时间
	 */
	private Date saveTime;

	/**
	 * 文件大小（单位：字节）
	 */
	private Long size;

	/**
	 * 文件类型 1-文件 2-目录
	 */
	private String type;


	/**
	 * 是否存在  1-存在 0-已经删除
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
		return "被侦探的目录："+this.detectDirectory +"\t"
				+"文件绝对路径："+this.filePath +"\t"
				+"文件最后修改时间："+sdf.format(this.lastModifyTime)+"\t"
				+"文件保存时间:"+sdf.format(this.saveTime)+"\t"
				+"文件的父目录绝对路径："+this.fileDirectoryPath+"\t"
				+"文件的大小："+this.size+"\t"
				+"文件的类型："+this.type+"\t"
				+"文件是否存在："+this.exists;
	}
}
