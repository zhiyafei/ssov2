package com.youzhi.ssoserver_v2.usermanager.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class UserType {


    // '类型id'
    @Id
    private String typeId;
    // '类型描述'
    private String typeDesc;

    public UserType(){

    }


}
