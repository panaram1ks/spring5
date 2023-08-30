package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTestIT {

    private final UserRepository userRepository;

    @Test
    void specialParameters(){
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));
        // safe method create sort object use fields without hardcoding string field names
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort.descending());
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkSort() {
        Sort sortById = Sort.by("firstname").and(Sort.by("lastname"));
        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById.descending());
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        List<User> allUsers = userRepository.findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate.now());
        assertThat(allUsers).hasSize(3);
        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5l, user.getId()));
    }

    @Test
    void checkQueries() {
//        List<User> users = userRepository.findAllBy("%a%", "%ov%");
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1l);
        assertSame(Role.ADMIN, ivan.getRole());

        int resultCount = userRepository.updateRole(Role.USER, 1l, 5l);
        assertEquals(resultCount, 2);

        User theSameIvan = userRepository.getById(1l);
        assertSame(Role.USER, theSameIvan.getRole());
    }
}