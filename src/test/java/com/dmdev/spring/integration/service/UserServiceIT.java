package com.dmdev.spring.integration.service;

import com.dmdev.spring.integration.annotation.IT;
import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

//@SpringBootTest// tests create 2 context, because they have different configurations
@IT
@RequiredArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceIT {

    private final UserService userService;

    @Test
    void test1() {
        System.out.println();
    }

    @Test
    void test2() {
        System.out.println();
    }
}