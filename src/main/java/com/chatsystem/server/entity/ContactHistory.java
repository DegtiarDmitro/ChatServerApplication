package com.chatsystem.server.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONTACT_HISTORY")
public class ContactHistory extends BaseEntity {



    @Column(name = "CREATED_TIME", nullable = false, updatable=false)
    @CreationTimestamp
    private Timestamp createdTime;


    @OneToMany(mappedBy = "contactHistory")
    private List<Message> messages = new ArrayList<>();


    public ContactHistory() {
        super();
    }



    public ContactHistory(List<Message> messages) {
        super();
        this.messages = messages;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }



    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        messages.add(message);
    }



}
