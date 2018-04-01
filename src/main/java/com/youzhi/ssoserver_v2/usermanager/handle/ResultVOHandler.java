package com.youzhi.ssoserver_v2.usermanager.handle;


import demo.sso.enums.ExceptionEnum;
import com.youzhi.ssoserver_v2.usermanager.vo.ResultVO;

public class ResultVOHandler {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success() {

        return success(null);
    }
    public static ResultVO error(Integer code,String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
    public static ResultVO error(ExceptionEnum exceptionEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        resultVO.setCode(exceptionEnum.getCode());
        resultVO.setMsg(exceptionEnum.getMsg());
        return resultVO;
    }

}
