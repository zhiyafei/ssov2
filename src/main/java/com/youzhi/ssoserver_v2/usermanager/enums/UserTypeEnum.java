package com.youzhi.ssoserver_v2.usermanager.enums;

import demo.sso.exception.APPCodes;
import lombok.Getter;

@Getter
public enum UserTypeEnum {


    ADMIN(APPCodes.ADMIN_USER_CODE,"管理员"),
    COMMON(APPCodes.COMMON_USER_CODE,"普通用户"),
    zhushui(APPCodes.ZHUSHUI_USER_CODE,"注水用户");
    private Integer code;
    private String msg;

    UserTypeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
