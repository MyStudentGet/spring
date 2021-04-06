package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceimpl implements UserService {
    // 使用依赖注入(set方法和构造方法)后可以不用从容器获取UserDao对象了（降低代码的依赖性）


    private UserDao userDao;
    // 创建set方法方便依赖注入（<property/>）
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // 有参构造方法方便依赖注入（<property/>）
    public UserServiceimpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // 无参构造方法
    public UserServiceimpl() {
    }

    public void save() {
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserDao userDao= (UserDao) app.getBean("userDao");
        userDao.save();
    }
}
