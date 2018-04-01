package com.youzhi.ssoserver_v2.usermanager.service.impl;

import com.youzhi.ssoserver_v2.usermanager.entity.UserPersistence;
import com.youzhi.ssoserver_v2.usermanager.repository.UserPersistenceRepository;
import com.youzhi.ssoserver_v2.usermanager.service.IUserPersistenceService;
import com.youzhi.ssoserver_v2.usermanager.vo.UserPersistenceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceServiceImpl implements IUserPersistenceService {

    @Autowired
    private UserPersistenceRepository userPersistenceRepository;
    @Override
    public UserPersistence saveByUserNameAndVT(String userName, String VT) {
        UserPersistenceVO userPersistenceVO = new UserPersistenceVO(userName,VT);
        UserPersistence userPersistence = new UserPersistence();
        BeanUtils.copyProperties(userPersistenceVO,userPersistence);
        return userPersistenceRepository.save(userPersistence);
    }

    @Override
    public UserPersistence saveOneByVO(UserPersistence userPersistence) {

        return userPersistenceRepository.save(userPersistence);
    }

    @Override
    public UserPersistence getUserPersistenceByVT(String VT) {
        return userPersistenceRepository.getUserPersistenceByVtEquals(VT);
    }
}
