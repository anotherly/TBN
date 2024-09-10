package kr.co.wizbrain.tbn.option.vo;

public class OptInftVo {
	
	//통신원유형코드 대
	public String ifmId1;
	//통신원유형코드 중
	public String ifmId2;
	//통신원유형코드 소
	public String ifmId3;
	//통신원유형 이름
	public String ifmName;
	//설명
	public String cmt;
	//지역코드
	public String areaCode;
	
	public String getIfmId1() {
		return ifmId1;
	}
	public void setIfmId1(String ifmId1) {
		this.ifmId1 = ifmId1;
	}
	public String getIfmId2() {
		return ifmId2;
	}
	public void setIfmId2(String ifmId2) {
		this.ifmId2 = ifmId2;
	}
	public String getIfmId3() {
		return ifmId3;
	}
	public void setIfmId3(String ifmId3) {
		this.ifmId3 = ifmId3;
	}
	public String getIfmName() {
		return ifmName;
	}
	public void setIfmName(String ifmName) {
		this.ifmName = ifmName;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Override
	public String toString() {
		return "OptInftVo [ifmId1=" + ifmId1 + ", ifmId2=" + ifmId2 + ", ifmId3=" + ifmId3 + ", ifmName=" + ifmName
				+ ", cmt=" + cmt + ", areaCode=" + areaCode + "]";
	}
	
}
