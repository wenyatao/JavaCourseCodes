package com.wyt.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        //基于XML装配
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-bean.xml");
        //读取bean，构造方式输出结果
        System.out.println(applicationContext.getBean("user1"));
        //读取bean，属性设值方式输出结果
        System.out.println(applicationContext.getBean("user2"));
        System.out.println("*********基于注解装配**********");
        // 加载配置文件-基于注解装配
        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("spring/spring-bean1.xml");
        UserService userService = (UserService) applicationContext1.getBean("userService");
        userService.say();
        System.out.println("*********自动装配**********");
        // 自动装配 去掉@Repository注解
        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("spring/spring-bean3.xml");
        UserService userService1 = (UserService) applicationContext2.getBean("userService");
        userService1.say();

    }



}
