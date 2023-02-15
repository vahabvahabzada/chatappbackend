package app.dao;

import app.database.DBOperation;
import app.entities.Message;

public class MessageDAO {
    public MessageDAO() {
    }

    public /* String */void sendMessage(Message mesaj) {
        // try{
        // return DBOperation.sendMessage(mesaj);
        // }
        // catch(Exception ignored){
        // }
        // return null;
        try {
            DBOperation.sendMessage(mesaj);
        } catch (Exception ignored) {
        }
    }

    public String getUserName(String token) {
        try {
            return DBOperation.getUserName(token);
        } catch (Exception ignored) {
        }
        return null;
    }
}
