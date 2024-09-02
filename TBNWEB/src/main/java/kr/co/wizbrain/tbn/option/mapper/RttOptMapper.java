package kr.co.wizbrain.tbn.option.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
@Mapper("rttOptMapper")
public interface RttOptMapper {

	List<String> selectRtt();
}
