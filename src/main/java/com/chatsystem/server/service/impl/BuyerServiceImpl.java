package com.chatsystem.server.service.impl;


import com.chatsystem.server.dao.DataRepository;
import com.chatsystem.server.entity.Buyer;
import com.chatsystem.server.dao.BuyerRepository;
import com.chatsystem.server.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyerServiceImpl extends DataServiceImpl<Buyer> implements BuyerService {

    private final BuyerRepository buyerRepository;


    @Autowired
    public BuyerServiceImpl(DataRepository<Buyer> dataRepository, BuyerRepository buyerRepository) {
        super(dataRepository);
        this.buyerRepository = buyerRepository;
    }

    @Transactional
    public Buyer findByEmail(String email) {
        return buyerRepository.findByEmail(email);
    }

    @Override
    public Buyer findByUsername(String username) {
        return buyerRepository.findByUsername(username);
    }
}
