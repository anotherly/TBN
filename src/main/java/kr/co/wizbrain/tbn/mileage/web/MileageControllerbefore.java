/*package kr.co.wizbrain.tbn.mileage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.award.vo.AwardVO;
import kr.co.wizbrain.tbn.award.web.AwardController;
import kr.co.wizbrain.tbn.infrm.service.InfrmService;
import kr.co.wizbrain.tbn.mileage.service.MileageService;
import kr.co.wizbrain.tbn.mileage.vo.GiftVO;
import kr.co.wizbrain.tbn.mileage.vo.GradeVO;
import kr.co.wizbrain.tbn.mileage.vo.MileageVO;
import kr.co.wizbrain.tbn.mileage.vo.MserchVO;
import kr.co.wizbrain.tbn.option.service.AreaOptService;
import kr.co.wizbrain.tbn.option.service.InfrmOptService;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;

@Controller
public class MileageControllerbefore {

	private static final Logger logger = LoggerFactory.getLogger(AwardController.class);
	public String url="";
	
	@Resource(name="mileageService")
	private MileageService mileageService;
	
	@Resource(name="areaOptService")
	private AreaOptService areaOptService;
	
	@Resource(name="infrmService")
	private InfrmService infrmService;
	
	@Resource(name="infrmOptService")
	private InfrmOptService infrmOptService;
	

	@RequestMapping(value= {"/informer/mileage/mileageMain.do","/mileage/allMile.do","/mileage/mileGrade.do"})
	public ModelAndView mileageMain(HttpServletRequest request,Model model) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		OptAreaVo areaVo = new OptAreaVo();
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			areaVo.setAreaCode(nlVo.getRegionId());
		}
		
		List<OptAreaVo> alist = areaOptService.selectAreaOpt1(areaVo);
		
		mav.addObject("informerRegionList", alist);
		mav.addObject("informerTypeList", infrmOptService.selectInft1(new OptInftVo()));
		mav.addObject("informerRegionList", areaOptService.selectAreaOpt1(areaVo));
		mav.addObject("informerAreaList", this.areaOptService.selectAreaOpt1(areaVo));
		
		if(url .equals( "/informer/mileage/mileageMain")) {
			mav.setViewName("/mileage/mileageMain");
		} else {
			mav.setViewName(url);
		}
		
		
		return mav;
	}
	
	
	// 마일리지 지급 목록
	@RequestMapping("/mileage/mileageList.do")
	public ModelAndView mileageList(@ModelAttribute("MileageVO") MileageVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/mileage/mileageList");

		List<Map<String, Object>> mileageList = mileageService.mileList(paramVO);
		int mileageListCount = mileageList.size();
		
        mav.addObject("mileageList", mileageList);
        mav.addObject("mileageListCount", mileageListCount);
		return mav;
	}
	
	
	// 총 마일리지 목록
	@RequestMapping("/mileage/allMileList.do")
	public ModelAndView allMileList(@ModelAttribute("MserchVO") MserchVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/mileage/allMileList");
		
		List<MileageVO> allMileList = mileageService.allMileList(paramVO);
		int allMileListCount = allMileList.size();
		
		mav.addObject("allMileList", allMileList);
        mav.addObject("allMileListCount", allMileListCount);
        
		return mav;
	}
	
	// 등급 조회 목록
	@RequestMapping("/mileage/gradeList.do")
	public ModelAndView gradeList(@ModelAttribute("MserchVO") MserchVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/mileage/gradeList");
		
		List<MileageVO> gradeList = mileageService.gradeList(paramVO);
		int gradeListCount = gradeList.size();
		
		mav.addObject("gradeList", gradeList);
		mav.addObject("gradeListCount", gradeListCount);
		
		return mav;
		
	}
	
	
	// 상품 지급 팝업
	@RequestMapping("/mileage/giftPop.do")
	public ModelAndView paymentGift(@RequestParam("INFORMER_ID")String ifmId, HttpServletRequest req) throws Exception {
		ModelAndView mv = new ModelAndView(); 
		
		// 통신원 기본 정보 가져오기 (1인)
		List<MileageVO> mileList = mileageService.selectIfm(ifmId);
		
		// 상품 정보 가져오기
		List<GiftVO> giftList = mileageService.getGift();
		
		mv.addObject("mileList", mileList);
		mv.addObject("giftList", giftList);
		mv.setViewName("/mileage/giftPop");
		
		return mv;
	}
	
	
	// 등급 부여 팝업
	@RequestMapping("/mileage/gradePop.do")
	public ModelAndView paymentGrade(@RequestParam("INFORMER_ID") String ifmId, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// 통신원 기본 정보 가져오기 (1인)
		List<MileageVO> mileList = mileageService.gSelectIfm(ifmId);
		
		// 등급 정보 가져오기
		List<GradeVO> gradeList = mileageService.getGrade();
		
		mav.addObject("mileList", mileList);
		mav.addObject("gradeList", gradeList);
		mav.setViewName("/mileage/gradePop");
		
		return mav;
	}
	
	// 총 마일리지 - 마일리지 지급 내역 (로그 확인)
	@RequestMapping("/mileage/allMilePop.do")
	public ModelAndView paymentMileLog(@RequestParam("INFORMER_ID") String ifmId, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<MileageVO> informerInfo = mileageService.informerInfo(ifmId);
		List<MileageVO> mileList = mileageService.mileLogList(ifmId);
		List<MileageVO> giftList = mileageService.giftLogList(ifmId);
		
		
		mav.addObject("informerInfo",informerInfo);
		mav.addObject("mileList",mileList);
		mav.addObject("giftList",giftList);
		mav.setViewName("/mileage/allMilePop");
		
		return mav;
	}
	
	// 등급 조회 - 등급 부여 내역(로그 확인)
	@RequestMapping("/mileage/gradeLogPop.do")
	public ModelAndView gradeLog(@RequestParam("INFORMER_ID") String ifmId, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<MileageVO> informerInfo = mileageService.informerInfo(ifmId);
		List<MileageVO> mileList = mileageService.mileLogList(ifmId);
		List<MileageVO> gradeList = mileageService.gradeLogList(ifmId);
		
		
		mav.addObject("informerInfo",informerInfo);
		mav.addObject("mileList",mileList);
		mav.addObject("gradeList",gradeList);
		mav.setViewName("/mileage/gradeLogPop");
		
		return mav;
	}
	
	
	// 상품 지급 후 마일리지 변동 및 상품 지급 로그
	@RequestMapping("/mileage/sendGift.do")
	public ModelAndView sendGift(@ModelAttribute("MileageVO") MileageVO paramVO, HttpServletRequest req) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		// 마일리지 차감 작업
		mileageService.minusMile(paramVO);
		
		// 상품 지급 로그 작업
		mileageService.giftHistory(paramVO);
		
		mv.addObject("msg","success");
		return mv;
	}
	
	// 등급 부여 및 등급 부여 로그
	@RequestMapping("/mileage/sendGrade.do")
	public ModelAndView sendGrade(@ModelAttribute("MileageVO") MileageVO paramVO, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		// 등급 부여 로그 작업
		mileageService.gradeHistory(paramVO);
		
		// 등급 부여 작업
		boolean checker = mileageService.gradeChecker(paramVO);
		
		if(checker) { // 조회 결과가 있는 경우 => 업데이트
			mileageService.updateGrade(paramVO);
		} else { // 조회 결과가 없는 경우 => 등록
			mileageService.insertGrade(paramVO);
		}
		
		mav.addObject("msg", "success");
		return mav;
	}
	
	// 마일리지 지급
	@RequestMapping("/mileage/paymentMile.do")
	public ModelAndView paymentMile(Model model,HttpServletRequest req,@ModelAttribute("MileageVO") MileageVO paramVO) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");  
		String[] Selection = req.getParameterValues("Selection");
		logger.debug("Selection===>"+Selection[0]);

		String getDate = paramVO.getPAYMENT_DATE();
		String startDate = paramVO.getSTART_DATE();
		
		List<MileageVO> alist = new ArrayList<>(); 
		
		for (int i = 0; i < Selection.length; i++) {
			MileageVO mlvo = new MileageVO();
			String[] str = Selection[i].split("%%");
			mlvo.setINFORMER_ID(str[0]);
			mlvo.setACT_ID(str[1]);
			mlvo.setORG_NAME(str[3]);
			mlvo.setPHONE_CELL(str[4]);
			mlvo.setPAYMENT_MILE(str[5]);
			mlvo.setPAYMENT_DATE(getDate);
			mlvo.setSTART_DATE(startDate);
			alist.add(mlvo);
		}

 		mileageService.paymentMile(paramVO,alist);
 		
 		for(int i=0; i < Selection.length; i++) {
 			MileageVO mlvo = new MileageVO();
			String[] str = Selection[i].split("%%");
			
			List<MileageVO> mileList = new ArrayList<>();
			
			mlvo.setINFORMER_ID(str[0]);
			mlvo.setACT_ID(str[1]);
			mlvo.setORG_NAME(str[3]);
			mlvo.setPHONE_CELL(str[4]);
			mlvo.setPAYMENT_MILE(str[5]);
			mlvo.setPAYMENT_DATE(getDate);
			mlvo.setSTART_DATE(startDate);
			mileList.add(mlvo);
			
			boolean checker = mileageService.selectMile(paramVO,mileList);
			
 			if(!checker) {
 				mileageService.insertMile(paramVO,mileList);
 			} else {
 				mileageService.updateMile(paramVO,mileList);
 			}
 		}
		
		mv.addObject("msg", "success");
		return mv;
	}
	
	// 마일리지 반영 - 엑셀 다운로드 기능
	@RequestMapping("/informer/mileage/excelDownloadMileList.do")
	public String exselDownload(Model model,@ModelAttribute("MileageVO") MileageVO paramVO) throws Exception {
		logger.debug("★★★"+paramVO);
		
		List mileageList = mileageService.getMileList(paramVO);
		
		
		model.addAttribute("fileName", "마일리지 반영 목록.xls");
		model.addAttribute("columnTitles", new String[]{
				"ID"
				,"이름"
				,"소속"
				,"연락처"
				,"월 제보건수" 
				,"지급 마일리지"
		});
		model.addAttribute("columnNames", new String[]{
				"ACT_ID",
				"INFORMER_NAME",
				"ORG_NAME",
				"PHONE_CELL",
				"MON_RPT",
				"PAYMENT_MILEAGE"
		});
		model.addAttribute("exportData", mileageList);
		
		return "hssfView";
	}
	
	// 총 마일리지 조회 - 엑셀 다운로드 기능
	@RequestMapping("/informer/mileage/excelDownloadMileList.do")
	public String exselDownload(Model model,@ModelAttribute("AwardVO") AwardVO paramVO) throws Exception {
		logger.debug("★★★"+paramVO);
		List awardInformerList = mileageService.getAllMileList2(paramVO);
		
		model.addAttribute("fileName", "총 마일리지 조회 목록.xls");
		model.addAttribute("columnTitles", new String[]{
				,"ID"
				,"이름"
				,"소속"
				,"연락처"
				,"제보건수" 
				,"제보점수"
				,"주요제보건수"
				,"주요제보점수"
				,"전월건수"
				,"전월점수"
				,"총점"
		});
		model.addAttribute("columnNames", new String[]{
				"RNUM",
				"ACT_ID",
				"INFORMER_NAME",
				"ORG_NAME",
				"PHONE_CELL",
				"MON_CNT",
				"RPT_GRADE",
				"MAIN_CNT",
				"MAIN_GRADE",
				"ADD_CNT",
				"ADD_GRADE",
				"ALL_RANK",
		});
		model.addAttribute("exportData", awardInformerList);
		
		return "hssfView";
	}
	
	// 등급 조회 - 엑셀 다운로드 기능
	@RequestMapping("/informer/award/excelDownloadInformerList.do")
	public String exselDownload(Model model,@ModelAttribute("AwardVO") AwardVO paramVO) throws Exception {
		logger.debug("★★★"+paramVO);
		List awardInformerList = awardService.getAwardInformerList2(paramVO);
		
		model.addAttribute("fileName", "수상자선정.xls");
		model.addAttribute("columnTitles", new String[]{
				"순위"
				,"ID"
				,"이름"
				,"소속"
				,"연락처"
				,"제보건수" 
				,"제보점수"
				,"주요제보건수"
				,"주요제보점수"
				,"전월건수"
				,"전월점수"
				,"총점"
		});
		model.addAttribute("columnNames", new String[]{
				"RNUM",
				"ACT_ID",
				"INFORMER_NAME",
				"ORG_NAME",
				"PHONE_CELL",
				"MON_CNT",
				"RPT_GRADE",
				"MAIN_CNT",
				"MAIN_GRADE",
				"ADD_CNT",
				"ADD_GRADE",
				"ALL_RANK",
		});
		model.addAttribute("exportData", awardInformerList);
		
		return "hssfView";
	}
}
*/