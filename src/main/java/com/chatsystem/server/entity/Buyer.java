package com.chatsystem.server.entity;


import javax.persistence.*;

@Entity
@DiscriminatorValue(UserRole.BUYER)
public class Buyer extends User {


    public Buyer() {
        super();
        setUserStatus(UserStatus.DISABLED);
    }
}