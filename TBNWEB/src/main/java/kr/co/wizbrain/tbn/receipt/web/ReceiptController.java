package kr.co.wizbrain.tbn.receipt.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.wizbrain.tbn.cid.TcpAndWebClient;
import kr.co.wizbrain.tbn.comm.ParamsDto;
import kr.co.wizbrain.tbn.comm.RSSParser;
import kr.co.wizbrain.tbn.map.vo.ImsVO;
import kr.co.wizbrain.tbn.receipt.service.ReceiptService;
import kr.co.wizbrain.tbn.receipt.vo.AreaCodeVO;
import kr.co.wizbrain.tbn.receipt.vo.AreaSubCodeVO;
import kr.co.wizbrain.tbn.receipt.vo.ArteryVO;
import kr.co.wizbrain.tbn.receipt.vo.CriteriaVO;
import kr.co.wizbrain.tbn.receipt.vo.InformerStatVO;
import kr.co.wizbrain.tbn.receipt.vo.InformerVO;
import kr.co.wizbrain.tbn.receipt.vo.MissedCallVO;
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

@Controller
public class ReceiptController {
	public static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);
	
	@Resource(name="receiptService")
	private ReceiptService receiptService;
	
	public String url="";
	public boolean isClose = false;
	
	//주소에 맞게 매핑
	@RequestMapping(value="/receipt/*.do")
	public ModelAndView ReceiptUrlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.Receipt 최초 컨트롤러 진입 httpSession : "+httpSession);
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
		
		ModelAndView mv = new ModelAndView();
		
		//selectBox - 제보유형(대)
		List<ReportTypeVO> reportFirstList = receiptService.firstReportType();
		mv.addObject("reportFirstList", reportFirstList);
		
		//selectBox - 지역구분(대)
		List<AreaCodeVO> areaCodeList = receiptService.selectAreaCode();
		mv.addObject("areaCodeList", areaCodeList);
		
		//selectBox - 제보처
		List<ReportMeanVO> reportMeanTypeList = receiptService.selectReportMean();
		mv.addObject("reportMeanTypeList", reportMeanTypeList);
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		mv.setViewName(url);
		return mv;
	}
	
//	@RequestMapping("/receipt/imsFromUtic.do")
//	public ModelAndView imsFromUtic() throws Exception {
//		logger.debug("---------------------imsFromUtic---------------------");
//		ModelAndView mv = new ModelAndView("/receipt/imsFromUtic");
//		
//		RSSParser parser = new RSSParser();
//		List<ImsVO> imsList;
//		
//		imsList = parser.imsParser();
//		mv.addObject("imsListSize", imsList.size());
//		//mv.addObject("imsList", imsList);
//		return mv;
//	}
	
//	@RequestMapping("/receipt/imsFromUticList.do")
//	public ModelAndView imsFromUticList() throws Exception {
//		logger.debug("---------------------imsFromUticList---------------------");
//		ModelAndView mv = new ModelAndView("/receipt/imsFromUticList");
//		
//		RSSParser parser = new RSSParser();
//		List<ImsVO> imsList = parserS.imsParser();
//		mv.addObject("imsList", imsList);
//		
//		return mv;
//	}
	
	@RequestMapping("/receipt/checkIfPickUpInfoExists.ajax")
	public ModelAndView checkIfPickUpInfoExists(PickUpCallVO vo) throws Exception {
		logger.debug("---------------------checkIfPickUpInfoExists---------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<PickUpCallVO> list = receiptService.checkIfPickUpInfoExists(vo);
		
		mv.addObject("data", list);
		return mv;
	}
	
//	@RequestMapping("/receipt/checkIfPickUpInfoExists.ajax")
//	public ModelAndView checkIfPickUpInfoExists(PickUpCallVO vo) throws Exception {
//		logger.debug("---------------------checkIfPickUpInfoExists---------------------");
//		
//		while(true) {
//			if(true) {
//				break;
//			}
//		}
//		
//		ModelAndView mv = new ModelAndView("jsonView");
//		List<PickUpCallVO> list = receiptService.checkIfPickUpInfoExists(vo);
//		
//		mv.addObject("data", list);
//		return mv;
//	}
	
	//전화온 테이블에셔 전화 받았을 시  
	//받은 데이터를 삭제하고(통신원 목록에 해당 데이터를 띄울시) delete
	//그 데이터는 수신목록에 띄워야 하니까 insert -> 23 01 17 cid에서 처리하도록 수정
	@RequestMapping("/receipt/deletePickUpInfo.ajax")
	public ModelAndView deletePickUpInfo(PickUpCallVO vo) throws Exception {
		logger.debug("---------------------deletePickUpInfo---------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		int reulst = receiptService.deletePickUpInfo(vo);
		//receiptService.insertReceiveCall(vo);
		mv.addObject("data", reulst);
		return mv;
	}
	
	@RequestMapping("/receipt/subscribe.do")
	public void subscribe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("---------------------Subscribing---------------------");
		TcpAndWebClient telClient;
		PrintWriter writer;
		
		telClient = new TcpAndWebClient();
		
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		logger.debug("header 생성");
		
		writer = response.getWriter();
		logger.debug("writer생성");
		
		try {
			while(true) {
				String data = telClient.readDataFromTelServer();
				System.out.println("data: " + data);
				writer.write("data: { \"message\" : \"msg : PushEvent from server" + data + "\" }\n\n");
				logger.debug("Success to submit to Fromt: " + data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
			writer.close();
		} finally {
			writer.close();
		}
		
	}
	
//	@RequestMapping("/receipt/test.do")
//	public ModelAndView subscribe(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
//		logger.debug("---------------------Subscribing---------------------");
//		ModelAndView mv = new ModelAndView("/receipt/noResult");
//		
//		response.setContentType("text/event-stream");
//		response.setCharacterEncoding("UTF-8");
//		logger.debug("header 생성");
//		
//		PrintWriter writer = response.getWriter();
//		logger.debug("writer생성");
//		
//		for (int i = 0; i < 2; i++) {
//			writer.write("data: { \"message\" : \"msg : PushEvent from server" + i + "\" }\n\n");
//			logger.debug(i + "번째 데이터전송완료");
//		}
//			
//		writer.close();
//		return mv;
//	}
	//수신전화 목록
	@RequestMapping("/receipt/pickupCallList.do")
	public ModelAndView pickupCallList(HttpServletRequest request,ReceiveCallVO vo) throws Exception {
		//logger.info("------------------pickupCallList진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/pickupCallList");
		
		String AREA_CODE = request.getParameter("AREA_CODE");
		String INTEL_NUM = request.getParameter("INTEL_NUM");
		List<ReceiveCallVO> receivedCallList = receiptService.selectPickupCall(vo);
		mv.addObject("receivedCallList", receivedCallList);
		mv.addObject("receivedCallListSize", receivedCallList.size());

		return mv;
	}
	
	//부재중 전화목록 조회
	@RequestMapping("/receipt/missedCallList.do")
	public ModelAndView missedCallList(HttpServletRequest request) throws Exception {
		//logger.info("------------------missedCallList진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/missedCallList");
		
		String AREA_CODE = request.getParameter("AREA_CODE");
		List<MissedCallVO> missedCallList = receiptService.selectMissedCall(AREA_CODE);
		mv.addObject("missedCallList", missedCallList);
		mv.addObject("missedCallListSize", missedCallList.size());
		
		return mv;
	}
	
	@RequestMapping("/receipt/getNewMissedCallInfo.ajax")
	public ModelAndView getNewMissedCallInfo(HttpServletRequest request) throws Exception {
//		logger.info("------------------getNewMissedCallInfo진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		
//		String AREA_CODE = request.getParameter("AREA_CODE");
//		List<MissedCallVO> missedCallList = receiptService.selectMissedCall(AREA_CODE);
//		mv.addObject("missedCallList", missedCallList);
//		mv.addObject("missedCallListSize", missedCallList.size());

		return mv;
	}
	
	@RequestMapping("/receipt/selectEditVO.do")
	public ModelAndView selectEditVO(String RECEIPT_ID, String RNUM) throws Exception{
		logger.info("------------------selectEditVO진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/editResultList");
		//selectBox - 제보유형(대)
		List<ReportTypeVO> reportFirstList = receiptService.firstReportType();
		mv.addObject("reportFirstList", reportFirstList);
		
		//selectBox - 지역구분(대)
		List<AreaCodeVO> areaCodeList = receiptService.selectAreaCode();
		mv.addObject("areaCodeList", areaCodeList);
		
		//selectBox - 제보처
		List<ReportMeanVO> reportMeanTypeList = receiptService.selectReportMean();
		mv.addObject("reportMeanTypeList", reportMeanTypeList);
		
		EditVO vo = receiptService.selectEditVO(RECEIPT_ID);
		vo.setRNUM(RNUM);
		mv.addObject("editVO", vo);
		
		return mv;
	}
	
	@RequestMapping("/receipt/cancleEditVO.do")
	public ModelAndView cancleEditVO(String RECEIPT_ID, String RNUM) throws Exception{
		logger.info("------------------cancleEditVO진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/showEditResult");
		ReceivedStatusVO vo = receiptService.showEditResult(RECEIPT_ID);
		vo.setRNUM(RNUM);
		mv.addObject("editVO", vo);
		return mv;
	}
	
	@RequestMapping("/receipt/updateReceipt.ajax")
	public ModelAndView updateReceipt(EditVO vo) throws Exception {
		logger.info("------------------selectEditVO진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		
		int result = receiptService.updateReceipt(vo);
		vo.setRNUM(vo.getRNUM());
		System.out.println("EditVO: " + vo.toString());
		
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/receipt/showEditResult.do")
	public ModelAndView showEditResult(String RECEIPT_ID, String RNUM) throws Exception {
		logger.info("------------------showEditResult진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/showEditResult");
		
		ReceivedStatusVO vo = receiptService.showEditResult(RECEIPT_ID);
		vo.setRNUM(RNUM);
		System.out.println("showEditResult: " + vo.toString());
		
		mv.addObject("editVO", vo);
		return mv;
	}
	
	@RequestMapping("/receipt/insertReceipt.do")
	public ModelAndView insertReceipt(ReceiptVO vo) throws Exception {
		logger.info("------------------insertReceipt진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		vo.setMEMO(vo.getMEMO().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
		int result = receiptService.insertReceipt(vo);
		System.out.println("vo: " + vo.toString());
		System.out.println("insertRecipt result: "+result);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/receipt/updateMonthlyStat.ajax")
	public ModelAndView updateMonthlyStat(ReceiptVO vo) throws Exception{
		logger.info("------------------updateMonthlyStat진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("VO: " +vo.toString());
		int result = receiptService.updateMonthlyStat(vo);
		System.out.println("updateMonthlyStat result: " + result);
		mv.addObject("result", result);
		return mv;
	}
	
	//selectBox - 제보유형(중)
	@RequestMapping("/receipt/reportTypeOnChange.ajax")
	public ModelAndView reportTypeOnChange(String BAS_LCOD) throws Exception{
		logger.info("------------------reportTypeOnChange진입------------------");
		//System.out.println("bas_lcod: " + BAS_LCOD);
		ModelAndView mv = new ModelAndView("jsonView");
		List<ReportTypeVO> reportTypeList = receiptService.reportTypeOnChange(BAS_LCOD);
		//System.out.println("reportTypeList: " + reportTypeList);
		mv.addObject("data", reportTypeList);
		return mv;
	}
	
	//selectBox - 지역구분(소)
	@RequestMapping("/receipt/selectAreaCodeSub.ajax")
	public ModelAndView selectAreaCodeSub(String AREA_CODE) throws Exception{
		logger.info("------------------selectAreaCodeSub진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<AreaSubCodeVO> subAreaCodeList = receiptService.selectAreaCodeSub(AREA_CODE);
		mv.addObject("data", subAreaCodeList);
		return mv;
	}
	
	//통신원 - 검색:연락처(IP-PBX용)
	@RequestMapping("/receipt/selectInformerByPhone.ajax")
	public ModelAndView selectInformerByPhone(String phone_cell, String month1, String month2, String month3) throws Exception {
		logger.info("------------------selectInformerByPhone진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<InformerVO> list = receiptService.selectInformerByPhone(phone_cell);
		
		if(list.size() > 0) {
			InformerVO vo = list.get(0);
			if(vo.getMEMO() != null) {
				vo.setMEMO(vo.getMEMO().replace("\r\n", "<br>"));
			}
			
			Integer MON_CNT= receiptService.getMonthlyTotal(new InformerStatVO(vo.getINFORMER_ID(), month1));
			if(MON_CNT == null) MON_CNT = 0;
			vo.setSTAT1(MON_CNT);
			
			MON_CNT = receiptService.getMonthlyTotal(new InformerStatVO(vo.getINFORMER_ID(), month2));
			if(MON_CNT == null) MON_CNT = 0;
			vo.setSTAT2(MON_CNT);
			
			MON_CNT = receiptService.getMonthlyTotal(new InformerStatVO(vo.getINFORMER_ID(), month3));
			if(MON_CNT == null) MON_CNT = 0;
			vo.setSTAT3(MON_CNT);
			
			mv.addObject("data", vo);
		} else {
			mv.addObject("data", "noInfoFound");
		}
		return mv;
	}
	
	//통신원 - 검색:이름(1),코드(2),연락처(3)
	@RequestMapping("/receipt/popUpInformerList.do")
	public ModelAndView popUpInformerList(HttpServletRequest request) throws Exception{
		logger.info("------------------popUpInformerList진입------------------");
		ModelAndView mv = new ModelAndView();
		String searchType = request.getParameter("searchType");
		String parameter = URLDecoder.decode(request.getParameter("SEARCH_TEXT"),"utf-8");
		System.out.println("Parameter: "+parameter);
		System.out.println("SearchType: " + searchType);
		
		List<InformerVO> informerList = null;
		if(searchType.equals("1")) {
			informerList = receiptService.searchInformerByName(parameter);
		} else if(searchType.equals("2")) {
			informerList = receiptService.searchInformerByCode(parameter);
		} else if(searchType.equals("3")) {
			informerList = receiptService.selectInformerByPhone(parameter);
		} else if(searchType.equals("4")) {//금일접수 편집에서 붎러올경우
			informerList = receiptService.searchInformerByName(parameter);
		}
		
		mv.addObject("informerList", informerList);
		mv.addObject("informerListSize", informerList.size());
		mv.setViewName("/receipt/popUpInformerList");
		return mv;
	}
	
	@RequestMapping("/receipt/selectInformerByID.ajax")
	public ModelAndView selectInformerByID(String INFORMER_ID, String month1, String month2, String month3 ) throws Exception{
		logger.info("------------------selectInformerByID진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		InformerVO vo = receiptService.selectInformerByID(INFORMER_ID);
		
		Integer MON_CNT= receiptService.getMonthlyTotal(new InformerStatVO(INFORMER_ID, month1));
		if(MON_CNT == null) MON_CNT = 0;
		vo.setSTAT1(MON_CNT);
		
		MON_CNT = receiptService.getMonthlyTotal(new InformerStatVO(INFORMER_ID, month2));
		if(MON_CNT == null) MON_CNT = 0;
		vo.setSTAT2(MON_CNT);

		MON_CNT = receiptService.getMonthlyTotal(new InformerStatVO(INFORMER_ID, month3));
		if(MON_CNT == null) MON_CNT = 0;
		vo.setSTAT3(MON_CNT);

		System.out.println("stat Informer VO: " + vo.toString());
		mv.addObject("data", vo);
		return mv;
	}
	
	//통신원 - 메모
	@RequestMapping("/receipt/saveInformerMemo.ajax")
	public ModelAndView saveInformerMemo(InformerVO vo) throws Exception {
		logger.info("------------------saveInformerMemo진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		
		vo.setMEMO(vo.getMEMO().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
		int result = receiptService.saveInformerMemo(vo);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/receipt/listUpArtery.ajax")
	public ModelAndView listUpArtery(ArteryVO vo) throws Exception {
		logger.info("------------------listUpArtery진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<ArteryVO> arteryList = receiptService.selectArtery(vo);
		logger.info("도로명조회 결과 size: " + arteryList.size());
		mv.addObject("arteryList", arteryList);
		return mv;
	} 
	
	@RequestMapping("/receipt/listUpNodeLink.ajax")
	public ModelAndView listUpNodeLink(ArteryVO vo) throws Exception {
		logger.info("------------------listUpNodeLink진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<ArteryVO> nodeLinkList = receiptService.selectNodeLink(vo);
		logger.info("nodeLinkList 결과 size: " + nodeLinkList.size());
		mv.addObject("nodeLinkList", nodeLinkList);
		return mv;
	}
	
	//부재중 - 삽입
	@RequestMapping("/receipt/insertMissedCall.ajax")
	public ModelAndView insertMissedCall(MissedCallVO vo) throws Exception {
		logger.info("------------------insertMissedCall진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		int result = receiptService.insertMissedCall(vo);
		logger.info("부재중목록저장 결과: "+result);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/recipet/getMissedCallInfoById.ajax")
	public ModelAndView getMissedCallInfoById(String MISSED_CALL_ID) throws Exception {
		System.out.println("missedId: " + MISSED_CALL_ID);
		logger.info("------------------getMissedCallInfoById진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		MissedCallVO vo = new MissedCallVO();
		vo.setMISSED_CALL_ID(MISSED_CALL_ID);
		vo = receiptService.getMissedCallInfoById(vo);
		System.out.println("vo: " + vo.toString());
		mv.addObject("data", vo);
		return mv;
	}
	
	@RequestMapping("/receipt/receivedStatusList.do")
	public ModelAndView receivedStatusList(CriteriaVO cri) throws Exception{
		logger.info("------------------receivedStatusList진입------------------");
//		ModelAndView mv = new ModelAndView("/receipt/receivedStatusList");
		ModelAndView mv = new ModelAndView();
		System.out.println("RECEIPT_DAY " + cri.getRECEIPT_DAY());
		System.out.println("startRow: " + cri.getStartRow());
		System.out.println("AREA_CODE: " + cri.getAREA_CODE());
		
		int size = 300;
		cri.setSize(size);
		cri.setStartRow((cri.getStartRow()-1) * size);
		
		List<ReceivedStatusVO> receivedStatusList = receiptService.receivedStatusList(cri);
		//System.out.println("receivedStatusList vo: " + receivedStatusList.get(0).toString());
		
		mv.addObject("receivedStatusList", receivedStatusList);
		mv.setViewName("/receipt/receivedStatusList");
		//mv.addObject("receivedStatusListSize", receivedStatusList.size());
		return mv;
	}
	
	@RequestMapping("/receipt/getTotalPage.ajax")
	public ModelAndView getTotalPage(CriteriaVO cri) throws Exception {
		logger.info("------------------getTotalPage진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("cri: " + cri.toString());
		int total = receiptService.countReceivedStatusList(cri);
		System.out.println("total: " + total);
		
		int size = 300;
		int totalPages = total/size;
		if(total % size > 0) {
			totalPages++;
		}
		System.out.println("totalPage: " + totalPages);
		
		mv.addObject("totalPage", totalPages);
		mv.addObject("totalSize", total);
		return mv;
	}
	
	@RequestMapping("/receipt/getSearchTotalPage.ajax")
	public ModelAndView getSearchTotalPage(ReceiptSearchVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		int total = receiptService.countSearchStatusList(vo);
		System.out.println("total: " + total);
		System.out.println("vo: " + vo.toString());
		
		int size = 300;
		int totalPages = total/size;
		if(total % size > 0) {
			totalPages++;
		}
		
		mv.addObject("totalPage", totalPages);
		mv.addObject("totalSize", total);
		return mv;
	}
	
	@RequestMapping("/receipt/searchFullStatus.do")
	public ModelAndView searchFullStatus(HttpServletRequest request, ReceiptSearchVO searchVO) throws Exception{
		logger.info("------------------searchFullStatus진입------------------");
		ModelAndView mv = new ModelAndView();
//		ReceiptSearchVO searchVO = new ReceiptSearchVO(request.getParameter("REPORT_TYPE")
//												, request.getParameter("REPORT_TYPE2")
//												, request.getParameter("REPORTMEAN_TYPE")
//												, request.getParameter("FLAG_SEND")
//												, request.getParameter("FLAG_BROD")
//												, request.getParameter("AREA_CODE")
//												, request.getParameter("CONTENT")
//												, request.getParameter("START_DAY")
//												, request.getParameter("END_DAY"));
//		
		int size = 300;
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		System.out.println("startRow: " + startRow);
		
		searchVO.setSize(size);
		searchVO.setStartRow((startRow-1) * size);
		System.out.println("resultVO: " + searchVO.toString());
		
		List<ReceivedStatusVO> fullStatusList = receiptService.searchStatusList(searchVO);
		logger.info("searchFullStatus 결과 size: " + fullStatusList.size());
		mv.addObject("receivedStatusList", fullStatusList);
		mv.addObject("receivedStatusListSize", fullStatusList.size());
		
		String SEARCH_TAB = request.getParameter("SEARCH_TAB");
		System.out.println("search_tab: "+ SEARCH_TAB);
		//여기서 쿼리 다르게 해야함...;; 금일접수랑 전체접수랑 갈리니까..
		//테이블도 다르고
		if(SEARCH_TAB.equals("receivedStatusSearch")) {
			mv.setViewName("/receipt/receivedStatusList");
		} else {
			mv.setViewName("/receipt/fullReceivedHistoryList");
		}
		return mv;
	}
	
	@RequestMapping("/receipt/updateFlagSTT.ajax")
	public ModelAndView updateFlagSTT(String MISSED_CALL_ID) throws Exception {
		logger.info("------------------searchFullStatus진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		int result = receiptService.updateFlagSTT(MISSED_CALL_ID);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/receipt/updatePersonalMemo.do")
	public ModelAndView updatePersonalMemo(UserVO vo) throws Exception {
		logger.info("------------------updatePersonalMemo진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		int result = receiptService.updatePersonalMemo(vo);
		System.out.println("result: " + result);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping("/receipt/selectPersonalMemo.do")
	public ModelAndView selectPersonalMemo(UserVO vo) throws Exception {
		logger.info("------------------selectPersonalMemo진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		UserVO memoVO = receiptService.selectPersonalMemo(vo);
		System.out.println("vo: " + memoVO.toString());
		mv.addObject("data", memoVO);
		return mv;
	}
	//임시저장
	@RequestMapping("/receipt/tempSave.do")
	public ModelAndView tempSave(HttpServletRequest request, @ModelAttribute TempSaveVo vo, RedirectAttributes redirectAttributes){
		logger.info("------------------tempSave진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		vo.setMEMO(vo.getMEMO().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
		try {
			receiptService.tempSave(vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("임시저장 실패 : "+e);
		}
		logger.debug("임시저장 리턴");
		return mv;
	}
	//당일 로그인 사용자에 대한 임시저장 전체목록 불러오기
	@RequestMapping("/receipt/tempsaveList.ajax")
	public ModelAndView tempsaveList(TempSaveVo vo) throws Exception {
		logger.info("------------------tempsaveList진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		List<TempSaveVo> tsList = receiptService.tempsaveList(vo);
		mv.addObject("tsList", tsList);
		return mv;
	}
	//선택한  임시저장 데이터를 불러와서 반영
	@RequestMapping("/receipt/tempsavePush.ajax")
	public ModelAndView tempsavePush(TempSaveVo vo) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		TempSaveVo tsvo = receiptService.tempsavePush(vo);
		mv.addObject("tsvo", tsvo);
		return mv;
	}
	//선택한  임시저장 데이터 삭제
	@RequestMapping("/receipt/tempSaveDelete.ajax")
	public ModelAndView tempSaveDelete (
			HttpServletRequest request
//			,@RequestParam(required=false, value="TS_TIME") String TS_TIME
//			,@RequestParam(required=false, value="TS_TIME") String RECEPTION_ID
			,@RequestParam(required=false, value="chkArr[]")List<String> chkArr
			, RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			receiptService.tempSaveDelete(chkArr);
		} catch (Exception e) {
			mv.addObject("msg","삭제에 실패하였습니다"+e);
		}
		return mv;
	}
	
	/*
	 * 전체접수용 테이블 분할 관련 230118
	 * */
	
	//최초 전체 행 수 검색
	@RequestMapping("/receipt/getTotalPageFR.ajax")
	public ModelAndView getTotalPageFR(CriteriaVO cri) throws Exception {
		logger.info("------------------getTotalPage진입------------------");
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("cri: " + cri.toString());
		int total = receiptService.countReceivedStatusListFR(cri);
		System.out.println("total: " + total);
		
		int size = 300;
		int totalPages = total/size;
		if(total % size > 0) {
			totalPages++;
		}
		System.out.println("totalPage: " + totalPages);
		
		mv.addObject("totalPage", totalPages);
		mv.addObject("totalSize", total);
		return mv;
	}
	
	//검색 시 행 갯수 검색
	@RequestMapping("/receipt/getSearchTotalPageFR.ajax")
	public ModelAndView getSearchTotalPageFR(ReceiptSearchVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		int total = receiptService.countSearchStatusListFR(vo);
		System.out.println("total: " + total);
		System.out.println("vo: " + vo.toString());
		
		int size = 300;
		int totalPages = total/size;
		if(total % size > 0) {
			totalPages++;
		}
		
		mv.addObject("totalPage", totalPages);
		mv.addObject("totalSize", total);
		return mv;
	}
	
	@RequestMapping("/receipt/receivedStatusListFR.do")
	public ModelAndView receivedStatusListFR(CriteriaVO cri) throws Exception{
		logger.debug("------------------receivedStatusList FR 진입------------------");
		ModelAndView mv = new ModelAndView("/receipt/receivedStatusList");
		System.out.println("RECEIPT_DAY " + cri.getRECEIPT_DAY());
		System.out.println("startRow: " + cri.getStartRow());
		System.out.println("AREA_CODE: " + cri.getAREA_CODE());
		
		int size = 300;
		cri.setSize(size);
		cri.setStartRow((cri.getStartRow()-1) * size);
		
		List<ReceivedStatusVO> receivedStatusList = receiptService.receivedStatusListFR(cri);
		//System.out.println("receivedStatusList vo: " + receivedStatusList.get(0).toString());
		
		mv.addObject("receivedStatusList", receivedStatusList);
		//mv.addObject("receivedStatusListSize", receivedStatusList.size());
		return mv;
	}
	
	@RequestMapping("/receipt/searchFullStatusFR.do")
	public ModelAndView searchFullStatusFR(HttpServletRequest request, ReceiptSearchVO searchVO) throws Exception{
		logger.info("------------------searchFullStatus진입------------------");
		ModelAndView mv = new ModelAndView();
//		ReceiptSearchVO searchVO = new ReceiptSearchVO(request.getParameter("REPORT_TYPE")
//												, request.getParameter("REPORT_TYPE2")
//												, request.getParameter("REPORTMEAN_TYPE")
//												, request.getParameter("FLAG_SEND")
//												, request.getParameter("FLAG_BROD")
//												, request.getParameter("AREA_CODE")
//												, request.getParameter("CONTENT")
//												, request.getParameter("START_DAY")
//												, request.getParameter("END_DAY"));
//		
		int size = 300;
		int startRow = Integer.parseInt(request.getParameter("startRow"));
		System.out.println("startRow: " + startRow);
		
		searchVO.setSize(size);
		searchVO.setStartRow((startRow-1) * size);
		System.out.println("resultVO: " + searchVO.toString());
		
		List<ReceivedStatusVO> fullStatusList = receiptService.searchStatusListFR(searchVO);
		logger.info("searchFullStatus 결과 size: " + fullStatusList.size());
		mv.addObject("receivedStatusList", fullStatusList);
		mv.addObject("receivedStatusListSize", fullStatusList.size());
		
		String SEARCH_TAB = request.getParameter("SEARCH_TAB");
		System.out.println("search_tab: "+ SEARCH_TAB);
		if(SEARCH_TAB.equals("receivedStatus")) {
			mv.setViewName("/receipt/receivedStatusList");
		} else {
			mv.setViewName("/receipt/fullReceivedHistoryList");
		}
		return mv;
	}
	
}
