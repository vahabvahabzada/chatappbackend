package app.services;

import app.dao.MessageDAO;
import app.entities.Message;

public class MessageService {
    private final MessageDAO daoMessage;

    public MessageService(MessageDAO daoMessage) {
        this.daoMessage = daoMessage;
    }

    public /* String */void sendMessage(Message mesaj) {
        /* return */ daoMessage.sendMessage(mesaj);
    }

    public String getUserName(String token) {
        return daoMessage.getUserName(token);
    }
}
