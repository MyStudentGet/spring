package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//<bean id="userService" class="com.itheima.service.impl.UserServiceImpl">
//@Component("userService")
@Service("userService")
// 单例或多例
//@Scope("prototype") // 多例
public class UserServiceImpl implements UserService {

    // 注入普通值（值来自于容器中导入的文件（jdbc.properties））
    @Value("${jdbc.driver}")
    private String driver;

    //<property name="userDao" ref="userDao"/>
    @Autowired  // 自动注入
    @Qualifier("userDao")
    // Resource("userDao"): 相当于上面两个
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        System.out.println(driver);
        userDao.save();
    }

    //类似于bean属性的init-method(初始化时自动调用的方法)
    @PostConstruct
    public void init(){
        System.out.println("Service对象的初始化方法");
    }

    //类似于bean属性的destory-method(销毁时自动调用的方法)
    @PreDestroy
    public void destory(){
        System.out.println("Service对象的销毁方法");
    }
}
