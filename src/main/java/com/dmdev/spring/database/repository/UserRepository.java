package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(@Param("firstname") String firstname, @Param("lastname") String lastname);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User  u set u.role = :role where u.id in (:ids)")
    int updateRole(@Param("role") Role role, @Param("ids") Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByBirthDateDesc(LocalDate birthDate);
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    List<User> findAllBy(Pageable pageable);

}
