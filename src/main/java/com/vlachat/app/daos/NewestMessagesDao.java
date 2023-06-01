package com.vlachat.app.daos;

import java.util.List;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.Message;

public class NewestMessagesDao {
    public List<Message> getNewestMessages(String from,String to,long bound){
        try{
            return DBOperation.getNewestMessages(from, to, bound);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}// N-2