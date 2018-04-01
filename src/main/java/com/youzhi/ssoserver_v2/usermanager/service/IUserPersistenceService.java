package com.youzhi.ssoserver_v2.usermanager.service;

import com.youzhi.ssoserver_v2.usermanager.entity.UserPersistence;
import com.youzhi.ssoserver_v2.usermanager.vo.UserPersistenceVO;

public interface IUserPersistenceService {
    UserPersistence saveByUserNameAndVT(String userName,String VT);
    UserPersistence saveOneByVO(UserPersistence userPersistence);
    UserPersistence getUserPersistenceByVT(String VT);
}
