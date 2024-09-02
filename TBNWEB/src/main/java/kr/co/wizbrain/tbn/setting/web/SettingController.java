package kr.co.wizbrain.tbn.setting.web;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.comm.SessionListener;
import kr.co.wizbrain.tbn.statistic.vo.StatisticVO;
import kr.co.wizbrain.tbn.setting.service.SettingService;
import kr.co.wizbrain.tbn.setting.vo.SettingVO;

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
public class SettingController {
	
	public static final Logger logger = LoggerFactory.getLogger(SettingController.class);
	
	@Resource(name="settingService")
	private SettingService settingService;
	public String url="";
	public boolean isClose = false;
	
	
	//주소에 맞게 매핑
	@RequestMapping(value="/setting/*.do")
	public String SettingUrlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.Setting 최초 컨트롤러 진입 httpSession : "+httpSession);
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
}
