package com.gitittech.paygo.message.api;

import com.gitittech.paygo.entities.JpaNotificationTemplate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationTemplateReadRepository extends JpaRepository<JpaNotificationTemplate, String>, 
        QuerydslPredicateExecutor {
    String PATH_META_DATA = "jpaNotificationTemplate";
}
