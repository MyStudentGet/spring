package com.itheima.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    // 测试scope属性(创建多个新的对象)
    public void test(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(app.getBean("userDao"));
        System.out.println(app.getBean("userDao"));
        System.out.println(app.getBean("userDao"));

    }
}
