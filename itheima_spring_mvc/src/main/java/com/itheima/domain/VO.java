package com.itheima.domain;

import java.util.List;

public class VO {
    private List<Tbl_User> tbl_userList;

    public List<Tbl_User> getTbl_userList() {
        return tbl_userList;
    }

    public void setTbl_userList(List<Tbl_User> tbl_userList) {
        this.tbl_userList = tbl_userList;
    }

    @Override
    public String toString() {
        return "VO{" +
                "tbl_userList=" + tbl_userList +
                '}';
    }
}
