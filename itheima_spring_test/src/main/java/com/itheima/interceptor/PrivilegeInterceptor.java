package com.itheima.interceptor;

import com.itheima.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器（判断用户执行操作之前是否已登录）（需要在spring中配置）
public class PrivilegeInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //逻辑：判断用户是否登录  本质：判断session中有没有user
        //1、获得session对象并取出user对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user==null){
            //没有登录就重定向到登录界面
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        //放行 访问目标资源
        return true;
    }
}
