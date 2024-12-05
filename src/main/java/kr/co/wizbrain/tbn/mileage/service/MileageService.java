package kr.co.wizbrain.tbn.mileage.service;

import java.util.List;
import java.util.Map;

import kr.co.wizbrain.tbn.mileage.vo.GiftVO;
import kr.co.wizbrain.tbn.mileage.vo.GradeVO;
import kr.co.wizbrain.tbn.mileage.vo.MileageVO;
import kr.co.wizbrain.tbn.mileage.vo.MserchVO;

public interface MileageService {

	// 마일리지 지급 조회
	public List<Map<String, Object>> mileList(MileageVO paramVO) throws Exception;
	
	// 총 마일리지 조회
	public List<MileageVO> allMileList(MserchVO paramVO) throws Exception;
	
	// 등급 조회
	public List<MileageVO> gradeList(MserchVO paramVO) throws Exception;
	
	// 마일리지 지급
	public void paymentMile(MileageVO paramVO, List<MileageVO> alist) throws Exception;
	
	// 지급된 마일리지 반영 전 조회
	public boolean selectMile(MileageVO paramVO,List<MileageVO> mileList) throws Exception;
	
	// 지급된 마일리지 반영(등록 - 처음 지급하는 경우 )
	public void insertMile(MileageVO paramVO,List<MileageVO> mileList) throws Exception;
	
	// 지급된 마일리지 반영(수정 - 지급 이력이 있는 경우 )
	public void updateMile(MileageVO paramVO, List<MileageVO> mileList) throws Exception;
	
	
	// 상품 지급을 위한 통신원 정보 가져오기
	public List<MileageVO> selectIfm(String ifmId) throws Exception;
	
	// 상품 정보 가져오기
	public List<GiftVO> getGift() throws Exception;
	
	// 등급 조회를 위한 통신원 정보 가져오기
	public List<MileageVO> gSelectIfm(String ifmId) throws Exception;
	
	// 등급 정보 가져오기
	public List<GradeVO> getGrade() throws Exception;
	
	// 마일리지 차감
	public void minusMile(MileageVO paramVO) throws Exception;
	
	// 상품 지급 로그
	public void giftHistory(MileageVO paramVO) throws Exception;

	// 등급 부여 로그
	public void gradeHistory(MileageVO paramVO) throws Exception;
	
	// 등급 부여 전 이력 확인
	public boolean gradeChecker(MileageVO paramVO) throws Exception;
	
	// 부여된 등급 반영 ( 등록 - 처음 부여하는 경우 )
	public void insertGrade(MileageVO paramVO) throws Exception;
	
	// 부여된 등급 반영 ( 수정 - 부여된 이력이 있는 경우 )
	public void updateGrade(MileageVO paramVO) throws Exception;
}
