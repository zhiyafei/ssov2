package com.youzhi.ssoserver_v2.usermanager.web;

import com.youzhi.ssoserver_v2.usermanager.manager.Config;
import com.youzhi.ssoserver_v2.usermanager.manager.SpringContextUtil;
import com.youzhi.ssoserver_v2.usermanager.manager.TokenManager;
import com.youzhi.ssoserver_v2.usermanager.serializer.UserSerializer;
import com.youzhi.ssoserver_v2.usermanager.entity.User;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 提供系统内网间VT验证服务
 */
@WebServlet("/validate_service")
public class ValidateServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // 客户端传来的vt
        String vt = request.getParameter("vt");
        User user = null;

        // 验证vt有效性
        if (vt != null) {
            user = TokenManager.validate(vt);
        }

        // 返回结果
        Config config = SpringContextUtil.getBean(Config.class);
        UserSerializer userSerializer = config.getUserSerializer();
        try {
            response.getWriter().write(userSerializer.serial(user));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
