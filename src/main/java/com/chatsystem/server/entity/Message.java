package com.chatsystem.server.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE")
public class Message extends BaseEntity {


    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TIME", nullable = false, updatable=false)
    @CreationTimestamp
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name="ADDRESSEE_USER_ID")
    private User addressee;

    @ManyToOne
    @JoinColumn(name="DESTINATION_USER_ID")
    private User destination;

    @ManyToOne
    @JoinColumn(name="CONTACT_ID")
    private ContactHistory contactHistory;

    @OneToOne(mappedBy = "message")
    private MessageFile file;

    public Message() {
        super();
    }

    public Message(String message, Timestamp time, User addressee, User destination, ContactHistory contactHistory) {
        super();
        this.message = message;
        this.time = time;
        this.addressee = addressee;
        this.destination = destination;
        this.contactHistory = contactHistory;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public User getAddressee() {
        return addressee;
    }

    public void setAddressee(User addressee) {
        this.addressee = addressee;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public ContactHistory getContactHistory() {
        return contactHistory;
    }

    public void setContactHistory(ContactHistory contactHistory) {
        this.contactHistory = contactHistory;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", addressee=" + addressee +
                ", destination=" + destination +
                ", contactHistory=" + contactHistory +
                '}';
    }
}
