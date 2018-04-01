package com.youzhi.ssoserver_v2.usermanager.controller;


import demo.sso.enums.ExceptionEnum;
import demo.sso.exception.APPCodes;
import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.handle.ResultVOHandler;
import com.youzhi.ssoserver_v2.usermanager.service.impl.UserServiceImpl;
import com.youzhi.ssoserver_v2.usermanager.vo.ResultVO;
import com.youzhi.ssoserver_v2.usermanager.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    // 新增用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultVO addUser(@RequestBody UserVO userVO){
        userVO = UserVO.getCheckedUserVO(userVO);
        userVO.setUserTypeId(APPCodes.COMMON_USER_CODE.toString());
        User user = userService.addUser(userVO);
        if (user != null){
            return ResultVOHandler.success(user);
        }else {
            return ResultVOHandler.error(ExceptionEnum.ADDENTITY_ERROR);
        }
    }

    // 检查用户是否存在
    @RequestMapping(value = "/checkuser",method = RequestMethod.POST)
    public ResultVO checkUser(@RequestBody UserVO userVO){
        User user = userService.findUserByUserNameAndAndPwd(userVO.getUserName(),userVO.getPwd());
        if (user != null){
            return ResultVOHandler.success(user);
        }else {
            return ResultVOHandler.error(ExceptionEnum.ADDENTITY_ERROR);
        }
    }

}
