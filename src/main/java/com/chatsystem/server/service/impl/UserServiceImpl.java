package com.chatsystem.server.service.impl;

import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.dao.UserRepository;
import com.chatsystem.server.service.UserService;
import com.chatsystem.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends DataServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(DataRepository<User> dataRepository, UserRepository userRepository) {
        super(dataRepository);
        this.userRepository = userRepository;
    }

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
