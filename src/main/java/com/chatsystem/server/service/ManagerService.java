package com.chatsystem.server.service;

import com.chatsystem.server.entity.Manager;

public interface ManagerService extends DataService<Manager> {
    Manager findByUsername(String email);
}
