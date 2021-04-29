package com.itheima.service.impl;

import com.itheima.dao.Tbl_UserDao;
import com.itheima.service.Tbl_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("tbl_userService")
//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
public class Tbl_UserServiceImpl implements Tbl_UserService {

    @Autowired
    @Qualifier("tbl_userDao")
    private Tbl_UserDao tbl_userDao;

//    public void setTbl_userDao(Tbl_UserDao tbl_userDao) {
//        this.tbl_userDao = tbl_userDao;
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    //相当于通知和aop织入
    //当有多个方法需要增强时
            //1、在需要增强的方法上加上此注释设置不同参数（传播行为等）
            //2、在类上加此注释（表示此类所有方法都增强）
    public void transfer(String outMan,String inMan,double money){
        tbl_userDao.out(outMan,money);
//        int i=1/0;
        tbl_userDao.in(inMan,money);
    }

}
