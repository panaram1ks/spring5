package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@IT
@RequiredArgsConstructor
class UserRepositoryTestIT {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {
//        List<User> users = userRepository.findAllBy("%a%", "%ov%");
        List<User> users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
    }
}