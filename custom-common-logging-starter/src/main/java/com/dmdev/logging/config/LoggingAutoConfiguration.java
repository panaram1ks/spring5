package com.dmdev.logging.config;

import com.dmdev.logging.config.aop.CommonPointcuts;
import com.dmdev.logging.config.aop.FirstAspect;
import com.dmdev.logging.config.aop.SecondAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class) // starter will work only LoggingProperties.class is in classpath
@ConditionalOnProperty(prefix = "app.common.logging", name = "enable", havingValue = "true")
// starter will work only if we set property to TRUE
// we need both condition is true to our starter work !
public class LoggingAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }

    @Bean
    @ConditionalOnMissingBean // if you want to be able to override this bean in your application
    public CommonPointcuts commonPointcuts(){
        return new CommonPointcuts();
    }

    @Bean
    @Order(1)
    public FirstAspect firstAspect(){
        return new FirstAspect();
    }

    @Bean
    @Order(2)
    public SecondAspect secondAspect(){
        return new SecondAspect();
    }
}
