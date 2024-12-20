package kr.co.wizbrain.tbn.notice.service;

import java.util.List;

import kr.co.wizbrain.tbn.notice.vo.NoticeVO;

public interface NoticeService {
	
	// 24-11-14 : 전체 공지사항 조회
	public List<NoticeVO> noticeList(String searchText,String searchDate) throws Exception;
	
	// 24-11-15 : 공지사항 등록
	public void insertNotice(NoticeVO vo) throws Exception;
	
	// 24-11-15 : 공지사항 상세
	public List<NoticeVO> detailNotice(String noticeId) throws Exception;
	
	// 24-11-15 : 공지사항 수정
	public void updateNotice(NoticeVO vo) throws Exception;
	
	// 24-11-15 : 공지사항 삭제
	public void deleteNotice(String noticeId) throws Exception;
	
	public List<NoticeVO> selectNotice(String today) throws Exception;
	
	// 24-12-09 : 조회된 공지사항이 여러 개인 경우 공지사항 목록으로 이동하게 하기 위한 행 개수 조회
	public List<NoticeVO> selectNoticeCnt(String today) throws Exception;
}
