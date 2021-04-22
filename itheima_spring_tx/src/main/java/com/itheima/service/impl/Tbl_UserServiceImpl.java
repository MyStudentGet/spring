package com.itheima.service.impl;

import com.itheima.dao.Tbl_UserDao;
import com.itheima.service.Tbl_UserService;

public class Tbl_UserServiceImpl implements Tbl_UserService {

    private Tbl_UserDao tbl_userDao;

    public void setTbl_userDao(Tbl_UserDao tbl_userDao) {
        this.tbl_userDao = tbl_userDao;
    }
    public void transfer(String outMan,String inMan,double money){
        tbl_userDao.out(outMan,money);
        tbl_userDao.in(inMan,money);
    }
}
