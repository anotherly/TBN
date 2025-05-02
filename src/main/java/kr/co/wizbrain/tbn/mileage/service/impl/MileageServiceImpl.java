package kr.co.wizbrain.tbn.mileage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.award.vo.AwardVO;
import kr.co.wizbrain.tbn.mileage.mapper.MileageMapper;
import kr.co.wizbrain.tbn.mileage.service.MileageService;
import kr.co.wizbrain.tbn.mileage.vo.MileageVO;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;

@Service("mileageService")
public class MileageServiceImpl implements MileageService {
	
	@Resource(name="mileageMapper")
	private MileageMapper mileageMapper;
	
	
	
	// 굿 제보 마일리지 조회
	@Override
	public List<MileageVO> mileList(MileageVO MileageVO) throws Exception {
		return mileageMapper.mileList(MileageVO);
	}

	// 시상별 배점 갱신
	public void updateGrade(AwardVO thvo) {
		mileageMapper.updateGrade(thvo);
	}
	

	// 우수 제보자 시상 리스트
	@Override
	public List<AwardVO> getExcellInformerList(AwardVO thvo) throws Exception {
		return mileageMapper.getExcellInformerList(thvo);
	}
	
	// 시상별 배점 조회
	@Override
	public List<AwardVO> excellGrade(OptAreaVo thvo) {
		return mileageMapper.excellGrade(thvo);
	}
	
	// 우수 제보자 > 수상자 조회 
	@Override
	public List<AwardVO> selectUserAwardList(AwardVO thvo) throws Exception {
		return mileageMapper.selectUserAwardList(thvo);
	}
	
	// 시상 저장
	@Override
	public void saveAward(AwardVO thvo, List<AwardVO> alist) throws Exception {
		mileageMapper.saveAward(thvo,alist);
	}
	
	
	// 시상 삭제
	@Override
	public void deleteAward(String[] slt) throws Exception {
		mileageMapper.deleteAward(slt);
	}
	
	
	// 우수 제보자 > 수상자 선정 엑셀 다운로드
	@Override
	public List getAwardInformerList2(AwardVO paramVO) {
		return mileageMapper.getAwardInformerList2(paramVO);
	}
	
	
	// 우수 제보자 > 수상자 조회 엑셀 다운로드
	@Override
	public List selectUserAwardList2(AwardVO paramVO) {
		return mileageMapper.selectUserAwardList2(paramVO);
	}
}
