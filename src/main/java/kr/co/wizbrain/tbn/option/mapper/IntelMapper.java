package kr.co.wizbrain.tbn.option.mapper;

import java.util.List;


import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.option.vo.IntelVO;

@Mapper("intelMapper")
public interface IntelMapper {
	public List<IntelVO> selectIntel(IntelVO thvo);
	public List<IntelVO> selectIntel1(IntelVO thvo);
	public List<IntelVO> selectIntel2(IntelVO thvo);
	void updateIntel(IntelVO thvo);
	public void updateRptel(IntelVO thvo);
}
