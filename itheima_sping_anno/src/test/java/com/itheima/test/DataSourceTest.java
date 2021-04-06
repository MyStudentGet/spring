package com.itheima.test;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ResourceBundle;

//手动创建数据源
public class DataSourceTest {

    @Test
    //测试spring容器创建    c3p0数据源
    public void test4() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取容器中的DataSource类型的对象（已经配置好了各种参数）
        DataSource dataSource = (DataSource) app.getBean("dataSource");

        // 获得连接对象
        Connection connection = dataSource.getConnection();

        // 输出连接对象地址
        System.out.println(connection);

        // 回收资源
        connection.close();
    }

    @Test
    //测试手动创建    c3p0数据源(加载properties配置文件形式)
    public void test3() throws Exception {
        // 读取配置文件(jdbc.properties)
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        // 创建数据源对象  设置连接参数   获取资源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        //获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        // 关闭数据池（归还资源）
        connection.close();
    }

    @Test
    //测试手动创建    c3p0数据源
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xsgl");
        dataSource.setUser("root");
        dataSource.setPassword("528812");
        //获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        // 关闭数据池（归还资源）
        connection.close();
    }
    @Test
    //测试手动创建    druid(jdbc)数据源
    public void test2() throws Exception {
        DruidAbstractDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xsgl");
        dataSource.setUsername("root");
        dataSource.setPassword("528812");
        //获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        // 关闭数据池（归还资源）
        connection.close();
    }
}
