package kr.co.wizbrain.tbn.statistic.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.receipt.vo.popup.ReceiptSearchVO;
import kr.co.wizbrain.tbn.statistic.mapper.StatisticMapper;
import kr.co.wizbrain.tbn.statistic.service.StatisticService;
import kr.co.wizbrain.tbn.statistic.vo.StatisticVO;

/**
 * 사용자 서비스 구현 클래스
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

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService{
	
	@Resource(name="statisticMapper")
	private StatisticMapper statisticMapper;

	@Override
	public List<StatisticVO> getReceiptCntList(StatisticVO stvo) {
		return statisticMapper.getReceiptCntList(stvo);
	}
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보자별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeAll(params);
	}
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보자별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeOurOther(params);
	}
	
	/** 표준화통계: 교통정보 제공대장 - 제보자별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeData(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeData(params);
	}
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보수단별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeAll(params);
	}
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보수단별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeOurOther(params);
	}
	
	/** 표준화통계: 교통정보 제공대장 - 제보수단별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeData(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeData(params);
	}
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보유형별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeAll(params);
	}
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보유형별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeOurOther(params);
	}
	
	/** 표준화통계: 교통정보 제공대장 - 제보유형별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeData(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeData(params);
	}
	@Override
	public List selectInfrm() {
		return statisticMapper.selectInfrm();
	}
	@Override
	public List selectRtt() {
		return statisticMapper.selectRtt();
	}
	@Override
	public List selectRpt() {
		return statisticMapper.selectRpt();
	}
	@Override
	public List selectArea() {
		return statisticMapper.selectArea();
	}
	@Override
	public List monthReceipt(ParamsDto params) {
		return statisticMapper.monthReceipt(params);
	}
	@Override
	public List monthOurReceipt(ParamsDto params) {
		return statisticMapper.monthOurReceipt(params);
	}
	@Override
	public List monthOtherReceipt(ParamsDto params) throws Exception {
		return statisticMapper.monthOtherReceipt(params);
	}
	@Override
	public List monthInformer(ParamsDto params) throws Exception {
		return statisticMapper.monthInformer(params);
	}
	@Override
	public List monthInformer1Inform(ParamsDto params) throws Exception {
		return statisticMapper.monthInformer1Inform(params);
	}
	@Override
	public List receiptUseDaily(ParamsDto params) {
		return statisticMapper.receiptUseDaily(params);
	}
	@Override
	public List monthSendYNA07A08(ParamsDto params) {
		return statisticMapper.monthSendYNA07A08(params);
	}
	@Override
	public List dailyRegionSendYNA07A08(ParamsDto params) {
		return statisticMapper.dailyRegionSendYNA07A08(params);
	}
	@Override
	public List korLxInfrm(ParamsDto params) {
		return statisticMapper.korLxInfrm(params);
	}
	@Override
	public List korLxMonCnt(ParamsDto params) {
		return statisticMapper.korLxMonCnt(params);
	}
	@Override
	public List krGasInfrm(ParamsDto params) {
		return statisticMapper.krGasInfrm(params);
	}
	@Override
	public List krGasMonCnt(ParamsDto params) {
		return statisticMapper.krGasMonCnt(params);
	}
	@Override
	public List muJeboList(ParamsDto params) {
		return statisticMapper.muJeboList(params);
	}
	@Override
	public List muJeboList2(ParamsDto params) {
		return statisticMapper.muJeboList2(params);
	}
	@Override
	public List muJeboCnt(ParamsDto params) {
		return statisticMapper.muJeboCnt(params);
	}
	@Override
	public List extrBro(ParamsDto params) {
		return statisticMapper.extrBro(params);
	}
	@Override
	public List disastorStat(ParamsDto params) {
		return statisticMapper.disastorStat(params);
	}
	@Override
	public List informerStats(ParamsDto params) {
		return statisticMapper.informerStats(params);
	}
	@Override
	public List nationalIncident(ParamsDto params) {
		return statisticMapper.nationalIncident(params);
	}
	@Override
	public void statInfrmType(String areaCode, String ifmId1) {
		 statisticMapper.statInfrmType(areaCode,ifmId1);
	}
		
	@Override
	public List<OptInftVo> statRtt() {
		return statisticMapper.statRtt();
	}
	@Override
	public void statRptType(String areaCode, String basScod) {
		 statisticMapper.statRptType(areaCode,basScod);
	}
	@Override
	public void statRttType(String areaCode, String ifmId1) {
		 statisticMapper.statRttType(areaCode,ifmId1);
	}
	
	public void schmonInfrmInsert(){
		statisticMapper.schmonInfrmInsert();
	}
	
	public void schmonInfrmDelete(){
		statisticMapper.schmonInfrmDelete();
	}
	@Override
	public List searchStatusListToday(ReceiptSearchVO searchVO) {
		return statisticMapper.searchStatusListToday(searchVO);
	}
	@Override
	public List searchStatusList(ReceiptSearchVO searchVO) {
		return statisticMapper.searchStatusList(searchVO);
	}
	@Override
	public List dayReceipt(ParamsDto params) {
		return statisticMapper.dayReceipt(params);
	}
	@Override
	public List orgOrgSub(ParamsDto params,List chkArr,String orgStartDate, String orgEndDate) {
		return statisticMapper.orgOrgSub(params,chkArr,orgStartDate,orgEndDate);
	}
	@Override
	public List orgType(ParamsDto params) {
		return statisticMapper.orgType(params);
	}
	
	@Override
	public List volunteer(ParamsDto params) {
		return statisticMapper.volunteer(params);
	}
	@Override
	public List statDateCal(String orgStartDate, String orgEndDate) {
		return statisticMapper.statDateCal(orgStartDate,orgEndDate);
	}
	
}
