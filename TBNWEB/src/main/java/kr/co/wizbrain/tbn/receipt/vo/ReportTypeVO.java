package kr.co.wizbrain.tbn.receipt.vo;

public class ReportTypeVO {
	private String BAS_LCOD;
	private String BAS_SCOD;
	private String BAS_NAME;
	private String FONT;
	private String BACKGROUND;
	private String BAS_TEMP;
	
	public ReportTypeVO() {}
	public ReportTypeVO(String bAS_LCOD, String bAS_SCOD, String bAS_NAME, String fONT, String bACKGROUND,
			String bAS_TEMP) {
		BAS_LCOD = bAS_LCOD;
		BAS_SCOD = bAS_SCOD;
		BAS_NAME = bAS_NAME;
		FONT = fONT;
		BACKGROUND = bACKGROUND;
		BAS_TEMP = bAS_TEMP;
	}

	public String getBAS_LCOD() {
		return BAS_LCOD;
	}
	public void setBAS_LCOD(String bAS_LCOD) {
		BAS_LCOD = bAS_LCOD;
	}
	public String getBAS_SCOD() {
		return BAS_SCOD;
	}
	public void setBAS_SCOD(String bAS_SCOD) {
		BAS_SCOD = bAS_SCOD;
	}
	public String getFONT() {
		return FONT;
	}
	public void setFONT(String fONT) {
		FONT = fONT;
	}
	public String getBACKGROUND() {
		return BACKGROUND;
	}
	public void setBACKGROUND(String bACKGROUND) {
		BACKGROUND = bACKGROUND;
	}
	public String getBAS_TEMP() {
		return BAS_TEMP;
	}
	public void setBAS_TEMP(String bAS_TEMP) {
		BAS_TEMP = bAS_TEMP;
	}
	public String getBAS_NAME() {
		return BAS_NAME;
	}
	public void setBAS_NAME(String bAS_NAME) {
		BAS_NAME = bAS_NAME;
	}

	@Override
	public String toString() {
		return "reportTypeVO [BAS_LCOD=" + BAS_LCOD + ", BAS_SCOD=" + BAS_SCOD + ", BAS_NAME=" + BAS_NAME + ", FONT="
				+ FONT + ", BACKGROUND=" + BACKGROUND + ", BAS_TEMP=" + BAS_TEMP + "]\n";
	}
	
}
