package kr.co.wizbrain.tbn.map.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.map.vo.BoundsVO;
import kr.co.wizbrain.tbn.map.vo.CctvVO;

@Mapper("mapMapper")
public interface MapMapper {
	public List<CctvVO> getCctvList(BoundsVO vo);
	//public List<CctvVO> getCctvList();
}
