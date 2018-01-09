package com.chatsystem.server.translator;

import com.chatsystem.server.entity.ContactHistory;
import org.json.JSONObject;

public class ContactHistoryTranslator implements JSONTranslator<ContactHistory> {
    @Override
    public ContactHistory fromJSON(JSONObject obj) {
        return null;
    }

    @Override
    public JSONObject toJSON(ContactHistory entity) {
        JSONObject userObj = new JSONObject();
        userObj.put("contact_history_id", entity.getId());
        return userObj;
    }
}
