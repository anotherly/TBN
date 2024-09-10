package kr.co.wizbrain.tbn.receipt.vo;

public class InformerTypeVO {
	private String TYPE_ID;
	private String TYPE_NAME;
	
	public InformerTypeVO() {}
	public InformerTypeVO(String tYPE_ID, String tYPE_NAME) {
		TYPE_ID = tYPE_ID;
		TYPE_NAME = tYPE_NAME;
	}
	public String getTYPE_ID() {
		return TYPE_ID;
	}
	public void setTYPE_ID(String tYPE_ID) {
		TYPE_ID = tYPE_ID;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String tYPE_NAME) {
		TYPE_NAME = tYPE_NAME;
	}
	
	@Override
	public String toString() {
		return "InformerTypeVO [TYPE_ID=" + TYPE_ID + ", TYPE_NAME=" + TYPE_NAME + "]\n";
	}
	
}
