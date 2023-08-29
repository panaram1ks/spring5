package com.dmdev.spring.integration.service;


import com.dmdev.spring.config.DatabaseProperties;
import com.dmdev.spring.dto.CompanyReadDto;
import com.dmdev.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//@SpringBootTest//(classes = ApplicationRunner.class) // (classes = ApplicationRunner.class)-optional, this annotation find main class itself
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class) // initializers = ConfigDataApplicationContextInitializer.class need to get properties form YAML file
@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // we can use it from spring.properties file
public class CompanyServiceIT {
    public static final Integer COMPANY_ID = 1;
     private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
        ;
    }

}
