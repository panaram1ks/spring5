package com.dmdev.spring.config;

import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

//@ImportResource("classpath:application.xml") // we can add configuration from xml
@Import(WebConfiguration.class) // can import configuration from different packages!
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.dmdev.spring", useDefaultFilters = false,
        includeFilters = {
        @Filter(type= FilterType.ANNOTATION, value = Component.class),
        @Filter(type= FilterType.ASSIGNABLE_TYPE , value = CrudRepository.class),
        @Filter(type= FilterType.REGEX , pattern = "com\\..+Repostirory"),
        }
)
public class ApplicationConfiguration {

        // we can create bean as a field of ApplicationConfiguration.class cause it is Component

        @Bean("pool2")
        @Scope(BeanDefinition.SCOPE_SINGLETON)
        public ConnectionPool pool2(@Value("${db.username}") String username) {
                return new ConnectionPool(username, 20);
        }

        @Bean
        public UserRepository userRepository2(ConnectionPool pool2){
                return new UserRepository(pool2);
        }
}
