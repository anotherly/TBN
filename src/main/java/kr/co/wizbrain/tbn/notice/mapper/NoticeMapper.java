package kr.co.wizbrain.tbn.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.notice.vo.NoticeVO;
import kr.co.wizbrain.tbn.notice.vo.nFileVO;

@Mapper("noticeMapper")
public interface NoticeMapper {

	//24-11-14 :공지사항 전체 조회
	public List<NoticeVO> noticeList(@Param("searchText")String searchText, @Param("searchDate") String searchDate, @Param("authCode") String authCode) throws Exception;

	// 24-11-15 : 공지사항 등록
	public void insertNotice(NoticeVO vo) throws Exception;
	
	// 등록한 공지사항의 ID 가져오기
	public String selectIdOne() throws Exception;
	
	// 파일 등록하기
	public void insertFile(List<nFileVO> inputVo) throws Exception;
	
	// 24-11-15 : 공지사항 상세
	public List<NoticeVO> detailNotice(String noticeId) throws Exception;
	
	// 파일 가져오기
	public List<NoticeVO> getFileList(String noticeId) throws Exception;
	
	// 24-11-15 : 공지사항 수정
	public void updateNotice(NoticeVO vo) throws Exception;
	
	// 24-11-15 : 공지사항 삭제
	public void deleteNotice(String noticeId) throws Exception;
	
	// 삭제 할 파일 조회
	public List<NoticeVO> selectFileList2(String noticeId) throws Exception;
	
	// 파일 삭제
	public void deleteNFile(String noticeId) throws Exception;
	
	public List<NoticeVO> selectNotice(@Param("today") String today, @Param("authCode") String authCode) throws Exception;
	
	public List<NoticeVO> selectNoticeCnt(String today) throws Exception;
	
	public List<NoticeVO> selectFileList(NoticeVO vo) throws Exception;
	
	
	
	// 25-02-11 : 공지사항을 등록하면, 등록된 공지사항의 ID를 가져온다. (첨부 파일 용)
	public String selectEventId() throws Exception;
	
	// 파일 일괄 삭제 - 파일 이름 찾기
	public String selectFileName(String fileId)throws Exception;
	
	// 파일 일괄 삭제
	public void deleteFileOne(String fileId)throws Exception;
}
