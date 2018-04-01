package com.youzhi.ssoserver_v2.usermanager.persistence;

import com.youzhi.ssoserver_v2.usermanager.entity.User;
import org.junit.Test;

public class UserPersistenceObjectTest {

    @Test
    public void testGetUser(){
        User user = new UserPersistenceObject().getUser("admin");
    }

}