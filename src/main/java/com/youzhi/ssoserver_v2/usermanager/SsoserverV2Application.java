package com.youzhi.ssoserver_v2.usermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SsoserverV2Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoserverV2Application.class, args);
	}

}
