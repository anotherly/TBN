package kr.co.wizbrain.tbn.setting.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.wizbrain.tbn.setting.mapper.SettingMapper;
import kr.co.wizbrain.tbn.setting.service.SettingService;
import kr.co.wizbrain.tbn.setting.vo.SettingVO;

/**
 * 사용자 서비스 구현 클래스
 * @author 미래전략사업팀 정다빈
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2020.07.23  정다빈           최초 생성
 */

@Service("settingService")
public class SettingServiceImpl implements SettingService{
	
	@Resource(name="settingMapper")
	private SettingMapper settingMapper;
	

}
