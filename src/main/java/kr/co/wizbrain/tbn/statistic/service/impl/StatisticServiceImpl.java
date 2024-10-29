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
 *  2021.11.   정다빈     최초 생성
 *  2024.10.24 정다빈     항목별 index 부여 및 개편
 */

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService{
	
	@Resource(name="statisticMapper")
	private StatisticMapper statisticMapper;

	//월별 제보자별 총 제보건수 집계
	@Override
	public List<StatisticVO> getReceiptCntList(StatisticVO stvo) {
		return statisticMapper.getReceiptCntList(stvo);
	}
	//전체 방송국 표출	
	@Override
	public List selectArea() {
		return statisticMapper.selectArea();
	}
	// 1. 교통정보 제공대장
	//1.1 - 통신원 유형별 현황 sheet
	//1.1.0  통신원 유형 title
	@Override
	public List selectInfrm() {
		return statisticMapper.selectInfrm();
	}
	//1.1.1 , 5.1.1  통신원 별 전체건수
	@Override
	public List informerTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeAll(params);
	}
	//1.1.2 , 5.1.2 통신원별 자국/타국 전체건수
	@Override
	public List informerTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeOurOther(params);
	}
	//1.1.3 통신원 유형별 상세 데이터
	public List informerTypeData(ParamsDto params) throws Exception {
		return statisticMapper.informerTypeData(params);
	}
	
	//1.2 - 제보수단별 현황 sheet
	//1.2.0 - 제보수단 title
	@Override
	public List selectRtt() {
		return statisticMapper.selectRtt();
	}
	//1.2.1 제보수단별 전체건수
	public List reportmeanTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeAll(params);
	}
	//1.2.2 제보수단별 자국/타국 건수
	public List reportmeanTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeOurOther(params);
	}
	//1.2.3 제보수단별 상세 데이터
	public List reportmeanTypeData(ParamsDto params) throws Exception {
		return statisticMapper.reportmeanTypeData(params);
	}
	
	//1.3 - 제보유형별 현황 sheet
	//1.3.0 - 제보유형별 title
	@Override
	public List selectRpt() {
		return statisticMapper.selectRpt();
	}
	//1.3.1 제보유형별 전체건수
	public List reportTypeAll(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeAll(params);
	}
	//1.3.2 제보유형별 자국/타국 건수
	public List reportTypeOurOther(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeOurOther(params);
	}
	//1.3.3 제보유형별 상세 데이터
	public List reportTypeData(ParamsDto params) throws Exception {
		return statisticMapper.reportTypeData(params);
	}

	//2. 긴급교통정보_방송현황분석
	@Override
	public List extrBro(ParamsDto params) {
		return statisticMapper.extrBro(params);
	}
	//3. 재난 제보건수
	@Override
	public List disastorStat(ParamsDto params) {
		return statisticMapper.disastorStat(params);
	}
	//4. 월별 제보자별 제보건수
	//4.1 제보자 리스트
	@Override
	public List monInfrmList(ParamsDto params) {
		return statisticMapper.muJeboList(params);
	}
	//4.2 월별 건수
	@Override
	public List monInfrmCnt(ParamsDto params) {
		return statisticMapper.monInfrmCnt(params);
	}
	//5. 무 제보자 현황
	@Override
	public List muJeboList(ParamsDto params) {
		return statisticMapper.muJeboList(params);
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
	public List orgOrgSub(ParamsDto params) {
		return statisticMapper.orgOrgSub(params);
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
	public List statDateCal(ParamsDto params) {
		return statisticMapper.statDateCal(params);
	}
	
}
