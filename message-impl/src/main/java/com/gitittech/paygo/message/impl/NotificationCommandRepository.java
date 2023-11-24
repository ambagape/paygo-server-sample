package com.gitittech.paygo.message.impl;

import com.gitittech.paygo.entities.JpaNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationCommandRepository extends JpaRepository<JpaNotification, Long> {
}