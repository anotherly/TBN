package kr.co.wizbrain.tbn.option.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.InfrmOptMapper;
import kr.co.wizbrain.tbn.option.service.InfrmOptService;
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

@Service("infrmOptService")
public class InfrmOptServiceImpl implements InfrmOptService{
	
	@Resource(name="infrmOptMapper")
	private InfrmOptMapper infrmOptMapper;
	

	//<!-- #############통신원 유형################ -->

	@Override
	public List<OptInftVo> selectInft1(OptInftVo thvo) {
		return infrmOptMapper.selectInft1(thvo);
	}

	@Override
	public List<OptInftVo> selectInft2(OptInftVo thvo) {
		return infrmOptMapper.selectInft2(thvo);
	}

	@Override
	public List<OptInftVo> selectInft3(OptInftVo thvo) {
		return infrmOptMapper.selectInft3(thvo);
	}
	
	@Override
	public void insertInft1(OptInftVo thvo) {
		infrmOptMapper.insertInft1(thvo);
		
	}

	@Override
	public void insertInft2(OptInftVo thvo) {
		infrmOptMapper.insertInft2(thvo);
		
	}

	@Override
	public void insertInft3(OptInftVo thvo) {
		infrmOptMapper.insertInft3(thvo);
		
	}

	@Override
	public void updateInft1(OptInftVo thvo) {
		infrmOptMapper.updateInft1(thvo);
		
	}

	@Override
	public void updateInft2(OptInftVo thvo) {
		infrmOptMapper.updateInft2(thvo);
		
	}

	@Override
	public void updateInft3(OptInftVo thvo) {
		infrmOptMapper.updateInft3(thvo);
		
	}

	@Override
	public void deleteInft1(String cdFlag, List<String> chkArr, String areaCode) {
		infrmOptMapper.deleteInft1(cdFlag, chkArr, areaCode);
		
	}

	@Override
	public void deleteInft2(String cdFlag, String pCode, List<String> chkArr, String areaCode) {
		infrmOptMapper.deleteInft2(cdFlag, pCode, chkArr, areaCode);
		
	}

	@Override
	public void deleteInft3(String cdFlag, String pCode,  String pCode2, List<String> chkArr, String areaCode) {
		infrmOptMapper.deleteInft3(cdFlag, pCode,pCode2, chkArr, areaCode);
	}

	
}
