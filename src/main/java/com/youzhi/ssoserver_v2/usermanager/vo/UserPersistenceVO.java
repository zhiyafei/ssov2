package com.youzhi.ssoserver_v2.usermanager.vo;


import demo.sso.utils.IdUtils;
import demo.sso.utils.TimeUtils;
import lombok.Data;

@Data
public class UserPersistenceVO {
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

    public UserPersistenceVO() {

    }

    public UserPersistenceVO( String userName, String vt) {
        this.persistenceId = IdUtils.genUniqueId();
        this.userName = UserVO.checkUserName(userName);
        this.vt = vt;
        this.createYear = TimeUtils.getCurrentYear();
        this.createMonth = TimeUtils.getCurrenMonth();
        this.createDay = TimeUtils.getCurrentDay();
        this.createTime = TimeUtils.getCurrentTime();
        this.updateTime = TimeUtils.getCurrentTime();
    }
}
