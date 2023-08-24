package com.dmdev.spring;

import com.dmdev.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
//        System.out.println(context.getBean(ConnectionPool.class));
        ConnectionPool pool2 = context.getBean("pool2", ConnectionPool.class);
        System.out.println(pool2);

    }

}
