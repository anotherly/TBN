package kr.co.wizbrain.tbn.option.vo;

public class OptAreaVo {
	//도시(방송국)코드 
	public String areaCode;
	//도시(방송국) 이름 
	public String areaName;
	//인근도시(방송국)코드 
	public String areaSubCode;
	//인근도시(방송국) 이름 
	public String areaSubName;
	//x좌표(위도)
	public String xCoor;
	//y좌표(경도)
	public String yCoor;
	//도로별 영문구분자
	public String areaDiv;
	//통신원별 영문구분자
	public String infrmDiv;
	//지역방송국 대표번호
	public String rpTel;
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
	public String getAreaSubCode() {
		return areaSubCode;
	}
	public void setAreaSubCode(String areaSubCode) {
		this.areaSubCode = areaSubCode;
	}
	public String getAreaSubName() {
		return areaSubName;
	}
	public void setAreaSubName(String areaSubName) {
		this.areaSubName = areaSubName;
	}
	public String getxCoor() {
		return xCoor;
	}
	public void setxCoor(String xCoor) {
		this.xCoor = xCoor;
	}
	public String getyCoor() {
		return yCoor;
	}
	public void setyCoor(String yCoor) {
		this.yCoor = yCoor;
	}
	public String getAreaDiv() {
		return areaDiv;
	}
	public void setAreaDiv(String areaDiv) {
		this.areaDiv = areaDiv;
	}
	public String getInfrmDiv() {
		return infrmDiv;
	}
	public void setInfrmDiv(String infrmDiv) {
		this.infrmDiv = infrmDiv;
	}
	public String getRpTel() {
		return rpTel;
	}
	public void setRpTel(String rpTel) {
		this.rpTel = rpTel;
	}
	@Override
	public String toString() {
		return "OptAreaVo [areaCode=" + areaCode + ", areaName=" + areaName + ", areaSubCode=" + areaSubCode
				+ ", areaSubName=" + areaSubName + ", xCoor=" + xCoor + ", yCoor=" + yCoor + ", areaDiv=" + areaDiv
				+ ", infrmDiv=" + infrmDiv + ", rpTel=" + rpTel + "]";
	}
}
