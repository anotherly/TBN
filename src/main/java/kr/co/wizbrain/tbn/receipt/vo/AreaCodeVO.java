package kr.co.wizbrain.tbn.receipt.vo;

public class AreaCodeVO {
	private String AREA_CODE;
	private String AREA_NAME;
	
	public AreaCodeVO() {}
	public AreaCodeVO(String aREA_CODE, String aREA_NAME) {
		AREA_CODE = aREA_CODE;
		AREA_NAME = aREA_NAME;
	}
	
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	
	@Override
	public String toString() {
		return "AreaCodeVO [AREA_CODE=" + AREA_CODE + ", AREA_NAME=" + AREA_NAME + "]\n";
	}
	
}
