package com.itheima.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect // 标注当前MyAspect是一个切面类
public class MyAspect {

    // 前置增强
    @Before("execution(* com.itheima.anno.*.*(..))")
    public void before(){
        System.out.println("前置增强。。。。。。");
    }

    // 后置增强
//    @AfterReturning("execution(* com.itheima.anno.*.*(..))")
    public void afterReturning(){
        System.out.println("后置增强。。。。。。");
    }

    // 环绕增强
    // ProceedingJoinPoint：切点(正在执行的连接点)
    @Around("execution(* com.itheima.anno.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强。。。。。。");
        //运行切点方法（切点方法可能有返回值）
        Object proceed = pjp.proceed();
        System.out.println("环绕后增强。。。。。。");
        return proceed;
    }

    // 异常抛出增强
    @AfterThrowing("execution(* com.itheima.anno.*.*(..))")
    public void afterThrowing(){
        System.out.println("异常抛出增强。。。。。。");
    }

    // 最终增强
    @After("execution(* com.itheima.anno.*.*(..))")
    public void after(){
        System.out.println("最终增强。。。。。。");
    }
}
