package com.chatsystem.server.translator.impl;

import com.chatsystem.server.entity.UserContact;
import com.chatsystem.server.service.ContactHistoryService;
import com.chatsystem.server.service.UserService;
import com.chatsystem.server.translator.JSONTranslator;
import org.json.JSONObject;

public class UserContactTranslator implements JSONTranslator<UserContact> {

    private final UserService userService;
    private final ContactHistoryService contactHistoryService;

    public UserContactTranslator(UserService userService, ContactHistoryService contactHistoryService) {
        this.userService = userService;
        this.contactHistoryService = contactHistoryService;
    }


    @Override
    public UserContact fromJSON(JSONObject contactObj) {
        UserContact userContact = new UserContact();
        userContact.setId(contactObj.getInt("contact_id"));
        userContact.setContactUser(userService.get(contactObj.getInt("contact_user")));
        userContact.setUser(userService.get(contactObj.getInt("user")));
        return userContact;
    }


    @Override
    public JSONObject toJSON(UserContact entity) {
        JSONObject userObj = new JSONObject();
        userObj.put("contact_id", entity.getId());
        userObj.put("contact_user", entity.getContactUser().getId());
        userObj.put("user", entity.getUser().getId());
        userObj.put("contact_history", entity.getContactHistory() == null ? null : entity.getContactHistory().getId());
        return userObj;
    }
}
