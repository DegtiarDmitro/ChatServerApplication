package com.chatsystem.server.service;

import com.chatsystem.server.entity.Buyer;

public interface BuyerService extends DataService<Buyer> {
    Buyer findByEmail(String email);
    Buyer findByUsername (String username);
}
