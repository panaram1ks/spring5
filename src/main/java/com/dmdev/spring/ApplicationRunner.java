package com.dmdev.spring;

import com.dmdev.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
//        System.out.println(context.getBean(ConnectionPool.class));
        ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
        System.out.println(pool1);

    }

}
