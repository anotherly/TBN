package kr.co.wizbrain.tbn.producer.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.wizbrain.tbn.producer.service.ProducerService;

@Controller
public class ProducerController {
	public static final Logger logger = LoggerFactory.getLogger(ProducerController.class);
	public String url="";
	
	@Resource(name="producerService")
	private ProducerService producerService;
	
//	//주소에 맞게 매핑
//	@RequestMapping(value="/producer/*.do")
//	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
//		logger.debug("▶▶▶▶▶▶▶.producer 최초 컨트롤러 진입 httpSession : "+httpSession);
//		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
//		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
//		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
//		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
//		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
//		return url;
//	}
}




















