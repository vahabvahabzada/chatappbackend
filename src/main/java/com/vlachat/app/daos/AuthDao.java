package com.vlachat.app.daos;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.User;

public class AuthDao {
    public AuthDao() {}

    public User getUser(String username) {
        try {
            System.out.println("Bura isleyir");
            System.out.println(DBOperation.getUser(username));
            return DBOperation.getUser(username);
        } catch (Exception ignored) {
            return null;
        }
        // return null;
    }
}