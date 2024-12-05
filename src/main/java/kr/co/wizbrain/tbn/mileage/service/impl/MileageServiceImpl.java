package kr.co.wizbrain.tbn.mileage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.mileage.mapper.MileageMapper;
import kr.co.wizbrain.tbn.mileage.service.MileageService;
import kr.co.wizbrain.tbn.mileage.vo.GiftVO;
import kr.co.wizbrain.tbn.mileage.vo.GradeVO;
import kr.co.wizbrain.tbn.mileage.vo.MileageVO;
import kr.co.wizbrain.tbn.mileage.vo.MserchVO;

@Service("mileageService")
public class MileageServiceImpl implements MileageService {
	
	@Resource(name="mileageMapper")
	private MileageMapper mileageMapper;
	
	@Override
	public List<Map<String, Object>> mileList(MileageVO paramVO) throws Exception {
		return mileageMapper.mileList(paramVO);
	}
	
	@Override
	public List<MileageVO> allMileList(MserchVO paramVO) throws Exception {
		return mileageMapper.allMileList(paramVO);
	}
	
	// 등급조회
	@Override
	public List<MileageVO> gradeList(MserchVO paramVO) throws Exception {
		return mileageMapper.gradeList(paramVO);
	}
	
	
	@Override
	public void paymentMile(MileageVO paramVO, List<MileageVO> alist) throws Exception {
		mileageMapper.paymentMile(paramVO, alist);
	}
	
	@Override
	public void updateMile(MileageVO paramVO, List<MileageVO> mileList) throws Exception {
		mileageMapper.updateMile(paramVO, mileList);
	}
	
	@Override
	public boolean selectMile(MileageVO paramVO,List<MileageVO> mileList) throws Exception {
		List<MileageVO> milecheck = mileageMapper.selectMile(paramVO, mileList);
		
		if(milecheck.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void insertMile(MileageVO paramVO,List<MileageVO> mileList) throws Exception {
		mileageMapper.insertMile(paramVO, mileList);
	}
	
	
	// 상품 지급을 위한 통신원 정보 가져오기
	@Override
	public List<MileageVO> selectIfm(String ifmId) throws Exception {
		return mileageMapper.selectIfm(ifmId);
	}

	// 상품 정보 가져오기
	@Override
	public List<GiftVO> getGift() throws Exception {
		return mileageMapper.getGift();
	}
	
	// 등급 부여를 위한 통신원 정보 가져오기
	@Override
	public List<MileageVO> gSelectIfm(String ifmId) throws Exception {
		return mileageMapper.gSelectIfm(ifmId);
	}
	
	// 등급 정보 가져오기
	@Override
	public List<GradeVO> getGrade() throws Exception {
		return mileageMapper.getGrade();
	}
	
	// 마일리지 차감
	@Override
	public void minusMile(MileageVO paramVO) throws Exception {
		mileageMapper.minusMile(paramVO);
	}
	
	// 상품 지급 로그
	@Override
	public void giftHistory(MileageVO paramVO) throws Exception {
		mileageMapper.giftHistory(paramVO);
	}
	
	// 등급 부여 로그
	@Override
	public void gradeHistory(MileageVO paramVO) throws Exception {
		mileageMapper.gradeHistory(paramVO);
	}
	
	// 등급 부여 전 이력 확인
	@Override
	public boolean gradeChecker(MileageVO paramVO) throws Exception{
		List<MileageVO> milecheck = mileageMapper.gradeChecker(paramVO);
		
		if(milecheck.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
		
	// 부여된 등급 반영 ( 등록 - 처음 부여하는 경우 )
	@Override
	public void insertGrade(MileageVO paramVO) throws Exception{
		mileageMapper.insertGrade(paramVO);
	}
		
	// 부여된 등급 반영 ( 수정 - 부여된 이력이 있는 경우 )
	@Override
	public void updateGrade(MileageVO paramVO) throws Exception{
		mileageMapper.updateGrade(paramVO);
	}
	
}
