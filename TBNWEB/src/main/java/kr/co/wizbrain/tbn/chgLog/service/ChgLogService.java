package kr.co.wizbrain.tbn.chgLog.service;

import java.util.List;

import kr.co.wizbrain.tbn.chgLog.vo.ChgLogVO;


/**
 * 사용자 서비스 클래스
 * @author 솔루션사업팀 정다빈
 * @since 2021.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2021.07.23  정다빈           최초 생성
 */

public interface ChgLogService {
	
	List<ChgLogVO> selectChgLog(ChgLogVO thvo);
	void insertChgLog(ChgLogVO thvo);

}
