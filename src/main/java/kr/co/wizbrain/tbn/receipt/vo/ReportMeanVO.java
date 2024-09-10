package kr.co.wizbrain.tbn.receipt.vo;

public class ReportMeanVO {
	private String ID;
	private String NAME;
	
	public ReportMeanVO() {}
	public ReportMeanVO(String iD, String nAME) {
		ID = iD;
		NAME = nAME;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	@Override
	public String toString() {
		return "ReportMeanVO [ID=" + ID + ", NAME=" + NAME + "]\n";
	}
	
}
