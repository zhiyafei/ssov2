package com.youzhi.ssoserver_v2.usermanager.vo;



import demo.sso.utils.IdUtils;
import demo.sso.utils.TimeUtils;
import lombok.Data;

@Data
public class UserLogsVO {

    //'用户记录id'
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

    public UserLogsVO() {}

    // 新建log
    public UserLogsVO(String userId, String routerName, String pageName) {
        this.logId = IdUtils.genUniqueId();
        this.userId = UserVO.checkUserId(userId);
        this.routerName = routerName;
        this.pageName = pageName;
        this.createYear = TimeUtils.getCurrentYear();
        this.createMonth = TimeUtils.getCurrenMonth();
        this.createDay = TimeUtils.getCurrentDay();
        this.createTime = TimeUtils.getCurrentTime();
    }


}
