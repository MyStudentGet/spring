package com.itheima.web;

import com.itheima.config.SpringCofiguration;
import com.itheima.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    static {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringCofiguration.class);
    }

    @Autowired
    @Qualifier("userService")
    public UserService userService;

    @Test
    public void save(){

        userService.save();
    }

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 测试新注解（导入代替xml的类）
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringCofiguration.class);
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
//        app.close(); // 关闭容器，方便测试Service对象销毁方法
    }
}
