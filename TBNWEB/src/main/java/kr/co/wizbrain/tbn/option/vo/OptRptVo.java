package kr.co.wizbrain.tbn.option.vo;

public class OptRptVo {
	//상위코드 
	 public String basLcod;
	//코드 
	 public String basScod;
	//코드명 
	 public String basName;
	//폰트색 
	 public String fColor;
	//폰트색이름 
	 public String fName;
	//배경색 
	 public String bColor;
	//배경색이름 
	 public String bName;
	//대중소구분 
	 public String cdFlag;
	 
	public String getBasLcod() {
		return basLcod;
	}
	public void setBasLcod(String basLcod) {
		this.basLcod = basLcod;
	}
	public String getBasScod() {
		return basScod;
	}
	public void setBasScod(String basScod) {
		this.basScod = basScod;
	}
	public String getBasName() {
		return basName;
	}
	public void setBasName(String basName) {
		this.basName = basName;
	}
	public String getfColor() {
		return fColor;
	}
	public void setfColor(String fColor) {
		this.fColor = fColor;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getbColor() {
		return bColor;
	}
	public void setbColor(String bColor) {
		this.bColor = bColor;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getCdFlag() {
		return cdFlag;
	}
	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}
	@Override
	public String toString() {
		return "OptRptVo [basLcod=" + basLcod + ", basScod=" + basScod + ", basName=" + basName + ", fColor=" + fColor
				+ ", fName=" + fName + ", bColor=" + bColor + ", bName=" + bName + ", cdFlag=" + cdFlag + "]";
	}
}
