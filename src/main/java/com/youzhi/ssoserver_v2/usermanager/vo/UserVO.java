package com.youzhi.ssoserver_v2.usermanager.vo;


import com.youzhi.ssoserver_v2.usermanager.entity.User;
import demo.sso.enums.ExceptionEnum;
import demo.sso.exception.APPCodes;
import demo.sso.exception.ExceptionUtil;
import demo.sso.reg.RegResult;
import demo.sso.reg.RegUtils;
import com.youzhi.ssoserver_v2.usermanager.enums.SexEnum;
import com.youzhi.ssoserver_v2.usermanager.handle.UserTypeHandle;
import demo.sso.utils.IdUtils;
import demo.sso.utils.TimeUtils;
import lombok.Data;

@Data
public class UserVO {
    //'用户id'



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
    // 重要 强制判断用户类型
    public void setUserTypeId(String userTypeId) {
        this.userTypeId = checkUserTypeId(userTypeId);
    }
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




    // 新增用户
    public UserVO( String userName, String nickName, String avatar, Integer sex,  String pwd, String phone) {
        this.userId = IdUtils.genUniqueId();
        this.userName = checkUserName(userName);
        this.nickName = checkNickName(nickName);
        this.avatar = checkAvatar(avatar);
        this.sex = checkSex(sex);
        this.userTypeId = APPCodes.COMMON_USER_CODE.toString();
        this.pwd = checkPwd(pwd);
        this.phone = checkPhone(phone);
        this.createYear = TimeUtils.getCurrentYear();
        this.createMonth = TimeUtils.getCurrenMonth();
        this.createDay = TimeUtils.getCurrentDay();
        this.createTime = TimeUtils.getCurrentTime();
        this.updateTime = TimeUtils.getCurrentTime();
    }
    // 更改用户权限
    public UserVO( String userId, String userTypeId) {
        this.userId = userId;
        this.userTypeId = checkUserTypeId(userTypeId);
        this.updateTime = TimeUtils.getCurrentTime();
    }
    public UserVO() {
    }
    public static UserVO getCheckedUserVO(UserVO userVO){
        return new UserVO(userVO.getUserName(),userVO.getNickName(),userVO.getAvatar(),userVO.getSex(),userVO.getPwd(),userVO.getPhone());
    }
    static String checkUserId(String userId){
        RegResult result = RegUtils.userId(userId);
        if (result.isSuccess()){
            return userId;
        } else {
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }
    static String checkUserName(String userName){
        RegResult result = RegUtils.userName(userName);
        if (result.isSuccess()){
            return userName;
        } else {
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }
    static String checkNickName(String nickName){
        return checkUserName(nickName);
    }

    static String checkAvatar(String avatar){
        RegResult result = RegUtils.url(avatar);
        if (result.isSuccess()){
            return avatar;
        }else{
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }
    static Integer checkSex(Integer sex){
        RegResult result = RegUtils.sex(sex);

        if (result.isSuccess()){
            return sex.equals(SexEnum.BOY.getMsg()) ? SexEnum.BOY.getCode() : SexEnum.Girl.getCode();
        }else{
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }
    static String checkUserTypeId(String userTypeId){
        RegResult result = RegUtils.userTypeId(userTypeId);
        // 强制判断用户非Admin用户不能做的事情
        if (result.isSuccess()){
            if (userTypeId.equals(APPCodes.ADMIN_USER_CODE.toString()) && UserTypeHandle.isAdminUser() == false
                    || userTypeId.equals(APPCodes.ZHUSHUI_USER_CODE.toString()) && UserTypeHandle.isAdminUser() == false){
                throw new ExceptionUtil(ExceptionEnum.AUTHORITY_ERROR);
            }
            return userTypeId;
        }else{
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }

    static String checkPwd (String pwd){
        RegResult result = RegUtils.pwd(pwd);
        if (result.isSuccess()){
            return pwd;
        } else {
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }

    static String checkPhone (String phone){
        RegResult result = RegUtils.phone(phone);
        if (result.isSuccess()){
            return phone;
        } else {
            throw new ExceptionUtil(ExceptionEnum.REG_ERROR.getCode(),result.getMsg());
        }
    }

}
