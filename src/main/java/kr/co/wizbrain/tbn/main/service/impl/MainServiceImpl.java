package kr.co.wizbrain.tbn.main.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.main.mapper.MainMapper;
import kr.co.wizbrain.tbn.main.service.MainService;

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

@Service("mainService")
public class MainServiceImpl implements MainService{
	
	@Resource(name="mainMapper")
	private MainMapper mainMapper;
	

}
