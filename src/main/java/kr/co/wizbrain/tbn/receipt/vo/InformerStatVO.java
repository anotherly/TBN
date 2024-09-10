package kr.co.wizbrain.tbn.receipt.vo;

public class InformerStatVO {
	private String INFORMER_ID;
	private int MON_CNT;
	private String DATE;
	
	public InformerStatVO() {}
	public InformerStatVO(String iNFORMER_ID, int mON_CNT, String dATE) {
		INFORMER_ID = iNFORMER_ID;
		MON_CNT = mON_CNT;
		DATE = dATE;
	}
	public InformerStatVO(String iNFORMER_ID, String dATE) {
		INFORMER_ID = iNFORMER_ID;
		DATE = dATE;
	}
	
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public int getMON_CNT() {
		return MON_CNT;
	}
	public void setMON_CNT(int mON_CNT) {
		MON_CNT = mON_CNT;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	
	@Override
	public String toString() {
		return "InformerStatVO [INFORMER_ID=" + INFORMER_ID + ", MON_CNT=" + MON_CNT + ", DATE=" + DATE + "]\n";
	}
	
}
