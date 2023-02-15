package app.dao;

import java.util.List;

import app.database.DBOperation;
import app.entities.Message;

public class PartialMsgHisDAO {
    public PartialMsgHisDAO() {
    }

    public List</* String */Message> getPartialMessageHistory(String kimden, String kime, long latestmsgId) {
        try {
            return DBOperation.getPartialMessageHistory(kimden, kime, latestmsgId);
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
