package kr.co.wizbrain.tbn.option.vo;

public class AuthVo {
	
	//순번
	private String idx;
	//권한코드
	private String authCode;
	//권한 주소
	private String url;
	//권한명
	private String authName;
	//권한주소명
	private String authUrlName;
	//허용여부
	private String useYn;
	//대중소구분 
	public String cdFlag;
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthUrlName() {
		return authUrlName;
	}
	public void setAuthUrlName(String authUrlName) {
		this.authUrlName = authUrlName;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCdFlag() {
		return cdFlag;
	}
	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}
	@Override
	public String toString() {
		return "AuthVo [idx=" + idx + ", authCode=" + authCode + ", url=" + url + ", authName=" + authName
				+ ", authUrlName=" + authUrlName + ", useYn=" + useYn + ", cdFlag=" + cdFlag + "]";
	}
	
}
