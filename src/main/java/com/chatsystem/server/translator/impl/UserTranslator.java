package com.chatsystem.server.translator.impl;


import com.chatsystem.server.entity.User;
import com.chatsystem.server.translator.JSONTranslator;
import org.json.JSONObject;

public class UserTranslator implements JSONTranslator<User> {


    @Override
    public User fromJSON(JSONObject obj) {

        User user = new User();


        user.setUsername(obj.getString("user_name"));
        user.setPassword(obj.getString("user_password"));
        user.setRole(obj.getString("user_role"));
        if(obj.has("user_id")) {
            user.setId(obj.getInt("user_id"));
        }
        if(obj.has("user_status")) {
            user.setUserStatus(obj.getString("user_status"));
        }
        return user;
    }

    @Override
    public JSONObject toJSON(User user) {
        JSONObject userObj = new JSONObject();
        userObj.put("user_id", user.getId());
        userObj.put("user_name", user.getUsername());
        userObj.put("user_password", user.getPassword());
        userObj.put("user_role", user.getRole());
        userObj.put("user_status", user.getUserStatus());
        return userObj;
    }
}