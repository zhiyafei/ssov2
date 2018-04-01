package com.youzhi.ssoserver_v2.usermanager.controller;

import com.youzhi.ssoserver_v2.usermanager.manager.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class IndexController {

    @Autowired
    private Config config;
    @GetMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException{

        return config.getHomePageName();

    }
}
