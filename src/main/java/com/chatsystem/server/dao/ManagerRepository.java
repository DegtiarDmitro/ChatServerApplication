package com.chatsystem.server.dao;

import com.chatsystem.server.entity.Manager;

public interface ManagerRepository extends DataRepository<Manager>  {
    Manager findByEmail (String email);
    Manager findByUsername (String username);
}
