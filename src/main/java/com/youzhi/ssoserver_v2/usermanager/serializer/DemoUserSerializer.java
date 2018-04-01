package com.youzhi.ssoserver_v2.usermanager.serializer;

import com.youzhi.ssoserver_v2.usermanager.entity.User;
import org.springframework.stereotype.Service;

/**
 * 序列化DemoLoginUser
 * 
 * @author Administrator
 *
 */
@Service
public class DemoUserSerializer extends UserSerializer {

    @Override
    protected void translate(User user, UserData userData) throws Exception {
        
        // 实现类型已知，可强制转换
        //DemoLoginUser demoLoginUser = (DemoLoginUser) loginUser;

        userData.setId(user.getUserId());
        userData.setProperty("name", user.getUserName());
    }

}
