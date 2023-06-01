package com.vlachat.app.daos;

import java.util.List;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.User;

public class SearchDao {
    public List<User> searchUser(String searchText){
        try{
            return DBOperation.searchUser(searchText);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
// S-2