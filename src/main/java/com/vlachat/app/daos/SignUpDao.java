package com.vlachat.app.daos;

import com.vlachat.app.database.DBOperation;
import com.vlachat.app.entities.User;

public class SignUpDao {
    public SignUpDao() {
    }

    public boolean addUserToDB(User user) {
        try {
            return DBOperation.addUserToDB(user);
        } catch (Exception ignored) {
        }
        return false;
    }
}
