package com.youzhi.ssoserver_v2.usermanager.repository;


import com.youzhi.ssoserver_v2.usermanager.entity.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersistenceRepository extends JpaRepository<UserPersistence,String> {
    UserPersistence getUserPersistenceByVtEquals(String VT);
}
