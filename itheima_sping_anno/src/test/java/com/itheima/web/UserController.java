package com.itheima.web;

import com.itheima.config.SpringCofiguration;
import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 测试新注解（导入代替xml的类）
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringCofiguration.class);
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
        app.close(); // 关闭容器，方便测试Service对象销毁方法
    }
}
