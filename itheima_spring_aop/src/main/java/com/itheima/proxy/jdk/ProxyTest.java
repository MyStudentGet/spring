package com.itheima.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
// 基于jdbc的代理实现（目标对象有接口时使用）
/*
    一、创建目标对象
    二、获得增强对象（切面类）
    三、创建动态生成的代理对象
        1、得到目标对象的类加载器
        2、得到目标对象相同的“接口”字节码对象数组
        3、创建代理对象
*/
public class ProxyTest {

    public static void main(String[] args) {

         //创建目标对象
         final Target target = new Target();

         // 获得增强对象（切面类）
        final Advice advice = new Advice();


         // 返回值就是动态生成的代理对象
         //因为是兄弟关系（与目标类继承同一接口）必须用目标对象的接口对象来接
         TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标对象类加载器（通过它可以得到一个文件的输入流）
                target.getClass().getInterfaces(),//目标对象相同的接口字节码对象数组
                new InvocationHandler() {
                    //调用代理对象的任何方法  实质执行的都是invoke方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();

                        //执行需要增强的方法
                        Object invoke = method.invoke(target, args);

                        //后置增强
                        advice.afterReturning();

                        return invoke;
                    }
                }
        );
         // 调用代理对象的方法
        proxy.save();

    }
}
