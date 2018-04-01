package com.youzhi.ssoserver_v2.usermanager.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserPersistence {
    @Id
    private String persistenceId;
    //'用户名称'
    private String userName ;
    //'vt'
    private String vt ;
    //'创建年份'
    private String createYear;

    //'创建月份'
    private String createMonth;

    //'创建当天'
    private String createDay;
    //'创建时间'
    private String createTime ;
    //'更新时间'
    private String updateTime ;

    public UserPersistence() {
    }
}
