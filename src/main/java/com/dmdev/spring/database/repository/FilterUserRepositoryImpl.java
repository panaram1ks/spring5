package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.database.querydsl.QPredicates;
import com.dmdev.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.dmdev.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;

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
