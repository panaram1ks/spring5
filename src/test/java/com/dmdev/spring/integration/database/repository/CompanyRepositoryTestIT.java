package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
//@Transactional // from Spring, not from javax package // We also have @Rollback & @Commit
class CompanyRepositoryTestIT {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(transaction -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            Assertions.assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Apple")
                .locales(
                        Map.of("ru", "Apple описание",
                                "en", "Apple description")
                ).build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}