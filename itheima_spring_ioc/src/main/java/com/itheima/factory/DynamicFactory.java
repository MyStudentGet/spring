package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoimpl;

public class DynamicFactory {

    public UserDao getUserDao(){ //创建一个静态工厂（生产UserDao对象）
        return new UserDaoimpl();
    }
}
