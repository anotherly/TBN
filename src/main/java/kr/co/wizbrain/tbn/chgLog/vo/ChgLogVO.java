package kr.co.wizbrain.tbn.chgLog.vo;

public class ChgLogVO{
	public String USER_ID;
	public String CG_TIME;
	public String CG_FUNC;
	public String CG_TYPE;
	public String CG_TGT;
	public String CG_VAL;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getCG_TIME() {
		return CG_TIME;
	}
	public void setCG_TIME(String cG_TIME) {
		CG_TIME = cG_TIME;
	}
	public String getCG_FUNC() {
		return CG_FUNC;
	}
	public void setCG_FUNC(String cG_FUNC) {
		CG_FUNC = cG_FUNC;
	}
	public String getCG_TYPE() {
		return CG_TYPE;
	}
	public void setCG_TYPE(String cG_TYPE) {
		CG_TYPE = cG_TYPE;
	}
	public String getCG_TGT() {
		return CG_TGT;
	}
	public void setCG_TGT(String cG_TGT) {
		CG_TGT = cG_TGT;
	}
	public String getCG_VAL() {
		return CG_VAL;
	}
	public void setCG_VAL(String cG_VAL) {
		CG_VAL = cG_VAL;
	}
	@Override
	public String toString() {
		return "ChgLogVO [USER_ID=" + USER_ID + ", CG_TIME=" + CG_TIME + ", CG_FUNC=" + CG_FUNC + ", CG_TYPE=" + CG_TYPE
				+ ", CG_TGT=" + CG_TGT + ", CG_VAL=" + CG_VAL + "]";
	}
	
}