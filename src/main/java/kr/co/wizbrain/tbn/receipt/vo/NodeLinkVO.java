package kr.co.wizbrain.tbn.receipt.vo;

public class NodeLinkVO {
	private String NODELINK_ID;
	private String ARTERY_ID;
	private String SEQ_NO;
	private String L_NODE_NAME;
	private String R_NODE_NAME;
	
	public NodeLinkVO() {}
	public NodeLinkVO(String nODELINK_ID, String aRTERY_ID, String sEQ_NO, String l_NODE_NAME, String r_NODE_NAME) {
		NODELINK_ID = nODELINK_ID;
		ARTERY_ID = aRTERY_ID;
		SEQ_NO = sEQ_NO;
		L_NODE_NAME = l_NODE_NAME;
		R_NODE_NAME = r_NODE_NAME;
	}
	
	public String getNODELINK_ID() {
		return NODELINK_ID;
	}
	public void setNODELINK_ID(String nODELINK_ID) {
		NODELINK_ID = nODELINK_ID;
	}
	public String getARTERY_ID() {
		return ARTERY_ID;
	}
	public void setARTERY_ID(String aRTERY_ID) {
		ARTERY_ID = aRTERY_ID;
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
	
	@Override
	public String toString() {
		return "NodeLinkVO [NODELINK_ID=" + NODELINK_ID + ", ARTERY_ID=" + ARTERY_ID + ", SEQ_NO=" + SEQ_NO
				+ ", L_NODE_NAME=" + L_NODE_NAME + ", R_NODE_NAME=" + R_NODE_NAME + "]\n";
	}
	
}
