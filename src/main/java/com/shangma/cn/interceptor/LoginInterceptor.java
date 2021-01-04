package com.shangma.cn.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getSession().toString());
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            response.setContentType("application/json;charset=utf-8");
            String str = "{\"status\":60000,\"msg\":\"未登录\"}";
            response.getWriter().write(str);
            return false;
        }
        return true;
    }
}
