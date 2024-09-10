package kr.co.wizbrain.tbn.option.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.option.service.IntelService;
import kr.co.wizbrain.tbn.option.vo.IntelVO;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;



/**
 * 사용자 컨트롤러 클래스
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
@Controller
public class IntelController {
	public static final Logger logger = LoggerFactory.getLogger(IntelController.class);
	
	@Resource(name="intelService")
	private IntelService intelService;
	public String url="";
	
	@RequestMapping(value= {"/option/inTel/main.do","/option/inTel/code2.do","/option/inTel/code3.do"})
	public ModelAndView selectInTel(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="tCode") String tCode
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="typeNum") String typeNum
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String surl="";
		String skey="";
		List<IntelVO> thlist = new ArrayList<IntelVO>();
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶내선번호 분류 컨트롤러");
		IntelVO thvo = new IntelVO();
		// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login");
		if(!(nlVo.getAuthCode().equals("999"))) {//999 관리자 권한
			thvo.setAreaCode(nlVo.getRegionId());
		}
		if(typeNum==null) {//대
			surl = "/option/inTel/tabReportType";
			skey = "reportType1";
			thlist = intelService.selectIntel1(thvo);
		}else {//중,소
			thvo.setAreaCode(tCode);
			surl = "/option/inTel/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			thlist = intelService.selectIntel2(thvo);
		}
		mv.addObject(skey, thlist);
		mv.setViewName(surl);
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
	@RequestMapping(value="/option/inTel/modify.do")
	public ModelAndView updateInTel(HttpSession httpSession, HttpServletRequest request,Model model
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
		IntelVO thvo = new IntelVO();
		List<IntelVO> thlist = new ArrayList<IntelVO>();
		thvo.setItId(code);
		
		if (cdFlag.equals("1")){
			thvo.setAreaCode(code);
			thvo.setRpTel(codeName);
			mv.setViewName("/option/inTel/tabReportType");
			skey = "reportType1";
			intelService.updateRptel(thvo);
			thlist = intelService.selectIntel1(new IntelVO());
		} else if (cdFlag.equals("2")){
			thvo.setInTel(codeName);
			mv.setViewName("/option/inTel/reportType2");
			skey = "userTypeCode2";
			intelService.updateIntel(thvo);
			thvo = new IntelVO();
			thvo.setAreaCode(pCode);
			thlist = intelService.selectIntel2(thvo);
		}else {
			thvo.setRptTel(codeName);
			mv.setViewName("/option/inTel/reportType2");
			skey = "userTypeCode2";
			intelService.updateIntel(thvo);
			thvo = new IntelVO();
			thvo.setAreaCode(pCode);
			thlist = intelService.selectIntel2(thvo);
		}
		
		mv.addObject(skey, thlist);
		return mv;
	}
	
}
