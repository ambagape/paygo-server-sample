package com.gitittech.paygo.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitittech.ambagapequery.predicates.PredicateBuilder;
import com.gitittech.paygo.entities.QJpaUser;
import com.gitittech.paygo.entities.QUserWithBalanceView;
import com.gitittech.paygo.entities.UserWithBalanceView;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import java.util.Iterator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.NoResultException;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserViewReadRepository extends JpaRepository<UserWithBalanceView, String>, QuerydslPredicateExecutor {

    String PATH_META_DATA = "userWithBalanceView";

    default Optional<UserWithBalanceView> findExistingUser(String email, String phone, String bvn) throws NoResultException {
        final var qUser = QUserWithBalanceView.userWithBalanceView;
        BooleanBuilder predicate = new BooleanBuilder();
        if (email == null && phone == null && bvn != null) {
            return Optional.empty();
        }
        if (email != null) {
            predicate.or(qUser.email.eq(email));
        }
        if (phone != null) {
            predicate.or(qUser.phone.eq(phone));
        }
        if (bvn != null) {
            predicate.or(qUser.bvn.eq(bvn));
        }

        final var optional = findOne(predicate);
        return optional;
    }

    default Optional<UserWithBalanceView> findByCustomerCode(String customerCode) {
        var exp = new PredicateBuilder(PATH_META_DATA, UserWithBalanceView.class)
                .with("customerCode", ":", customerCode)
                .buildAndCombination();
        return findOne(exp);
    }

    default Page<UserWithBalanceView> getUsers(Boolean isSearch, String filter,
            Integer page, Integer size,
            Sort.Direction direction, String... properties) throws JsonProcessingException {
        var predicate = isSearch
                ? new PredicateBuilder<>(PATH_META_DATA, UserWithBalanceView.class)
                        .buildFromJson(filter) : Expressions.TRUE.isTrue();
        return findAll(predicate, PageRequest.of(page, size, direction, properties));
    }
}
