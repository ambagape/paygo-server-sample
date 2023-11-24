package com.gitittech.paygo.message.impl;

import com.gitittech.paygo.entities.JpaNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationCommandTemplateRepository extends JpaRepository<JpaNotificationTemplate, Long> {

}