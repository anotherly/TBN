package kr.co.wizbrain.tbn.option.vo;

public class IntelVO {
	//고유id
	public String itId;
	//지역코드
	public String areaCode;
	//지역명
	public String areaName;
	//내선번호
	public String inTel;
	//방송국대표번호
	public String rpTel;
	//제보접수 전화번호
	public String rptTel;
	public String getItId() {
		return itId;
	}
	public void setItId(String itId) {
		this.itId = itId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getInTel() {
		return inTel;
	}
	public void setInTel(String inTel) {
		this.inTel = inTel;
	}
	public String getRpTel() {
		return rpTel;
	}
	public void setRpTel(String rpTel) {
		this.rpTel = rpTel;
	}
	public String getRptTel() {
		return rptTel;
	}
	public void setRptTel(String rptTel) {
		this.rptTel = rptTel;
	}
	@Override
	public String toString() {
		return "IntelVO [itId=" + itId + ", areaCode=" + areaCode + ", areaName=" + areaName + ", inTel=" + inTel
				+ ", rpTel=" + rpTel + ", rptTel=" + rptTel + "]";
	}
	
}
