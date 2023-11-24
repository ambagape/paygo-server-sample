package com.gitittech.paygo.entities;

import com.gitittech.paygo.commons.entities.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class JpaNotification extends BaseEntity {

    @ManyToOne
    private JpaUser recipient;
    private String title;
    private String message;
    private String status = "pending";
    private String type;

    public JpaNotification() {

    }

    public JpaNotification(JpaUser recipient, String title, String message, String type) {
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public JpaUser getRecipient() {
        return recipient;
    }

    public void setRecipient(JpaUser recipient) {
        this.recipient = recipient;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
