package com.gitittech.paygo.user.impl;

import com.gitittech.paygo.entities.JpaUser;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCommandRepository extends JpaRepository<JpaUser, String> {

    default JpaUser create(JpaUser jpaUser, String executor) {                     
        jpaUser.setCreated(new Date());
        jpaUser.setCreated(new Date());
        jpaUser.setCreatedBy(executor);
        jpaUser.setModifiedBy(executor);
        this.save(jpaUser);
        return jpaUser;
    }
}
