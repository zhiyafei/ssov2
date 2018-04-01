package com.youzhi.ssoserver_v2.usermanager.system;

import com.youzhi.ssoserver_v2.usermanager.manager.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private Config config;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            config.refreshConfig();
        }catch (Exception e){
            System.out.println("刷新配置出错");
        }


    }
}
