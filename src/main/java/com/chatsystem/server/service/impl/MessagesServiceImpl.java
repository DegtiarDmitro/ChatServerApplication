package com.chatsystem.server.service.impl;


import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.entity.Message;
import com.chatsystem.server.service.MessagesService;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl extends DataServiceImpl<Message> implements MessagesService {
    public MessagesServiceImpl(DataRepository<Message> dataRepository) {
        super(dataRepository);
    }
}
