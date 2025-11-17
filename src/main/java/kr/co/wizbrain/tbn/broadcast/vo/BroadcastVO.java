package kr.co.wizbrain.tbn.broadcast.vo;

public class BroadcastVO {
	private String RECEIPT_ID;
	private String FLAG_BROAD;
	private String RECEIPT_TIME;
	private String REPORT_TYPE;
	private String INFORMER_NAME;
	private String INFORMER_TEL;
	private String INFORMER_TYPE;
	private String CONTENTS;
	private String RECEIPT_DAY;
	private String BROAD_TIME;
	private String UPDATE_TABLE;
	
	public BroadcastVO() {}

	public BroadcastVO(String rECEIPT_ID, String fLAG_BROAD, String rECEIPT_TIME, String rEPORT_TYPE,
			String iNFORMER_NAME, String iNFORMER_TEL, String iNFORMER_TYPE, String cONTENTS, String rECEIPT_DAY,
			String bROAD_TIME) {
		RECEIPT_ID = rECEIPT_ID;
		FLAG_BROAD = fLAG_BROAD;
		RECEIPT_TIME = rECEIPT_TIME;
		REPORT_TYPE = rEPORT_TYPE;
		INFORMER_NAME = iNFORMER_NAME;
		INFORMER_TEL = iNFORMER_TEL;
		INFORMER_TYPE = iNFORMER_TYPE;
		CONTENTS = cONTENTS;
		RECEIPT_DAY = rECEIPT_DAY;
		BROAD_TIME = bROAD_TIME;
	}

	public String getRECEIPT_ID() {
		return RECEIPT_ID;
	}
	public void setRECEIPT_ID(String rECEIPT_ID) {
		RECEIPT_ID = rECEIPT_ID;
	}
	public String getFLAG_BROAD() {
		return FLAG_BROAD;
	}
	public void setFLAG_BROAD(String fLAG_BROAD) {
		FLAG_BROAD = fLAG_BROAD;
	}
	public String getRECEIPT_TIME() {
		return RECEIPT_TIME;
	}
	public String getUPDATE_TABLE() {
		return UPDATE_TABLE;
	}

	public void setUPDATE_TABLE(String uPDATE_TABLE) {
		UPDATE_TABLE = uPDATE_TABLE;
	}

	public void setRECEIPT_TIME(String rECEIPT_TIME) {
		RECEIPT_TIME = rECEIPT_TIME;
	}
	public String getREPORT_TYPE() {
		return REPORT_TYPE;
	}
	public void setREPORT_TYPE(String rEPORT_TYPE) {
		REPORT_TYPE = rEPORT_TYPE;
	}
	public String getINFORMER_NAME() {
		return INFORMER_NAME;
	}
	public void setINFORMER_NAME(String iNFORMER_NAME) {
		INFORMER_NAME = iNFORMER_NAME;
	}
	public String getINFORMER_TEL() {
		return INFORMER_TEL;
	}
	public void setINFORMER_TEL(String iNFORMER_TEL) {
		INFORMER_TEL = iNFORMER_TEL;
	}
	public String getINFORMER_TYPE() {
		return INFORMER_TYPE;
	}
	public void setINFORMER_TYPE(String iNFORMER_TYPE) {
		INFORMER_TYPE = iNFORMER_TYPE;
	}
	public String getCONTENTS() {
		return CONTENTS;
	}
	public void setCONTENTS(String cONTENTS) {
		CONTENTS = cONTENTS;
	}
	public String getRECEIPT_DAY() {
		return RECEIPT_DAY;
	}
	public void setRECEIPT_DAY(String rECEIPT_DAY) {
		RECEIPT_DAY = rECEIPT_DAY;
	}
	public String getBROAD_TIME() {
		return BROAD_TIME;
	}
	public void setBROAD_TIME(String bROAD_TIME) {
		BROAD_TIME = bROAD_TIME;
	}

	@Override
	public String toString() {
		return "BroadcastVO [RECEIPT_ID=" + RECEIPT_ID + ", FLAG_BROAD=" + FLAG_BROAD + ", RECEIPT_TIME=" + RECEIPT_TIME
				+ ", REPORT_TYPE=" + REPORT_TYPE + ", INFORMER_NAME=" + INFORMER_NAME + ", INFORMER_TEL=" + INFORMER_TEL
				+ ", INFORMER_TYPE=" + INFORMER_TYPE + ", CONTENTS=" + CONTENTS + ", RECEIPT_DAY=" + RECEIPT_DAY
				+ ", BROAD_TIME=" + BROAD_TIME + "]\n";
	}
	
}
