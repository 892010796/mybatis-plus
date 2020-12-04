package com.example.mybatisplustest.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/12/4 16:10
 */
public class BeanLifeCycle {

    @Bean(name = "person2")
    Person2 getPerson(){
        return new Person2();
    }



    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("com/example/mybatisplustest/beans/beans.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
