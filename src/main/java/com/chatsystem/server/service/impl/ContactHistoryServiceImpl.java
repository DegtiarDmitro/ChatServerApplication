package com.chatsystem.server.service.impl;

import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.entity.ContactHistory;
import com.chatsystem.server.service.ContactHistoryService;
import org.springframework.stereotype.Service;

@Service
public class ContactHistoryServiceImpl extends DataServiceImpl<ContactHistory> implements ContactHistoryService {

    public ContactHistoryServiceImpl(DataRepository<ContactHistory> dataRepository) {
        super(dataRepository);
    }
}
