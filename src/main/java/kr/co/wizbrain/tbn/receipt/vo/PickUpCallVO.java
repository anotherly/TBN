package kr.co.wizbrain.tbn.receipt.vo;

public class PickUpCallVO {
	private String PICKUP_CALL_ID;
	private String PICKUP_CALL_TIME;
	private String PICKUP_CALL_TO;
	private String PICKUP_CALL_INTEL;
	private String PICKUP_CALL_FROM;
	private String AREA_CODE;
	private String PICKUP_CALL_DAY;
	
	public PickUpCallVO() {}
	
	public PickUpCallVO(String pICKUP_CALL_ID, String pICKUP_CALL_TO, String pICKUP_CALL_INTEL, String pICKUP_CALL_FROM,
			String aREA_CODE) {
		PICKUP_CALL_ID = pICKUP_CALL_ID;
		PICKUP_CALL_TO = pICKUP_CALL_TO;
		PICKUP_CALL_INTEL = pICKUP_CALL_INTEL;
		PICKUP_CALL_FROM = pICKUP_CALL_FROM;
		AREA_CODE = aREA_CODE;
	}

	public PickUpCallVO(String pICKUP_CALL_ID, String pICKUP_CALL_TIME, String pICKUP_CALL_TO, String pICKUP_CALL_INTEL,
			String pICKUP_CALL_FROM, String aREA_CODE, String pICKUP_CALL_DAY) {
		PICKUP_CALL_ID = pICKUP_CALL_ID;
		PICKUP_CALL_TIME = pICKUP_CALL_TIME;
		PICKUP_CALL_TO = pICKUP_CALL_TO;
		PICKUP_CALL_INTEL = pICKUP_CALL_INTEL;
		PICKUP_CALL_FROM = pICKUP_CALL_FROM;
		AREA_CODE = aREA_CODE;
		PICKUP_CALL_DAY = pICKUP_CALL_DAY;
	}

	public String getPICKUP_CALL_ID() {
		return PICKUP_CALL_ID;
	}
	public void setPICKUP_CALL_ID(String pICKUP_CALL_ID) {
		PICKUP_CALL_ID = pICKUP_CALL_ID;
	}
	public String getPICKUP_CALL_TIME() {
		return PICKUP_CALL_TIME;
	}
	public void setPICKUP_CALL_TIME(String pICKUP_CALL_TIME) {
		PICKUP_CALL_TIME = pICKUP_CALL_TIME;
	}
	public String getPICKUP_CALL_TO() {
		return PICKUP_CALL_TO;
	}
	public void setPICKUP_CALL_TO(String pICKUP_CALL_TO) {
		PICKUP_CALL_TO = pICKUP_CALL_TO;
	}
	public String getPICKUP_CALL_INTEL() {
		return PICKUP_CALL_INTEL;
	}
	public void setPICKUP_CALL_INTEL(String pICKUP_CALL_INTEL) {
		PICKUP_CALL_INTEL = pICKUP_CALL_INTEL;
	}
	public String getPICKUP_CALL_FROM() {
		return PICKUP_CALL_FROM;
	}
	public void setPICKUP_CALL_FROM(String pICKUP_CALL_FROM) {
		PICKUP_CALL_FROM = pICKUP_CALL_FROM;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getPICKUP_CALL_DAY() {
		return PICKUP_CALL_DAY;
	}
	public void setPICKUP_CALL_DAY(String pICKUP_CALL_DAY) {
		PICKUP_CALL_DAY = pICKUP_CALL_DAY;
	}
	
	@Override
	public String toString() {
		return "PickUpCallVO [PICKUP_CALL_ID=" + PICKUP_CALL_ID + ", PICKUP_CALL_TIME=" + PICKUP_CALL_TIME
				+ ", PICKUP_CALL_TO=" + PICKUP_CALL_TO + ", PICKUP_CALL_INTEL=" + PICKUP_CALL_INTEL
				+ ", PICKUP_CALL_FROM=" + PICKUP_CALL_FROM + ", AREA_CODE=" + AREA_CODE + ", PICKUP_CALL_DAY="
				+ PICKUP_CALL_DAY + "]\n";
	}
	
}
