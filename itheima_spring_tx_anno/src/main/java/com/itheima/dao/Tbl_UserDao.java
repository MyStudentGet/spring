package com.itheima.dao;

public interface Tbl_UserDao {
    //钱转出
    public void out(String outMan, double money);
    //钱转入
    public void in(String inMan, double money);
}
