package kr.co.wizbrain.tbn.map.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.map.mapper.MapMapper;
import kr.co.wizbrain.tbn.map.service.MapService;
import kr.co.wizbrain.tbn.map.vo.BoundsVO;
import kr.co.wizbrain.tbn.map.vo.CctvVO;

@Service("mapService")
public class MapServiceImpl implements MapService {
	
	@Resource(name="mapMapper")
	private MapMapper mapMapper;

//	@Override
//	public List<CctvVO> getCctvList() {
//		return mapMapper.getCctvList();
//	}

	@Override
	public List<CctvVO> getCctvList(BoundsVO vo) {
		return mapMapper.getCctvList(vo);
	}
}













