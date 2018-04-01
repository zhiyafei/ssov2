package com.youzhi.ssoserver_v2.usermanager.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class UserLogs {

    //'用户记录id'
    @Id
    private String logId;

    //'用户id'
    private String userId;

    //'路由名称'
    private String routerName;

    //'页面名称'
    private String pageName;

    //'创建年份'
    private String createYear;

    //'创建月份'
    private String createMonth;

    //'创建当天'
    private String createDay;

    //'创建时间'
    private String createTime;

    public UserLogs() {}
}
