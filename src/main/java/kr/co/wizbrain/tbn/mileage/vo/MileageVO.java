package kr.co.wizbrain.tbn.mileage.vo;

public class MileageVO {
	private String INFORMER_ID;
	private String INFORMER_NAME;
	private String ACT_ID;
	private String AREA_CODE;
	private String ORG_NAME;
	private String PHONE_CELL;
	private int MONTH_RPT;
	private String PAYMENT_MILE;  
	private String PAYMENT_DATE;
	private String PAYMENT_YN;
	private int ALL_MILEAGE;
	private int CUM_MILEAGE;
	private String PAYMENT_GIFT;
	private String PAYMENT_GCODE;
	private int MINUS_MILE;
	private String ADDRESS_HOME;
	private String GIFT_NAME;
	private String GCODE;
	private String MINUS_VAL;
	private String GRADE;
	private String GRADE_DATE;
	private String START_DATE;
	private String PAYMENT_TEXT;
	
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public String getINFORMER_NAME() {
		return INFORMER_NAME;
	}
	public void setINFORMER_NAME(String iNFORMER_NAME) {
		INFORMER_NAME = iNFORMER_NAME;
	}
	public String getACT_ID() {
		return ACT_ID;
	}
	public void setACT_ID(String aCT_ID) {
		ACT_ID = aCT_ID;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getPHONE_CELL() {
		return PHONE_CELL;
	}
	public void setPHONE_CELL(String pHONE_CELL) {
		PHONE_CELL = pHONE_CELL;
	}
	public int getMONTH_RPT() {
		return MONTH_RPT;
	}
	public void setMONTH_RPT(int mONTH_RPT) {
		MONTH_RPT = mONTH_RPT;
	}
	public String getPAYMENT_MILE() {
		return PAYMENT_MILE;
	}
	public void setPAYMENT_MILE(String pAYMENT_MILE) {
		PAYMENT_MILE = pAYMENT_MILE;
	}
	public String getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}
	public void setPAYMENT_DATE(String pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}
	public String getPAYMENT_YN() {
		return PAYMENT_YN;
	}
	public void setPAYMENT_YN(String pAYMENT_YN) {
		PAYMENT_YN = pAYMENT_YN;
	}
	public int getALL_MILEAGE() {
		return ALL_MILEAGE;
	}
	public void setALL_MILEAGE(int aLL_MILEAGE) {
		ALL_MILEAGE = aLL_MILEAGE;
	}
	public int getCUM_MILEAGE() {
		return CUM_MILEAGE;
	}
	public void setCUM_MILEAGE(int cUM_MILEAGE) {
		CUM_MILEAGE = cUM_MILEAGE;
	}
	public String getPAYMENT_GIFT() {
		return PAYMENT_GIFT;
	}
	public void setPAYMENT_GIFT(String pAYMENT_GIFT) {
		PAYMENT_GIFT = pAYMENT_GIFT;
	}
	public String getPAYMENT_GCODE() {
		return PAYMENT_GCODE;
	}
	public void setPAYMENT_GCODE(String pAYMENT_GCODE) {
		PAYMENT_GCODE = pAYMENT_GCODE;
	}
	public int getMINUS_MILE() {
		return MINUS_MILE;
	}
	public void setMINUS_MILE(int mINUS_MILE) {
		MINUS_MILE = mINUS_MILE;
	}
	public String getADDRESS_HOME() {
		return ADDRESS_HOME;
	}
	public void setADDRESS_HOME(String aDDRESS_HOME) {
		ADDRESS_HOME = aDDRESS_HOME;
	}
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
	public String getMINUS_VAL() {
		return MINUS_VAL;
	}
	public void setMINUS_VAL(String mINUS_VAL) {
		MINUS_VAL = mINUS_VAL;
	}
	public String getGRADE() {
		return GRADE;
	}
	public void setGRADE(String gRADE) {
		GRADE = gRADE;
	}
	public String getGRADE_DATE() {
		return GRADE_DATE;
	}
	public void setGRADE_DATE(String gRADE_DATE) {
		GRADE_DATE = gRADE_DATE;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getPAYMENT_TEXT() {
		return PAYMENT_TEXT;
	}
	public void setPAYMENT_TEXT(String pAYMENT_TEXT) {
		PAYMENT_TEXT = pAYMENT_TEXT;
	}
	
	@Override
	public String toString() {
		return "MileageVO [INFORMER_ID=" + INFORMER_ID + ", INFORMER_NAME=" + INFORMER_NAME + ", ACT_ID=" + ACT_ID
				+ ", AREA_CODE=" + AREA_CODE + ", ORG_NAME=" + ORG_NAME + ", PHONE_CELL=" + PHONE_CELL + ", MONTH_RPT="
				+ MONTH_RPT + ", PAYMENT_MILE=" + PAYMENT_MILE + ", PAYMENT_DATE=" + PAYMENT_DATE + ", PAYMENT_YN="
				+ PAYMENT_YN + ", ALL_MILEAGE=" + ALL_MILEAGE + ", CUM_MILEAGE=" + CUM_MILEAGE + ", PAYMENT_GIFT="
				+ PAYMENT_GIFT + ", PAYMENT_GCODE=" + PAYMENT_GCODE + ", MINUS_MILE=" + MINUS_MILE + ", ADDRESS_HOME="
				+ ADDRESS_HOME + ", GIFT_NAME=" + GIFT_NAME + ", GCODE=" + GCODE + ", MINUS_VAL=" + MINUS_VAL
				+ ", GRADE=" + GRADE + ", GRADE_DATE=" + GRADE_DATE + ", START_DATE=" + START_DATE + ", PAYMENT_TEXT="
				+ PAYMENT_TEXT + "]";
	}
	
	
}
