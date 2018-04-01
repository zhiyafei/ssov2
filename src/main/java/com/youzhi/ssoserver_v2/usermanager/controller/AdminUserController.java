package com.youzhi.ssoserver_v2.usermanager.controller;

import demo.sso.enums.ExceptionEnum;
import demo.sso.exception.APPCodes;
import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.handle.ResultVOHandler;
import com.youzhi.ssoserver_v2.usermanager.handle.UserTypeHandle;
import com.youzhi.ssoserver_v2.usermanager.service.impl.UserServiceImpl;
import com.youzhi.ssoserver_v2.usermanager.vo.ResultVO;
import com.youzhi.ssoserver_v2.usermanager.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * admin
 */
@RestController
@RequestMapping(value = "/admin/user")
public class AdminUserController {



    @Autowired
    private UserServiceImpl userService;
    // 新增管理员
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultVO addAdminUser(@RequestBody UserVO userVO){
        // 判断用户是否有权限增加
        if (UserTypeHandle.isAdminUser()){
            userVO.setUserTypeId(APPCodes.ADMIN_USER_CODE.toString());
            User user = userService.addUser(userVO);
            if (user != null){
                return ResultVOHandler.success(user);
            }else {
                return ResultVOHandler.error(ExceptionEnum.ADDENTITY_ERROR);
            }
        }else{
            return ResultVOHandler.error(ExceptionEnum.AUTHORITY_ERROR);
        }

    }

    // 添加注水用户
    @RequestMapping(value = "/addOtherUsers",method = RequestMethod.POST)
    public ResultVO addOtherUsers(@RequestBody List<UserVO> userVOs){
        // 判断用户是否有权限增加
        if (UserTypeHandle.isAdminUser()){
            List<User> users = userService.saveList(userVOs);
            if (users != null){
                for (User user:users){
                    user.setUserTypeId(APPCodes.ZHUSHUI_USER_CODE.toString());
                }
                return ResultVOHandler.success(users);
            }else {
                return ResultVOHandler.error(ExceptionEnum.ADDENTITY_ERROR);
            }
        }else{
            return ResultVOHandler.error(ExceptionEnum.AUTHORITY_ERROR);
        }

    }


}
