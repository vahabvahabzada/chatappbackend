package app.dao;

import java.util.List;

import app.database.DBOperation;
import app.entities.Message;

public class MsgHisDAO {
    public MsgHisDAO() {
    }

    public List</* String */Message> getMessageHistory(String kimden, String kime) {
        try {
            return DBOperation.getMessageHistory(kimden, kime);
        } catch (Exception ignored) {
        }
        return null;
    }

    public String getUserName(String token) {
        try {
            return DBOperation.getUserName(token);
        } catch (Exception ignored) {
        }
        return null;
    }
}
