package kr.co.wizbrain.tbn.broadcast.vo;

public class BroadcastVO2 {

    // 사용자 id 
    private String BroadcastId;

    // 사용자 비밀번호 
    private String BroadcastPw;

	// 사용자 이름 
    private String BroadcastName;

    // 사용자 직급 
    private String BroadcastRank;

    // 사용자 부서 
    private String BroadcastDept;

    // 사용자 전화번호 
    private String BroadcastPhone;

    // 사용자 이메일 
    private String BroadcastEmail;

    //사용자 레벨
    private String BroadcastLevel;
    
    //계정사용여부
    private String useYn;
    
    // 가입일 
    private String regDt;

	public String getBroadcastId() {
		return BroadcastId;
	}

	public void setBroadcastId(String BroadcastId) {
		this.BroadcastId = BroadcastId;
	}

	public String getBroadcastPw() {
		return BroadcastPw;
	}

	public void setBroadcastPw(String BroadcastPw) {
		this.BroadcastPw = BroadcastPw;
	}

	public String getBroadcastName() {
		return BroadcastName;
	}

	public void setBroadcastName(String BroadcastName) {
		this.BroadcastName = BroadcastName;
	}

	public String getBroadcastRank() {
		return BroadcastRank;
	}

	public void setBroadcastRank(String BroadcastRank) {
		this.BroadcastRank = BroadcastRank;
	}

	public String getBroadcastDept() {
		return BroadcastDept;
	}

	public void setBroadcastDept(String BroadcastDept) {
		this.BroadcastDept = BroadcastDept;
	}

	public String getBroadcastPhone() {
		return BroadcastPhone;
	}

	public void setBroadcastPhone(String BroadcastPhone) {
		this.BroadcastPhone = BroadcastPhone;
	}

	public String getBroadcastEmail() {
		return BroadcastEmail;
	}

	public void setBroadcastEmail(String BroadcastEmail) {
		this.BroadcastEmail = BroadcastEmail;
	}

	public String getBroadcastLevel() {
		return BroadcastLevel;
	}

	public void setBroadcastLevel(String BroadcastLevel) {
		this.BroadcastLevel = BroadcastLevel;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "BroadcastVO [BroadcastId=" + BroadcastId + ", BroadcastPw=" + BroadcastPw + ", BroadcastName=" + BroadcastName + ", BroadcastRank=" + BroadcastRank
				+ ", BroadcastDept=" + BroadcastDept + ", BroadcastPhone=" + BroadcastPhone + ", BroadcastEmail=" + BroadcastEmail + ", BroadcastLevel="
				+ BroadcastLevel + ", useYn=" + useYn + ", regDt=" + regDt + "]";
	}

}
