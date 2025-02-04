package kr.co.wizbrain.tbn.event.vo;

public class EventVO{
	//이벤트내용 
	 public String CONTENTS;
	//이벤트일자 
	 public String EVENT_DATE;
	//이벤트ID 
	 public String EVENT_ID;
	//이벤트명 
	 public String EVENT_NAME;
	//방송국ID 
	 public String REGION_ID;
	 //방송국명 
	 public String REGION_NM;
	//참여자ID 
	 public String INFORMER_ID;
	//등록일 
	 public String REG_DATE;
	 //검색유형
	 public String searchType;
	 //검색 값
	 public String searchValue;
	 //검색 지역
	 public String searchRegion;
	 //검색 통신원 위해촉(활동여부)
	 public String searchActYn;
	// 검색 시작 시간
	public String sDate;
	// 검색 종료 시간
	public String eDate;
	
	
	// 행사 등록 이후 반환되는 필드 (EVNEVT_ID와 같으나, 파일 첨부를 위해 사용)
	public String nowEventId;
	
	// EVENT_FILE_INFO table
	public String FILE_DIR;
	public String FILE_ID;
	public String FILE_NAME;
	public String REG_DT;
	
	
	public String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCONTENTS() {
		return CONTENTS;
	}
	public void setCONTENTS(String cONTENTS) {
		CONTENTS = cONTENTS;
	}
	public String getEVENT_DATE() {
		return EVENT_DATE;
	}
	public void setEVENT_DATE(String eVENT_DATE) {
		EVENT_DATE = eVENT_DATE;
	}
	public String getEVENT_ID() {
		return EVENT_ID;
	}
	public void setEVENT_ID(String eVENT_ID) {
		EVENT_ID = eVENT_ID;
	}
	public String getEVENT_NAME() {
		return EVENT_NAME;
	}
	public void setEVENT_NAME(String eVENT_NAME) {
		EVENT_NAME = eVENT_NAME;
	}
	public String getREGION_ID() {
		return REGION_ID;
	}
	public void setREGION_ID(String rEGION_ID) {
		REGION_ID = rEGION_ID;
	}
	public String getREGION_NM() {
		return REGION_NM;
	}
	public void setREGION_NM(String rEGION_NM) {
		REGION_NM = rEGION_NM;
	}
	public String getINFORMER_ID() {
		return INFORMER_ID;
	}
	public void setINFORMER_ID(String iNFORMER_ID) {
		INFORMER_ID = iNFORMER_ID;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchRegion() {
		return searchRegion;
	}
	public void setSearchRegion(String searchRegion) {
		this.searchRegion = searchRegion;
	}
	public String getSearchActYn() {
		return searchActYn;
	}
	public void setSearchActYn(String searchActYn) {
		this.searchActYn = searchActYn;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getNowEventId() {
		return nowEventId;
	}
	public void setNowEventId(String nowEventId) {
		this.nowEventId = nowEventId;
	}
	public String getFILE_DIR() {
		return FILE_DIR;
	}
	public void setFILE_DIR(String fILE_DIR) {
		FILE_DIR = fILE_DIR;
	}
	public String getFILE_ID() {
		return FILE_ID;
	}
	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	
	@Override
	public String toString() {
		return "EventVO [CONTENTS=" + CONTENTS + ", EVENT_DATE=" + EVENT_DATE + ", EVENT_ID=" + EVENT_ID
				+ ", EVENT_NAME=" + EVENT_NAME + ", REGION_ID=" + REGION_ID + ", REGION_NM=" + REGION_NM
				+ ", INFORMER_ID=" + INFORMER_ID + ", REG_DATE=" + REG_DATE + ", searchType=" + searchType
				+ ", searchValue=" + searchValue + ", searchRegion=" + searchRegion + ", searchActYn=" + searchActYn
				+ ", sDate=" + sDate + ", eDate=" + eDate + ", nowEventId=" + nowEventId + ", FILE_DIR=" + FILE_DIR
				+ ", FILE_ID=" + FILE_ID + ", FILE_NAME=" + FILE_NAME + ", REG_DT=" + REG_DT + "]";
	}
	
	
}