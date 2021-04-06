package com.itheima.dao.impl;

import com.itheima.dao.UserDao;

public class UserDaoimpl implements UserDao {

    private String username;




    public UserDaoimpl() { // 每调用一次代表创建一个新的对象（scope="prototype"：控制容器创建新的对象）
        System.out.println("UserDaoimpl创建。。。。");
    }

    public void save() {
        System.out.println("save runing...");
    }

    public void init(){
        System.out.println("初始化方法...");
    }
    public void destory(){
        System.out.println("销毁方法...");
    }

}
