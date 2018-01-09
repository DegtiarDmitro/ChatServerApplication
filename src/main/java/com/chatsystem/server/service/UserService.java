package com.chatsystem.server.service;


import com.chatsystem.server.entity.User;

public interface UserService extends DataService<User> {
    User findByEmail(String email);
    User findByUsername (String username);
}
