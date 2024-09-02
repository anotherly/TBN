package kr.co.wizbrain.tbn.option.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.IntelMapper;
import kr.co.wizbrain.tbn.option.service.IntelService;
import kr.co.wizbrain.tbn.option.vo.IntelVO;

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

@Service("intelService")
public class IntelServiceImpl implements IntelService{

	@Resource(name="intelMapper")
	private IntelMapper intelMapper;
	
	@Override
	public List<IntelVO> selectIntel(IntelVO thvo) {
		return intelMapper.selectIntel(thvo);
	}

	@Override
	public List<IntelVO> selectIntel1(IntelVO thvo) {
		return intelMapper.selectIntel1(thvo);
	}

	@Override
	public List<IntelVO> selectIntel2(IntelVO thvo) {
		return intelMapper.selectIntel2(thvo);
	}

	@Override
	public void updateIntel(IntelVO thvo) {
		 intelMapper.updateIntel(thvo);		
	}

	@Override
	public void updateRptel(IntelVO thvo) {
		intelMapper.updateRptel(thvo);	
		
	}

}
