package kr.co.wizbrain.tbn.map.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.comm.RSSParser;
import kr.co.wizbrain.tbn.map.service.MapService;
import kr.co.wizbrain.tbn.map.vo.BoundsVO;
import kr.co.wizbrain.tbn.map.vo.CctvVO;
import kr.co.wizbrain.tbn.map.vo.ImsVO;

@Controller
public class MapController {
	public static final Logger logger = LoggerFactory.getLogger(MapController.class);
	public String url="";
	
	@Resource(name = "mapService")
	private MapService mapService;

	//주소에 맞게 매핑
	//@CrossOrigin("https://map.kakao.com/")
//	@RequestMapping(value="/map/*.do")
//	public ModelAndView urlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
//		//ModelAndView mv = new ModelAndView("jsonView");
//		ModelAndView mv = new ModelAndView();
//
//		logger.debug("▶▶▶▶▶▶▶.map 최초 컨트롤러 진입 httpSession : "+httpSession);
//		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
//		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
//		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
//		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
//		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
//		
//		mv.setViewName(url);
//		//mv.addObject("page", "https://map.kakao.com/");
//		return mv;
//		//return "https://map.kakao.com/";
//	}
	
	@RequestMapping(value="/map/*.do")
	public ModelAndView urlMapping(HttpSession httpSession, HttpServletRequest request,Model model) throws Exception{
		ModelAndView mv = new ModelAndView();
		logger.debug("▶▶▶▶▶▶▶.map 최초 컨트롤러 진입 httpSession : "+httpSession);
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		
		mv.setViewName(url);
		return mv;
	}
	
	@RequestMapping("/cctv/getCctvList.ajax")
	@ResponseBody
	public ModelAndView getCctvList(BoundsVO vo) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.getCctvList.ajax 진입!!!!!!!!!!!!!!!!");
		ModelAndView mv = new ModelAndView("jsonView");
		List<CctvVO> cctvList = mapService.getCctvList(vo);
		System.out.println("Cctv Size: " + cctvList.size());
		mv.addObject("data", cctvList);
		return mv;
	}
	
	@RequestMapping("/ims/getImsList.ajax")
	@ResponseBody
	public ModelAndView getImsList() throws Exception {
		logger.debug("▶▶▶▶▶▶▶.getImsList.ajax 진입!!!!!!!!!!!!!!!!");
		ModelAndView mv = new ModelAndView("jsonView");
		RSSParser parser = new RSSParser();
		List<ImsVO> imsList = parser.imsParser();
		mv.addObject("data", imsList);
		System.out.println("Ims Size : " + imsList.size());
		return mv;
	}
	
}



















