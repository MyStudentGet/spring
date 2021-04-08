package com.itheima.proxy.cglib;
// 切面类
public class Advice {

    //前置增强
    public void before(){
        System.out.println("前置增强。。。");
    }
    //后置增强
    public void afterReturning(){
        System.out.println("后置增强。。。");
    }

}
