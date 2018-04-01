package com.youzhi.ssoserver_v2.usermanager.handle;


public class UserTypeHandle {
    public static boolean isAdminUser(){
        // TODO
        return false;
        //return UserHolder.getUser().getProperty("userTypeId").equals(APPCodes.ADMIN_USER_CODE);
    }

}
