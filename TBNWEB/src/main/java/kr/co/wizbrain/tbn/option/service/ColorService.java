package kr.co.wizbrain.tbn.option.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.option.vo.OptColorVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.user.vo.UserVO;

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

public interface ColorService {

	//---------제보유형
	//제보유형 조회
	public List<OptRptVo> selectRpt(OptRptVo thvo);
	//제보유형 등록
	public void insertRpt(OptRptVo thvo);
	//제보유형 수정
	public void updateRpt(OptRptVo thvo);
	//제보유형 삭제
	public void deleteRpt(String cdFlag, String pcode, List<String> chkArr);
	
	public List<OptColorVo> selectColorCode();
}
