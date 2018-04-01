package com.youzhi.ssoserver_v2.usermanager.service;



import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.vo.UserVO;

import java.util.List;

public interface IUserService {

    // 增加用户
    User addUser(UserVO userVO);

    // 用户名获取用户
    User findUserByUserName(String userName);

    // 更改用户权限
    User editUserPermission(UserVO userVO);

    // 获取用户名和密码是否合法
    User findUserByUserNameAndAndPwd(String userName, String pwd);

    // 获取用户详情
    User selectOneByUserId(String userId);

    // 获取本年度新增用户
    List<User> getUsersByCurrentYear(String createYear);
    // 获取本月新增用户
    List<User> getUsersByCurrentMonth(String createMonth);
    // 获取当天新增用户
    List<User> getUsersByCurrentDay(String createDay);



    // 新建注水用户
    List<User> saveList(Iterable<UserVO> userVOS);

}
