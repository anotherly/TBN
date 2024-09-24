package kr.co.wizbrain.tbn.statistic.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.receipt.vo.popup.ReceiptSearchVO;
import kr.co.wizbrain.tbn.statistic.vo.StatisticVO;

/**
 * 사용자 매퍼 클래스
 * @author 미래전략사업팀 정다빈
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2020.07.23  정다빈           최초 생성
 */

@Mapper("statisticMapper")
public interface StatisticMapper{
	//월별 제보자별 총 제보건수 집계
	public List<StatisticVO> getReceiptCntList(StatisticVO stvo);

	public List reportmeanTypeAll(ParamsDto params);

	public List reportmeanTypeOurOther(ParamsDto params);

	public List reportmeanTypeData(ParamsDto params);

	public List reportTypeAll(ParamsDto params);

	public List reportTypeOurOther(ParamsDto params);

	public List reportTypeData(ParamsDto params);

	public List informerTypeAll(ParamsDto params);

	public List informerTypeOurOther(ParamsDto params);

	public List informerTypeData(ParamsDto params);
	
	//<!-- 표준화통계: 제보자별 일별 상세 -->
	public List receiptUseDaily(ParamsDto params);
	
	//<!-- 표준화통계: 소속별 제보자 현황 -->
	public List preMonthRegionsub(ParamsDto params);
	
	public List thisMonthRegionsub(ParamsDto params);

	//<!-- 표준화통계: 유형별 제보자 현황 -->
	public List preMonthInformerType(ParamsDto params);		

	public List thisMonthInformerType(ParamsDto params);

	//<!-- 표준화통계: 인근지역 제보자 현황 -->
	public List preMonthArea(ParamsDto params);

	public List thisMonthArea(ParamsDto params);
	
	//<!-- 표준화통계: 월별 전체 수집건수 -->
	public List monthReceipt(ParamsDto params);
	
	//<!-- 표준화통계:월별 방송자료(전송)/자체처리(비전송)/안내/기관통보 -->
	public List monthSendYNA07A08(ParamsDto params);
	
	//표준화통계:일별 방송자료(전송)/자체처리(비전송)/안내/기관통보
	public List dailySendYNA07A08(ParamsDto params);
	
	//표준화통계:일별 방송국별 방송자료(전송)/자체처리(비전송)/안내/기관통보 
	public List dailyRegionSendYNA07A08(ParamsDto params);
	
	//<!-- 전국통계: 돌발정보 표출실적 --> 
	public List nationalIncident(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 수집건수 -->
	public List receiptAll(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 일별 수집건수 -->
	public List dailyReceiptAll(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 방송건수 -->
	public List receiptBroad(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 일별 방송건수 -->
	public List dailyReceiptBroad(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 전송건수 -->
	public List receiptSend(ParamsDto params);
	
	//<!-- 전국통계: 방송국별 일별 전송건수 -->
	public List dailyReceiptSend(ParamsDto params);

	public List selectInfrm();

	public List selectRtt();

	public List selectRpt();
	
	public List selectArea();

	public List monthOurReceipt(ParamsDto params);

	public List monthOtherReceipt(ParamsDto params);

	public List monthInformer(ParamsDto params);

	public List monthInformer1Inform(ParamsDto params);

	public List korLxInfrm(ParamsDto params);

	public List korLxMonCnt(ParamsDto params);

	public List krGasInfrm(ParamsDto params);

	public List krGasMonCnt(ParamsDto params);

	public List muJeboList(ParamsDto params);
	
	public List muJeboList2(ParamsDto params);

	public List muJeboCnt(ParamsDto params);

	public List extrBro(ParamsDto params);
	
	public List disastorStat(ParamsDto params);

	public List informerStats(ParamsDto params);

	public void statInfrmType(@Param("areaCode")String areaCode, @Param("ifmId1")String ifmId1);

	public List<OptInftVo> statRtt();

	public void statRptType(@Param("areaCode")String areaCode, @Param("basScod")String basScod);

	public void statRttType(@Param("areaCode")String areaCode, @Param("ifmId1")String ifmId1);

	public void schmonInfrmDelete();
	
	public void schmonInfrmInsert();

	public List searchStatusListToday(ReceiptSearchVO searchVO);
	public List searchStatusList(ReceiptSearchVO searchVO);

	public List dayReceiptInfrm(ParamsDto params);
	public List dayReceiptCnt(ParamsDto params);
	public List dayReceipt(ParamsDto params);

	public List orgType(ParamsDto params);
	public List volunteer(ParamsDto params);

	
	public List statDateCal(@Param("orgStartDate")String orgStartDate,  @Param("orgEndDate")String orgEndDate);

	public List orgOrgSub(@Param("params")ParamsDto params,@Param("chkArr")List chkArr,@Param("orgStartDate")String orgStartDate,  @Param("orgEndDate")String orgEndDate);

	
}


