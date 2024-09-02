package kr.co.wizbrain.tbn.option.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;
import kr.co.wizbrain.tbn.option.service.AreaOptService;
import kr.co.wizbrain.tbn.option.service.InfrmOptService;

/**
 * 사용자 컨트롤러 클래스
 * @author 솔루션사업팀 정다빈
 * @since 2021.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2021.07.23  정다빈           최초 생성
 */

@Controller
public class AreaOptController {
	
	public static final Logger logger = LoggerFactory.getLogger(AreaOptController.class);
	
	@Resource(name="areaOptService")
	private AreaOptService areaOptService;
	public String url="";
	public boolean isClose = false;
	
	
	/*********************** 통신원유형 코드 관리 *************************/
	
	/**
	 * <pre>
	 * 1. 개요 : 통신원유형 대,중,소 분류 조회
	 * 2. 처리내용 :
	 * 3. 입력 Data :
	 * 4. 출력 Data :
	 * </pre>
	 * @Method Name : searchReportTypeCode1
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= {"/option/area/main.do","/option/area/code2.do","/option/area/code3.do"})
	public ModelAndView selectAreaOpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="typeNum") String typeNum
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String surl="";
		String skey="";
		List<OptAreaVo> thlist = new ArrayList<OptAreaVo>();
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶제보유형 분류 컨트롤러");
		OptAreaVo thvo = new OptAreaVo();
		thvo.setAreaSubCode(tCode);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		thvo.setAreaSubCode(tCode);
		if(typeNum==null) {//대
			surl = "/option/area/tabReportType";
			skey = "reportType1";
			thlist = areaOptService.selectAreaOpt1(thvo);
		}else {//중,소
			thvo.setAreaCode(tCode);
			surl = "/option/area/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			thlist = areaOptService.selectAreaOpt2(thvo);
		}
		mv.addObject(skey, thlist);
		mv.setViewName(surl);
		return mv;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 통신원유형 추가
	 * 2. 처리내용 :
	 * 3. 입력 Data :
	 * 4. 출력 Data :
	 * </pre>
	 * @Method Name : searchReportTypeCode1
	 * @param model
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/option/area/save.do")
	public ModelAndView insertAreaOpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="value") String vName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptAreaVo thvo = new OptAreaVo();
		List<OptAreaVo> thlist = new ArrayList<OptAreaVo>();
		
		thvo.setAreaName(vName);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		thvo.setAreaSubCode(tCode);
		
		if (cdFlag.equals("1")){
			mv.setViewName("/option/area/tabReportType");
			skey = "reportType1";
			areaOptService.insertAreaOpt1(thvo);
			thlist = areaOptService.selectAreaOpt1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setAreaCode(pCode);
			mv.setViewName("/option/area/reportType2");
			skey = "userTypeCode2";
			areaOptService.insertAreaOpt2(thvo);
			thlist = areaOptService.selectAreaOpt2(thvo);
		} else {
//			mv.setViewName("/option/area/reportType3");
//			skey = "userTypeCode3";
//			areaOptService.insertAreaOpt3(thvo);
//			thlist = areaOptService.selectAreaOpt3(thvo);
		}
		
		mv.addObject(skey, thlist);
		return mv;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 통신원유형 수정
	 * 2. 처리내용 :
	 * 3. 입력 Data :
	 * 4. 출력 Data :
	 * </pre>
	 * @Method Name : searchReportTypeCode1
	 * @param model
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/option/area/modify.do")
	public ModelAndView updateAreaOpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="pCode2") String pCode2
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="codeName") String codeName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptAreaVo thvo = new OptAreaVo();
		List<OptAreaVo> thlist = new ArrayList<OptAreaVo>();
		thvo.setAreaName(codeName);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if (cdFlag.equals("1")){
			thvo.setAreaCode(code);
			mv.setViewName("/option/area/tabReportType");
			skey = "reportType1";
			areaOptService.updateAreaOpt1(thvo);
			thlist = areaOptService.selectAreaOpt1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setAreaCode(pCode);
			thvo.setAreaSubCode(code);
			mv.setViewName("/option/area/reportType2");
			skey = "userTypeCode2";
			areaOptService.updateAreaOpt2(thvo);
			thlist = areaOptService.selectAreaOpt2(thvo);
		} else {
//			thvo.setAreaCode(pCode);
//			thvo.setAreaSubCode(pCode2);
//			thvo.setIfmId3(code);
//			mv.setViewName("/option/area/reportType3");
//			skey = "userTypeCode3";
//			areaOptService.updateAreaOpt3(thvo);
//			thlist = areaOptService.selectAreaOpt3(thvo);
		}
		mv.addObject(skey, thlist);
		return mv;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 통신원유형 추가
	 * 2. 처리내용 :
	 * 3. 입력 Data :
	 * 4. 출력 Data :
	 * </pre>
	 * @Method Name : searchReportTypeCode1
	 * @param model
	 * @return 
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/option/area/delete.do")
	public ModelAndView deleteAreaOpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="pCode2") String pCode2
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="chkValList[]")List<String> chkArr
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptAreaVo thvo = new OptAreaVo();
		List<OptAreaVo> thlist = new ArrayList<OptAreaVo>();
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if (cdFlag.equals("1")){
			mv.setViewName("/option/area/tabReportType");
			skey = "reportType1";
			areaOptService.deleteAreaOpt1(cdFlag, chkArr);
			thlist = areaOptService.selectAreaOpt1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setAreaCode(pCode);
			thvo.setAreaSubCode(code);
			mv.setViewName("/option/area/reportType2");
			skey = "userTypeCode2";
			areaOptService.deleteAreaOpt2(cdFlag, pCode, chkArr);
			thlist = areaOptService.selectAreaOpt2(thvo);
		} else {
//			thvo.setIfmId1(pCode);
//			thvo.setIfmId2(pCode2);
//			thvo.setIfmId3(code);
//			mv.setViewName("/option/area/reportType3");
//			skey = "userTypeCode3";
//			areaOptService.deleteAreaOpt3(cdFlag, pCode,pCode2, chkArr);
//			thlist = areaOptService.selectAreaOpt3(thvo);
		}
		//thvo.setBasScod(code);//소
		//thvo.setCdFlag(cdFlag);
		//optionService.deleteRpt(thvo);
		//삭제시
		//대랑 소일때는 그냥 값만 배열로 넘김 foreach
		//중일때는 정규식 like에서 포이치
		//삭제 후 인덱스 수정시
		//반복 for문 어려번 실행 (띄엄띄엄 삭제할 수도 있으므로)
		mv.addObject(skey, thlist);
		return mv;
	}
}
