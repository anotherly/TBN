package kr.co.wizbrain.tbn.receipt.vo.popup;

public class ReceiptSearchVO {
	private String REPORT_TYPE;
	private String REPORT_TYPE2;
	private String REPORTMEAN_TYPE;
	private String FLAG_SEND;
	private String FLAG_BROD;
	private String AREA_CODE;
	private String CONTENT1;
	private String CONTENT2;
	private String START_DAY;
	private String END_DAY;
	private String FLAG_IMPORTANT;
	private String FLAG_DISASTOR;//재난제보
	private String RECEIPT_TIME;
	private String RECEPTION_NAME;
	private String INDIVIDUAL_NAME;
	private String rcptAndOr;
	
	//검색결과 페이징용
	private int startRow;
	private int size;
	
	
	//////////////////////////////////////
	public String getFLAG_DISASTOR() {
		return FLAG_DISASTOR;
	}
	public void setFLAG_DISASTOR(String fLAG_DISASTOR) {
		FLAG_DISASTOR = fLAG_DISASTOR;
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
	public String getREPORTMEAN_TYPE() {
		return REPORTMEAN_TYPE;
	}
	public void setREPORTMEAN_TYPE(String rEPORTMEAN_TYPE) {
		REPORTMEAN_TYPE = rEPORTMEAN_TYPE;
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
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getCONTENT1() {
		return CONTENT1;
	}
	public void setCONTENT1(String cONTENT1) {
		CONTENT1 = cONTENT1;
	}
	public String getCONTENT2() {
		return CONTENT2;
	}
	public void setCONTENT2(String cONTENT2) {
		CONTENT2 = cONTENT2;
	}
	public String getSTART_DAY() {
		return START_DAY;
	}
	public void setSTART_DAY(String sTART_DAY) {
		START_DAY = sTART_DAY;
	}
	public String getEND_DAY() {
		return END_DAY;
	}
	public void setEND_DAY(String eND_DAY) {
		END_DAY = eND_DAY;
	}
	public String getFLAG_IMPORTANT() {
		return FLAG_IMPORTANT;
	}
	public void setFLAG_IMPORTANT(String fLAG_IMPORTANT) {
		FLAG_IMPORTANT = fLAG_IMPORTANT;
	}
	public String getRECEIPT_TIME() {
		return RECEIPT_TIME;
	}
	public void setRECEIPT_TIME(String rECEIPT_TIME) {
		RECEIPT_TIME = rECEIPT_TIME;
	}
	public String getRECEPTION_NAME() {
		return RECEPTION_NAME;
	}
	public void setRECEPTION_NAME(String rECEPTION_NAME) {
		RECEPTION_NAME = rECEPTION_NAME;
	}
	public String getINDIVIDUAL_NAME() {
		return INDIVIDUAL_NAME;
	}
	public void setINDIVIDUAL_NAME(String iNDIVIDUAL_NAME) {
		INDIVIDUAL_NAME = iNDIVIDUAL_NAME;
	}
	public String getRcptAndOr() {
		return rcptAndOr;
	}
	public void setRcptAndOr(String rcptAndOr) {
		this.rcptAndOr = rcptAndOr;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ReceiptSearchVO [REPORT_TYPE=" + REPORT_TYPE + ", REPORT_TYPE2=" + REPORT_TYPE2 + ", REPORTMEAN_TYPE="
				+ REPORTMEAN_TYPE + ", FLAG_SEND=" + FLAG_SEND + ", FLAG_BROD=" + FLAG_BROD + ", AREA_CODE=" + AREA_CODE
				+ ", CONTENT1=" + CONTENT1 + ", CONTENT2=" + CONTENT2 + ", START_DAY=" + START_DAY + ", END_DAY="
				+ END_DAY + ", FLAG_IMPORTANT=" + FLAG_IMPORTANT + ", RECEIPT_TIME=" + RECEIPT_TIME
				+ ", RECEPTION_NAME=" + RECEPTION_NAME + ", INDIVIDUAL_NAME=" + INDIVIDUAL_NAME + ", rcptAndOr="
				+ rcptAndOr + ", startRow=" + startRow + ", size=" + size + "]";
	}

	
	
	
}
