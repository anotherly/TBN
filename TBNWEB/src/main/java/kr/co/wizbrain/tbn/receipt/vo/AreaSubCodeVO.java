package kr.co.wizbrain.tbn.receipt.vo;

public class AreaSubCodeVO {
	private String AREA_SUB_CODE;
	private String AREA_SUB_NAME;
	private String AREA_CODE;
	private String Y_COORDINATE;
	private String X_COORDINATE;
	
	public AreaSubCodeVO() {}
	public AreaSubCodeVO(String aREA_SUB_CODE, String aREA_SUB_NAME, String aREA_CODE, String y_COORDINATE,
			String x_COORDINATE) {
		AREA_SUB_CODE = aREA_SUB_CODE;
		AREA_SUB_NAME = aREA_SUB_NAME;
		AREA_CODE = aREA_CODE;
		Y_COORDINATE = y_COORDINATE;
		X_COORDINATE = x_COORDINATE;
	}
	
	public String getAREA_SUB_CODE() {
		return AREA_SUB_CODE;
	}
	public void setAREA_SUB_CODE(String aREA_SUB_CODE) {
		AREA_SUB_CODE = aREA_SUB_CODE;
	}
	public String getAREA_SUB_NAME() {
		return AREA_SUB_NAME;
	}
	public void setAREA_SUB_NAME(String aREA_SUB_NAME) {
		AREA_SUB_NAME = aREA_SUB_NAME;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getY_COORDINATE() {
		return Y_COORDINATE;
	}
	public void setY_COORDINATE(String y_COORDINATE) {
		Y_COORDINATE = y_COORDINATE;
	}
	public String getX_COORDINATE() {
		return X_COORDINATE;
	}
	public void setX_COORDINATE(String x_COORDINATE) {
		X_COORDINATE = x_COORDINATE;
	}
	
	@Override
	public String toString() {
		return "AreaSubCodeVO [AREA_SUB_CODE=" + AREA_SUB_CODE + ", AREA_SUB_NAME=" + AREA_SUB_NAME + ", AREA_CODE="
				+ AREA_CODE + ", Y_COORDINATE=" + Y_COORDINATE + ", X_COORDINATE=" + X_COORDINATE + "]\n";
	}
	
}
