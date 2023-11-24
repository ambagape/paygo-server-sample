package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "notification_templates")
public class JpaNotificationTemplate extends BaseEntity {
    private String type;
    private String title;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
