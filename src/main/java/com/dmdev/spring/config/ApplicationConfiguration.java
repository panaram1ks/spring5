package com.dmdev.spring.config;

import com.dmdev.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.*;

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


}
