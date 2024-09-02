package kr.co.wizbrain.tbn.receipt.vo;

public class ReportFirstVO {
	private String REPORT_FIRST_ID;
	private String REPORT_FIRST_NAME;
	private String REPORT_FIRST_CODE;
	
	public ReportFirstVO() {}
	
	public ReportFirstVO(String rEPORT_FIRST_ID, String rEPORT_FIRST_NAME, String rEPORT_FIRST_CODE) {
		REPORT_FIRST_ID = rEPORT_FIRST_ID;
		REPORT_FIRST_NAME = rEPORT_FIRST_NAME;
		REPORT_FIRST_CODE = rEPORT_FIRST_CODE;
	}
	
	public String getREPORT_FIRST_ID() {
		return REPORT_FIRST_ID;
	}
	public void setREPORT_FIRST_ID(String rEPORT_FIRST_ID) {
		REPORT_FIRST_ID = rEPORT_FIRST_ID;
	}
	public String getREPORT_FIRST_NAME() {
		return REPORT_FIRST_NAME;
	}
	public void setREPORT_FIRST_NAME(String rEPORT_FIRST_NAME) {
		REPORT_FIRST_NAME = rEPORT_FIRST_NAME;
	}
	public String getREPORT_FIRST_CODE() {
		return REPORT_FIRST_CODE;
	}
	public void setREPORT_FIRST_CODE(String rEPORT_FIRST_CODE) {
		REPORT_FIRST_CODE = rEPORT_FIRST_CODE;
	}
	
	@Override
	public String toString() {
		return "ReportFirstVO [REPORT_FIRST_ID=" + REPORT_FIRST_ID + ", REPORT_FIRST_NAME=" + REPORT_FIRST_NAME
				+ ", REPORT_FIRST_CODE=" + REPORT_FIRST_CODE + "]\n";
	}
	
}
