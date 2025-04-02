package kr.co.wizbrain.tbn.infrm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.infrm.mapper.InfrmMapper;
import kr.co.wizbrain.tbn.infrm.service.InfrmService;
import kr.co.wizbrain.tbn.infrm.vo.InfrmVO;

/**
 * 사용자 서비스 구현 클래스
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

@Service("infrmService")
public class InfrmServiceImpl implements InfrmService{
	
	@Resource(name="infrmMapper")
	private InfrmMapper infrmMapper;
	
	//전체 회원정보 조회
	public List<InfrmVO> selectInfrmList(InfrmVO infrmVO) throws Exception{
		System.out.println(infrmVO);
		List<InfrmVO> tlist = infrmMapper.selectInfrmList(infrmVO);
		return tlist;
	}
	
	public List<InfrmVO> countInfrmList(InfrmVO infrmVO) throws Exception{
		System.out.println(infrmVO);
		List<InfrmVO> tlist = infrmMapper.countInfrmList(infrmVO);
		return tlist;
	}
	
	//전체 회원정보 조회(스크롤)
	public List<InfrmVO> selectInfrmList2(InfrmVO infrmVO, int startRnum , int endRnum) throws Exception{
		System.out.println(infrmVO);
		List<InfrmVO> tlist = infrmMapper.selectInfrmList2(infrmVO,startRnum,endRnum);
		return tlist;
	}
	
	//특정 사용자 조회
	public InfrmVO selectInfrm(InfrmVO infrmVO) throws Exception {
		InfrmVO rvo = infrmMapper.selectInfrmList(infrmVO).get(0);
		rvo.setPhoneCell(rvo.getPhoneCell().replace("-", ""));
		return rvo;
	}

	@Override
	public String creActId(InfrmVO thvo) throws Exception {
		return infrmMapper.creActId(thvo);
	}

	@Override
	public String getInformerId(InfrmVO infrmVO) throws Exception {
		InfrmVO rvo = infrmMapper.selectInfrmList(infrmVO).get(0);
		return rvo.getInformerId();
	}

	@Override
	public int saveInformer(InfrmVO paramVO) {
		int cnt = 0;
		
		if(paramVO.getPageDiv().equals("new")){
			cnt = infrmMapper.saveInformer(paramVO);
		}else {
			cnt = infrmMapper.updateInformer(paramVO);
		}
		
		return cnt;
	}

	@Override
	public void saveInformerHist(InfrmVO paramVO) {
		infrmMapper.saveInformerHist(paramVO);
	}

	@Override
	public String getUpdateCode(InfrmVO paramVO) {
		return infrmMapper.getUpdateCode(paramVO);
	}

	@Override
	public int deleteInformer(InfrmVO paramVO) {
		return infrmMapper.deleteInformer(paramVO);
	}

	@Override
	public List<InfrmVO> getInformerHistory(InfrmVO ifmVO) {
		return infrmMapper.getInformerHistory(ifmVO);
	}
	
	@Override
	public List<InfrmVO> getInformerHistoryList(InfrmVO ifmVO) {
		return infrmMapper.getInformerHistoryList(ifmVO);
	}


	@Override
	public String getNewId(InfrmVO thvo) throws Exception {
		return infrmMapper.getNewId(thvo);
	}


	@Override
	public InfrmVO detailInformer(InfrmVO thvo) {
		return infrmMapper.detailInformer(thvo);
	}

	@Override
	public String chkPhone(InfrmVO ifmVO) throws Exception{
		return infrmMapper.chkPhone(ifmVO);
	}
	
	
	// 24-11-21 : 통신원 월별 제보건수
	@Override
	public List<InfrmVO> monthReport(String selectYear, String informerId) throws Exception {
		String selectYear1 = selectYear + "-01-01";
		String selectYear2 = selectYear + "-12-31";
		
		return infrmMapper.monthReport(selectYear1,selectYear2, informerId);
	}

}
