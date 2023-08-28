package com.dmdev.spring.config;

import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.web.config.WebConfiguration;
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


}
