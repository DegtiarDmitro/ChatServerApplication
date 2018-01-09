package com.chatsystem.server.service.impl;

import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.entity.UserContact;
import com.chatsystem.server.service.UserContactService;
import org.springframework.stereotype.Service;

@Service
public class UserContactServiceImpl extends DataServiceImpl<UserContact> implements UserContactService {

    public UserContactServiceImpl(DataRepository<UserContact> dataRepository) {
        super(dataRepository);
    }
}
