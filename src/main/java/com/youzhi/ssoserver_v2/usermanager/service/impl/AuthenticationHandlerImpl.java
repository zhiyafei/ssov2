package com.youzhi.ssoserver_v2.usermanager.service.impl;

import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.entity.UserPersistence;
import com.youzhi.ssoserver_v2.usermanager.service.IAuthenticationHandler;
import demo.sso.common.MD5;
import demo.sso.common.StringUtil;
import com.youzhi.ssoserver_v2.usermanager.model.Credential;
import com.youzhi.ssoserver_v2.usermanager.model.DemoLoginUser;
import com.youzhi.ssoserver_v2.usermanager.model.LoginUser;
import com.youzhi.ssoserver_v2.usermanager.persistence.UserPersistenceObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Set;

@Service
public class AuthenticationHandlerImpl implements IAuthenticationHandler {

	@Autowired
	private UserPersistenceObject userPersistenceObject;

	@Autowired
	private UserPersistenceServiceImpl userPersistenceService;

	@Override
	public User authenticate(Credential credential) throws Exception {

		// 获取session中保存的验证码
		String sessionCode = (String) credential.getSettedSessionValue();
		String captcha = credential.getParameter("captcha");
		//String sessionCode ="";
		//String captcha = "";

		//TOTO
		if (!captcha.equalsIgnoreCase(sessionCode)) {
			credential.setError("验证码错误");
			return null;
		}

		// 从持久化中查询登录账号对应的用户对象
		User user = userPersistenceObject.getUser(credential
				.getParameter("name"));
		
		if (user != null) {
			String passwd = credential.getParameter("pwd");
			String passwd2 = MD5.encode(MD5.encode(user.getPwd())
					+ sessionCode);
			if (passwd2.equals(passwd)) {
				return user;
			}
		}
		
		credential.setError("帐号或密码错误");
		return null;

		// String passwd = credential.getParameter("passwd");
		// String passwd2 = MD5.encode(MD5.encode("admin") + sessionCode);
		// if ("admin".equals(credential.getParameter("name"))
		// && passwd2.equals(passwd)) {
		//
		// DemoLoginUser user = new DemoLoginUser();
		// user.setLoginName("admin");
		// return user;
		// } else {
		// credential.setError("帐号或密码错误");
		// return null;
		// }
	}

	@Override
	public Set<String> authedSystemIds(User user) throws Exception {
		return null;
	}

	// 自动登录
	@Override
	public User autoLogin(String lt) throws Exception {

		// String[] tmp = lt.split(",");
		// if (tmp.length == 2) {
		// String uname = tmp[0];
		// String passwd = tmp[1];
		//
		// if ("admin".equals(uname) && "admin".equals(passwd)) {
		// DemoLoginUser user = new DemoLoginUser();
		// user.setLoginName("admin");
		// return user;
		// }
		// }
		//
		// return null;

		// lt = DES.decrypt(lt, "test==");
		// String[] tmp = lt.split(",");
		// if (tmp.length == 2) {
		// String uname = tmp[0];
		// String passwd = tmp[1];
		//
		// if ("admin".equals(uname) && MD5.encode("admin").equals(passwd)) {
		// DemoLoginUser user = new DemoLoginUser();
		// user.setLoginName("admin");
		// return user;
		// }
		// }

		// 从持久化存储中按lt查找对应loginUser
//		FileInputStream fis = new FileInputStream("/Users/zhiyafei/war_output");
//		byte[] buff = new byte[fis.available()];
//		fis.read(buff);
//		fis.close();
//
//		String tmp = new String(buff);
//		String[] tmps = tmp.split("=");
//
//		// 相当于从存储中找个了与lt匹配的数据记录
//		if (lt.equals(tmps[0])) {
//			// 将匹配的数据装配成loginUser对象
//			User user = userPersistenceObject.getUser(tmps[1]);
//			return user;
//		}
//
//		// 没有匹配项则表示自动登录标识无效
//		return null;
		UserPersistence userPersistence = userPersistenceService.getUserPersistenceByVT(lt);
		if (userPersistence == null) {
			return null;
		}
		String UserName = userPersistence.getUserName();
		return  userPersistenceObject.getUser(UserName);
	}

	// 生成自动登录标识
	@Override
	public String loginToken(User user) throws Exception {

		//UserVO userVO = (UserVO) user;

		// 生成一个唯一标识用作lt
		String lt = StringUtil.uniqueKey();

		// 将新lt更新到当前user对应字段
		userPersistenceObject
				.updateLoginToken(user.getUserName(), lt);

		return lt;
	}

	// 更新持久化的lt
	@Override
	public void clearLoginToken(User user)
			throws Exception {
		//DemoLoginUser demoLoginUser = (DemoLoginUser) loginUser;
		userPersistenceObject.updateLoginToken(user.getUserName(), null);
	}
}
