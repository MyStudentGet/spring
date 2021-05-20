package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor2 implements HandlerInterceptor {
    //接口内部方法都用default修饰（有方法体），需要手动覆盖重要方法

    //在目标方法执行之前执行（前端控制器（TargerController）方法执行之前）

    /**
     * 如果如果参数param为yes就放行否则就转发到error.jsp
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle2222......");
        return true;
    }

    //在目标方法执行之后，视图对象返回之前执行（可用于修改视图）
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle2222......");
    }

    //在流程都执行完毕后执行（收尾工作）
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion2222......");
    }
}
