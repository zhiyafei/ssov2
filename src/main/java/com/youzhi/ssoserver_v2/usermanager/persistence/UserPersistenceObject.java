package com.youzhi.ssoserver_v2.usermanager.persistence;

import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.entity.UserPersistence;
import com.youzhi.ssoserver_v2.usermanager.service.impl.UserPersistenceServiceImpl;
import com.youzhi.ssoserver_v2.usermanager.service.impl.UserServiceImpl;
import demo.sso.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

/**
 * 登录用户信息持久化服务，相当于DAO对象的模拟
 * @author preach
 *
 */
@Service
public class UserPersistenceObject {

	// TODO
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserPersistenceServiceImpl userPersistenceService;

	/**
	 * 更新当前登录用户的lt标识
	 * @param loginName
	 * @param lt
	 * @throws Exception 
	 */
	public void updateLoginToken(String loginName, String lt) throws Exception {
		
		//将信息写入存储文件test，格式为lt=loginName，如：02564fc6a02a35c689cbdf898458d2da=admin
//		FileOutputStream fos = new FileOutputStream("/Users/zhiyafei/war_output/test.txt");
//		fos.write((lt + "=" + loginName).getBytes());
//		fos.close();

		UserPersistence userPersistence = userPersistenceService.getUserPersistenceByVT(lt);
		if (userPersistence == null) {
			userPersistenceService.saveByUserNameAndVT(loginName,lt);
		}else{
			userPersistence.setUserName(loginName);
			userPersistence.setUpdateTime(TimeUtils.getCurrentTime());

		}
	}

	/**
	 * 按登录账号查询用户信息
	 * @param
	 * @return
	 */
	public User getUser(String uname) {
		User user = userService.findUserByUserName(uname);
		return user.getUserName().equals(uname) ? user : null;
	}



	

}
