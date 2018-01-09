package com.chatsystem.server.translator.impl;


import com.chatsystem.server.entity.Message;
import com.chatsystem.server.service.ContactHistoryService;
import com.chatsystem.server.service.UserService;
import com.chatsystem.server.translator.JSONTranslator;
import org.json.JSONObject;

/**
 *
 */

public class MessageTranslator implements JSONTranslator<Message> {

    private final UserService userService;
    private final ContactHistoryService contactHistoryService;


    public MessageTranslator(UserService userService, ContactHistoryService contactHistoryService) {
        this.userService = userService;
        this.contactHistoryService = contactHistoryService;
    }

    @Override
    public Message fromJSON(JSONObject obj){
        Message message = new Message();

        if(obj.has("message_id")) {
            message.setId(obj.getInt("message_id"));
        }
        message.setAddressee(userService.get(obj.getInt("addressee_id")));
        if(obj.has("destination_id")) {
            message.setDestination(userService.get(obj.getInt("destination_id")));
        }
        if(obj.has("contact_history_id")) {
            message.setContactHistory(contactHistoryService.get(obj.getInt("contact_history_id")));
        }
        if(obj.has("message")){
            message.setMessage(obj.getString("message"));
        }
        return message;
    }

    @Override
    public JSONObject toJSON(Message message) {
        JSONObject messageObj = new JSONObject();
        messageObj.put("message_id", message.getId());
        messageObj.put("addressee_id", message.getAddressee().getId());
        messageObj.put("destination_id", message.getDestination().getId());
        if(message.getContactHistory() != null) {
            messageObj.put("contact_history_id", message.getContactHistory().getId());
        }
        if(message.getMessage() != null){
            messageObj.put("message", message.getMessage());
        }
        return messageObj;
    }
}
