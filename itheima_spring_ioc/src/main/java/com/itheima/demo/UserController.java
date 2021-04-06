package com.itheima.demo;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceimpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {

    public static void main(String[] args){
//        没配置Bean之前的写法
//        UserService userService = new UserServiceimpl();
//        userService.save();

//        配置之后的写法
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService= (UserService) app.getBean("userService");
        userService.save();


    }
}
