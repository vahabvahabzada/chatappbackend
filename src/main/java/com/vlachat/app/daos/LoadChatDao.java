package com.vlachat.app.daos;

import java.util.List;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.Message;

public class LoadChatDao {
    public List<Message> getMessages(String from,String to){
        try{
            return DBOperation.getMessageHistory(from, to);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}// L-2
