package kr.co.wizbrain.tbn.receipt.vo.popup;

public class EditVO {
	private String RNUM;
	private String RECEIPT_ID;
	private String RECEIPT_DAY;
	private String FLAG_SEND;
	private String FLAG_BROD;
	private String FLAG_IMPORTANT;
	private String FLAG_DISASTOR;
	private String FLAG_MAP;
	private String RECEIPT_TIME;
	private String REPORT_TYPE;
	private String REPORT_TYPE2;
	private String INDIVIDUAL_ID;
	private String INDIVIDUAL_NAME;
	private String TYPE_ID; //구분(통신원타입)
	private String TYPE_NAME; //구분(통신원타입)
	private String RECEPTION_NAME;
	private String MEMO;
	private String REPORTER_TYPE; //제보처
	private String REGION_ID; //교통방송
	
	
	
	public String getFLAG_DISASTOR() {
		return FLAG_DISASTOR;
	}
	public void setFLAG_DISASTOR(String fLAG_DISASTOR) {
		FLAG_DISASTOR = fLAG_DISASTOR;
	}
	public String getTYPE_ID() {
		return TYPE_ID;
	}
	public void setTYPE_ID(String tYPE_ID) {
		TYPE_ID = tYPE_ID;
	}
	public String getINDIVIDUAL_ID() {
		return INDIVIDUAL_ID;
	}
	public void setINDIVIDUAL_ID(String iNDIVIDUAL_ID) {
		INDIVIDUAL_ID = iNDIVIDUAL_ID;
	}
	public String getRNUM() {
		return RNUM;
	}
	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}
	public String getRECEIPT_ID() {
		return RECEIPT_ID;
	}
	public void setRECEIPT_ID(String rECEIPT_ID) {
		RECEIPT_ID = rECEIPT_ID;
	}
	public String getRECEIPT_DAY() {
		return RECEIPT_DAY;
	}
	public void setRECEIPT_DAY(String rECEIPT_DAY) {
		RECEIPT_DAY = rECEIPT_DAY;
	}
	public String getFLAG_SEND() {
		return FLAG_SEND;
	}
	public void setFLAG_SEND(String fLAG_SEND) {
		FLAG_SEND = fLAG_SEND;
	}
	public String getFLAG_BROD() {
		return FLAG_BROD;
	}
	public void setFLAG_BROD(String fLAG_BROD) {
		FLAG_BROD = fLAG_BROD;
	}
	public String getFLAG_IMPORTANT() {
		return FLAG_IMPORTANT;
	}
	public void setFLAG_IMPORTANT(String fLAG_IMPORTANT) {
		FLAG_IMPORTANT = fLAG_IMPORTANT;
	}
	public String getFLAG_MAP() {
		return FLAG_MAP;
	}
	public void setFLAG_MAP(String fLAG_MAP) {
		FLAG_MAP = fLAG_MAP;
	}
	public String getRECEIPT_TIME() {
		return RECEIPT_TIME;
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
	public String getREPORT_TYPE2() {
		return REPORT_TYPE2;
	}
	public void setREPORT_TYPE2(String rEPORT_TYPE2) {
		REPORT_TYPE2 = rEPORT_TYPE2;
	}
	public String getINDIVIDUAL_NAME() {
		return INDIVIDUAL_NAME;
	}
	public void setINDIVIDUAL_NAME(String iNDIVIDUAL_NAME) {
		INDIVIDUAL_NAME = iNDIVIDUAL_NAME;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String tYPE_NAME) {
		TYPE_NAME = tYPE_NAME;
	}
	public String getRECEPTION_NAME() {
		return RECEPTION_NAME;
	}
	public void setRECEPTION_NAME(String rECEPTION_NAME) {
		RECEPTION_NAME = rECEPTION_NAME;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public String getREPORTER_TYPE() {
		return REPORTER_TYPE;
	}
	public void setREPORTER_TYPE(String rEPORTER_TYPE) {
		REPORTER_TYPE = rEPORTER_TYPE;
	}
	public String getREGION_ID() {
		return REGION_ID;
	}
	public void setREGION_ID(String rEGION_ID) {
		REGION_ID = rEGION_ID;
	}
	@Override
	public String toString() {
		return "EditVO [RNUM=" + RNUM + ", RECEIPT_ID=" + RECEIPT_ID + ", RECEIPT_DAY=" + RECEIPT_DAY + ", FLAG_SEND="
				+ FLAG_SEND + ", FLAG_BROD=" + FLAG_BROD + ", FLAG_IMPORTANT=" + FLAG_IMPORTANT + ", FLAG_MAP="
				+ FLAG_MAP + ", RECEIPT_TIME=" + RECEIPT_TIME + ", REPORT_TYPE=" + REPORT_TYPE + ", REPORT_TYPE2="
				+ REPORT_TYPE2 + ", INDIVIDUAL_ID=" + INDIVIDUAL_ID + ", INDIVIDUAL_NAME=" + INDIVIDUAL_NAME
				+ ", TYPE_NAME=" + TYPE_NAME + ", RECEPTION_NAME=" + RECEPTION_NAME + ", MEMO=" + MEMO
				+ ", REPORTER_TYPE=" + REPORTER_TYPE + ", REGION_ID=" + REGION_ID + "]";
	}
	
}
