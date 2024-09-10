package kr.co.wizbrain.tbn.cid;
public class MissedCallVO {
	private String MISSED_CALL_ID;	// 2
	private String MISSED_CALL_TIME;// 2
	private String MISSED_CALL_TEL; // 1
	private String INFORMER_NAME;	// 1
	private String STT_CONTENT;		//stt
	private String FLAG_READ;		//stt
	private String INFORMER_ID;		// 1
	private String AREA_CODE;
	private String MISSED_CALL_DAY;
	
	public MissedCallVO() {}

	public MissedCallVO(String mISSED_CALL_ID, String mISSED_CALL_TIME, String mISSED_CALL_TEL, String iNFORMER_NAME,
			String sTT_CONTENT, String fLAG_READ, String iNFORMER_ID, String aREA_CODE, String mISSED_CALL_DAY) {
		MISSED_CALL_ID = mISSED_CALL_ID;
		MISSED_CALL_TIME = mISSED_CALL_TIME;
		MISSED_CALL_TEL = mISSED_CALL_TEL;
		INFORMER_NAME = iNFORMER_NAME;
		STT_CONTENT = sTT_CONTENT;
		FLAG_READ = fLAG_READ;
		INFORMER_ID = iNFORMER_ID;
		AREA_CODE = aREA_CODE;
		MISSED_CALL_DAY = mISSED_CALL_DAY;
	}

	public String getMISSED_CALL_ID() {
		return MISSED_CALL_ID;
	}
	public void setMISSED_CALL_ID(String mISSED_CALL_ID) {
		MISSED_CALL_ID = mISSED_CALL_ID;
	}
	public String getMISSED_CALL_TIME() {
		return MISSED_CALL_TIME;
	}
	public void setMISSED_CALL_TIME(String mISSED_CALL_TIME) {
		MISSED_CALL_TIME = mISSED_CALL_TIME;
	}
	public String getMISSED_CALL_TEL() {
		return MISSED_CALL_TEL;
	}
	public void setMISSED_CALL_TEL(String mISSED_CALL_TEL) {
		MISSED_CALL_TEL = mISSED_CALL_TEL;
	}
	public String getINFORMER_NAME() {
		return INFORMER_NAME;
	}
	public void setINFORMER_NAME(String iNFORMER_NAME) {
		INFORMER_NAME = iNFORMER_NAME;
	}
	public String getSTT_CONTENT() {
		return STT_CONTENT;
	}
	public void setSTT_CONTENT(String sTT_CONTENT) {
		STT_CONTENT = sTT_CONTENT;
	}
	public String getFLAG_READ() {
		return FLAG_READ;
	}
	public void setFLAG_READ(String fLAG_READ) {
		FLAG_READ = fLAG_READ;
	}
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getMISSED_CALL_DAY() {
		return MISSED_CALL_DAY;
	}
	public void setMISSED_CALL_DAY(String mISSED_CALL_DAY) {
		MISSED_CALL_DAY = mISSED_CALL_DAY;
	}

	@Override
	public String toString() {
		return "MissedCallVO [MISSED_CALL_ID=" + MISSED_CALL_ID + ", MISSED_CALL_TIME=" + MISSED_CALL_TIME
				+ ", MISSED_CALL_TEL=" + MISSED_CALL_TEL + ", INFORMER_NAME=" + INFORMER_NAME + ", STT_CONTENT="
				+ STT_CONTENT + ", FLAG_READ=" + FLAG_READ + ", INFORMER_ID=" + INFORMER_ID + ", AREA_CODE=" + AREA_CODE
				+ ", MISSED_CALL_DAY=" + MISSED_CALL_DAY + "]\n";
	}
	
}
