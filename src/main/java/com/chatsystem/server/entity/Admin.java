package com.chatsystem.server.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(UserRole.ADMIN)
public class Admin extends User {

}
