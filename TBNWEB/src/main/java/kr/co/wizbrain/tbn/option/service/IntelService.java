package kr.co.wizbrain.tbn.option.service;

import java.util.List;

import kr.co.wizbrain.tbn.option.vo.IntelVO;

public interface IntelService {
	List<IntelVO> selectIntel(IntelVO thvo);
	List<IntelVO> selectIntel1(IntelVO thvo);
	List<IntelVO> selectIntel2(IntelVO thvo);
	void updateIntel(IntelVO thvo);
	void updateRptel(IntelVO thvo);
}
