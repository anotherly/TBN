package kr.co.wizbrain.tbn.receipt.vo;

public class InformerVO {
	private String INFORMER_ID;
	private String ACT_ID;
	private String INFORMER_NAME;
	private String PHONE_CELL;
	private String PHONE_HOME;
	
	private String INFORMER_TYPE;
	private String ADDRESS_HOME;
	private String ZIPCODE;
	private String FLAG_BLACKLIST;
	private String MEMO;
	
	private String REG_DATE;
	private String DEL_DATE;
	private String UPD_DATE;
	private String AREA_CODE;
	private String AREA_NAME;
	private String ORG_ID;
	
	private String ORG_SUB_ID;
	private String TYPE_NAME;
	private String ORG_NAME;
	private String ORG_SNAME;
	
	private int STAT1;
	private int STAT2;
	private int STAT3;
	
	public InformerVO() {}
	public InformerVO(String iNFORMER_NAME, String pHONE_CELL, String iNFORMER_TYPE, String aREA_CODE) {
		INFORMER_NAME = iNFORMER_NAME;
		PHONE_CELL = pHONE_CELL;
		INFORMER_TYPE = iNFORMER_TYPE;
		AREA_CODE = aREA_CODE;
	}
	public InformerVO(String iNFORMER_ID, String aCT_ID, String iNFORMER_NAME, String pHONE_CELL, String pHONE_HOME,
			String iNFORMER_TYPE, String aDDRESS_HOME, String zIPCODE, String fLAG_BLACKLIST, String mEMO,
			String rEG_DATE, String dEL_DATE, String uPD_DATE, String aREA_CODE, String oRG_ID, String oRG_SUB_ID,
			String tYPE_NAME, String oRG_NAME, String oRG_SNAME, int sTAT1, int sTAT2, int sTAT3) {
		INFORMER_ID = iNFORMER_ID;
		ACT_ID = aCT_ID;
		INFORMER_NAME = iNFORMER_NAME;
		PHONE_CELL = pHONE_CELL;
		PHONE_HOME = pHONE_HOME;
		INFORMER_TYPE = iNFORMER_TYPE;
		ADDRESS_HOME = aDDRESS_HOME;
		ZIPCODE = zIPCODE;
		FLAG_BLACKLIST = fLAG_BLACKLIST;
		MEMO = mEMO;
		REG_DATE = rEG_DATE;
		DEL_DATE = dEL_DATE;
		UPD_DATE = uPD_DATE;
		AREA_CODE = aREA_CODE;
		ORG_ID = oRG_ID;
		ORG_SUB_ID = oRG_SUB_ID;
		TYPE_NAME = tYPE_NAME;
		ORG_NAME = oRG_NAME;
		ORG_SNAME = oRG_SNAME;
		STAT1 = sTAT1;
		STAT2 = sTAT2;
		STAT3 = sTAT3;
	}
	
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public String getACT_ID() {
		return ACT_ID;
	}
	public void setACT_ID(String aCT_ID) {
		ACT_ID = aCT_ID;
	}
	public String getINFORMER_NAME() {
		return INFORMER_NAME;
	}
	public void setINFORMER_NAME(String iNFORMER_NAME) {
		INFORMER_NAME = iNFORMER_NAME;
	}
	public String getPHONE_CELL() {
		return PHONE_CELL;
	}
	public void setPHONE_CELL(String pHONE_CELL) {
		PHONE_CELL = pHONE_CELL;
	}
	public String getPHONE_HOME() {
		return PHONE_HOME;
	}
	public void setPHONE_HOME(String pHONE_HOME) {
		PHONE_HOME = pHONE_HOME;
	}
	public String getINFORMER_TYPE() {
		return INFORMER_TYPE;
	}
	public void setINFORMER_TYPE(String iNFORMER_TYPE) {
		INFORMER_TYPE = iNFORMER_TYPE;
	}
	public String getADDRESS_HOME() {
		return ADDRESS_HOME;
	}
	public void setADDRESS_HOME(String aDDRESS_HOME) {
		ADDRESS_HOME = aDDRESS_HOME;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getFLAG_BLACKLIST() {
		return FLAG_BLACKLIST;
	}
	public void setFLAG_BLACKLIST(String fLAG_BLACKLIST) {
		FLAG_BLACKLIST = fLAG_BLACKLIST;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getDEL_DATE() {
		return DEL_DATE;
	}
	public void setDEL_DATE(String dEL_DATE) {
		DEL_DATE = dEL_DATE;
	}
	public String getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(String uPD_DATE) {
		UPD_DATE = uPD_DATE;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRG_ID) {
		ORG_ID = oRG_ID;
	}
	public String getORG_SUB_ID() {
		return ORG_SUB_ID;
	}
	public void setORG_SUB_ID(String oRG_SUB_ID) {
		ORG_SUB_ID = oRG_SUB_ID;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String tYPE_NAME) {
		TYPE_NAME = tYPE_NAME;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getORG_SNAME() {
		return ORG_SNAME;
	}
	public void setORG_SNAME(String oRG_SNAME) {
		ORG_SNAME = oRG_SNAME;
	}
	public int getSTAT1() {
		return STAT1;
	}
	public void setSTAT1(int sTAT1) {
		STAT1 = sTAT1;
	}
	public int getSTAT2() {
		return STAT2;
	}
	public void setSTAT2(int sTAT2) {
		STAT2 = sTAT2;
	}
	public int getSTAT3() {
		return STAT3;
	}
	public void setSTAT3(int sTAT3) {
		STAT3 = sTAT3;
	}

	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	
	@Override
	public String toString() {
		return "InformerVO [INFORMER_ID=" + INFORMER_ID + ", ACT_ID=" + ACT_ID + ", INFORMER_NAME=" + INFORMER_NAME
				+ ", PHONE_CELL=" + PHONE_CELL + ", PHONE_HOME=" + PHONE_HOME + ", INFORMER_TYPE=" + INFORMER_TYPE
				+ ", ADDRESS_HOME=" + ADDRESS_HOME + ", ZIPCODE=" + ZIPCODE + ", FLAG_BLACKLIST=" + FLAG_BLACKLIST
				+ ", MEMO=" + MEMO + ", REG_DATE=" + REG_DATE + ", DEL_DATE=" + DEL_DATE + ", UPD_DATE=" + UPD_DATE
				+ ", AREA_CODE=" + AREA_CODE + ", ORG_ID=" + ORG_ID + ", ORG_SUB_ID=" + ORG_SUB_ID + ", TYPE_NAME="
				+ TYPE_NAME + ", ORG_NAME=" + ORG_NAME + ", ORG_SNAME=" + ORG_SNAME + ", STAT1=" + STAT1 + ", STAT2="
				+ STAT2 + ", STAT3=" + STAT3 + "]\n";
	}
	
}
