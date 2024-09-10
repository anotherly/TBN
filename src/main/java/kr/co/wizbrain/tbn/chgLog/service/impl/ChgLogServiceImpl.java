package kr.co.wizbrain.tbn.chgLog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.chgLog.mapper.ChgLogMapper;
import kr.co.wizbrain.tbn.chgLog.service.ChgLogService;
import kr.co.wizbrain.tbn.chgLog.vo.ChgLogVO;

@Service("chgLogService")
public class ChgLogServiceImpl implements ChgLogService{
	
	@Resource(name="chgLogMapper")
	private ChgLogMapper chgLogMapper;

	@Override
	public void insertChgLog(ChgLogVO thvo) {
		chgLogMapper.insertChgLog(thvo);
	}

	@Override
	public List<ChgLogVO> selectChgLog(ChgLogVO thvo) {
		return chgLogMapper.selectChgLog(thvo);
	}
	
}