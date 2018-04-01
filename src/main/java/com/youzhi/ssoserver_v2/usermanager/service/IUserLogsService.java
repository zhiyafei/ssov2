package com.youzhi.ssoserver_v2.usermanager.service;


import com.youzhi.ssoserver_v2.usermanager.entity.UserLogs;
import com.youzhi.ssoserver_v2.usermanager.vo.UserLogsVO;

import java.util.List;

public interface IUserLogsService {

    //新建log数据
    UserLogs addLogs(UserLogsVO userLogsVO);
    // 获取当月的用户记录
    List<UserLogs> getCurrentMonthUserLogs(String currentMonth);
    // 获取当天的用户记录
    List<UserLogs> getCurrentDayUserLogs(String currentDay);

}
