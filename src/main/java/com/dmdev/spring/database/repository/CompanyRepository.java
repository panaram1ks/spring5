package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.bpp.Auditing;
import com.dmdev.spring.database.bpp.Transaction;
import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    //    @Resource(name = "pool1")
//    @Autowired
//    @Qualifier("pool1")
    private ConnectionPool pool1;

    @Autowired
    private List<ConnectionPool> pools;

    @Value("${db.pool.size}")
    private Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("deleteById method...");
    }
}
