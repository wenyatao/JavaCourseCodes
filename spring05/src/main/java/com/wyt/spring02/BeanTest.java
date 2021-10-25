package com.wyt.spring02;

import com.wyt.spring01.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-bean4.xml");
        ISchool school = (ISchool) applicationContext.getBean("school");
        school.ding();



    }



}
