package com.vlachat.app.daos;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.User;

public class MapperDao {
    public User getUser(String username){
        try{
            return DBOperation.getUser(username);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
