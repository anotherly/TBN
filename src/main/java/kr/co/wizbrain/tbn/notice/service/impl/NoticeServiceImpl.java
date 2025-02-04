package kr.co.wizbrain.tbn.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.notice.mapper.NoticeMapper;
import kr.co.wizbrain.tbn.notice.service.NoticeService;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name="noticeMapper")
	private NoticeMapper noticeMapper;
	
	// 24-11-14 : 공지사항 전체 조회
	@Override
	public List<NoticeVO> noticeList(String searchText,String searchDate) throws Exception {
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("searchText", searchText);
		map.put("searchDate", searchDate);
		
		return noticeMapper.noticeList(map);
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
	public void insertFile(List<NoticeVO> inputVo) throws Exception {
		noticeMapper.insertFile(inputVo);
	}
	
	// 24-11-15 : 공지사항 상세
	@Override
	public List<NoticeVO> detailNotice(String noticeId) throws Exception {
		return noticeMapper.detailNotice(noticeId);
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
	
	@Override
	public List<NoticeVO> selectNotice(String today) throws Exception {
		return noticeMapper.selectNotice(today);
	}
	
	// 24-12-09 : 조회된 공지사항이 여러 개인 경우 공지사항 목록으로 이동하게 하기 위한 행 개수 조회
	@Override
	public List<NoticeVO> selectNoticeCnt(String today) throws Exception {
		return noticeMapper.selectNoticeCnt(today);
	}
}
