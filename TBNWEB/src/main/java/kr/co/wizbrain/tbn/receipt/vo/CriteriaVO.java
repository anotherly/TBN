package kr.co.wizbrain.tbn.receipt.vo;

public class CriteriaVO {
	private int startRow;
	private int size;
	private String RECEIPT_DAY;
	private String AREA_CODE;
	
	public CriteriaVO() {}
	
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getRECEIPT_DAY() {
		return RECEIPT_DAY;
	}
	public void setRECEIPT_DAY(String rECEIPT_DAY) {
		RECEIPT_DAY = rECEIPT_DAY;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}

	@Override
	public String toString() {
		return "CriteriaVO [startRow=" + startRow + ", size=" + size + ", RECEIPT_DAY=" + RECEIPT_DAY + ", AREA_CODE="
				+ AREA_CODE + "]\n";
	}

}
