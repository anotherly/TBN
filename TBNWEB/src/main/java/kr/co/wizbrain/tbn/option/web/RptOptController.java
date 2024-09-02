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

import kr.co.wizbrain.tbn.option.vo.AuthVo;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import kr.co.wizbrain.tbn.option.service.AreaOptService;
import kr.co.wizbrain.tbn.option.service.AuthService;
import kr.co.wizbrain.tbn.option.service.RptOptService;

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

@Slf4j
@Controller
public class RptOptController {
	
	public static final Logger logger = LoggerFactory.getLogger(RptOptController.class);
	
	@Resource(name="rptOptService")
	private RptOptService rptOptService;
	public String url="";
	public boolean isClose = false;
	@Resource(name="areaOptService")
	private AreaOptService areaOptService;

	@Resource(name = "authService")
	private AuthService authService;
	
	//22.03.16 긴급생성
	//1.  최초 정보관리 진입 시 지역정보 받아옴
	@RequestMapping(value="/option/codeMng.do")
	public ModelAndView userUrlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView(url);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		//지역관련
		OptAreaVo avo = new OptAreaVo();
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			avo.setAreaCode(nlVo.getRegionId());
		}
		
		// ####### 권한별 정보관리 화면 분기처리 ##########
		
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");

		AuthVo authVo = new AuthVo();
		authVo.setAuthCode(reqLoginVo.getAuthCode());
		authVo.setUrl(url);
		authVo.setCdFlag("3");
		
		List<AuthVo> alist = authService.selectAuthUrl(authVo);
		String nowUrl = alist.get(0).getUrl()+".do";
		//권한에 따라 산출된 최초 url이 동일할 경우
		//(코드관리의 모든 기능을 사용 가능할 경우)
		//재귀함수 무한루프 방지
		if(nowUrl.equals("/option/codeMng.do")) {
			nowUrl = "/option/reportType/main.do";
		}
		mav.addObject("chgUrl", nowUrl);
		
		// ####### 권한별 정보관리 화면 분기처리 ########## 
		
		mav.addObject("informerRegionList", areaOptService.selectAreaOpt1(avo));
		return mav;
	}
	
	/*********************** 통신원유형 코드 관리 *************************/
	
	/**
	 * <pre>
	 * 1. 개요 : 제보유형 대,중,소 분류 조회
	 * 2. 처리내용 :
	 * 3. 입력 Data :
	 * 4. 출력 Data :
	 * </pre>
	 * @Method Name : searchReportTypeCode1
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= {"/option/reportType/main.do","/option/reportType/code2.do","/option/reportType/code3.do"})
	public ModelAndView selectRpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="typeNum") String typeNum
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String surl="";
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶제보유형 분류 컨트롤러");
		String subUrl=url.split("/")[2];
		OptRptVo thvo = new OptRptVo();
		thvo.setCdFlag(typeNum);
		if(typeNum==null) {//대
			thvo.setBasLcod("100");
			surl = "/option/reportType/tabReportType";
			skey = "reportType1";
		}else {//중,소
			surl = "/option/reportType/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			if (typeNum.equals("2")) {//중
				thvo.setBasLcod(tCode);
			} else {//소
				thvo.setBasLcod(pCode+tCode);
			}
		}
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = rptOptService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		mv.setViewName(surl);
		return mv;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 제보유형 추가
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
	@RequestMapping(value="/option/reportType/save.do")
	public ModelAndView insertRpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="value") String vName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptRptVo thvo = new OptRptVo();
		thvo.setBasLcod(pCode);
		thvo.setBasName(vName);
		thvo.setCdFlag(cdFlag);
		if (cdFlag.equals("1")){
			mv.setViewName("/option/reportType/tabReportType");
			skey = "reportType1";
		} else if (cdFlag.equals("2")){
			mv.setViewName("/option/reportType/reportType2");
			skey = "userTypeCode2";
		} else {
			mv.setViewName("/option/reportType/reportType3");
			skey = "userTypeCode3";
		}
		rptOptService.insertRpt(thvo);
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = rptOptService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		return mv;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제보유형 수정
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
	@RequestMapping(value="/option/reportType/modify.do")
	public ModelAndView updateRpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="codeName") String codeName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptRptVo thvo = new OptRptVo();
		
		thvo.setBasLcod(pCode);
		if (cdFlag.equals("1")){//대
			mv.setViewName("/option/reportType/tabReportType");
			skey = "reportType1";
		} else if (cdFlag.equals("2")){//중
			mv.setViewName("/option/reportType/reportType2");
			skey = "userTypeCode2";
		} else {
			thvo.setBasLcod(pCode);//소
			mv.setViewName("/option/reportType/reportType3");
			skey = "userTypeCode3";
		}
		thvo.setBasScod(code);//소
		thvo.setBasName(codeName);
		thvo.setCdFlag(cdFlag);
		rptOptService.updateRpt(thvo);
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = rptOptService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		return mv;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제보유형 삭제
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
	
	@RequestMapping("/option/reportType/delete")
	public ModelAndView deleteRpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="chkValList[]")List<String> chkArr
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptRptVo thvo = new OptRptVo();
		thvo.setBasLcod(pCode);
		if (cdFlag.equals("1")){//대
			mv.setViewName("/option/reportType/tabReportType");
			skey = "reportType1";
		} else if (cdFlag.equals("2")){//중
			mv.setViewName("/option/reportType/reportType2");
			skey = "userTypeCode2";
		} else {
			thvo.setBasLcod(pCode);//소
			mv.setViewName("/option/reportType/reportType3");
			skey = "userTypeCode3";
		}
		//thvo.setBasScod(code);//소
		thvo.setCdFlag(cdFlag);
		//optionService.deleteRpt(thvo);
		//삭제시
		//대랑 소일때는 그냥 값만 배열로 넘김 foreach
		//중일때는 정규식 like에서 포이치
		rptOptService.deleteRpt(cdFlag,pCode,chkArr);
		//삭제 후 인덱스 수정시
		//반복 for문 어려번 실행 (띄엄띄엄 삭제할 수도 있으므로)
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = rptOptService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		return mv;
	}

	
}
