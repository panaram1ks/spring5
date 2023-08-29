package com.dmdev.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
        System.out.println("init: Initialization connection pool");
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("destroy use interface DisposableBean");
    }
}
