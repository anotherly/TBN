package kr.co.wizbrain.tbn.statistic.vo;

public class StatisticVO {
	//통신원 id
	public String indId;
	//1~12월
	public String monthVar;
	//해당 년월
	public String stateDt;
	//해당 건수
	public String stateCnt;
	//제보유형 id
	public String rptTypeId;
	
	
	
	public String getIndId() {
		return indId;
	}

	public void setIndId(String indId) {
		this.indId = indId;
	}

	public String getMonthVar() {
		return monthVar;
	}

	public void setMonthVar(String monthVar) {
		this.monthVar = monthVar;
	}

	public String getStateDt() {
		return stateDt;
	}

	public void setStateDt(String stateDt) {
		this.stateDt = stateDt;
	}

	public String getStateCnt() {
		return stateCnt;
	}

	public void setStateCnt(String stateCnt) {
		this.stateCnt = stateCnt;
	}

	public String getRptTypeId() {
		return rptTypeId;
	}

	public void setRptTypeId(String rptTypeId) {
		this.rptTypeId = rptTypeId;
	}

	@Override
	public String toString() {
		return "StatisticVO [indId=" + indId + ", monthVar=" + monthVar + ", stateDt=" + stateDt + ", stateCnt="
				+ stateCnt + ", rptTypeId=" + rptTypeId + "]";
	}
}
