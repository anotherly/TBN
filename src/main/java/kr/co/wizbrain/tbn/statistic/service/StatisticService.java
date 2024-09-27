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

public interface StatisticService {
	//월별 제보자별 총 제보건수 집계
	public List<StatisticVO> getReceiptCntList(StatisticVO stvo);
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보자별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeAll(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보자별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeOurOther(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 제공대장 - 제보자별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List informerTypeData(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보수단별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeAll(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보수단별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeOurOther(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 제공대장 - 제보수단별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportmeanTypeData(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보유형별 전체건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeAll(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 수집건수 및 활용실적 - 제보유형별 자국/타국
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeOurOther(ParamsDto params) throws Exception;
	
	/** 표준화통계: 교통정보 제공대장 - 제보유형별 현황
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List reportTypeData(ParamsDto params) throws Exception ;

	public List selectInfrm();

	public List selectRtt();

	public List selectRpt();
	
	public List selectArea();
	
	/** 표준화통계: 월별 전체 수집건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List monthReceipt(ParamsDto params);
	
	/** 표준화통계: 월별 자국 수집건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List monthOurReceipt(ParamsDto params);
	/** 표준화통계: 월별 타국 수집건수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List monthOtherReceipt(ParamsDto params) throws Exception ;
	
	/** 표준화통계: 월별 제보자 수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List monthInformer(ParamsDto params) throws Exception ;
	
	/** 표준화통계: 월별 1건이상 제보자 수
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List monthInformer1Inform(ParamsDto params) throws Exception ;

	public List receiptUseDaily(ParamsDto params);

	public List monthSendYNA07A08(ParamsDto params);

	public List dailyRegionSendYNA07A08(ParamsDto params);

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
	public List orgOrgSub(ParamsDto params,List chkArr,String orgStartDate, String orgEndDate);

	public List orgType(ParamsDto params);

	public List volunteer(ParamsDto params);

	public List statDateCal(String sDate, String eDate);
}
