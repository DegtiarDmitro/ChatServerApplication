package com.chatsystem.server.entity;


import javax.persistence.*;

@Entity
@DiscriminatorValue(UserRole.MANAGER)
public class Manager extends User {

}
