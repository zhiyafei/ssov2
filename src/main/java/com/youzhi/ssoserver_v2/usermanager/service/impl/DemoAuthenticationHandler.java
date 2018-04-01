package com.youzhi.ssoserver_v2.usermanager.service.impl;

import com.youzhi.ssoserver_v2.usermanager.model.Credential;
import com.youzhi.ssoserver_v2.usermanager.model.DemoLoginUser;
import com.youzhi.ssoserver_v2.usermanager.model.LoginUser;
import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.service.IAuthenticationHandler;
import org.springframework.stereotype.Service;


import java.util.Set;

/**
 * 示例性的鉴权处理器，当用户名和密码都为admin时授权通过，返回的也是一个示例性Credential实例
 * 
 * @author Administrator
 *
 */
@Service
public class DemoAuthenticationHandler implements IAuthenticationHandler {

	@Override
	public User authenticate(Credential credential) throws Exception {
		if ("admin".equals(credential.getParameter("name"))
				&& "admin".equals(credential.getParameter("pwd"))) {
			User user = new User();
			user.setUserName("admin");
			return user;
		} else {
			credential.setError("帐号或密码错误");
			return null;
		}
	}

	@Override
	public Set<String> authedSystemIds(User loginUser) throws Exception {
		return null;
	}

	@Override
	public User autoLogin(String lt) throws Exception {
		return null;
	}

	@Override
	public String loginToken(User loginUser) throws Exception {
		return null;
	}

	@Override
	public void clearLoginToken(User loginUser) throws Exception {
	}
	
}
