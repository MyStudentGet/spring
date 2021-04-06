package com.itheima.demo;

import com.itheima.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserDaoDemo {
    public static void main(String[] args) {
        //从类的根路径下加载配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        //从磁盘路径上加载配置文件
//        ApplicationContext app = new FileSystemXmlApplicationContext("D:\\2020上学期备课\\ssmPro\\spring\\itheima_spring_ioc\\src\\main\\resources\\applicationContext.xml");

        //使用注解配置容器对象时，需要使用此类来创建spring容器。他来读取注解
//        ApplicationContext app = new AnnotationConfigApplicationContext("applicationContext.xml");



        // 使用getBean的两种方法：
        //1、用id查找（允许容器有多个相同类型的bean（class相同））
        UserDao userDao= (UserDao) app.getBean("userDao");

        //2、用对象类型(不用强转)
//        UserDao userDao=app.getBean(UserDao.class);

        userDao.save();
        ((ClassPathXmlApplicationContext) app).close();// 手动关闭容器（使生命周期例子生效）（app强转成子类才有关闭方法）

    }
}