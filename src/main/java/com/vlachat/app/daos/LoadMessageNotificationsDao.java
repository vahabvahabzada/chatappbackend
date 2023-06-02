package com.vlachat.app.daos;

import java.util.List;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.Message;

public class LoadMessageNotificationsDao {
    public List<Message> loadMessageBoxes(String currentUser){
        try{
            return DBOperation.loadMessageBoxes(currentUser);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
//NF-2