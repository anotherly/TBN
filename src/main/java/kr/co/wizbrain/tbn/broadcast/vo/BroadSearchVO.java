package kr.co.wizbrain.tbn.broadcast.vo;

public class BroadSearchVO {
	private String REPORT_TYPE;
	private String REPORT_TYPE2;
	private String RECEIPT_DAY;
	private String AREA_CODE;
	private String AREA_CODE_SUB;
	private String INDIVIDUAL_TYPE;
	private String FLAG_BROD;
	private String CONTENT;
	private String FLAG_IMPORTANT;
	
	//검색결과 페이징용
	private int startRow;
	private int size;
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
	public String getRECEIPT_DAY() {
		return RECEIPT_DAY;
	}
	public void setRECEIPT_DAY(String rECEIPT_DAY) {
		RECEIPT_DAY = rECEIPT_DAY;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getAREA_CODE_SUB() {
		return AREA_CODE_SUB;
	}
	public void setAREA_CODE_SUB(String aREA_CODE_SUB) {
		AREA_CODE_SUB = aREA_CODE_SUB;
	}
	public String getINDIVIDUAL_TYPE() {
		return INDIVIDUAL_TYPE;
	}
	public void setINDIVIDUAL_TYPE(String iNDIVIDUAL_TYPE) {
		INDIVIDUAL_TYPE = iNDIVIDUAL_TYPE;
	}
	public String getFLAG_BROD() {
		return FLAG_BROD;
	}
	public void setFLAG_BROD(String fLAG_BROD) {
		FLAG_BROD = fLAG_BROD;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getFLAG_IMPORTANT() {
		return FLAG_IMPORTANT;
	}
	public void setFLAG_IMPORTANT(String fLAG_IMPORTANT) {
		FLAG_IMPORTANT = fLAG_IMPORTANT;
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
		return "BroadSearchVO [REPORT_TYPE=" + REPORT_TYPE + ", REPORT_TYPE2=" + REPORT_TYPE2 + ", RECEIPT_DAY="
				+ RECEIPT_DAY + ", AREA_CODE=" + AREA_CODE + ", AREA_CODE_SUB=" + AREA_CODE_SUB + ", INDIVIDUAL_TYPE="
				+ INDIVIDUAL_TYPE + ", FLAG_BROD=" + FLAG_BROD + ", CONTENT=" + CONTENT + ", FLAG_IMPORTANT="
				+ FLAG_IMPORTANT + ", startRow=" + startRow + ", size=" + size + "]";
	}

}
