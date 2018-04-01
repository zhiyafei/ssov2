package com.youzhi.ssoserver_v2.usermanager.repository;


import com.youzhi.ssoserver_v2.usermanager.entity.UserLogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLogsRepository  extends JpaRepository<UserLogs,String>{
    List<UserLogs> getUserLogsByCreateMonthEquals(String month);

    List<UserLogs> getUserLogsByCreateDayEquals(String day);
}
