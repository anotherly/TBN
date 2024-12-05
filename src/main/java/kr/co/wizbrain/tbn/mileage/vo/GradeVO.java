package kr.co.wizbrain.tbn.mileage.vo;

public class GradeVO {
	private String GRADE;
	private int CUM_MILEAGE;
	
	public String getGRADE() {
		return GRADE;
	}
	public void setGRADE(String gRADE) {
		GRADE = gRADE;
	}
	public int getCUM_MILEAGE() {
		return CUM_MILEAGE;
	}
	public void setCUM_MILEAGE(int cUM_MILEAGE) {
		CUM_MILEAGE = cUM_MILEAGE;
	}
	
	
	@Override
	public String toString() {
		return "GradeVO [GRADE=" + GRADE + ", CUM_MILEAGE=" + CUM_MILEAGE + "]";
	}
	
	
}
