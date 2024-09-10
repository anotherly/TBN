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

import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.option.vo.OptColorVo;
import kr.co.wizbrain.tbn.option.service.ColorService;

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
public class ColorController {
	
	public static final Logger logger = LoggerFactory.getLogger(ColorController.class);
	
	@Resource(name="colorService")
	private ColorService colorService;
	public String url="";
	
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
	@RequestMapping(value= {"/option/color/main.do","/option/color/code2.do","/option/color/code3.do"})
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
			surl = "/option/color/tabReportType";
			skey = "reportType1";
		}else {//중,소
			surl = "/option/color/reportType"+typeNum;
			skey = "userTypeCode"+typeNum;
			if (typeNum.equals("2")) {//중
				thvo.setBasLcod(tCode);
			} else {//소
				thvo.setBasLcod(pCode+tCode);
			}
		}
		
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = colorService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		
		List<OptColorVo> clist = new ArrayList<OptColorVo>();
		clist = colorService.selectColorCode();
		
		mv.addObject("colorList", clist);
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
	@RequestMapping(value="/option/color/save.do")
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
			mv.setViewName("/option/color/tabReportType");
			skey = "reportType1";
		} else if (cdFlag.equals("2")){
			mv.setViewName("/option/color/reportType2");
			skey = "userTypeCode2";
		} else {
			mv.setViewName("/option/color/reportType3");
			skey = "userTypeCode3";
		}
		colorService.insertRpt(thvo);
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = colorService.selectRpt(thvo);
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
	@RequestMapping(value="/option/color/modify.do")
	public ModelAndView updateRpt(HttpSession httpSession, HttpServletRequest request,Model model
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="pCode") String pCode
			,@RequestParam(required=false, value="code") String code
			,@RequestParam(required=false, value="color") String color
			,@RequestParam(required=false, value="colorName") String colorName
			) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		String skey="";
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.제보유형 등록 ");
		OptRptVo thvo = new OptRptVo();
		thvo.setBasLcod(pCode);
		thvo.setBasScod(code);
		thvo.setCdFlag(cdFlag);
		if (cdFlag.equals("1")){//대
			mv.setViewName("/option/color/tabReportType");
			skey = "reportType1";
			thvo.setfColor(color);
			thvo.setfName(colorName);
		} else {
			mv.setViewName("/option/color/reportType3");
			skey = "userTypeCode3";
			thvo.setbColor(color);
			thvo.setbName(colorName);
		}
		colorService.updateRpt(thvo);
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = colorService.selectRpt(thvo);
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
	
	@RequestMapping("/option/color/delete")
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
			mv.setViewName("/option/color/tabReportType");
			skey = "reportType1";
		} else if (cdFlag.equals("2")){//중
			mv.setViewName("/option/color/reportType2");
			skey = "userTypeCode2";
		} else {
			thvo.setBasLcod(pCode);//소
			mv.setViewName("/option/color/reportType3");
			skey = "userTypeCode3";
		}
		//thvo.setBasScod(code);//소
		thvo.setCdFlag(cdFlag);
		//optionService.deleteRpt(thvo);
		//삭제시
		//대랑 소일때는 그냥 값만 배열로 넘김 foreach
		//중일때는 정규식 like에서 포이치
		colorService.deleteRpt(cdFlag,pCode,chkArr);
		//삭제 후 인덱스 수정시
		//반복 for문 어려번 실행 (띄엄띄엄 삭제할 수도 있으므로)
		List<OptRptVo> thlist = new ArrayList<OptRptVo>();
		thlist = colorService.selectRpt(thvo);
		mv.addObject(skey, thlist);
		return mv;
	}

	
}
