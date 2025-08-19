package kr.co.wizbrain.tbn.mileage.vo;

public class MileageVO {
	private String standardDate;  // 검색 조건 - 년월
	private String stdt;  // 시작일
	private String edt;  // 종료일
	private String searchAreaCode; // 검색 조건 - 지역
	private String startDate;
	private String endDate;
	
	private String ACT_ID;
	private String INFORMER_ID;
	private String INFORMER_NAME;
	private String PHONE_CELL;
	private String REG_DATE;
	private String ORG_NAME;
	private String AREA_CODE;
	private String AREA_NAME;

	// 건수 X / 마일리지 적립 점수 O
	private int BEFORE_POINT;  // 전월 마일리지 점수
	private int DISASTOR_POINT; // 재난 제보 마일리지 점수
	private int RECEIPT_POINT; // 당월 마일리지 점수
	private int VIDEO_POINT; // 사진, 영상 제보 마일리지 점수
	private int ALL_POINT; // 총점
	private int RANKING; // 순위
	
	public String getStdt() {
		return stdt;
	}
	public void setStdt(String stdt) {
		this.stdt = stdt;
	}
	public String getEdt() {
		return edt;
	}
	public void setEdt(String edt) {
		this.edt = edt;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public String getStandardDate() {
		return standardDate;
	}
	public void setStandardDate(String standardDate) {
		this.standardDate = standardDate;
	}
	public String getSearchAreaCode() {
		return searchAreaCode;
	}
	public void setSearchAreaCode(String searchAreaCode) {
		this.searchAreaCode = searchAreaCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getACT_ID() {
		return ACT_ID;
	}
	public void setACT_ID(String aCT_ID) {
		ACT_ID = aCT_ID;
	}
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
	public String getPHONE_CELL() {
		return PHONE_CELL;
	}
	public void setPHONE_CELL(String pHONE_CELL) {
		PHONE_CELL = pHONE_CELL;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}
	public int getBEFORE_POINT() {
		return BEFORE_POINT;
	}
	public void setBEFORE_POINT(int bEFORE_POINT) {
		BEFORE_POINT = bEFORE_POINT;
	}
	public int getDISASTOR_POINT() {
		return DISASTOR_POINT;
	}
	public void setDISASTOR_POINT(int dISASTOR_POINT) {
		DISASTOR_POINT = dISASTOR_POINT;
	}
	public int getRECEIPT_POINT() {
		return RECEIPT_POINT;
	}
	public void setRECEIPT_POINT(int rECEIPT_POINT) {
		RECEIPT_POINT = rECEIPT_POINT;
	}
	public int getVIDEO_POINT() {
		return VIDEO_POINT;
	}
	public void setVIDEO_POINT(int vIDEO_POINT) {
		VIDEO_POINT = vIDEO_POINT;
	}
	public int getALL_POINT() {
		return ALL_POINT;
	}
	public void setALL_POINT(int aLL_POINT) {
		ALL_POINT = aLL_POINT;
	}
	public int getRANKING() {
		return RANKING;
	}
	public void setRANKING(int rANKING) {
		RANKING = rANKING;
	}
	
	
	
	
	

}
