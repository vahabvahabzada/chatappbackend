package com.vlachat.app.services;

import com.vlachat.app.daos.SendMessageDao;
import com.vlachat.app.entities.Message;
import com.vlachat.app.exceptions.CannotBeEmptyException;
import com.vlachat.app.exceptions.SenderOrReciverCannotbeNullException;

public class SendMessageService {
    private final SendMessageDao sendMessageDao;
    
    public SendMessageService(SendMessageDao sendMessageDao){
        this.sendMessageDao=sendMessageDao;
    }

    public Message sendMessage(Message message){
        if(message.getFrom()==null || message.getTo()==null){
            throw new SenderOrReciverCannotbeNullException("Sender or reciver can not be null!");
        }
        if(message.getFrom().equals("") || message.getTo().equals("") || message.getBody().equals("")){
            throw new CannotBeEmptyException("Fields can not be empty!");
        }
        return sendMessageDao.sendMessage(message);
    }
}

// M-5