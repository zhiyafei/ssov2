package com.youzhi.ssoserver_v2.usermanager.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

@Component
public class SSOServerFilter {

	public FilterRegistrationBean ssoClient() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CharacterEncodingFilter());
        registration.addUrlPatterns("/*");


		registration.addInitParameter("encoding","UTF-8");
		registration.addInitParameter("contextConfigLocation","classpath:spring-ctx.xml");
		registration.addInitParameter("serverBaseUrl", "http://127.0.0.1:8080");
		registration.addInitParameter("serverInnerAddress", "http://127.0.0.1:8080");
		registration.addInitParameter("excludes", "^\\/public\\/.*$");
		registration.setName("ssoClient");
		return registration;
	}
}
