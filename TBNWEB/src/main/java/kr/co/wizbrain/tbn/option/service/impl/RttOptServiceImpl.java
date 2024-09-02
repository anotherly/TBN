package kr.co.wizbrain.tbn.option.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.option.mapper.RttOptMapper;
import kr.co.wizbrain.tbn.option.service.RttOptService;

@Service("rttOptService")
public class RttOptServiceImpl implements RttOptService{

	@Resource(name="rttOptMapper")
	private RttOptMapper rttOptMapper;
	
	@Override
	public List<String> selectRtt() {
		return rttOptMapper.selectRtt();
	}

}
