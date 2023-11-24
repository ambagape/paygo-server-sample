package com.gitittech.paygo.user.api;

import com.gitittech.ambagapequery.predicates.PredicateBuilder;
import com.gitittech.paygo.entities.JpaUser;
import com.gitittech.paygo.entities.QJpaUser;
import com.querydsl.core.BooleanBuilder;

import javax.persistence.NoResultException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserReadRepository extends JpaRepository<JpaUser, String>, QuerydslPredicateExecutor {

    String PATH_META_DATA = "jpaUser";

    default Optional<JpaUser> findExistingUser(String email, String phone, String bvn) throws NoResultException {
        final var qUser = QJpaUser.jpaUser;
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

        final var iterator = this.findAll(predicate).iterator();
        return iterator.hasNext() ? Optional.of((JpaUser) iterator.next()) : Optional.empty();
    }

    default Optional<JpaUser> findByCustomerCode(String customerCode
    ) {
        var exp = new PredicateBuilder(PATH_META_DATA, JpaUser.class)
                .with("customerCode", ":", customerCode)
                .buildAndCombination();
        return findOne(exp);
    }

}
