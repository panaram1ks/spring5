package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.bpp.InjectBean;
import com.dmdev.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;


}
