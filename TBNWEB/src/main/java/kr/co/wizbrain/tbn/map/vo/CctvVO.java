package kr.co.wizbrain.tbn.map.vo;

public class CctvVO {
	private String CCTVID;
	private String CCTVNAME;
	private String CENTERNAME;
	private double XCOORD;
	private double YCOORD;
	private String CCTVIP;
	private String MOVIE;
	private String STRMID;
	private String PORT;
	private String CH;
	private String PASSWD;
	private String ID;
	private String KIND;
	
	public CctvVO() {}
	public CctvVO(String cCTVID, String cCTVNAME, String cENTERNAME, double xCOORD, double yCOORD, String cCTVIP,
			String mOVIE, String sTRMID, String pORT, String cH, String pASSWD, String iD, String kIND) {
		CCTVID = cCTVID;
		CCTVNAME = cCTVNAME;
		CENTERNAME = cENTERNAME;
		XCOORD = xCOORD;
		YCOORD = yCOORD;
		CCTVIP = cCTVIP;
		MOVIE = mOVIE;
		STRMID = sTRMID;
		PORT = pORT;
		CH = cH;
		PASSWD = pASSWD;
		ID = iD;
		KIND = kIND;
	}

	public String getCCTVID() {
		return CCTVID;
	}
	public void setCCTVID(String cCTVID) {
		CCTVID = cCTVID;
	}
	public String getCCTVNAME() {
		return CCTVNAME;
	}
	public void setCCTVNAME(String cCTVNAME) {
		CCTVNAME = cCTVNAME;
	}
	public String getCENTERNAME() {
		return CENTERNAME;
	}
	public void setCENTERNAME(String cENTERNAME) {
		CENTERNAME = cENTERNAME;
	}
	public double getXCOORD() {
		return XCOORD;
	}
	public void setXCOORD(double xCOORD) {
		XCOORD = xCOORD;
	}
	public double getYCOORD() {
		return YCOORD;
	}
	public void setYCOORD(double yCOORD) {
		YCOORD = yCOORD;
	}
	public String getCCTVIP() {
		return CCTVIP;
	}
	public void setCCTVIP(String cCTVIP) {
		CCTVIP = cCTVIP;
	}
	public String getMOVIE() {
		return MOVIE;
	}
	public void setMOVIE(String mOVIE) {
		MOVIE = mOVIE;
	}
	public String getSTRMID() {
		return STRMID;
	}
	public void setSTRMID(String sTRMID) {
		STRMID = sTRMID;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getCH() {
		return CH;
	}
	public void setCH(String cH) {
		CH = cH;
	}
	public String getPASSWD() {
		return PASSWD;
	}
	public void setPASSWD(String pASSWD) {
		PASSWD = pASSWD;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getKIND() {
		return KIND;
	}
	public void setKIND(String kIND) {
		KIND = kIND;
	}
	
	@Override
	public String toString() {
		return "CctvVO [CCTVID=" + CCTVID + ", CCTVNAME=" + CCTVNAME + ", CENTERNAME=" + CENTERNAME + ", XCOORD="
				+ XCOORD + ", YCOORD=" + YCOORD + ", CCTVIP=" + CCTVIP + ", MOVIE=" + MOVIE + ", STRMID=" + STRMID
				+ ", PORT=" + PORT + ", CH=" + CH + ", PASSWD=" + PASSWD + ", ID=" + ID + ", KIND=" + KIND + "]\n";
	}
}
