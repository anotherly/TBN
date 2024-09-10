package kr.co.wizbrain.tbn.option.service;

import java.util.List;

import kr.co.wizbrain.tbn.option.vo.OptRoadVo;

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

public interface RoadOptService {

	//<!-- #############통신원 유형################ -->
	//제보유형 조회 대
	public List<OptRoadVo> selectRoadOpt1(OptRoadVo thvo);
	//제보유형 조회 중
	public List<OptRoadVo> selectRoadOpt2(OptRoadVo thvo);
	//제보유형 조회 소
	public List<OptRoadVo> selectRoadOpt3(OptRoadVo thvo);
	//통신원유형 등록 대
	public void insertRoadOpt1(OptRoadVo thvo);
	//통신원유형 등록 중
	public void insertRoadOpt2(OptRoadVo thvo);
	//통신원유형 등록 중
	public void insertRoadOpt3(OptRoadVo thvo);
	//통신원유형 수정 대
	public void updateRoadOpt1(OptRoadVo thvo);
	//통신원유형 수정 중
	public void updateRoadOpt2(OptRoadVo thvo);
	//통신원유형 수정 중
	public void updateRoadOpt3(OptRoadVo thvo);
	//통신원유형 삭제 대
	public void deleteRoadOpt1(String cdFlag,  List<String> chkArr);
	//통신원유형 삭제 중
	public void deleteRoadOpt2(String cdFlag, String pCode, List<String> chkArr);
	
	public void deleteRoadOpt3(String cdFlag, String pCode,String pCode2, List<String> chkArr);
}
