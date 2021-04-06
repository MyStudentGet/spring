package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import org.springframework.stereotype.Component;

//<bean id="userDao" class="com.itheima.dao.impl.UserDaoImpl"/>
@Component("userDao")
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("save...running...");
    }
}
