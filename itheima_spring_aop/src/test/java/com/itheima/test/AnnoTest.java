package com.itheima.test;

import com.itheima.aop.TargetInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 测试配置文件前置增强

//Spring集成Junit（测试spring容器的bean是否注入成功)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-anno.xml")
public class AnnoTest {

    //要测哪个对象就注入谁
    @Autowired
    private TargetInterface target;

    @Test
    public void test(){
        target.save();
    }

}
