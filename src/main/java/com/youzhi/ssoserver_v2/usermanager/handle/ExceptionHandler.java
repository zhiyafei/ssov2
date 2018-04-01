package com.youzhi.ssoserver_v2.usermanager.handle;


import demo.sso.exception.ExceptionUtil;
import com.youzhi.ssoserver_v2.usermanager.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler{

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExceptionUtil.class)
    @ResponseBody
    public ResultVO handlerException(ExceptionUtil e){
        return ResultVOHandler.error(e.getCode(),e.getMsg());
    }
}