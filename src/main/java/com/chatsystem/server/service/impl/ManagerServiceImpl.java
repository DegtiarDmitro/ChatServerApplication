package com.chatsystem.server.service.impl;


import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.dao.ManagerRepository;
import com.chatsystem.server.service.ManagerService;
import com.chatsystem.server.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManagerServiceImpl extends DataServiceImpl<Manager> implements ManagerService{

    final
    private ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(DataRepository<Manager> dataRepository, ManagerRepository managerRepository) {
        super(dataRepository);
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager findByUsername(String userName) {
        return managerRepository.findByUsername(userName);
    }
}
