package kr.co.wizbrain.tbn.notice.vo;

public class nFileVO {
	private String fileId;
	private String fileDir;
	private String fileName;
	private String regDt;
	private String fileVersion;
	private String filePath;
	private String fileType;
	private String noticeId;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getFileVersion() {
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	
	
	
	@Override
	public String toString() {
		return "nFileVO [fileId=" + fileId + ", fileDir=" + fileDir + ", fileName=" + fileName + ", regDt=" + regDt
				+ ", fileVersion=" + fileVersion + ", filePath=" + filePath + ", fileType=" + fileType + ", noticeId="
				+ noticeId + "]";
	}
	
	
	
}
