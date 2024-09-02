package kr.co.wizbrain.tbn.receipt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.receipt.mapper.ReceiptMapper;
import kr.co.wizbrain.tbn.receipt.service.ReceiptService;
import kr.co.wizbrain.tbn.receipt.vo.AreaCodeVO;
import kr.co.wizbrain.tbn.receipt.vo.AreaSubCodeVO;
import kr.co.wizbrain.tbn.receipt.vo.ArteryVO;
import kr.co.wizbrain.tbn.receipt.vo.CriteriaVO;
import kr.co.wizbrain.tbn.receipt.vo.InformerStatVO;
import kr.co.wizbrain.tbn.receipt.vo.InformerTypeVO;
import kr.co.wizbrain.tbn.receipt.vo.InformerVO;
import kr.co.wizbrain.tbn.receipt.vo.MissedCallVO;
import kr.co.wizbrain.tbn.receipt.vo.PersonalMemoVO;
import kr.co.wizbrain.tbn.receipt.vo.PickUpCallVO;
import kr.co.wizbrain.tbn.receipt.vo.ReceiptVO;
import kr.co.wizbrain.tbn.receipt.vo.ReceiveCallVO;
import kr.co.wizbrain.tbn.receipt.vo.ReportMeanVO;
import kr.co.wizbrain.tbn.receipt.vo.ReportTypeVO;
import kr.co.wizbrain.tbn.receipt.vo.TempSaveVo;
import kr.co.wizbrain.tbn.receipt.vo.popup.EditVO;
import kr.co.wizbrain.tbn.receipt.vo.popup.ReceiptSearchVO;
import kr.co.wizbrain.tbn.receipt.vo.popup.ReceivedStatusVO;
import kr.co.wizbrain.tbn.user.vo.UserVO;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService{
	
	@Resource(name="receiptMapper")
	private ReceiptMapper receiptMapper;
	
	//제보접수 등록
	public int insertReceipt(ReceiptVO ReceiptVO) throws Exception {
		return receiptMapper.insertReceipt(ReceiptVO);
	}
	
	@Override
	public List<InformerVO> selectInformerByPhone(String phone_cell) throws Exception {
		return receiptMapper.selectInformerByPhone(phone_cell);
	}

	@Override
	public List<ReportTypeVO> firstReportType() throws Exception {
		return receiptMapper.firstReportType();
	}

	@Override
	public List<ReportTypeVO> reportTypeOnChange(String bas_lcod) throws Exception {
		return receiptMapper.reportTypeOnChange(bas_lcod);
	}

	@Override
	public List<ReportMeanVO> selectReportMean() throws Exception {
		return receiptMapper.selectReportMean();
	}

	@Override
	public List<InformerVO> searchInformerByName(String informer_name) throws Exception {
		return receiptMapper.searchInformerByName(informer_name);
	}

	@Override
	public int saveInformerMemo(InformerVO vo) throws Exception {
		return receiptMapper.saveInformerMemo(vo);
	}

	@Override
	public List<ReceiveCallVO> selectPickupCall(ReceiveCallVO vo) throws Exception {
		return receiptMapper.selectPickupCall(vo);
	}
	
	@Override
	public List<MissedCallVO> selectMissedCall(String AREA_CODE) throws Exception {
		return receiptMapper.selectMissedCall(AREA_CODE);
	}

	@Override
	public int insertMissedCall(MissedCallVO vo) throws Exception {
		return receiptMapper.insertMissedCall(vo);
	}

	@Override
	public List<InformerVO> searchInformerByPhone(String phone_cell) throws Exception {
		return receiptMapper.searchInformerByPhone(phone_cell);
	}

	@Override
	public List<InformerVO> searchInformerByCode(String act_id) throws Exception {
		return receiptMapper.searchInformerByCode(act_id);
	}

	@Override
	public List<AreaCodeVO> selectAreaCode() throws Exception {
		return receiptMapper.selectAreaCode();
	}

	@Override
	public List<AreaSubCodeVO> selectAreaCodeSub(String area_code) throws Exception {
		return receiptMapper.selectAreaCodeSub(area_code);
	}

	@Override
	public List<ArteryVO> selectArtery(ArteryVO vo) throws Exception {
		return receiptMapper.selectArtery(vo);
	}

	@Override
	public List<ArteryVO> selectNodeLink(ArteryVO vo) throws Exception {
		return receiptMapper.selectNodeLink(vo);
	}

	@Override
	public List<InformerTypeVO> selectInformerType() throws Exception {
		return receiptMapper.selectInformerType();
	}

	@Override
	public List<ReceivedStatusVO> searchStatusList(ReceiptSearchVO vo) throws Exception {
		return receiptMapper.searchStatusList(vo);
	}

	@Override
	public int countReceivedStatusList(CriteriaVO cri) throws Exception {
		return receiptMapper.countReceivedStatusList(cri);
	}

	@Override
	public List<ReceivedStatusVO> receivedStatusList(CriteriaVO cri) throws Exception {
		return receiptMapper.receivedStatusList(cri);
	}

	@Override
	public int countSearchStatusList(ReceiptSearchVO vo) throws Exception {
		return receiptMapper.countSearchStatusList(vo);
	}

	@Override
	public EditVO selectEditVO(String RECEIPT_ID) throws Exception {
		return receiptMapper.selectEditVO(RECEIPT_ID);
	}

	@Override
	public int updateReceipt(EditVO vo) throws Exception {
		return receiptMapper.updateReceipt(vo);
	}

	@Override
	public ReceivedStatusVO showEditResult(String RECEIPT_ID) throws Exception {
		return receiptMapper.showEditResult(RECEIPT_ID);
	}

	@Override
	public InformerVO selectInformerByID(String INFORMER_ID) throws Exception {
		return receiptMapper.selectInformerByID(INFORMER_ID);
	}

	@Override
	public int updateFlagSTT(String MISSED_CALL_ID) throws Exception {
		return receiptMapper.updateFlagSTT(MISSED_CALL_ID);
	}

	@Override
	public int updatePersonalMemo(UserVO vo) throws Exception {
		return receiptMapper.updatePersonalMemo(vo);
	}

	@Override
	public UserVO selectPersonalMemo(UserVO vo) throws Exception {
		return receiptMapper.selectPersonalMemo(vo);
	}

	@Override
	public Integer getMonthlyTotal(InformerStatVO vo) throws Exception {
		return receiptMapper.getMonthlyTotal(vo);
	}

	@Override
	public int updateMonthlyStat(ReceiptVO vo) throws Exception {
		return receiptMapper.updateMonthlyStat(vo);
	}

	@Override
	public MissedCallVO getMissedCallInfoById(MissedCallVO vo) throws Exception {
		return receiptMapper.getMissedCallInfoById(vo);
	}

	@Override
	public List<PickUpCallVO> checkIfPickUpInfoExists(PickUpCallVO vo) throws Exception {
		return receiptMapper.checkIfPickUpInfoExists(vo);
	}

	@Override
	public int deletePickUpInfo(PickUpCallVO vo) throws Exception {
		return receiptMapper.deletePickUpInfo(vo);
	}
	//임시저장
	@Override
	public void tempSave(TempSaveVo vo) {
		System.out.println(vo.getRECEPTION_NAME());
		System.out.println(vo.getRECEPTION_ID());
		receiptMapper.tempSave(vo);
	}

	@Override
	public List<TempSaveVo> tempsaveList(TempSaveVo vo) {
		return receiptMapper.tempsaveList(vo);
	}

	@Override
	public TempSaveVo tempsavePush(TempSaveVo vo) {
		return receiptMapper.tempsavePush(vo);
	}
	
	//수신목록 테이블에 추가
	@Override
	public void insertReceiveCall(PickUpCallVO vo){
		receiptMapper.insertReceiveCall(vo);
	}

	/*
	 * 전체접수용 테이블 분할 관련 230118
	 * */
	
	@Override
	public int countReceivedStatusListFR(CriteriaVO cri) throws Exception {
		return receiptMapper.countReceivedStatusListFR(cri);
	}

	@Override
	public List<ReceivedStatusVO> receivedStatusListFR(CriteriaVO cri) throws Exception {
		return receiptMapper.receivedStatusListFR(cri);
	}

	@Override
	public int countSearchStatusListFR(ReceiptSearchVO vo) throws Exception {
		return receiptMapper.countSearchStatusListFR(vo);
	}

	@Override
	public List<ReceivedStatusVO> searchStatusListFR(ReceiptSearchVO vo) throws Exception {
		return receiptMapper.searchStatusListFR(vo);
	}

	@Override
	public void tempSaveDelete(List<String> chkArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",chkArr);
		receiptMapper.tempSaveDelete(map);
	}
}




