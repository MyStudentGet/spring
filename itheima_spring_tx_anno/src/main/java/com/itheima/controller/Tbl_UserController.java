package com.itheima.controller;

import com.itheima.service.Tbl_UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tbl_UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        Tbl_UserService tbl_userService = (Tbl_UserService) app.getBean("tbl_userService");
        tbl_userService.transfer("小明","王刚",100.0);
        //转账（测试事务回滚（事务控制））
        //测试代码之前在Tbl_UserServiceImpl类里添加一个错误，使得代码出错从而事务回滚
    }
}
