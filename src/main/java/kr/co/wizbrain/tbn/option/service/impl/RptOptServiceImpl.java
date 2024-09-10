package kr.co.wizbrain.tbn.option.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.RptOptMapper;
import kr.co.wizbrain.tbn.option.service.RptOptService;
import kr.co.wizbrain.tbn.option.vo.OptRptVo;
import kr.co.wizbrain.tbn.user.mapper.UserMapper;

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

@Service("rptOptService")
public class RptOptServiceImpl implements RptOptService{
	
	@Resource(name="rptOptMapper")
	private RptOptMapper rptOptMapper;
	
	//---------제보유형
	@Override
	public List<OptRptVo> selectRpt(OptRptVo thvo) {
		return rptOptMapper.selectRpt(thvo);
	}

	@Override
	public void insertRpt(OptRptVo thvo) {
		rptOptMapper.insertRpt(thvo);
	}

	@Override
	public void updateRpt(OptRptVo thvo) {
		rptOptMapper.updateRpt(thvo);
	}

	@Override
	public void deleteRpt(String cdFlag, String pcode, List<String> chkArr) {
		if(cdFlag.equals("1")) {
			pcode="";
			for (int i = 0; i < chkArr.size(); i++) {
				if (i==chkArr.size()-1) {
					pcode+=chkArr.get(i);
				} else {
					pcode+=chkArr.get(i)+"|";
				}
			}
		}
		rptOptMapper.deleteRpt(cdFlag,pcode,chkArr);
	}

}
