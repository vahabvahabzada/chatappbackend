package com.vlachat.app.daos;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.Message;

public class SendMessageDao {
    public Message sendMessage(Message message){
        try{
            DBOperation.sendMessage(message);
            return message;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}

// M-4