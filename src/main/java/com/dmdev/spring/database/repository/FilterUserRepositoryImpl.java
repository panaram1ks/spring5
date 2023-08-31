package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.querydsl.QPredicates;
import com.dmdev.spring.dto.PersonalInfo;
import com.dmdev.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.dmdev.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    public static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
               firstname,
               lastname,
               birth_date
            FROM users
            WHERE company_id = ?
            AND role = ?
            """;

    private final EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.firstname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();

    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ),
                companyId, role.name()
        );
    }

//    @Override
//    public List<User> findAllByFilter(UserFilter filter) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = criteriaQuery.from(User.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        if(filter.firstname() != null){
//            predicates.add(criteriaBuilder.like(root.get("firstname"), filter.firstname()));
//        }
//        if(filter.lastname() != null){
//            predicates.add(criteriaBuilder.like(root.get("lastname"), filter.lastname()));
//        }
//        if(filter.birthDate() != null){
//            predicates.add(criteriaBuilder.lessThan(root.get("birthDate"), filter.birthDate()));
//        }
//        criteriaQuery.select(root).where(predicates.toArray(Predicate[]::new));
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }
}
