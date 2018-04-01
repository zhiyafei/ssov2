package com.youzhi.ssoserver_v2.usermanager.service.impl;


import com.youzhi.ssoserver_v2.usermanager.entity.UserLogs;
import com.youzhi.ssoserver_v2.usermanager.repository.UserLogsRepository;
import com.youzhi.ssoserver_v2.usermanager.service.IUserLogsService;
import com.youzhi.ssoserver_v2.usermanager.vo.UserLogsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogsServiceImpl implements IUserLogsService {

    @Autowired
    private UserLogsRepository userLogsRepository;
    @Override
    public UserLogs addLogs(UserLogsVO userLogsVO) {
        UserLogs userLogs = new UserLogs();
        BeanUtils.copyProperties(userLogsVO,userLogs);
        return userLogsRepository.save(userLogs);
    }

    @Override
    public List<UserLogs> getCurrentMonthUserLogs(String currentMonth) {
        return userLogsRepository.getUserLogsByCreateMonthEquals(currentMonth);
    }

    @Override
    public List<UserLogs> getCurrentDayUserLogs(String currentDay) {
        return userLogsRepository.getUserLogsByCreateDayEquals(currentDay);
    }
}
