package kr.co.wizbrain.tbn.infrm.mapper;
import java.util.HashMap;
import java.util.List;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;

/**
 * 사용자 매퍼 클래스
 * @author 미래전략사업팀 정다빈
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2020.07.23  정다빈           최초 생성
 */

@Mapper("infrmMapper")
public interface InfrmMapper{
	//전체 회원정보 조회
	public List<InfrmVO> selectInfrmList(InfrmVO InfrmVO) throws Exception;
	
	//신규 informer id
	public String getNewId(InfrmVO paramVO);
	//신규 act id
	public String creActId(InfrmVO thvo) throws Exception;

	public String chkPhone(InfrmVO thvo) throws Exception;
	
	public int saveInformer(InfrmVO paramVO);
	
	public int updateInformer(InfrmVO paramVO);

	public void updateActId(InfrmVO paramVO);

	public void saveInformerHist(InfrmVO paramVO);

	public String getUpdateCode(InfrmVO paramVO);

	public int deleteInformer(InfrmVO paramVO);

	public List<InfrmVO> getInformerHistory(InfrmVO ifmVO);

	public List<InfrmVO> getInformerHistoryList(InfrmVO ifmVO);

	public InfrmVO detailInformer(InfrmVO thvo);

}