package kr.co.wizbrain.tbn.receipt.vo;

public class ReceiveCallVO {
	public String RECEIVED_CALL_ID;
	public String RECEIVED_CALL_TIME;
	public String RECEIVED_CALL_TEL;
	public String INFORMER_NAME;
	public String INTEL_NUM;
	public String AREA_CODE;
	public String INFORMER_ID;
	public String RECEIVED_CALL_DAY;
	public String getRECEIVED_CALL_ID() {
		return RECEIVED_CALL_ID;
	}
	public void setRECEIVED_CALL_ID(String rECEIVED_CALL_ID) {
		RECEIVED_CALL_ID = rECEIVED_CALL_ID;
	}
	public String getRECEIVED_CALL_TIME() {
		return RECEIVED_CALL_TIME;
	}
	public void setRECEIVED_CALL_TIME(String rECEIVED_CALL_TIME) {
		RECEIVED_CALL_TIME = rECEIVED_CALL_TIME;
	}
	public String getRECEIVED_CALL_TEL() {
		return RECEIVED_CALL_TEL;
	}
	public void setRECEIVED_CALL_TEL(String rECEIVED_CALL_TEL) {
		RECEIVED_CALL_TEL = rECEIVED_CALL_TEL;
	}
	public String getINFORMER_NAME() {
		return INFORMER_NAME;
	}
	public void setINFORMER_NAME(String iNFORMER_NAME) {
		INFORMER_NAME = iNFORMER_NAME;
	}
	public String getINTEL_NUM() {
		return INTEL_NUM;
	}
	public void setINTEL_NUM(String iNTEL_NUM) {
		INTEL_NUM = iNTEL_NUM;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public String getRECEIVED_CALL_DAY() {
		return RECEIVED_CALL_DAY;
	}
	public void setRECEIVED_CALL_DAY(String rECEIVED_CALL_DAY) {
		RECEIVED_CALL_DAY = rECEIVED_CALL_DAY;
	}
	@Override
	public String toString() {
		return "ReceiveCallVO [RECEIVED_CALL_ID=" + RECEIVED_CALL_ID + ", RECEIVED_CALL_TIME=" + RECEIVED_CALL_TIME
				+ ", RECEIVED_CALL_TEL=" + RECEIVED_CALL_TEL + ", INFORMER_NAME=" + INFORMER_NAME + ", INTEL_NUM="
				+ INTEL_NUM + ", AREA_CODE=" + AREA_CODE + ", INFORMER_ID=" + INFORMER_ID + ", RECEIVED_CALL_DAY="
				+ RECEIVED_CALL_DAY + "]";
	}
}
