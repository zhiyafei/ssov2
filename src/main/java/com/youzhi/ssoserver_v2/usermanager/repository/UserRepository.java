package com.youzhi.ssoserver_v2.usermanager.repository;


import com.youzhi.ssoserver_v2.usermanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    // 用户名密码获取用户
    User findUserByUserNameAndAndPwd(String userName, String pwd);
    // 用户名获取用户
    User findUserByUserName(String userName);
    // 获取本年度新增用户
    List<User> getUsersByCreateYearEquals(String createYear);
    // 获取本月新增用户
    List<User> getUsersByCreateMonthEquals(String createMonth);
    // 获取当天新增用户
    List<User> getUsersByCreateDayEquals(String createDay);

}
