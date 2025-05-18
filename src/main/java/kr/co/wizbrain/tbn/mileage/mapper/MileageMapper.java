package kr.co.wizbrain.tbn.mileage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.award.vo.AwardVO;
import kr.co.wizbrain.tbn.mileage.vo.MileageVO;
import kr.co.wizbrain.tbn.option.vo.OptAreaVo;

@Mapper("mileageMapper")
public interface MileageMapper {
	
	// 굿 제보 마일리지 조회
	public List<MileageVO> mileList(MileageVO MileageVO) throws Exception;
	
	// 우수 통신원 조회
	public List<MileageVO> excellenceList(MileageVO MileageVO) throws Exception;
	
	// 시상별 배점 갱신
	void updateGrade(AwardVO thvo);
	
	// 우수 제보자 시상 리스트
	public List<AwardVO> getExcellInformerList(AwardVO thvo) throws Exception;
	
	// 시상별 배점 조회
	List<AwardVO> excellGrade(OptAreaVo thvo);
	
	// 우수 제보자 > 수상자 조회 
	public List<AwardVO> selectUserAwardList(AwardVO thvo) throws Exception;
	
	//시상 저장
	void saveAward(@Param("thvo")AwardVO thvo, @Param("chkArr")List<AwardVO> chkArr);
	
	//시상 제외(삭제)
	void deleteAward(@Param("selection")String[] slt);
	
	// 우수 제보자 > 수상자 선정 엑셀 다운로드
	List getAwardInformerList2(AwardVO paramVO);
	
	// 우수 제보자 > 수상자 조회 엑셀 다운로드
	List selectUserAwardList2(AwardVO paramVO);
	
	// 최고 통신원 기본 정보 조회
	public List<MileageVO> bestIfrmList(MileageVO MileageVO);
	
	// 최고 통신원 전체 제보건수 조회
	public List<MileageVO> bestIfrmCnt(@Param("bestIfrmList") List<MileageVO> bestIfrmList);
}
