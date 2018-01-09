package com.chatsystem.server.dao;

import com.chatsystem.server.entity.User;

public interface UserRepository extends DataRepository<User> {
    User findByEmail (String email);
    User findByUsername (String username);
}
