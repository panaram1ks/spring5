package com.dmdev.spring.service;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.dto.CompanyReadDto;
import com.dmdev.spring.listener.entity.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    public static final Integer COMPANY_ID = 1;

    @Mock
    private CrudRepository<Integer, Company> companyRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById(COMPANY_ID);
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertTrue(actualResult.isPresent());
        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
        Mockito.verify(eventPublisher).publishEvent(Mockito.any(EntityEvent.class));
        Mockito.verifyNoMoreInteractions(eventPublisher, userRepository);
    }
}