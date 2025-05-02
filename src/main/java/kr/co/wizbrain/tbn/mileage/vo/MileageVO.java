package kr.co.wizbrain.tbn.mileage.vo;

public class MileageVO {
	private String standardDate;  // 검색 조건 - 년월
	private String searchAreaCode; // 검색 조건 - 지역
	
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
	

	
}
