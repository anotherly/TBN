package kr.co.wizbrain.tbn.option.vo;

public class OptRoadVo {
	// 지역코드
	public String areaCode;
	// 지역명
	public String areaName;
	// 도로코드
	public String arteryId;
	// 순번
	public String seqNo;
	// 도로명
	public String arteryName;
	// 좌측노드명
	public String lNodeName;
	// 우측노드명
	public String rNodeName;
	// 노드링크 코드
	public String nodelinkId;
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
	public String getArteryId() {
		return arteryId;
	}
	public void setArteryId(String arteryId) {
		this.arteryId = arteryId;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getArteryName() {
		return arteryName;
	}
	public void setArteryName(String arteryName) {
		this.arteryName = arteryName;
	}
	public String getlNodeName() {
		return lNodeName;
	}
	public void setlNodeName(String lNodeName) {
		this.lNodeName = lNodeName;
	}
	public String getrNodeName() {
		return rNodeName;
	}
	public void setrNodeName(String rNodeName) {
		this.rNodeName = rNodeName;
	}
	public String getNodelinkId() {
		return nodelinkId;
	}
	public void setNodelinkId(String nodelinkId) {
		this.nodelinkId = nodelinkId;
	}
	@Override
	public String toString() {
		return "OptRoadVo [areaCode=" + areaCode + ", areaName=" + areaName + ", arteryId=" + arteryId + ", seqNo="
				+ seqNo + ", arteryName=" + arteryName + ", lNodeName=" + lNodeName + ", rNodeName=" + rNodeName
				+ ", nodelinkId=" + nodelinkId + "]";
	}
	
}
