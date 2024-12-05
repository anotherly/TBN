package kr.co.wizbrain.tbn.mileage.vo;

public class GiftVO {
	private String GIFT_NAME;
	private String GCODE;
	private int MINUS_VAL;
	
	public String getGIFT_NAME() {
		return GIFT_NAME;
	}
	public void setGIFT_NAME(String gIFT_NAME) {
		GIFT_NAME = gIFT_NAME;
	}
	public String getGCODE() {
		return GCODE;
	}
	public void setGCODE(String gCODE) {
		GCODE = gCODE;
	}
	public int getMINUS_VAL() {
		return MINUS_VAL;
	}
	public void setMINUS_VAL(int mINUS_VAL) {
		MINUS_VAL = mINUS_VAL;
	}
	
	
	@Override
	public String toString() {
		return "GiftVO [GIFT_NAME=" + GIFT_NAME + ", GCODE=" + GCODE + ", MINUS_VAL=" + MINUS_VAL + "]";
	}
	
	
}
