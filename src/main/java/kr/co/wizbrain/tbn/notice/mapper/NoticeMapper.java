package kr.co.wizbrain.tbn.notice.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;

@Mapper("noticeMapper")
public interface NoticeMapper {

	//24-11-14 :공지사항 전체 조회
	public List<NoticeVO> noticeList(Map<String,String> map) throws Exception;

	// 24-11-15 : 공지사항 등록
	public void insertNotice(NoticeVO vo) throws Exception;
	
	// 24-11-15 : 공지사항 상세
	public List<NoticeVO> detailNotice(String noticeId) throws Exception;
	
	// 24-11-15 : 공지사항 수정
	public void updateNotice(NoticeVO vo) throws Exception;
	
	// 24-11-15 : 공지사항 삭제
	public void deleteNotice(String noticeId) throws Exception;
	
	public List<NoticeVO> selectNotice(String today) throws Exception;
}
