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
public class InfrmOptController {
	
	public static final Logger logger = LoggerFactory.getLogger(InfrmOptController.class);
	
	@Resource(name="infrmOptService")
	private InfrmOptService infrmOptService;
	public String url="";
	public boolean isClose = false;
	@Resource(name="areaOptService")
	private AreaOptService areaOptService;
	
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
	@RequestMapping(value= {"/option/infrm/main.do","/option/infrm/code2.do","/option/infrm/code3.do"})
	public ModelAndView selectInft(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="typeNum") String typeNum
			,@RequestParam(required=false, value="selArea") String selArea
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String surl="";
		String skey="";
		List<OptInftVo> thlist = new ArrayList<OptInftVo>();
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶제보유형 분류 컨트롤러");
		OptInftVo thvo = new OptInftVo();
		thvo.setIfmId1(pCode);
		thvo.setIfmId2(tCode);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}else{
			if(url.equals("/option/infrm/main")) {
				//최초 지역코드 idx 맨 처음 (부산교통방송)
				String fArea= areaOptService.selectAreaOpt1(new OptAreaVo()).get(0).getAreaCode();
				thvo.setAreaCode(fArea);
			}else {
				thvo.setAreaCode(selArea);
			}
		}
		if(typeNum==null) {//대
			surl = "/option/infrm/tabReportType";
			skey = "reportType1";
			thlist = infrmOptService.selectInft1(thvo);
		}else {//중,소
			surl = "/option/infrm/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			if (typeNum.equals("2")) {
				thlist = infrmOptService.selectInft2(thvo);
			} else {
				thlist = infrmOptService.selectInft3(thvo);
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
	@RequestMapping(value="/option/infrm/save.do")
	public ModelAndView insertInft(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="value") String vName
			,@RequestParam(required=false, value="selArea") String selArea
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptInftVo thvo = new OptInftVo();
		List<OptInftVo> thlist = new ArrayList<OptInftVo>();
		
		thvo.setIfmName(vName);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}else{
			thvo.setAreaCode(selArea);
		}
		thvo.setIfmId1(pCode);
		thvo.setIfmId2(tCode);
		
		if (cdFlag.equals("1")){
			mv.setViewName("/option/infrm/tabReportType");
			skey = "reportType1";
			infrmOptService.insertInft1(thvo);
			thlist = infrmOptService.selectInft1(thvo);
		} else if (cdFlag.equals("2")){
			mv.setViewName("/option/infrm/reportType2");
			skey = "userTypeCode2";
			infrmOptService.insertInft2(thvo);
			thlist = infrmOptService.selectInft2(thvo);
		} else {
			mv.setViewName("/option/infrm/reportType3");
			skey = "userTypeCode3";
			infrmOptService.insertInft3(thvo);
			thlist = infrmOptService.selectInft3(thvo);
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
	@RequestMapping(value="/option/infrm/modify.do")
	public ModelAndView updateInft(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="pCode2") String pCode2
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="codeName") String codeName
			,@RequestParam(required=false, value="selArea") String selArea
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptInftVo thvo = new OptInftVo();
		List<OptInftVo> thlist = new ArrayList<OptInftVo>();
		thvo.setIfmName(codeName);
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}else{
			thvo.setAreaCode(selArea);
		}
		if (cdFlag.equals("1")){
			thvo.setIfmId1(code);
			mv.setViewName("/option/infrm/tabReportType");
			skey = "reportType1";
			infrmOptService.updateInft1(thvo);
			thlist = infrmOptService.selectInft1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setIfmId1(pCode);
			thvo.setIfmId2(code);
			mv.setViewName("/option/infrm/reportType2");
			skey = "userTypeCode2";
			infrmOptService.updateInft2(thvo);
			thlist = infrmOptService.selectInft2(thvo);
		} else {
			thvo.setIfmId1(pCode);
			thvo.setIfmId2(pCode2);
			thvo.setIfmId3(code);
			mv.setViewName("/option/infrm/reportType3");
			skey = "userTypeCode3";
			infrmOptService.updateInft3(thvo);
			thlist = infrmOptService.selectInft3(thvo);
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
	
	@RequestMapping("/option/infrm/delete.do")
	public ModelAndView deleteInft(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="pCode2") String pCode2
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="selArea") String selArea
			,@RequestParam(required=false, value="chkValList[]")List<String> chkArr
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptInftVo thvo = new OptInftVo();
		List<OptInftVo> thlist = new ArrayList<OptInftVo>();
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}else{
			thvo.setAreaCode(selArea);
		}
		if (cdFlag.equals("1")){
			mv.setViewName("/option/infrm/tabReportType");
			skey = "reportType1";
			infrmOptService.deleteInft1(cdFlag, chkArr,thvo.getAreaCode());
			thlist = infrmOptService.selectInft1(thvo);
		} else if (cdFlag.equals("2")){
			thvo.setIfmId1(pCode);
			thvo.setIfmId2(code);
			mv.setViewName("/option/infrm/reportType2");
			skey = "userTypeCode2";
			infrmOptService.deleteInft2(cdFlag, pCode, chkArr,thvo.getAreaCode());
			thlist = infrmOptService.selectInft2(thvo);
		} else {
			thvo.setIfmId1(pCode);
			thvo.setIfmId2(pCode2);
			thvo.setIfmId3(code);
			mv.setViewName("/option/infrm/reportType3");
			skey = "userTypeCode3";
			infrmOptService.deleteInft3(cdFlag, pCode,pCode2, chkArr,thvo.getAreaCode());
			thlist = infrmOptService.selectInft3(thvo);
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
