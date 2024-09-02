package kr.co.wizbrain.tbn.option.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
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

public interface InfrmOptService {

	//<!-- #############통신원 유형################ -->
	//제보유형 조회 대
	public List<OptInftVo> selectInft1(OptInftVo thvo);
	//제보유형 조회 중
	public List<OptInftVo> selectInft2(OptInftVo thvo);
	//제보유형 조회 소
	public List<OptInftVo> selectInft3(OptInftVo thvo);
	//통신원유형 등록 대
	public void insertInft1(OptInftVo thvo);
	//통신원유형 등록 중
	public void insertInft2(OptInftVo thvo);
	//통신원유형 등록 소
	public void insertInft3(OptInftVo thvo);
	//통신원유형 수정 대
	public void updateInft1(OptInftVo thvo);
	//통신원유형 수정 중
	public void updateInft2(OptInftVo thvo);
	//통신원유형 수정 소
	public void updateInft3(OptInftVo thvo);
	//통신원유형 삭제 대
	public void deleteInft1(String cdFlag,  List<String> chkArr, String areaCode);
	//통신원유형 삭제 중
	public void deleteInft2(String cdFlag, String pCode, List<String> chkArr, String areaCode);
	//통신원유형 삭제 소
	public void deleteInft3(String cdFlag, String pCode, String pCode2, List<String> chkArr, String areaCode);
}
