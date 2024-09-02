package kr.co.wizbrain.tbn.receipt.vo;

public class PersonalMemoVO {
	private String MEMO_ID;
	private String USER_ID;
	private String CONTENT;
	private String DATE_UPDATE;
	
	public PersonalMemoVO() {}
	
	public PersonalMemoVO(String mEMO_ID, String uSER_ID, String cONTENT, String dATE_UPDATE) {
		MEMO_ID = mEMO_ID;
		USER_ID = uSER_ID;
		CONTENT = cONTENT;
		DATE_UPDATE = dATE_UPDATE;
	}
	
	public String getMEMO_ID() {
		return MEMO_ID;
	}
	public void setMEMO_ID(String mEMO_ID) {
		MEMO_ID = mEMO_ID;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getDATE_UPDATE() {
		return DATE_UPDATE;
	}
	public void setDATE_UPDATE(String dATE_UPDATE) {
		DATE_UPDATE = dATE_UPDATE;
	}
	
	@Override
	public String toString() {
		return "PersonalMemoVO [MEMO_ID=" + MEMO_ID + ", USER_ID=" + USER_ID + ", CONTENT=" + CONTENT + ", DATE_UPDATE="
				+ DATE_UPDATE + "]\n";
	}
	
}
