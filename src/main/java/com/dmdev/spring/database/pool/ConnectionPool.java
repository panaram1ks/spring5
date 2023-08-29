package com.dmdev.spring.database.pool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    //    First invoke constructor then setters then init method!
    @PostConstruct
    private void init() {
        log.info("init: Initialization connection pool");
    }

    @PreDestroy
    public void destroy() throws Exception {
        log.info("destroy use interface DisposableBean");
    }
}
