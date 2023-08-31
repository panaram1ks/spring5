package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.dto.PersonalInfo;
import com.dmdev.spring.dto.PersonalInfo2;
import com.dmdev.spring.dto.UserFilter;
import com.dmdev.spring.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserRepositoryTestIT extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkBatch(){
        List<User> all = userRepository.findAll();
        userRepository.updateCompanyAndRole(all);
        System.out.println();
    }


//    @Test
//    @Commit
//    void checkAuditing(){
//        User ivan = userRepository.findById(1l).get();
//        ivan.setBirthDate(ivan.getBirthDate().plusYears(1l));
//        userRepository.flush();
//        System.out.println();
//    }

    @Test
    void checkJdbcTemplate_Spring(){
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.ADMIN);
        assertEquals(users.size(), 1);
    }

    @Test
    void checkQueryDsl(){
        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        List<User> users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkCustomImplementation(){
        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        List<User> users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkProjections() {
        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println();
    }

    @Test
    void checkSlice() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        ;
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkPageable() {
//        Pageable
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        assertThat(slice).hasSize(2);
    }

    @Test
    void specialParameters() {
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
    @Disabled
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