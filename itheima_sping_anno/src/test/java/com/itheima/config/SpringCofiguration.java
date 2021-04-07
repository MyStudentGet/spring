package com.itheima.config;

import org.springframework.context.annotation.*;

// 代替配置文件（applicationContext.xml）的类

//标志该类是Spring的核心配置类
@Configuration
// 注解扫描 ：<context:component-scan base-package="com.itheima"/>
@ComponentScan("com.itheima")
//加载另一个配置文件模块： <import resource="DataSourceCofiguration"/>
@Import({DataSourceCofiguration.class}) // 这可以接受数组（多个模块导入用“，”号隔开）
public class SpringCofiguration {

}
