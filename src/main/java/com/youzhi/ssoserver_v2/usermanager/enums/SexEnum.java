package com.youzhi.ssoserver_v2.usermanager.enums;

public enum SexEnum {
    BOY(0,"男孩"),
    Girl(1,"女孩");
    private  Integer code;
    private  String msg;

    SexEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
