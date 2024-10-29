package kr.co.wizbrain.tbn.statistic.service;

import java.util.List;


import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.receipt.vo.popup.ReceiptSearchVO;
import kr.co.wizbrain.tbn.statistic.vo.StatisticVO;

/**
 * 사용자 서비스 클래스
 * @author  정다빈
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

public interface StatisticService {
	//월별 제보자별 총 제보건수 집계
	public List<StatisticVO> getReceiptCntList(StatisticVO stvo);
	//전체 방송국 표출
	public List selectArea();
	
	// 1. 교통정보 제공대장
	//1.1 - 통신원 유형별 현황 sheet
	//1.1.0  통신원 유형 title
	public List selectInfrm();
	//1.1.1 , 5.1.1  통신원 별 전체건수
	public List informerTypeAll(ParamsDto params) throws Exception;
	//1.1.2 , 5.1.2 통신원별 자국/타국 전체건수
	public List informerTypeOurOther(ParamsDto params) throws Exception;
	//1.1.3 통신원 유형별 상세 데이터
	public List informerTypeData(ParamsDto params) throws Exception;
	
	//1.2 - 제보수단별 현황 sheet
	//1.2.0 - 제보수단 title
	public List selectRtt();
	//1.2.1 제보수단별 전체건수
	public List reportmeanTypeAll(ParamsDto params) throws Exception;
	//1.2.2 제보수단별 자국/타국 건수
	public List reportmeanTypeOurOther(ParamsDto params) throws Exception;
	//1.2.3 제보수단별 상세 데이터
	public List reportmeanTypeData(ParamsDto params) throws Exception;
	
	//1.3 - 제보유형별 현황 sheet
	//1.3.0 - 제보유형별 title
	public List selectRpt();
	//1.3.1 제보유형별 전체건수
	public List reportTypeAll(ParamsDto params) throws Exception;
	//1.3.2 제보유형별 자국/타국 건수	
	public List reportTypeOurOther(ParamsDto params) throws Exception;
	//1.3.3 제보유형별 상세 데이터
	public List reportTypeData(ParamsDto params) throws Exception ;
	
	//2. 긴급교통정보_방송현황분석
	public List extrBro(ParamsDto params);
	//3. 재난 제보건수
	public List disastorStat(ParamsDto params);
	//4. 월별 제보자별 제보건수
	//4.1 제보자 리스트
	public List monInfrmList(ParamsDto params);
	//4.2 월별 건수
	public List monInfrmCnt(ParamsDto params);
	//5. 무 제보자 현황
	public List muJeboList(ParamsDto params);
	
	public List monthReceipt(ParamsDto params);
	
	public List monthOurReceipt(ParamsDto params);
	
	public List monthOtherReceipt(ParamsDto params) throws Exception ;
	
	public List monthInformer(ParamsDto params) throws Exception ;
	
	public List monthInformer1Inform(ParamsDto params) throws Exception ;

	public List receiptUseDaily(ParamsDto params);

	public List monthSendYNA07A08(ParamsDto params);

	public List dailyRegionSendYNA07A08(ParamsDto params);

	public List korLxInfrm(ParamsDto params);

	public List korLxMonCnt(ParamsDto params);

	public List krGasInfrm(ParamsDto params);

	public List krGasMonCnt(ParamsDto params);

	public List informerStats(ParamsDto params);

	public List nationalIncident(ParamsDto params);
	//인서트 통신원 통계
	public void statInfrmType(String areaCode, String ifmId1);

	public List<OptInftVo> statRtt();

	public void statRptType(String areaCode, String basScod);

	public void statRttType(String areaCode, String ifmId1);
	
	public void schmonInfrmInsert();
	
	public void schmonInfrmDelete();

	public List searchStatusListToday(ReceiptSearchVO searchVO);
	public List searchStatusList(ReceiptSearchVO searchVO);
	
	public List dayReceipt(ParamsDto params);
	
	public List statDateCal(ParamsDto params);
	public List orgOrgSub(ParamsDto params);

	public List orgType(ParamsDto params);

	public List volunteer(ParamsDto params);

}
