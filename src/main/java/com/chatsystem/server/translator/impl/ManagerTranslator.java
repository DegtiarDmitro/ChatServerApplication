package com.chatsystem.server.translator.impl;


import com.chatsystem.server.entity.Manager;
import com.chatsystem.server.translator.JSONTranslator;
import org.json.JSONObject;

public class ManagerTranslator implements JSONTranslator<Manager> {
    @Override
    public Manager fromJSON(JSONObject obj) {
        Manager manager = new Manager();
        manager.setUsername(obj.getString("user_name"));
        manager.setRole(obj.getString("user_role"));
        manager.setEmail(obj.getString("user_password"));
        return manager;
    }

    @Override
    public JSONObject toJSON(Manager manager) {
        JSONObject userObj = new JSONObject();
        userObj.put("user_id", manager.getId());
        userObj.put("user_name", manager.getUsername());
        userObj.put("user_role", manager.getRole());
        userObj.put("user_password", manager.getPassword());
        userObj.put("user_status", manager.getUserStatus());
        return userObj;
    }
}