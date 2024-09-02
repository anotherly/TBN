package kr.co.wizbrain.tbn.chgLog.web;

import org.springframework.ui.Model;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.user.vo.UserVO;
import kr.co.wizbrain.tbn.user.service.UserService;
import kr.co.wizbrain.tbn.chgLog.service.ChgLogService;
import kr.co.wizbrain.tbn.comm.SessionListener;
import kr.co.wizbrain.tbn.option.service.AuthService;
import kr.co.wizbrain.tbn.option.service.IntelService;
import kr.co.wizbrain.tbn.option.vo.AuthVo;
import kr.co.wizbrain.tbn.option.vo.IntelVO;

/**
 * 사용자 컨트롤러 클래스
 * 
 * @author 솔루션사업팀 정다빈
 * @since 2021.09.23
 * @version 1.0
 * @see
 *
 * 		<< 개정이력(Modification Information) >>
 *
 *      수정일 수정자 수정내용 ------- -------- --------------------------- 2021.09.23 정다빈
 *      최초 생성
 */

@Controller
public class ChgLogController {

	public static final Logger logger = LoggerFactory.getLogger(ChgLogController.class);
	
	public String url = "";
	
	@Resource(name="chgLogService")
	private ChgLogService chgLogService;
	
	//주소에 맞게 매핑
	@RequestMapping(value="/chgLog/*.do")
	public String userUrlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.main 최초 컨트롤러 진입 httpSession : "+httpSession);
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
}
