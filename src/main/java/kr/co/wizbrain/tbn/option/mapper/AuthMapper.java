package kr.co.wizbrain.tbn.option.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.wizbrain.tbn.option.vo.AuthVo;

/**
 * 사용자 매퍼 클래스
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
@Mapper("authMapper")
public interface AuthMapper  {
	
	//전체 권한 종류만
	public List<AuthVo> selectAuthType(AuthVo thvo) throws Exception;
	
	//대분류 권한 조회
	public List<AuthVo> selectAuth1(AuthVo thvo) throws Exception;
	
	//중,소분류 권한 조회
	public List<AuthVo> selectAuth2(AuthVo thvo) throws Exception;
	
	//권한 사용여부 수정
	public void updateAuth(List<AuthVo> alist) throws Exception;

	//url관련 권한처리
	public List<AuthVo> selectAuthUrl(AuthVo thvo) throws Exception;
	
	//모든 url 리스트 조회
	public List<AuthVo> authUrlList() throws Exception;
		
}
