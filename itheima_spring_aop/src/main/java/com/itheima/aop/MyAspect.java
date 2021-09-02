package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;

public class MyAspect {


    // 前置增强
    public void before(){
        System.out.println("前置增强。。。。。。");
    }

    // 后置增强
    public void afterReturning(){
        System.out.println("后置增强。。。。。。");
    }

    // 环绕增强
    // ProceedingJoinPoint：切点(正在执行的连接点)
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强。。。。。。");
        //运行切点方法（切点方法可能有返回值）
        Object proceed = pjp.proceed();
        System.out.println("环绕后增强。。。。。。");
        return proceed;
    }

    // 异常抛出增强
    public void afterThrowing(Exception e){
        System.out.println("异常抛出增强。。。。。。");
    }

    // 最终增强
    public void after(){
        System.out.println("最终增强。。。。。。");
    }
}
