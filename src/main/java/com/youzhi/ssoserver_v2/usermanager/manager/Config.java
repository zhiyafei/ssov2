package com.youzhi.ssoserver_v2.usermanager.manager;

import com.youzhi.ssoserver_v2.usermanager.model.ClientSystem;
import com.youzhi.ssoserver_v2.usermanager.serializer.DemoUserSerializer;
import com.youzhi.ssoserver_v2.usermanager.serializer.UserSerializer;
import com.youzhi.ssoserver_v2.usermanager.service.*;
import com.youzhi.ssoserver_v2.usermanager.entity.User;
import com.youzhi.ssoserver_v2.usermanager.service.impl.AuthenticationHandlerImpl;
import com.youzhi.ssoserver_v2.usermanager.service.impl.PreLoginHandlerImpl;
import lombok.Data;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 应用配置信息
 * 
 * @author Administrator
 *
 */

@Component
@ConfigurationProperties(prefix = "url")
public class Config implements ResourceLoaderAware {

    private static Logger logger = LoggerFactory.getLogger(Config.class);

    private ResourceLoader resourceLoader ;

    @Autowired
    private AuthenticationHandlerImpl authenticationHandler;// 鉴权处理器


    // 用户信息转换序列化实现
   // private UserSerializer userSerializer = new DemoUserSerializer();
    @Autowired
    private DemoUserSerializer userSerializer;

    //private IPreLoginHandler preLoginHandler = new CaptchaPreLoginHandler(); // 登录前预处理器

    @Autowired
    private PreLoginHandlerImpl preLoginHandler;

    private String loginViewName = "loginpage"; // 登录页面视图名称
    private String homePageName = "indexpage";
    private String homePageLocation = null;

    public void setLoginPageLocation(String loginPageLocation) {
        this.loginPageLocation = loginPageLocation;
    }

    public String getLoginPageLocation() {
        return loginPageLocation;
    }

    private String loginPageLocation = null;



    public String getHomePageLocation() {return homePageLocation;}

    public void setHomePageLocation(String homePageLocation) {this.homePageLocation = homePageLocation;}

    public String getHomePageName() {return homePageName;}

    public void setHomePageName(String homePageName) {
        this.homePageName = homePageName;
    }

    private int tokenTimeout = 30; // 令牌有效期，单位为分钟，默认30分钟

    private boolean secureMode = false; // 是否必须为https

    private int autoLoginExpDays = 365; // 自动登录状态有效期限，默认一年

    private List<ClientSystem> clientSystems = new ArrayList<ClientSystem>();

    /**
     * 重新加载配置，以支持热部署
     * 
     * @throws Exception
     */
    public  void refreshConfig() throws Exception {

        // 加载config.properties
        Properties configProperties = new Properties();

        try {
            Resource resource = resourceLoader.getResource("classpath:config.properties");
            configProperties.load(resource.getInputStream());
        } catch (IOException e) {
            logger.warn("在classpath下未找到配置文件config.properties");
        }

        // vt有效期参数
        String configTokenTimeout = (String) configProperties.get("tokenTimeout");
        if (configTokenTimeout != null) {
            try {
                tokenTimeout = Integer.parseInt(configTokenTimeout);
                logger.debug("config.properties设置tokenTimeout={}", tokenTimeout);
            } catch (NumberFormatException e) {
                logger.warn("tokenTimeout参数配置不正确");
            }
        }

        // 是否仅https安全模式下运行
        String configScureMode = configProperties.getProperty("secureMode");
        if (configScureMode != null) {
            this.secureMode = Boolean.parseBoolean(configScureMode);
            logger.debug("config.properties设置secureMode={}", this.secureMode);
        }

        // 自动登录有效期
        String configAutoLoginExpDays = configProperties.getProperty("autoLoginExpDays");
        if (configAutoLoginExpDays != null) {
            try {
                autoLoginExpDays = Integer.parseInt(configAutoLoginExpDays);
                logger.debug("config.properties设置autoLoginExpDays={}", autoLoginExpDays);
            } catch (NumberFormatException e) {
                logger.warn("autoLoginExpDays参数配置不正确");
            }
        }

        // 加载客户端系统配置列表
        try {
            loadClientSystems();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("加载client system配置失败");
        }
    }

    // 加载客户端系统配置列表
    @SuppressWarnings("unchecked")
    private void loadClientSystems() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:client_systems.xml");
        // dom4j
        SAXReader reader = new SAXReader();
        Document doc = reader.read(resource.getInputStream());

        Element rootElement = doc.getRootElement();
        List<Element> systemElements = rootElement.elements();

        clientSystems.clear();
        for (Element element : systemElements) {
            ClientSystem clientSystem = new ClientSystem();

            clientSystem.setId(element.attributeValue("id"));
            clientSystem.setName(element.attributeValue("name"));
            clientSystem.setBaseUrl(element.elementText("baseUrl"));
            clientSystem.setHomeUri(element.elementText("homeUri"));
            clientSystem.setInnerAddress(element.elementText("innerAddress"));

            clientSystems.add(clientSystem);
        }
    }

    /**
     * 应用停止时执行，做清理性工作，如通知客户端logout
     */
    public void destroy() {
        for (ClientSystem clientSystem : clientSystems) {
            clientSystem.noticeShutdown();
        }
    }

    /**
     * 获取当前鉴权处理器
     * 
     * @return
     */
    public IAuthenticationHandler getAuthenticationHandler() {
        return authenticationHandler;
    }

    public void setAuthenticationHandler(AuthenticationHandlerImpl authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    /**
     * 获取登录前预处理器
     * 
     * @return
     */
    public IPreLoginHandler getPreLoginHandler() {
        return preLoginHandler;
    }

    public void setPreLoginHandler(PreLoginHandlerImpl preLoginHandler) {
        this.preLoginHandler = preLoginHandler;
    }

    /**
     * 获取登录页面视图名称
     * 
     * @return
     */
    public String getLoginViewName() {
        return loginViewName;
    }

    public void setLoginViewName(String loginViewName) {
        this.loginViewName = loginViewName;
    }

    /**
     * 获取令牌有效期，单位为分钟
     * 
     * @return
     */
    public int getTokenTimeout() {
        return tokenTimeout;
    }

    public void setTokenTimeout(int tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }

    /**
     * 客户端系统列表
     * 
     * @return
     */
    public List<ClientSystem> getClientSystems() {
        return clientSystems;
    }

    public void setClientSystems(List<ClientSystem> clientSystems) {
        this.clientSystems = clientSystems;
    }

    /**
     * 获取指定用户的可用系统列表
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public List<ClientSystem> getClientSystems(User user) throws Exception {
        Set<String> authedSysIds = getAuthenticationHandler().authedSystemIds(user);

        // null表示允许全部
        if (authedSysIds == null) {
            return clientSystems;
        }

        List<ClientSystem> auhtedSystems = new ArrayList<ClientSystem>();
        for (ClientSystem clientSystem : clientSystems) {
            if (authedSysIds.contains(clientSystem.getId())) {
                auhtedSystems.add(clientSystem);
            }
        }

        return auhtedSystems;
    }

    @Override
    public void setResourceLoader(ResourceLoader loader) {
        this.resourceLoader = loader;
    }

    public boolean isSecureMode() {
        return secureMode;
    }

    public int getAutoLoginExpDays() {
        return autoLoginExpDays;
    }

    public UserSerializer getUserSerializer() {
        return userSerializer;
    }

    public void setUserSerializer(DemoUserSerializer userSerializer) {
        this.userSerializer = userSerializer;
    }

}
