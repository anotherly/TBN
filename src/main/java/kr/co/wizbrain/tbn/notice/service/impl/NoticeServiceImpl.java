package kr.co.wizbrain.tbn.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.notice.mapper.NoticeMapper;
import kr.co.wizbrain.tbn.notice.service.NoticeService;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;
import kr.co.wizbrain.tbn.notice.vo.nFileVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name="noticeMapper")
	private NoticeMapper noticeMapper;
	
	// 24-11-14 : 공지사항 전체 조회
	@Override
	public List<NoticeVO> noticeList(String searchText,String searchDate,String authCode) throws Exception {
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("searchText", searchText);
		map.put("searchDate", searchDate);
		
		return noticeMapper.noticeList(searchText,searchDate,authCode);
	}
	
	// 24-11-15 : 공지사항 등록
	@Override
	public void insertNotice(NoticeVO vo) throws Exception {
		noticeMapper.insertNotice(vo);
	}
	
	
	// 등록된 공지사항의 ID 가져오기
	@Override
	public String selectIdOne() throws Exception {
		return noticeMapper.selectIdOne();
	}
	
	// 파일 등록하기
	@Override
	public void insertFile(List<nFileVO> inputVo) throws Exception {
		noticeMapper.insertFile(inputVo);
	}
	
	// 24-11-15 : 공지사항 상세
	@Override
	public List<NoticeVO> detailNotice(String noticeId) throws Exception {
		return noticeMapper.detailNotice(noticeId);
	}
	
	// 파일 상세
	@Override
	public List<NoticeVO> getFileList(String noticeId) throws Exception {
		return noticeMapper.getFileList(noticeId);
	}
	
	
	// 24-11-15 : 공지사항 수정
	@Override
	public void updateNotice(NoticeVO vo) throws Exception {
		noticeMapper.updateNotice(vo);
	}
	
	// 24-11-15 : 공지사항 삭제
	@Override
	public void deleteNotice(String noticeId) throws Exception {
		noticeMapper.deleteNotice(noticeId);
	}
	
	// 삭제할 파일 조회
	@Override
	public List<NoticeVO> selectFileList2(String noticeId) throws Exception {
		return noticeMapper.selectFileList2(noticeId);
	}
	
	// 파일 삭제
	@Override
	public void deleteNFile(String noticeId) throws Exception {
		noticeMapper.deleteNFile(noticeId);
	}
	
	@Override
	public List<NoticeVO> selectNotice(String today, String authCode) throws Exception {
		return noticeMapper.selectNotice(today, authCode);
	}
	
	// 24-12-09 : 조회된 공지사항이 여러 개인 경우 공지사항 목록으로 이동하게 하기 위한 행 개수 조회
	@Override
	public List<NoticeVO> selectNoticeCnt(String today) throws Exception {
		return noticeMapper.selectNoticeCnt(today);
	}
	
	// 파일 다운로드
	@Override
	public List<NoticeVO> selectFileList(NoticeVO vo) throws Exception {
		return noticeMapper.selectFileList(vo);
	}
	
	// 25-02-11 : 공지사항을 등록하면, 등록된 공지사항의 ID를 가져온다. (첨부 파일 용)
	@Override
	public String selectEventId() throws Exception{
		return noticeMapper.selectEventId();
	}
	
	
	// 파일 일괄 삭제 - 파일 이름 찾기
	@Override
	public String selectFileName(String fileId)throws Exception {
		return noticeMapper.selectFileName(fileId);
	}
	
	// 파일 일괄 삭제
	@Override
	public void deleteFileOne(String fileId) throws Exception {
		noticeMapper.deleteFileOne(fileId);
	}
}
