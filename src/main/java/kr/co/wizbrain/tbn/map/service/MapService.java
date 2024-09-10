package kr.co.wizbrain.tbn.map.service;

import java.util.List;

import kr.co.wizbrain.tbn.map.vo.BoundsVO;
import kr.co.wizbrain.tbn.map.vo.CctvVO;


public interface MapService {
	public List<CctvVO> getCctvList(BoundsVO vo);
	//public List<CctvVO> getCctvList();
}
