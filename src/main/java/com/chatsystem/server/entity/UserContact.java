package com.chatsystem.server.entity;




import javax.persistence.*;


@Entity
@Table(name = "USER_CONTACT")
public class UserContact extends BaseEntity {


    @ManyToOne
    @JoinColumn(name="CONTACT_HISTORY_ID")
    private ContactHistory contactHistory;


    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="CONTACT_USER_ID")
    private User contactUser;



    public UserContact() {
        super();
    }

    public UserContact(User user, User contactUser) {
        this.user = user;
        this.contactUser = contactUser;
    }

    public ContactHistory getContactHistory() {
        return contactHistory;
    }

    public void setContactHistory(ContactHistory contactHistory) {
        this.contactHistory = contactHistory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public User getContactUser() {
        return contactUser;
    }

    public void setContactUser(User contactUser) {
        this.contactUser = contactUser;
    }



}
