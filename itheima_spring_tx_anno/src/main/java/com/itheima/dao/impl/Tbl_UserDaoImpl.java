package com.itheima.dao.impl;

import com.itheima.dao.Tbl_UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//数据处理层
@Repository("tbl_userDao")
public class Tbl_UserDaoImpl implements Tbl_UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    //转钱的方法
    public void out(String outMan,double money){
        jdbcTemplate.update("update tbl_User set balance = balance-? where username = ?",money,outMan);
    }
    //收钱的方法
    public void in(String inMan,double money){
        jdbcTemplate.update("update tbl_User set balance = balance+? where username = ?",money,inMan);
    }
}
