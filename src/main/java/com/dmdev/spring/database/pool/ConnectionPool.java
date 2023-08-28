package com.dmdev.spring.database.pool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pool1")
public class ConnectionPool {
    private final String username;
    private final Integer poolSize;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

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
