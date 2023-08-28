package com.dmdev.spring;

import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.database.repository.CrudRepository;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

public class ApplicationRunner {

    public static void main(String[] args) {
        String value = "anytext";
        boolean assignableFrom = CharSequence.class.isAssignableFrom(value.getClass());
        System.out.println(assignableFrom);
        boolean assignableFrom1 = BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass());
        System.out.println(assignableFrom1);
        boolean assignableFrom2 = Serializable.class.isAssignableFrom(value.getClass());
        System.out.println(assignableFrom2);

        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
//        System.out.println(context.getBean(ConnectionPool.class));
            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool1);

            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }


    }

}
