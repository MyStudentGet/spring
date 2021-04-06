package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//<bean id="userService" class="com.itheima.service.impl.UserServiceImpl">
@Component("userService")
public class UserServiceImpl implements UserService {

    //<property name="userDao" ref="userDao"/>
    @Autowired  // 自动注入
    @Qualifier("userDao")
    // Resource("userDao"): 相当于上面两个
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
    }
}
