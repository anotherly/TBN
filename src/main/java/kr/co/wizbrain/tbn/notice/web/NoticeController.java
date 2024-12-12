package kr.co.wizbrain.tbn.notice.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
/*import javax.enterprise.inject.Model;*/
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.notice.service.NoticeService;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;
import kr.co.wizbrain.tbn.receipt.web.ReceiptController;

@Controller
public class NoticeController {

	public static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	// 이동할 주소 변수
	public String url="";
	public boolean isClose = false;
	
	
	// 24-11-14 : 공지사항  목록 + 검색 기능
	@RequestMapping(value="/notice/notice.do")
	public ModelAndView NoticeMapping(@RequestParam(value="searchText" ,defaultValue="") String searchText, @RequestParam(value="searchDate" ,defaultValue="") String searchDate,
			HttpSession httpSession, HttpServletRequest request, Model model) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.Notice. 최초 컨트롤러 진입 httpSession : "+httpSession);
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		// 공지사항 전체 조회 (목록)
		List<NoticeVO> noticeList = noticeService.noticeList(searchText,searchDate);
		mav.addObject("noticeList",noticeList);
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);

		mav.setViewName("/notice/notice");
		return mav;
	}
	
	// 24-11-15 : 공지사항 등록 페이지로 이동
	@RequestMapping(value="/notice/goInsert.do")
	public ModelAndView goInsertNotice() throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.setViewName("/notice/insertNotice");
		
		return mav;
	}
	
	// 24-11-15 : 공지사항 등록 처리
	@RequestMapping(value="/notice/insert.do")
	public ModelAndView insertNotice(@ModelAttribute NoticeVO vo) throws Exception{
		noticeService.insertNotice(vo);
		
		String msg = "success";
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("msg", msg);
		
		return mav;

	}
	
	// 24-11-15 : 공지사항 상세
	@RequestMapping(value="/notice/detailNotice.do")
	public ModelAndView detailNotice(@RequestParam("noticeId")String noticeId) throws Exception {		
		ModelAndView mav = new ModelAndView();
		
		List<NoticeVO> dList = noticeService.detailNotice(noticeId);
		mav.addObject("dList", dList);
		
		mav.setViewName("/notice/detailNotice");
		return mav;
	}
	
	// 24-11-15 : 공지사항 수정으로 이동
	@RequestMapping(value="/notice/goUpdate.do")
	public ModelAndView goUpdateNotice(@RequestParam("noticeId")String noticeId) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<NoticeVO> uList = noticeService.detailNotice(noticeId);
		mav.addObject("uList", uList);
		
		mav.setViewName("notice/updateNotice");	
		return mav;
	}
	
	// 24-11-15 : 공지사항 수정 처리
	@RequestMapping(value="/notice/update.do")
	public ModelAndView updateNotice(@ModelAttribute NoticeVO vo) throws Exception {
		noticeService.updateNotice(vo);
		
		String msg = "success";
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("msg", msg);
		
		return mav;
	}
	
	// 24-11-15 : 공지사항 삭제 처리
	@RequestMapping(value="/notice/delete.do")
	public ModelAndView deleteNotice(@RequestParam("noticeId")String noticeId) throws Exception {
		noticeService.deleteNotice(noticeId);
		
		String msg = "success";
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("msg", msg);
		
		return mav;
	}
	
	// 24-11-11 : 제보접수 페이지 이동 시 공지사항 조회
		@RequestMapping(value="/notice/selectNotice.ajax")
		public @ResponseBody ModelAndView selectNotice(@RequestParam("today")String today) throws Exception {
			
			ModelAndView mav = new ModelAndView("jsonView");
			List<NoticeVO> NoticeList = noticeService.selectNotice(today);
			
			int moreCount = (noticeService.selectNoticeCnt(today).size())-1; // 화면에 띄울 공지도 같이 포함이기 떄문에 -1 필요
			
			mav.addObject("NoticeList", NoticeList);	
			mav.addObject("moreCount", moreCount);
			
			return mav;
		}
		
}
