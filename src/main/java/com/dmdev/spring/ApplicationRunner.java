package com.dmdev.spring;

import com.dmdev.spring.config.ApplicationConfiguration;
import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

//        try (var context = new ClassPathXmlApplicationContext("application.xml")) {

        try (var context = new AnnotationConfigApplicationContext()) {

            // second variant install profile set it into context in environment
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool1);

//            CrudRepository companyRepository = context.getBean("companyRepository", CrudRepository.class);
            CompanyService companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }


    }

}
