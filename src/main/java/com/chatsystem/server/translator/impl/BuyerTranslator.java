package com.chatsystem.server.translator.impl;

import com.chatsystem.server.entity.Buyer;
import com.chatsystem.server.translator.JSONTranslator;
import org.json.JSONObject;

public class BuyerTranslator implements JSONTranslator<Buyer> {

    @Override
    public Buyer fromJSON(JSONObject obj) {

        Buyer buyer = new Buyer();


        buyer.setUsername(obj.getString("user_name"));
        buyer.setPassword(obj.getString("user_password"));
        buyer.setRole(obj.getString("user_role"));
        if(obj.has("user_id")) {
            buyer.setId(obj.getInt("user_id"));
        }
        if(obj.has("user_status")) {
            buyer.setUserStatus(obj.getString("user_status"));
        }
        return buyer;
    }

    @Override
    public JSONObject toJSON(Buyer buyer) {
        JSONObject buyerObj = new JSONObject();
        buyerObj.put("user_id", buyer.getId());
        buyerObj.put("user_name", buyer.getUsername());
        buyerObj.put("user_role", buyer.getRole());
        buyerObj.put("user_status", buyer.getUserStatus());
        return buyerObj;
    }
}
