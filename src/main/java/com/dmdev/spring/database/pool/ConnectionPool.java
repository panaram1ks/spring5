package com.dmdev.spring.database.pool;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

public class ConnectionPool implements InitializingBean {
    private final String username;
    private final Integer poolSize;
    private final List<Object> args;
    private Map<String, Object> properties;

    private final String driverName;

    public ConnectionPool(String username, Integer poolSize, List<Object> args, Map<String, Object> properties, String driverName) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
        this.driverName = driverName;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }


    //    First invoke constructor then setters then init method!
    private void init() {
        System.out.println("init: Initialization connection pool");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties set");
    }
}
