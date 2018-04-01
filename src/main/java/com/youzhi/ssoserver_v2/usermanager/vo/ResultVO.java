package com.youzhi.ssoserver_v2.usermanager.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;


}
