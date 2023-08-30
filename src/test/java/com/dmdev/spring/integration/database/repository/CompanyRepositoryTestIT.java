package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
//@Transactional // from Spring, not from javax package // We also have @Rollback & @Commit
class CompanyRepositoryTestIT {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("google");
        companyRepository.findAllByNameIsContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(11);
        Assertions.assertThat(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
//        companyRepository.delete(maybeCompany.get());
        entityManager.flush();
        assertTrue(companyRepository.findById(11).isEmpty());

    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(transaction -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            Assertions.assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    @Commit
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