package kr.co.wizbrain.tbn.notice.vo;


//24-11-11 : 공지사항을 위한 VO
public class NoticeVO {
	
	// notice_info table
	// 공지사항 작성자 아이디
	private String WRITER_ID;
	
	// 공지사항 고유번호
	private String NOTICE_ID;
	
	// 공지사항 작성자 이름
	private String WRITER_NAME;
	
	// 공지사항 작성일(시작일)
	private String START_DATE;
	
	// 공지사항 종료일
	private String END_DATE;
	
	// 공지사항 제목
	private String NOTICE_TITLE;
	
	// 공지사항 내용
	private String NOTICE_CONTENT;

	private String NOTICE_TYPE;
	
	// show_notice table
	// 하루동안 보지않음 누른 유저 아이디
	private String USER_ID;
	
	// 숨김 여부
	private String SHOW_24;
	
	// 숨길 날짜
	private String SHOW_TIME;

	
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

	@Override
	public String toString() {
		return "NoticeVO [WRITER_ID=" + WRITER_ID + ", NOTICE_ID=" + NOTICE_ID + ", WRITER_NAME=" + WRITER_NAME
				+ ", START_DATE=" + START_DATE + ", END_DATE=" + END_DATE + ", NOTICE_TITLE=" + NOTICE_TITLE
				+ ", NOTICE_CONTENT=" + NOTICE_CONTENT + ", NOTICE_TYPE=" + NOTICE_TYPE + ", USER_ID=" + USER_ID
				+ ", SHOW_24=" + SHOW_24 + ", SHOW_TIME=" + SHOW_TIME + ", getWRITER_ID()=" + getWRITER_ID()
				+ ", getNOTICE_ID()=" + getNOTICE_ID() + ", getWRITER_NAME()=" + getWRITER_NAME() + ", getSTART_DATE()="
				+ getSTART_DATE() + ", getEND_DATE()=" + getEND_DATE() + ", getNOTICE_TITLE()=" + getNOTICE_TITLE()
				+ ", getNOTICE_CONTENT()=" + getNOTICE_CONTENT() + ", getNOTICE_TYPE()=" + getNOTICE_TYPE()
				+ ", getUSER_ID()=" + getUSER_ID() + ", getSHOW_24()=" + getSHOW_24() + ", getSHOW_TIME()="
				+ getSHOW_TIME() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
