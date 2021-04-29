package com.itheima.service;

public interface Tbl_UserService {
    //两人转账方法
    public void transfer(String outMan, String inMan, double money);
}
