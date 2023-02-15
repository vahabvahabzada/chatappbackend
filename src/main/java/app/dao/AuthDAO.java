package app.dao;

import app.database.DBOperation;
import app.entities.User;

public class AuthDAO {
    public AuthDAO() {}

    public String putToken(String username) {
        try {
            return DBOperation.putToken(username);
        } catch (Exception ignored) {
            return null;
        }
    }

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
