package com.chatsystem.server.dao;


import com.chatsystem.server.entity.Buyer;

public interface BuyerRepository extends DataRepository<Buyer> {
    Buyer findByEmail (String email);
    Buyer findByUsername (String username);
}
