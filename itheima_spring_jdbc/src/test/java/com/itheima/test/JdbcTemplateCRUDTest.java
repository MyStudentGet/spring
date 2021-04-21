package com.itheima.test;

import com.itheima.domain.tbl_User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test //查询所有
    public void testQuerAll(){
        //BeanPropertyRowMapper
        //常应用于使用Spring的JdbcTemplate查询数据库，获取List结果列表，数据库表字段和实体类自动对应。
        List<tbl_User> tbl_UserList = jdbcTemplate.query("select * from tbl_User",new BeanPropertyRowMapper<tbl_User>(tbl_User.class));
        System.out.println(tbl_UserList);
    }
    @Test //查询指定id的对象
    public void testQuerOne(){
        tbl_User tbl_user = jdbcTemplate.queryForObject("select * from tbl_User where id=?", new BeanPropertyRowMapper<tbl_User>(tbl_User.class), 11);
        System.out.println(tbl_user);
    }
    @Test //查询总条数
    public void testQuerCount(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from tbl_User", Long.class);
        System.out.println(aLong);
    }

    @Test //修改余额
    public void testUpdate(){
        jdbcTemplate.update("update tbl_User set balance=? where username=?",2000,"王刚");
    }

}
