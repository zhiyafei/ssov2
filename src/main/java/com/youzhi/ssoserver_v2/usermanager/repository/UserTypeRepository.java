package com.youzhi.ssoserver_v2.usermanager.repository;


import com.youzhi.ssoserver_v2.usermanager.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,String> {
}
