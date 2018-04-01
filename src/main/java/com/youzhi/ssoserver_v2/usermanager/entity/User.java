package com.youzhi.ssoserver_v2.usermanager.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class User {
    //'用户id'
    @Id
    private String userId;
    //'用户名称'
    private String userName ;
    //'昵称'
    private String nickName ;
    //'头像'
    private String avatar ;
    //'性别'
    private Integer sex ;
    //'用户类型id'
    private String userTypeId;
    //'用户密码'
    private String pwd ;
    //'用户电话'
    private String phone ;
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

    public User() {
    }
}
