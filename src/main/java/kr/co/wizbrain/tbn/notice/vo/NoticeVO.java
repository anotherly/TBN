package kr.co.wizbrain.tbn.notice.vo;


//24-11-11 : 공지사항을 위한 VO
public class NoticeVO {
	
	// notice_info table
	// 공지사항 작성자 아이디
	public String WRITER_ID;
	
	// 공지사항 고유번호
	public String NOTICE_ID;
	
	// 공지사항 작성자 이름
	public String WRITER_NAME;
	
	// 공지사항 작성일(시작일)
	public String START_DATE;
	
	// 공지사항 종료일
	public String END_DATE;
	
	// 공지사항 제목
	public String NOTICE_TITLE;
	
	// 공지사항 내용
	public String NOTICE_CONTENT;

	public String NOTICE_TYPE;
	
	// show_notice table
	// 하루동안 보지않음 누른 유저 아이디
	public String USER_ID;
	
	// 숨김 여부
	public String SHOW_24;
	
	// 숨길 날짜
	public String SHOW_TIME;

	
	// 파일 첨부 테이블 필드
	public String FILE_DIR;
	public String FILE_ID;
	public String FILE_NAME;
	public String REG_DT;
	
	
	public String filePath;
	
	
	public String getWRITER_ID() {
		return WRITER_ID;
	}
	public void setWRITER_ID(String wRITER_ID) {
		WRITER_ID = wRITER_ID;
	}
	public String getNOTICE_ID() {
		return NOTICE_ID;
	}
	public void setNOTICE_ID(String nOTICE_ID) {
		NOTICE_ID = nOTICE_ID;
	}
	public String getWRITER_NAME() {
		return WRITER_NAME;
	}
	public void setWRITER_NAME(String wRITER_NAME) {
		WRITER_NAME = wRITER_NAME;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eND_DATE) {
		END_DATE = eND_DATE;
	}
	public String getNOTICE_TITLE() {
		return NOTICE_TITLE;
	}
	public void setNOTICE_TITLE(String nOTICE_TITLE) {
		NOTICE_TITLE = nOTICE_TITLE;
	}
	public String getNOTICE_CONTENT() {
		return NOTICE_CONTENT;
	}
	public void setNOTICE_CONTENT(String nOTICE_CONTENT) {
		NOTICE_CONTENT = nOTICE_CONTENT;
	}
	public String getNOTICE_TYPE() {
		return NOTICE_TYPE;
	}
	public void setNOTICE_TYPE(String nOTICE_TYPE) {
		NOTICE_TYPE = nOTICE_TYPE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getSHOW_24() {
		return SHOW_24;
	}
	public void setSHOW_24(String sHOW_24) {
		SHOW_24 = sHOW_24;
	}
	public String getSHOW_TIME() {
		return SHOW_TIME;
	}
	public void setSHOW_TIME(String sHOW_TIME) {
		SHOW_TIME = sHOW_TIME;
	}
	public String getFILE_DIR() {
		return FILE_DIR;
	}
	public void setFILE_DIR(String fILE_DIR) {
		FILE_DIR = fILE_DIR;
	}
	public String getFILE_ID() {
		return FILE_ID;
	}
	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	
	
	
	
	
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "NoticeVO [WRITER_ID=" + WRITER_ID + ", NOTICE_ID=" + NOTICE_ID + ", WRITER_NAME=" + WRITER_NAME
				+ ", START_DATE=" + START_DATE + ", END_DATE=" + END_DATE + ", NOTICE_TITLE=" + NOTICE_TITLE
				+ ", NOTICE_CONTENT=" + NOTICE_CONTENT + ", NOTICE_TYPE=" + NOTICE_TYPE + ", USER_ID=" + USER_ID
				+ ", SHOW_24=" + SHOW_24 + ", SHOW_TIME=" + SHOW_TIME + ", FILE_DIR=" + FILE_DIR + ", FILE_ID="
				+ FILE_ID + ", FILE_NAME=" + FILE_NAME + ", REG_DT=" + REG_DT + "]";
	}
	
}
