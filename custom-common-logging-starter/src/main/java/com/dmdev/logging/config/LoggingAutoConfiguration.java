package com.dmdev.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}
