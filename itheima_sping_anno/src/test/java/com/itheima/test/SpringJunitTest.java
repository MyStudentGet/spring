package com.itheima.test;

import com.itheima.config.SpringCofiguration;
import com.itheima.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class) //代码使用Spring提供的内核运行
//@ContextConfiguration("classpath:applicationContext.xml")// 导入哪个配置文件
@ContextConfiguration(classes = {SpringCofiguration.class})// 导入哪个配置类
public class SpringJunitTest {

    @Autowired // 要测试谁就注入谁
    private UserService userService;

    @Autowired // 要测试谁就注入谁
    private DataSource dataSource;

    @Test
    public void test1() throws SQLException {
        userService.save();
        System.out.println(dataSource.getConnection());
    }

}
