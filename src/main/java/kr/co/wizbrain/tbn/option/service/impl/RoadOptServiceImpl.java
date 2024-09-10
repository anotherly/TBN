package kr.co.wizbrain.tbn.option.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.RoadOptMapper;
import kr.co.wizbrain.tbn.option.service.RoadOptService;
import kr.co.wizbrain.tbn.option.vo.OptRoadVo;

/**
 * 사용자 서비스 구현 클래스
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

@Service("roadOptService")
public class RoadOptServiceImpl implements RoadOptService{
	
	@Resource(name="roadOptMapper")
	private RoadOptMapper roadOptMapper;
	

	//<!-- #############통신원 유형################ -->

	@Override
	public List<OptRoadVo> selectRoadOpt1(OptRoadVo thvo) {
		return roadOptMapper.selectRoadOpt1(thvo);
	}

	@Override
	public List<OptRoadVo> selectRoadOpt2(OptRoadVo thvo) {
		return roadOptMapper.selectRoadOpt2(thvo);
	}

	@Override
	public List<OptRoadVo> selectRoadOpt3(OptRoadVo thvo) {
		return roadOptMapper.selectRoadOpt3(thvo);
	}
	
	@Override
	public void insertRoadOpt1(OptRoadVo thvo) {
		roadOptMapper.insertRoadOpt1(thvo);
		
	}

	@Override
	public void insertRoadOpt2(OptRoadVo thvo) {
		roadOptMapper.insertRoadOpt2(thvo);
		
	}
	
	@Override
	public void insertRoadOpt3(OptRoadVo thvo) {
		roadOptMapper.insertRoadOpt3(thvo);
		
	}

	@Override
	public void updateRoadOpt1(OptRoadVo thvo) {
		roadOptMapper.updateRoadOpt1(thvo);
		
	}

	@Override
	public void updateRoadOpt2(OptRoadVo thvo) {
		roadOptMapper.updateRoadOpt2(thvo);
		
	}
	
	@Override
	public void updateRoadOpt3(OptRoadVo thvo) {
		roadOptMapper.updateRoadOpt3(thvo);
		
	}

	@Override
	public void deleteRoadOpt1(String cdFlag, List<String> chkArr) {
		roadOptMapper.deleteRoadOpt1(cdFlag, chkArr);
		
	}

	@Override
	public void deleteRoadOpt2(String cdFlag, String pCode, List<String> chkArr) {
		roadOptMapper.deleteRoadOpt2(cdFlag, pCode, chkArr);
		
	}
	
	@Override
	public void deleteRoadOpt3(String cdFlag, String pCode,String pCode2, List<String> chkArr) {
		roadOptMapper.deleteRoadOpt3(cdFlag, pCode,pCode2, chkArr);
		
	}

}
