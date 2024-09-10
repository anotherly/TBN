package kr.co.wizbrain.tbn.option.vo;

public class OptColorVo {
	
	public String colorId;
	
	public String colorCode;
	
	public String colorName;

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	@Override
	public String toString() {
		return "OptColorVo [colorId=" + colorId + ", colorCode=" + colorCode + ", colorName=" + colorName + "]";
	}
	
}
