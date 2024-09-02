package kr.co.wizbrain.tbn.receipt.vo;

public class ArteryVO {
	private String NUM;
	private String ARTERY_ID;
	private String ARTERY_NAME;
	private String AREA_CODE;
	private String SEQ_NO;
	private String L_NODE_NAME;
	private String R_NODE_NAME;
	private String NODELINK_ID;
	private String ordered;
	
	public String getNUM() {
		return NUM;
	}
	public void setNUM(String nUM) {
		NUM = nUM;
	}
	public String getARTERY_ID() {
		return ARTERY_ID;
	}
	public void setARTERY_ID(String aRTERY_ID) {
		ARTERY_ID = aRTERY_ID;
	}
	public String getARTERY_NAME() {
		return ARTERY_NAME;
	}
	public void setARTERY_NAME(String aRTERY_NAME) {
		ARTERY_NAME = aRTERY_NAME;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getSEQ_NO() {
		return SEQ_NO;
	}
	public void setSEQ_NO(String sEQ_NO) {
		SEQ_NO = sEQ_NO;
	}
	public String getL_NODE_NAME() {
		return L_NODE_NAME;
	}
	public void setL_NODE_NAME(String l_NODE_NAME) {
		L_NODE_NAME = l_NODE_NAME;
	}
	public String getR_NODE_NAME() {
		return R_NODE_NAME;
	}
	public void setR_NODE_NAME(String r_NODE_NAME) {
		R_NODE_NAME = r_NODE_NAME;
	}
	public String getNODELINK_ID() {
		return NODELINK_ID;
	}
	public void setNODELINK_ID(String nODELINK_ID) {
		NODELINK_ID = nODELINK_ID;
	}
	public String getOrdered() {
		return ordered;
	}
	public void setOrdered(String ordered) {
		this.ordered = ordered;
	}
	@Override
	public String toString() {
		return "ArteryVO [NUM=" + NUM + ", ARTERY_ID=" + ARTERY_ID + ", ARTERY_NAME=" + ARTERY_NAME + ", AREA_CODE="
				+ AREA_CODE + ", SEQ_NO=" + SEQ_NO + ", L_NODE_NAME=" + L_NODE_NAME + ", R_NODE_NAME=" + R_NODE_NAME
				+ ", NODELINK_ID=" + NODELINK_ID + ", ordered=" + ordered + "]";
	}
	
}
