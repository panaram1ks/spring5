package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.pool.ConnectionPool;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool pool1;

    public UserRepository(ConnectionPool connectionPool) {
        this.pool1 = connectionPool;
    }
}
