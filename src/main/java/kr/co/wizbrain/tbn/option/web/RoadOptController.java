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

import kr.co.wizbrain.tbn.option.vo.OptRoadVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.option.service.AreaOptService;
import kr.co.wizbrain.tbn.option.service.InfrmOptService;
import kr.co.wizbrain.tbn.option.service.RoadOptService;

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
public class RoadOptController {
	
	public static final Logger logger = LoggerFactory.getLogger(RoadOptController.class);
	
	@Resource(name="roadOptService")
	private RoadOptService roadOptService;
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
	@RequestMapping(value= {"/option/road/main.do","/option/road/code2.do","/option/road/code3.do"})
	public ModelAndView selectRoadOpt(HttpSession httpSession, HttpServletRequest request,Model model
	,@RequestParam(required=false, value="tCode") String tCode
	,@RequestParam(required=false, value="pCode") String pCode
	,@RequestParam(required=false, value="typeNum") String typeNum
	) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String surl="";
		String skey="";
		List<OptRoadVo> thlist = new ArrayList<OptRoadVo>();
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶제보유형 분류 컨트롤러");
		OptRoadVo thvo = new OptRoadVo();
		thvo.setArteryId(tCode);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		
		if(typeNum==null) {//대
			surl = "/option/road/tabReportType";
			skey = "reportType1";
			thlist = roadOptService.selectRoadOpt1(thvo);
		}else {//중,소
			surl = "/option/road/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			thvo.setAreaCode(pCode);
			if (typeNum.equals("2")) {
				thlist = roadOptService.selectRoadOpt2(thvo);
			} else {
				thlist = roadOptService.selectRoadOpt3(thvo);
			}
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
	@RequestMapping(value="/option/road/save.do")
	public ModelAndView insertRoadOpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="value") String vName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptRoadVo thvo = new OptRoadVo();
		List<OptRoadVo> thlist = new ArrayList<OptRoadVo>();
		
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if (cdFlag.equals("1")){
			mv.setViewName("/option/road/tabReportType");
			skey = "reportType1";
			roadOptService.insertRoadOpt1(thvo);
			thlist = roadOptService.selectRoadOpt1(thvo);
		} else if (cdFlag.equals("2")){
			mv.setViewName("/option/road/reportType2");
			skey = "userTypeCode2";
			thvo.setAreaCode(pCode);
			thvo.setArteryName(vName);
			roadOptService.insertRoadOpt2(thvo);
			thlist = roadOptService.selectRoadOpt2(thvo);
		} else {
			mv.setViewName("/option/road/reportType3");
			skey = "userTypeCode3";
			thvo.setArteryId(tCode);
			thvo.setlNodeName(vName);
			roadOptService.insertRoadOpt3(thvo);
			thlist = roadOptService.selectRoadOpt3(thvo);
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
	@RequestMapping(value="/option/road/modify.do")
	public ModelAndView updateRoadOpt(HttpSession httpSession, HttpServletRequest request,Model model
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
		OptRoadVo thvo = new OptRoadVo();
		List<OptRoadVo> thlist = new ArrayList<OptRoadVo>();
		
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if (cdFlag.equals("1")){
			thvo.setAreaCode(code);
			mv.setViewName("/option/road/tabReportType");
			skey = "reportType1";
			roadOptService.updateRoadOpt1(thvo);
			thlist = roadOptService.selectRoadOpt1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setAreaCode(pCode);
			thvo.setArteryId(code);
			thvo.setArteryName(codeName);
			mv.setViewName("/option/road/reportType2");
			skey = "userTypeCode2";
			roadOptService.updateRoadOpt2(thvo);
			thlist = roadOptService.selectRoadOpt2(thvo);
		} else {
			thvo.setAreaCode(pCode);
			thvo.setArteryId(pCode2);
			thvo.setNodelinkId(code);
			thvo.setlNodeName(codeName);
			mv.setViewName("/option/road/reportType3");
			skey = "userTypeCode3";
			roadOptService.updateRoadOpt3(thvo);
			thlist = roadOptService.selectRoadOpt3(thvo);
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
	
	@RequestMapping("/option/road/delete.do")
	public ModelAndView deleteRoadOpt(HttpSession httpSession, HttpServletRequest request,Model model
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
		OptRoadVo thvo = new OptRoadVo();
		List<OptRoadVo> thlist = new ArrayList<OptRoadVo>();
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if (cdFlag.equals("1")){
			mv.setViewName("/option/road/tabReportType");
			skey = "reportType1";
			roadOptService.deleteRoadOpt1(cdFlag, chkArr);
			thlist = roadOptService.selectRoadOpt1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setAreaCode(pCode);
			thvo.setArteryId(code);
			mv.setViewName("/option/road/reportType2");
			skey = "userTypeCode2";
			roadOptService.deleteRoadOpt2(cdFlag, pCode, chkArr);
			thlist = roadOptService.selectRoadOpt2(thvo);
		} else {
			thvo.setAreaCode(pCode);
			thvo.setArteryId(pCode2);
			thvo.setNodelinkId(code);
			mv.setViewName("/option/road/reportType3");
			skey = "userTypeCode3";
			roadOptService.deleteRoadOpt3(cdFlag, pCode,pCode2, chkArr);
			thlist = roadOptService.selectRoadOpt3(thvo);
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
