package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional, Entity, Future
    @Query(name = "Company.findByName")
    Optional<Company> findByName(@Param("name2") String name);

    // Collection, Stream (batch, close)
    List<Company> findAllByNameIsContainingIgnoreCase(String fragment);

    @Query(value = "select c from Company c join fetch c.locales cl where lower(c.name) = lower(:name) ")
    Optional<Company> findUseName(@Param("name") String name);
}
