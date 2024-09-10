package kr.co.wizbrain.tbn.map.vo;

public class ImsVO {
	private String incidentId;
	private String incidenteTypeCd;
	private String incidenteSubTypeCd;
	private double locationDataX;
	private double locationDataY;
	private String locationTypeCd;
	private String locationData;
	private String incidenteTrafficCd;
	private String incidenteGradeCd;
	private String important;
	private String addressJibun;
	private String addressJibunCd;
	private String addressNew;
	private String linkId;
	private String incidentRegionCd;
	private String lineLinkId;
	private String incidentTitle;
	private String startDate;
	private String endDate;
	private String lane;
	private String roadName;
	private String sourceCode;
	private String controlType;
	private String updateDate;
	
	public ImsVO() {}
	public ImsVO(String incidentId, String incidenteTypeCd, String incidenteSubTypeCd, double locationDataX,
			double locationDataY, String locationTypeCd, String locationData, String incidenteTrafficCd,
			String incidenteGradeCd, String important, String addressJibun, String addressJibunCd, String addressNew,
			String linkId, String incidentRegionCd, String lineLinkId, String incidentTitle, String startDate,
			String endDate, String lane, String roadName, String sourceCode, String controlType, String updateDate) {
		this.incidentId = incidentId;
		this.incidenteTypeCd = incidenteTypeCd;
		this.incidenteSubTypeCd = incidenteSubTypeCd;
		this.locationDataX = locationDataX;
		this.locationDataY = locationDataY;
		this.locationTypeCd = locationTypeCd;
		this.locationData = locationData;
		this.incidenteTrafficCd = incidenteTrafficCd;
		this.incidenteGradeCd = incidenteGradeCd;
		this.important = important;
		this.addressJibun = addressJibun;
		this.addressJibunCd = addressJibunCd;
		this.addressNew = addressNew;
		this.linkId = linkId;
		this.incidentRegionCd = incidentRegionCd;
		this.lineLinkId = lineLinkId;
		this.incidentTitle = incidentTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lane = lane;
		this.roadName = roadName;
		this.sourceCode = sourceCode;
		this.controlType = controlType;
		this.updateDate = updateDate;
	}
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	public String getIncidenteTypeCd() {
		return incidenteTypeCd;
	}
	public void setIncidenteTypeCd(String incidenteTypeCd) {
		this.incidenteTypeCd = incidenteTypeCd;
	}
	public String getIncidenteSubTypeCd() {
		return incidenteSubTypeCd;
	}
	public void setIncidenteSubTypeCd(String incidenteSubTypeCd) {
		this.incidenteSubTypeCd = incidenteSubTypeCd;
	}
	public double getLocationDataX() {
		return locationDataX;
	}
	public void setLocationDataX(double locationDataX) {
		this.locationDataX = locationDataX;
	}
	public double getLocationDataY() {
		return locationDataY;
	}
	public void setLocationDataY(double locationDataY) {
		this.locationDataY = locationDataY;
	}
	public String getLocationTypeCd() {
		return locationTypeCd;
	}
	public void setLocationTypeCd(String locationTypeCd) {
		this.locationTypeCd = locationTypeCd;
	}
	public String getLocationData() {
		return locationData;
	}
	public void setLocationData(String locationData) {
		this.locationData = locationData;
	}
	public String getIncidenteTrafficCd() {
		return incidenteTrafficCd;
	}
	public void setIncidenteTrafficCd(String incidenteTrafficCd) {
		this.incidenteTrafficCd = incidenteTrafficCd;
	}
	public String getIncidenteGradeCd() {
		return incidenteGradeCd;
	}
	public void setIncidenteGradeCd(String incidenteGradeCd) {
		this.incidenteGradeCd = incidenteGradeCd;
	}
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getAddressJibun() {
		return addressJibun;
	}
	public void setAddressJibun(String addressJibun) {
		this.addressJibun = addressJibun;
	}
	public String getAddressJibunCd() {
		return addressJibunCd;
	}
	public void setAddressJibunCd(String addressJibunCd) {
		this.addressJibunCd = addressJibunCd;
	}
	public String getAddressNew() {
		return addressNew;
	}
	public void setAddressNew(String addressNew) {
		this.addressNew = addressNew;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getIncidentRegionCd() {
		return incidentRegionCd;
	}
	public void setIncidentRegionCd(String incidentRegionCd) {
		this.incidentRegionCd = incidentRegionCd;
	}
	public String getLineLinkId() {
		return lineLinkId;
	}
	public void setLineLinkId(String lineLinkId) {
		this.lineLinkId = lineLinkId;
	}
	public String getIncidentTitle() {
		return incidentTitle;
	}
	public void setIncidentTitle(String incidentTitle) {
		this.incidentTitle = incidentTitle;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "ImsVO [incidentId=" + incidentId + ", incidenteTypeCd=" + incidenteTypeCd + ", incidenteSubTypeCd="
				+ incidenteSubTypeCd + ", locationDataX=" + locationDataX + ", locationDataY=" + locationDataY
				+ ", locationTypeCd=" + locationTypeCd + ", locationData=" + locationData + ", incidenteTrafficCd="
				+ incidenteTrafficCd + ", incidenteGradeCd=" + incidenteGradeCd + ", important=" + important
				+ ", addressJibun=" + addressJibun + ", addressJibunCd=" + addressJibunCd + ", addressNew=" + addressNew
				+ ", linkId=" + linkId + ", incidentRegionCd=" + incidentRegionCd + ", lineLinkId=" + lineLinkId
				+ ", incidentTitle=" + incidentTitle + ", startDate=" + startDate + ", endDate=" + endDate + ", lane="
				+ lane + ", roadName=" + roadName + ", sourceCode=" + sourceCode + ", controlType=" + controlType
				+ ", updateDate=" + updateDate + "]\n";
	}
}
