package kr.co.wizbrain.tbn.chgLog.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.chgLog.vo.ChgLogVO;

/**
 * 사용자 매퍼 클래스
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

@Mapper("chgLogMapper")
public interface ChgLogMapper{

	void insertChgLog(ChgLogVO thvo);

	List<ChgLogVO> selectChgLog(ChgLogVO thvo);
	
}