package kr.co.wizbrain.tbn.broadcast.vo;

public class BroadMessageVO {
	private String ID;
	private String CONTENT;
	private String FLAG_CHECK;
	private String DATE_CREATED;
	private String DATE_CHECKED;
	
	public BroadMessageVO() {}
	public BroadMessageVO(String iD, String cONTENT, String fLAG_CHECK, String dATE_CREATED, String dATE_CHECKED) {
		ID = iD;
		CONTENT = cONTENT;
		FLAG_CHECK = fLAG_CHECK;
		DATE_CREATED = dATE_CREATED;
		DATE_CHECKED = dATE_CHECKED;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getFLAG_CHECK() {
		return FLAG_CHECK;
	}
	public void setFLAG_CHECK(String fLAG_CHECK) {
		FLAG_CHECK = fLAG_CHECK;
	}
	public String getDATE_CREATED() {
		return DATE_CREATED;
	}
	public void setDATE_CREATED(String dATE_CREATED) {
		DATE_CREATED = dATE_CREATED;
	}
	public String getDATE_CHECKED() {
		return DATE_CHECKED;
	}
	public void setDATE_CHECKED(String dATE_CHECKED) {
		DATE_CHECKED = dATE_CHECKED;
	}
	
	@Override
	public String toString() {
		return "BroadMessageVO [ID=" + ID + ", CONTENT=" + CONTENT + ", FLAG_CHECK=" + FLAG_CHECK + ", DATE_CREATED="
				+ DATE_CREATED + ", DATE_CHECKED=" + DATE_CHECKED + "]\n";
	}
}
