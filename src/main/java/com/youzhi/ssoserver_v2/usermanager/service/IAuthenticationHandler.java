package com.youzhi.ssoserver_v2.usermanager.service;

import com.youzhi.ssoserver_v2.usermanager.model.Credential;
import com.youzhi.ssoserver_v2.usermanager.model.LoginUser;
import com.youzhi.ssoserver_v2.usermanager.entity.User;

import java.util.Set;

/**
 * 鉴权处理器
 * 
 * @author Administrator
 *
 */
public interface IAuthenticationHandler {

	/**
	 * 鉴权
	 * 
	 * @param params
	 *            页面传递过来的参数
	 * @param sessionAttr
	 *            特定session属性值，这个值是在跳到login页面前，loginPreHandler通过setSessionVal()
	 *            方法写入的
	 * @param errors
	 *            授权失败时，将失败信息写入此对象
	 * @return 授权成功返回Credentail, 否则返回null
	 */
	public User authenticate(Credential credential) throws Exception;

	/**
	 * 获取当前登录用户可用系统ID列表
	 * 
	 * @param loginUser
	 * @return 返回null表示全部
	 * @throws Exception
	 */
	public Set<String> authedSystemIds(User loginUser) throws Exception;


	/**
	 * 自动登录
	 * @param lt
	 * @return
	 * @throws Exception
	 */
	public User autoLogin(String lt) throws Exception;

	/**
	 * 生成自动登录标识
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public String loginToken(User loginUser) throws Exception;

	/**
	 * 清除用户自动登录信息
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public void clearLoginToken(User loginUser) throws Exception;
}
