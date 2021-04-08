package com.itheima.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

// 基于cglib的代理实现
/*
    一、创建目标对象
    二、获得增强对象（切面类）
    三、创建增强器
        1、创建增强器
        2、设置父类（目标类）
        3、设置回调
    四、创建代理对象（因为是父子关系，所以可以用目标对象去接）
*/
public class ProxyTest {

    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        // 获得增强对象
        final Advice advice = new Advice();


        // 返回值就是动态生成的代理对象
        //1、创建增强器
        Enhancer enhancer = new Enhancer();
        //2、设置父类（目标）
        enhancer.setSuperclass(Target.class);
        //3、设置回调
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 执行前置
                advice.before();

                //执行目标方法（要加强的方法）
                Object invoke = method.invoke(target, args);

                // 执行后置
                advice.afterReturning();

                return invoke;
            }
        });
        //4、创建代理对象
        //因为是父子关系，所以可以用目标对象去接
        Target proxy = (Target) enhancer.create();

        proxy.save();

    }
}
