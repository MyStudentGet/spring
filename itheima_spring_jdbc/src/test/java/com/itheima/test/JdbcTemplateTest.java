package com.itheima.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;
import java.util.Date;

public class JdbcTemplateTest {

    @Test
    //测试JdbcTemplate    xml开发步骤
    public void test2() throws PropertyVetoException {
        //从容器获取配置好的Jdbc模板对象
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");

        //执行操作（插入一条数据）
        int row = jdbcTemplate.update("insert into tbl_User (id,username,password,email,balance,birthday) values (?,?,?,?,?,?)",15,"李四","666666","88888@qq.com",10000.0,new Date());
        System.out.println(row);
    }

    @Test
    //测试JdbcTemplate    普通开发步骤
    public void test1() throws PropertyVetoException {
        //创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xsgl");
        dataSource.setUser("root");
        dataSource.setPassword("528812");


        // 创建Jdbc模板对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        // 设置数据源对象  知道数据库在哪
        jdbcTemplate.setDataSource(dataSource);

        //执行操作
        int row = jdbcTemplate.update("insert into tbl_User (id,username,password,email,balance,birthday) values (?,?,?,?,?,?)",15,"李四","666666","88888@qq.com",10000.0,new Date());
        System.out.println(row);

    }
}
