package com.youzhi.ssoserver_v2.usermanager.service.impl;


import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.repository.UserRepository;
import com.youzhi.ssoserver_v2.usermanager.service.IUserService;
import com.youzhi.ssoserver_v2.usermanager.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        return userRepository.save(user);
    }

    @Override
    public User editUserPermission(UserVO userVO) {
        User user = selectOneByUserId(userVO.getUserId());
        if (user != null) {
            BeanUtils.copyProperties(userVO,user);
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    @Override
    public User findUserByUserNameAndAndPwd(String userName, String pwd) {
        return userRepository.findUserByUserNameAndAndPwd(userName,pwd);
    }

    @Override
    public User selectOneByUserId(String userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public List<User> getUsersByCurrentYear(String createYear) {
        return userRepository.getUsersByCreateYearEquals(createYear);
    }

    @Override
    public List<User> getUsersByCurrentMonth(String createMonth) {
        return userRepository.getUsersByCreateMonthEquals(createMonth);
    }

    @Override
    public List<User> getUsersByCurrentDay(String createDay) {
        return userRepository.getUsersByCreateDayEquals(createDay);
    }

    @Override
    public List<User> saveList(Iterable<UserVO> userVOS) {
        Iterable<User> users = new ArrayList<>();
        BeanUtils.copyProperties(userVOS,users);
        return userRepository.save(users);
    }

    @Override
    public User findUserByUserName(String userName) {
        // TODO 验证userName
        return userRepository.findUserByUserName(userName);
    }
}
