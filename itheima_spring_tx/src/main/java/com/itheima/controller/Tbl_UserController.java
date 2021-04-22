package com.itheima.controller;


import com.itheima.service.Tbl_UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tbl_UserController {
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    Tbl_UserService a = (Tbl_UserService) app.getBean("tbl_userService");

}
