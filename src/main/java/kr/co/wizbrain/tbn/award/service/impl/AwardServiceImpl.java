package kr.co.wizbrain.tbn.award.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.award.mapper.AwardMapper;
import kr.co.wizbrain.tbn.award.service.AwardService;
import kr.co.wizbrain.tbn.award.vo.AwardVO;
import kr.co.wizbrain.tbn.comm.ParamsDto;

@Service("awardService")
public class AwardServiceImpl implements AwardService{
	@Resource(name="awardMapper")
	private AwardMapper awardMapper;

	@Override
	public List<AwardVO> getAwardInformerList(AwardVO thvo) throws Exception {
		return awardMapper.getAwardInformerList(thvo);
	}

	@Override
	public int selectAwardCnt() throws Exception {
//		String awardCnt = ""; 
//		awardCnt = (String)select("award.selectAwardCnt",null);
//		int cnt = 0;
//		if(awardCnt == null){
//			cnt = 0;
//		}else{
//			cnt  = Integer.parseInt(awardCnt);
//		}
//		 return cnt;
		 return 0;
	}

	@Override
	public void saveAward(AwardVO thvo, List<AwardVO> alist) throws Exception {
		awardMapper.saveAward(thvo,alist);
	}
	
	@Override
	public void deleteAward(String[] slt) throws Exception {
		awardMapper.deleteAward(slt);
	}

	@Override
	public List<AwardVO> selectUserAwardList(AwardVO thvo) throws Exception {
		// TODO Auto-generated method stub
		return awardMapper.selectUserAwardList(thvo);
	}

	@Override
	public List<AwardVO> getAwardList(AwardVO awvo) {
		return awardMapper.getAwardList(awvo);
	}

	@Override
	public List<AwardVO> selectGrade(AwardVO thvo) {
		return awardMapper.selectGrade(thvo);
	}

	@Override
	public void updateGrade(AwardVO thvo) {
		awardMapper.updateGrade(thvo);
	}

	@Override
	public List getAwardInformerList2(AwardVO paramVO) {
		return awardMapper.getAwardInformerList2(paramVO);
	}

	@Override
	public List selectUserAwardList2(AwardVO paramVO) {
		return awardMapper.selectUserAwardList2(paramVO);
	}
}