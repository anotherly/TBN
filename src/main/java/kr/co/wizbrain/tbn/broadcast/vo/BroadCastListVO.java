package kr.co.wizbrain.tbn.broadcast.vo;

public class BroadCastListVO {
	private String RNUM;
	private String RECEIPT_ID;
	private String R_TEL;
	private String FLAG_BROD;
	private String RECEIPT_TIME;
	private String BROAD_TIME;
	private String BAS_NAME;
	private String AREA_NAME;
	private String INDIVIDUAL_NAME;
	private String TYPE_NAME;
	private String MEMO;
	private String RECEIPT_DAY;
	private String FLAG_IMPORTANT;
	private String f_COLOR;
	private String b_COLOR;
	private String FLAG_KNEX;
	
	public String getFLAG_KNEX() {
		return FLAG_KNEX;
	}
	public void setFLAG_KNEX(String fLAG_KNEX) {
		FLAG_KNEX = fLAG_KNEX;
	}
	public String getRNUM() {
		return RNUM;
	}
	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}
	public String getRECEIPT_ID() {
		return RECEIPT_ID;
	}
	public void setRECEIPT_ID(String rECEIPT_ID) {
		RECEIPT_ID = rECEIPT_ID;
	}
	public String getR_TEL() {
		return R_TEL;
	}
	public void setR_TEL(String r_TEL) {
		R_TEL = r_TEL;
	}
	public String getFLAG_BROD() {
		return FLAG_BROD;
	}
	public void setFLAG_BROD(String fLAG_BROD) {
		FLAG_BROD = fLAG_BROD;
	}
	public String getRECEIPT_TIME() {
		return RECEIPT_TIME;
	}
	public void setRECEIPT_TIME(String rECEIPT_TIME) {
		RECEIPT_TIME = rECEIPT_TIME;
	}
	public String getBROAD_TIME() {
		return BROAD_TIME;
	}
	public void setBROAD_TIME(String bROAD_TIME) {
		BROAD_TIME = bROAD_TIME;
	}
	public String getBAS_NAME() {
		return BAS_NAME;
	}
	public void setBAS_NAME(String bAS_NAME) {
		BAS_NAME = bAS_NAME;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public String getINDIVIDUAL_NAME() {
		return INDIVIDUAL_NAME;
	}
	public void setINDIVIDUAL_NAME(String iNDIVIDUAL_NAME) {
		INDIVIDUAL_NAME = iNDIVIDUAL_NAME;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String tYPE_NAME) {
		TYPE_NAME = tYPE_NAME;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public String getRECEIPT_DAY() {
		return RECEIPT_DAY;
	}
	public void setRECEIPT_DAY(String rECEIPT_DAY) {
		RECEIPT_DAY = rECEIPT_DAY;
	}
	public String getFLAG_IMPORTANT() {
		return FLAG_IMPORTANT;
	}
	public void setFLAG_IMPORTANT(String fLAG_IMPORTANT) {
		FLAG_IMPORTANT = fLAG_IMPORTANT;
	}
	public String getF_COLOR() {
		return f_COLOR;
	}
	public void setF_COLOR(String f_COLOR) {
		this.f_COLOR = f_COLOR;
	}
	public String getB_COLOR() {
		return b_COLOR;
	}
	public void setB_COLOR(String b_COLOR) {
		this.b_COLOR = b_COLOR;
	}
	
	@Override
	public String toString() {
		return "BroadCastListVO [RNUM=" + RNUM + ", RECEIPT_ID=" + RECEIPT_ID + ", R_TEL=" + R_TEL + ", FLAG_BROD="
				+ FLAG_BROD + ", RECEIPT_TIME=" + RECEIPT_TIME + ", BROAD_TIME=" + BROAD_TIME + ", BAS_NAME=" + BAS_NAME
				+ ", AREA_NAME=" + AREA_NAME + ", INDIVIDUAL_NAME=" + INDIVIDUAL_NAME + ", TYPE_NAME=" + TYPE_NAME
				+ ", MEMO=" + MEMO + ", RECEIPT_DAY=" + RECEIPT_DAY + ", FLAG_IMPORTANT=" + FLAG_IMPORTANT
				+ ", f_COLOR=" + f_COLOR + ", b_COLOR=" + b_COLOR + "]";
	}
	
}
