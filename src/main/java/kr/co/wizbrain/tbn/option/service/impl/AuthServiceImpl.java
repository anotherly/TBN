package kr.co.wizbrain.tbn.option.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.comm.KeyMapVo;
import kr.co.wizbrain.tbn.option.mapper.AuthMapper;
import kr.co.wizbrain.tbn.option.service.AuthService;
import kr.co.wizbrain.tbn.option.vo.AuthVo;

@Service("authService")
public class AuthServiceImpl implements AuthService{
	@Resource(name="authMapper")
	private AuthMapper authMapper;

	@Override
	public List<AuthVo> selectAuthType(AuthVo thvo) throws Exception{
		return authMapper.selectAuthType(thvo);
	}
	
	@Override
	public List<AuthVo> selectAuth1(AuthVo thvo)  throws Exception{
		return authMapper.selectAuth1(thvo);
	}

	@Override
	public List<AuthVo> selectAuth2(AuthVo thvo)  throws Exception{
		return authMapper.selectAuth2(thvo);
	}

	@Override
	public void updateAuth(String authCode, List<String> menuList)  throws Exception{
		//HashMap<String, Object> map = new HashMap<String, Object>();
		List<AuthVo> alist=new ArrayList<>();
		for (String str : menuList) {
	        String[]stArr = str.split("-");
	        AuthVo avo = new AuthVo();
	        avo.setAuthCode(authCode);
	        avo.setIdx(stArr[0]);
	        avo.setUseYn(stArr[1]);
	        alist.add(avo);
		}
		//map.put("chkList",alist);
		authMapper.updateAuth(alist);
	}

	@Override
	public List<AuthVo> selectAuthUrl(AuthVo thvo) throws Exception {
		return authMapper.selectAuthUrl(thvo);
	}
	
	@Override
	public List<AuthVo> authUrlList() throws Exception {
		return authMapper.authUrlList();
	}
	
}
