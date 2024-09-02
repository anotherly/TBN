package kr.co.wizbrain.tbn.option.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.option.service.AuthService;
import kr.co.wizbrain.tbn.option.vo.AuthVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import lombok.extern.slf4j.Slf4j;


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
public class AuthController {
	
	public static final Logger logger = LoggerFactory.getLogger(ColorController.class);
	
	@Resource(name="authService")
	private AuthService authService;
	public String url="";
	
	@RequestMapping(value="/option/auth/authList.do")
	public @ResponseBody ModelAndView selectAuth1(HttpServletRequest request
			,Model model, @ModelAttribute AuthVo inputVo) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.setting 최초 컨트롤러 진입 httpSession : "+request.getSession());
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mv = new ModelAndView("jsonView");
		List<AuthVo> thlist = new ArrayList<AuthVo>();
		AuthVo thvo = new AuthVo();
		thlist = authService.selectAuth1(thvo);
		mv.addObject("authList", thlist);
		mv.addObject("msg", "메시지테스트");
		mv.setViewName(url);
		return mv;
	}
	
	@RequestMapping(value= {"/option/auth/authList2.do","/option/auth/authList3.do"})
	public @ResponseBody ModelAndView selectAuth2(HttpServletRequest request,Model model
			, @ModelAttribute AuthVo inputVo
			,@RequestParam(required=false, value="cdFlag") String cdFlag
			,@RequestParam(required=false, value="authCode") String authCode
			,@RequestParam(required=false, value="idx") String idx
			) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		List<AuthVo> thlist = new ArrayList<AuthVo>();
		AuthVo thvo = new AuthVo();
		String surl="";
		
		surl ="/option/auth/authList"+cdFlag;
		thvo.setAuthCode(authCode);
		thvo.setCdFlag(cdFlag);
		thvo.setIdx(idx);
		thlist = authService.selectAuth2(thvo);
		mv.addObject("authMenuList", thlist);
		mv.setViewName(surl);
		return mv;
	}
	
	@RequestMapping(value="/option/updateAuth.do")
	public @ResponseBody ModelAndView updateAuth(
			HttpServletRequest request,Model model
			//, @ModelAttribute AuthVo inputVo
			,@RequestParam(required=false, value="menuList[]") List<String> menuList
			,@RequestParam(required=false, value="authCode") String authCode
			//,@RequestParam(required=false, value="idx") String idx
			) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		List<AuthVo> thlist = new ArrayList<AuthVo>();
		String msg ="";
		authService.updateAuth(authCode,menuList);
		msg="저장되었습니다.";
		logger.debug(msg);	
		mv.addObject("msg", msg);
		return mv;
	}
}
