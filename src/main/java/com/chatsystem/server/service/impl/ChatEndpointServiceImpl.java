package com.chatsystem.server.service.impl;

import com.chatsystem.server.entity.*;
import com.chatsystem.server.service.*;
import com.chatsystem.server.translator.ContactHistoryTranslator;
import com.chatsystem.server.translator.impl.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
@ServerEndpoint(value = "/chat", configurator = SpringConfigurator.class)
public class ChatEndpointServiceImpl implements ChatEndpointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatEndpointServiceImpl.class);
    private final UserService userService;
    private final BuyerService buyerService;
    private final ManagerService managerService;
    private final MessagesService messagesService;
    private final UserContactService contactService;

    private final ContactHistoryService contactHistoryService;
    private Map<String, String> sessionIdUserNameMap = new HashMap<>();
    private Map<String, Session> userNameSessionMap = new HashMap<>();
    //private Map<String, Session> managerNameSessionMap = new HashMap<>();
    private Map<String, ByteArrayOutputStream> idSessionUserFileMap = new HashMap<>();

    private UserTranslator userTranslator;
    private BuyerTranslator buyerTranslator;
    private MessageTranslator messageTranslator;
    //private ManagerTranslator managerTranslator;
    private ContactHistoryTranslator contactHistoryTranslator;
    private UserContactTranslator userContactTranslator;





    @Inject
    public ChatEndpointServiceImpl(UserService userService, BuyerService buyerService, ManagerService managerService, UserContactService contactService, MessagesService messagesService, ContactHistoryService contactHistoryService) {
        super();
        this.userService = userService;
        this.buyerService = buyerService;
        this.managerService = managerService;
        this.contactService = contactService;
        this.messagesService = messagesService;
        userTranslator = new UserTranslator();
        buyerTranslator = new BuyerTranslator();
        //managerTranslator = new ManagerTranslator();
        this.contactHistoryService = contactHistoryService;
        contactHistoryTranslator = new ContactHistoryTranslator();
        userContactTranslator = new UserContactTranslator(userService, contactHistoryService);
        messageTranslator = new MessageTranslator(userService, contactHistoryService);

    }



    /**
     * On open connection
     * @param session -
     */
    @OnOpen
    public void onOpen(Session session){
        LOGGER.info("Established new connection: {}", session);
    }


    @SuppressWarnings("ServerEndpointInconsistencyInspection")
    @OnMessage
    public void binaryMessageHandler(ByteBuffer msg, boolean last, Session session) {

        ByteArrayOutputStream binaryMsg = idSessionUserFileMap.get(session.getId());
        if(binaryMsg == null){
            binaryMsg = new ByteArrayOutputStream();
            idSessionUserFileMap.put(session.getId(), binaryMsg);
        }
        while(msg.hasRemaining()) {
            binaryMsg.write(msg.get());
        }
        if(last){
            LOGGER.info("Received new binary message from client {}", session);
        }
    }


    @SuppressWarnings("ServerEndpointInconsistencyInspection")
    @OnMessage
    public void textMessageHandler(Session session, String textMessage){

        try{
            JSONObject obj = new JSONObject(textMessage);
            String action = obj.getString("action");
            JSONObject parameters = obj.getJSONObject("parameters");
            switch (action){
                case "messageNotification": actionHandleTextMessage(session, parameters); break;
                case "authentication":      actionAuthenticateUser(session, parameters); break;
                case "fileSent":            actionHandleFileMessage(session, parameters); break;
                case "contactConfirmation": actionContactConfirmation(session, parameters); break;
            }
        }catch (JSONException e){
            LOGGER.error("Parsing text message: ", session, e.getMessage());
        }
    }



    /**
     *
     * @param session -
     * @param parameters -
     */
    private void actionAuthenticateUser(Session session, JSONObject parameters){

        User user = userTranslator.fromJSON(parameters);
        User userFromDB = userService.findByUsername(user.getUsername());
        if(user.equals(userFromDB)){
            parameters.put("authenticated", true);
            sessionIdUserNameMap.put(session.getId(), userFromDB.getUsername());
            userNameSessionMap.put(userFromDB.getUsername(), session);
//            if(userFromDB.getRole().equals(UserRole.MANAGER)){
//                managerNameSessionMap.put(userFromDB.getUsername(), session);
//            }
            JSONObject requestObj = new JSONObject();
            requestObj.put("action", "authentication");
            requestObj.put("parameters", userTranslator.toJSON(userFromDB));
            sendTextMessage(session, requestObj.toString());
        }else{
            closeSessionConnection(session);
        }
    }




    /**
     *
     * @param userContactData -
     */
    private void actionContactConfirmation(Session session, JSONObject userContactData){

        UserContact userContact = userContactTranslator.fromJSON(userContactData);
        User contactUser = userContact.getContactUser();
        if(contactUser == null){
            return;
        }
        if(contactUser.getUserStatus().equals(UserStatus.DISABLED)){
            contactUser.setUserStatus(UserStatus.ENABLED);
            userService.update(contactUser);
            ContactHistory contactHistory = contactHistoryService.add(new ContactHistory());
            userContact.setContactHistory(contactHistory);
            userContact = contactService.update(userContact);

            JSONObject managerNotificationJSON = new JSONObject();
            JSONObject responseParameters = new JSONObject();
            responseParameters.put("contactHistory", contactHistoryTranslator.toJSON(contactHistory));
            responseParameters.put("contact", userContactTranslator.toJSON(userContact));
            managerNotificationJSON.put("action", "contactNotification");
            managerNotificationJSON.put("parameters", responseParameters);
            sendTextMessage(session, managerNotificationJSON.toString());

            Session buyerSession = userNameSessionMap.get(contactUser.getUsername());
            if(buyerSession != null){
                responseParameters.put("user", userTranslator.toJSON(userContact.getUser()));
                sendTextMessage(session, managerNotificationJSON.toString());
            }
        }
    }




    /**
     * уведомление всем менеджерам о появлении нового пользователя
     * @param username -
     */
    public void sendNewBuyerDataToManagers(String username){

        Buyer buyer = buyerService.findByUsername(username);
        if(buyer == null){
            return;
        }

        Collection<Manager> managers = managerService.getAll();
        Session onlineManagerSession;
        JSONObject managerNotificationJSON = new JSONObject();
        JSONObject responseParameters = new JSONObject();
        responseParameters.put("user", buyerTranslator.toJSON(buyer));
        managerNotificationJSON.put("action", "contactNotification");
        managerNotificationJSON.put("parameters", responseParameters);
        for(Manager manager: managers){
            responseParameters.put("contact", userContactTranslator.toJSON(contactService.add(new UserContact(manager, buyer))));
            if((onlineManagerSession = userNameSessionMap.get(manager.getUsername())) != null){
                sendTextMessage(onlineManagerSession, managerNotificationJSON.toString());
            }
        }
    }


    /**
     *
     * @param session -
     * @param parameters -
     */
    private void actionHandleTextMessage(Session session, JSONObject parameters){

        Message message = messagesService.add(messageTranslator.fromJSON(parameters));
        if(!userNameSessionMap.containsKey(message.getAddressee().getUsername())){
            closeSessionConnection(session);
            return;
        }
        JSONObject destinationMessage = new JSONObject();
        destinationMessage.put("action", "messageNotification");
        destinationMessage.put("parameters", messageTranslator.toJSON(message));
        if(message.getDestination() != null){
            Session destinationUserSession = userNameSessionMap.get(message.getDestination().getUsername());
            if(destinationUserSession != null){
                sendTextMessage(destinationUserSession, destinationMessage.toString());
            }
        }
        sendTextMessage(session, destinationMessage.toString());
    }


    /**
     *
     * @param session -
     * @param parameters -
     */
    private void actionHandleFileMessage(Session session, JSONObject parameters){
        /*
        Message message = messageTranslator.fromJSON(parameters);

        //String username = parameters.getString("from");
        if(!userNameSessionMap.containsKey(message.getAddressee().getUsername())){
            closeSessionConnection(session);
            return;
        }
        //if username have in contacts user to
        //saving message to db
        //Session destinationUserSession = userNameSessionMap.get(parameters.getString("to"));
        if(message.getDestination() != null){
            Session destinationUserSession = userNameSessionMap.get(message.getDestination().getUsername());
            if(destinationUserSession != null){

                ByteArrayOutputStream binaryMsg = idSessionUserFileMap.get(session.getId());
                sendBinaryMessage(destinationUserSession, ByteBuffer.wrap(binaryMsg.toByteArray()));
                JSONObject destinationMessage = new JSONObject();
                destinationMessage.put("action", "fileSent");
                destinationMessage.put("parameters", parameters);
                sendTextMessage(destinationUserSession, destinationMessage.toString());
                binaryMsg.reset();
                idSessionUserFileMap.remove(session.getId());
            }
        }else if(message.getAddressee().getRole().equals(UserRole.BUYER) ){

        }
        */
    }


    /**
     * sending textMessage
     * @param session -
     * @param textMessage -
     */
    private void sendTextMessage(Session session, String textMessage){
        try {
            session.getBasicRemote().sendText(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session -
     * @param fileBytes -
     */
    private void sendBinaryMessage(Session session, ByteBuffer fileBytes){
        try {
            session.getBasicRemote().sendBinary(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param session -
     */
    private void closeSessionConnection(Session session){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @OnClose
    public void onClose(Session session) throws IOException {

        String username = sessionIdUserNameMap.remove(session.getId());
        userNameSessionMap.remove(username);
        //managerNameSessionMap.remove(username);
        idSessionUserFileMap.remove(session.getId());
        LOGGER.info("Close connection: {}", session);
    }


    /**
     * Showing error with session
     * @param session -
     * @param t -
     */
    @OnError
    public void onError(Session session, Throwable t) {
        LOGGER.info("Transport error in connection: ", session, t.toString());
    }


}
