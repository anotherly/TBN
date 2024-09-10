package kr.co.wizbrain.tbn.option.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;

/**
 * 사용자 매퍼 클래스
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

@Mapper("rptOptMapper")
public interface RptOptMapper{
	
	//---------제보유형
	//제보유형 조회
	public List<OptRptVo> selectRpt(OptRptVo thvo);
	//제보유형 등록
	public void insertRpt(OptRptVo thvo);
	//제보유형 수정
	public void updateRpt(OptRptVo thvo);
	//제보유형 삭제
	public void deleteRpt(@Param("cdFlag")String cdFlag, @Param("pcode")String pcode, @Param("chkArr")List<String> chkArr);
}
