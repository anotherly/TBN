package kr.co.wizbrain.tbn.option.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.AreaOptMapper;
import kr.co.wizbrain.tbn.option.mapper.InfrmOptMapper;
import kr.co.wizbrain.tbn.option.service.AreaOptService;
import kr.co.wizbrain.tbn.option.service.InfrmOptService;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;
import kr.co.wizbrain.tbn.option.vo.OptInftVo;

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

@Service("areaOptService")
public class AreaOptServiceImpl implements AreaOptService{
	
	@Resource(name="areaOptMapper")
	private AreaOptMapper areaOptMapper;
	

	//<!-- #############통신원 유형################ -->

	@Override
	public List<OptAreaVo> selectAreaOpt1(OptAreaVo thvo) {
		return areaOptMapper.selectAreaOpt1(thvo);
	}

	@Override
	public List<OptAreaVo> selectAreaOpt2(OptAreaVo thvo) {
		return areaOptMapper.selectAreaOpt2(thvo);
	}

//	@Override
//	public List<OptAreaVo> selectAreaOpt3(OptAreaVo thvo) {
//		return areaOptMapper.selectAreaOpt3(thvo);
//	}
	
	@Override
	public void insertAreaOpt1(OptAreaVo thvo) {
		areaOptMapper.insertAreaOpt1(thvo);
		
	}

	@Override
	public void insertAreaOpt2(OptAreaVo thvo) {
		areaOptMapper.insertAreaOpt2(thvo);
		
	}

	@Override
	public void updateAreaOpt1(OptAreaVo thvo) {
		areaOptMapper.updateAreaOpt1(thvo);
		
	}

	@Override
	public void updateAreaOpt2(OptAreaVo thvo) {
		areaOptMapper.updateAreaOpt2(thvo);
		
	}

	@Override
	public void deleteAreaOpt1(String cdFlag, List<String> chkArr) {
		areaOptMapper.deleteAreaOpt1(cdFlag, chkArr);
		
	}

	@Override
	public void deleteAreaOpt2(String cdFlag, String pCode, List<String> chkArr) {
		areaOptMapper.deleteAreaOpt2(cdFlag, pCode, chkArr);
		
	}

}
