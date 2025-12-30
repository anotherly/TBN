package kr.co.wizbrain.tbn.notice.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
/*import javax.enterprise.inject.Model;*/
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.wizbrain.tbn.comm.util.UploadPolicy;
import kr.co.wizbrain.tbn.notice.service.NoticeService;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;
import kr.co.wizbrain.tbn.notice.vo.nFileVO;
import kr.co.wizbrain.tbn.receipt.web.ReceiptController;
import kr.co.wizbrain.tbn.user.vo.UserVO;

@Controller
public class NoticeController implements ApplicationContextAware{

	public static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);
	private WebApplicationContext context = null;
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	// 이동할 주소 변수
	public String url="";
	public boolean isClose = false;
	
	
	//context
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	
	
	
	
	
	// 24-11-14 : 공지사항  목록 + 검색 기능
	@RequestMapping(value="/notice/notice.do")
	public ModelAndView NoticeMapping(@RequestParam(value="searchText" ,defaultValue="") String searchText, @RequestParam(value="searchDate" ,defaultValue="") String searchDate,
			HttpSession httpSession, HttpServletRequest request, Model model) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.Notice. 최초 컨트롤러 진입 httpSession : "+httpSession);
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURL() : "+request.getRequestURL());
		logger.debug("▶▶▶▶▶▶▶.request.getRequestURI() : "+request.getRequestURI());
		logger.debug("▶▶▶▶▶▶▶.request.getContextPath() : "+request.getContextPath());
		
		ModelAndView mav = new ModelAndView("jsonView");
		UserVO nlVo = (UserVO) request.getSession().getAttribute("login"); // 현재 로그인 중인 사용자 정보 가져오기
		
		String authCode = nlVo.getAuthCode(); // 현재 로그인 중인 사용자의 권한 코드 가져오기
		// 공지사항 전체 조회 (목록)
		List<NoticeVO> noticeList = noticeService.noticeList(searchText,searchDate,authCode);
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
	

/*	// 24-11-15 : 공지사항 등록 처리 (첨부파일 X)
	@RequestMapping(value="/notice/insert.do")
	public ModelAndView insertNotice(@ModelAttribute NoticeVO vo) throws Exception {
		
		ModelAndView mav = new ModelAndView("jsonView");
		
		// 공지사항 등록하기 (파일 첨부 제외)
		noticeService.insertNotice(vo);
		
		return mav;

	}
	*/
	
	
	// 25-02-11 : 공지사항 등록 처리 (첨부파일O)
	@RequestMapping(value="/notice/insert.do") 
	public ModelAndView insertNotice(@RequestParam("multiFile") List<MultipartFile> multiFileList
			,HttpSession httpSession, HttpServletRequest request, Model model,
			@ModelAttribute("NoticeVO") NoticeVO vo) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		//보안취약점 6번 조치
		HttpSession session = request.getSession();
		Long lastTime = (Long) session.getAttribute("NOTICE_LAST_TIME");

		long now = System.currentTimeMillis();
		if (lastTime != null && (now - lastTime) < 10000) {
			mav.addObject("msg","시간 내 요청량이 최대치에 도달했습니다. 잠시 후 다시 시도하세요.");
			mav.addObject("cnt",0);
			return mav;
		}
		session.setAttribute("NOTICE_LAST_TIME", now);
		
		noticeService.insertNotice(vo); // 공지사항 등록 -> 행사 등록과 달리 등록 수정이 나뉘어져 있기 때문에 굳이 int를 return하지 않아도 된다.
		String nowNoticeId = noticeService.selectEventId(); // 등록된 공지사항의 ID 가져오는 쿼리문 실행
		
		try {
			String fileDir = context.getServletContext().getRealPath("/")+"noticeFile/"+ nowNoticeId +"/";
			FileUploadSave fus = new FileUploadSave();
			List<nFileVO> fileList = fus.fileUploadMultiple(multiFileList,fileDir,vo);
			
			if(fileList.size()!=0) {
				if (multiFileList != null && !multiFileList.isEmpty()) {
				    for (MultipartFile f : multiFileList) {
				        UploadPolicy.validate(f); // f가 비었으면(validate 내부에서) 그냥 return
				    }
				}
				noticeService.insertFile(fileList);
			}

			mav.addObject("cnt",1);
		} catch(Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			mav.addObject("cnt",0);
			mav.addObject("msg","시간 내 요청량이 최대치에 도달했습니다. 잠시 후 다시 시도하세요.");
		}
		return mav;
	}
	
	
	
	
	/*@RequestMapping(value="/notice/insert.do")
	public ModelAndView insertNotice(@ModelAttribute NoticeVO vo) throws Exception{
		noticeService.insertNotice(vo);
		
		String msg = "success";
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("msg", msg);
		
		return mav;

	}*/
	
	// 24-11-15 : 공지사항 상세
	@RequestMapping(value="/notice/detailNotice.do")
	public ModelAndView detailNotice(@RequestParam("noticeId")String noticeId) throws Exception {		
		ModelAndView mv = new ModelAndView("/notice/detailNotice");
		
		List<NoticeVO> dList = noticeService.detailNotice(noticeId);
		mv.addObject("dList", dList);

		List<NoticeVO> fileInfo = noticeService.getFileList(noticeId);
		if(fileInfo.size() != 0) {
			mv.addObject("fileInfo", fileInfo);			
		}
		
		return mv;
	}
	
	// 24-11-15 : 공지사항 수정으로 이동
	@RequestMapping(value="/notice/goUpdate.do")
	public ModelAndView goUpdateNotice(@RequestParam("noticeId")String noticeId) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<NoticeVO> uList = noticeService.detailNotice(noticeId);
		mav.addObject("uList", uList);
		
		List<NoticeVO> fileInfo = noticeService.getFileList(noticeId);
		if(fileInfo.size() != 0) {
			mav.addObject("fileInfo", fileInfo);			
		}

		mav.setViewName("notice/updateNotice");	
		return mav;
	}
	
	// 24-11-15 : 공지사항 수정 처리
	@RequestMapping(value="/notice/update.do")
	public ModelAndView updateNotice(@RequestParam("multiFile") List<MultipartFile> multiFileList
			,HttpSession httpSession, HttpServletRequest request, Model model, @ModelAttribute NoticeVO vo) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		//보안취약점 6번 조치
		HttpSession session = request.getSession();
		Long lastTime = (Long) session.getAttribute("NOTICE_LAST_TIME");

		long now = System.currentTimeMillis();
		if (lastTime != null && (now - lastTime) < 10000) {
			mav.addObject("msg","시간 내 요청량이 최대치에 도달했습니다. 잠시 후 다시 시도하세요.");
			mav.addObject("cnt",0);
			return mav;
		}
		session.setAttribute("NOTICE_LAST_TIME", now);
		
		noticeService.updateNotice(vo); // 내용 업데이트
		
		String nowNoticeId = vo.getNOTICE_ID();
		
		try {
			String fileDir = context.getServletContext().getRealPath("/")+"noticeFile/"+ nowNoticeId +"/";
			FileUploadSave fus = new FileUploadSave();
			List<nFileVO> fileList = fus.fileUploadMultiple(multiFileList,fileDir,vo);
			if(fileList.size()!=0) {
				if (multiFileList != null && !multiFileList.isEmpty()) {
				    for (MultipartFile f : multiFileList) {
				        UploadPolicy.validate(f); // f가 비었으면(validate 내부에서) 그냥 return
				    }
				}
				noticeService.insertFile(fileList);
			}
			
			mav.addObject("cnt",1);
		} catch(Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			mav.addObject("msg","저장에 실패했습니다");
			mav.addObject("cnt",0);
		}
		
		return mav;
	}
	
	// 24-11-15 : 공지사항 삭제 처리
	@RequestMapping(value="/notice/delete.do")
	public ModelAndView deleteNotice(@RequestParam("noticeId")String noticeId) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		List<NoticeVO> fileList = noticeService.selectFileList2(noticeId); // 파일이 존재하는지 조회
		
		if(fileList.size() > 0) { // 파일이 있다면
			noticeService.deleteNFile(noticeId); // 파일 데이터 삭제
			
			String fileDir = context.getServletContext().getRealPath("/")+"noticeFile/"+ noticeId +"/";
			// 실제 디렉토리에서 파일 삭제
			FileUploadSave fus = new FileUploadSave();
			fus.ndeleteFile(fileList,fileDir);
		}

		noticeService.deleteNotice(noticeId); // 데이터 삭제
		
		String msg = "success";
		mav.addObject("msg", msg);
		
		return mav;
	}
	
	// 24-11-11 : 제보접수 페이지 이동 시 공지사항 조회
		@RequestMapping(value="/common/selectNotice.ajax")
		public @ResponseBody ModelAndView selectNotice(@RequestParam("today")String today ,
				HttpSession httpSession, HttpServletRequest request, Model model) throws Exception {
			
			ModelAndView mav = new ModelAndView("jsonView");
			UserVO nlVo = (UserVO) request.getSession().getAttribute("login"); // 현재 로그인 중인 사용자 정보 가져오기
			
			String authCode = nlVo.getAuthCode(); // 현재 로그인 중인 사용자의 권한 코드 가져오기
			List<NoticeVO> NoticeList = noticeService.selectNotice(today,authCode);
			
			int moreCount = (noticeService.selectNoticeCnt(today).size())-1; // 화면에 띄울 공지도 같이 포함이기 떄문에 -1 필요
			
			mav.addObject("NoticeList", NoticeList);	
			mav.addObject("moreCount", moreCount);
			
			return mav;
		}
		
		
		
		
		
		// 파일 다운로드
		@RequestMapping(value="/common/noticefileDownload.do")
		public ModelAndView EventfileDownload(@RequestParam("fileId")String fileId ,ModelAndView mView
				,HttpServletRequest request, HttpServletResponse response) throws Exception {
			NoticeVO fvo = new NoticeVO();
			fvo.setFILE_ID(fileId);
			fvo = noticeService.selectFileList(fvo).get(0);
			
			String filePath = fvo.getFILE_DIR()+fvo.getFILE_NAME();
			fvo.setFilePath(filePath);
			mView.addObject("fvo", fvo);
			
			// 응답을 할 bean의 이름 설정
			mView.setViewName("nfileDownView");

			
			return mView;
		}
		
		
		
		
		// 파일 일괄 삭제
		@RequestMapping("/notice/eventOneDelete.do")
		public ModelAndView deleteOneEvent(Model model,@RequestParam("fileId") String fileId,
				@RequestParam("noticeId")String noticeId, @ModelAttribute("NoticeVO") NoticeVO paramVO) throws Exception {
			ModelAndView mv = new ModelAndView("jsonView");
					
					String fileDir = context.getServletContext().getRealPath("/")+"noticeFile/"+ noticeId +"/";
					// 실제 디렉토리에서 파일 삭제
					
					String fileName = noticeService.selectFileName(fileId);
					FileUploadSave fus = new FileUploadSave();
					fus.ndeleteFileOne(fileDir,fileName);
					
					
					// DB에서 파일 삭제
					noticeService.deleteFileOne(fileId);
					
					mv.addObject("fileName", fileName);
					mv.addObject("cnt", 1);
			
					return mv;
		}
}
