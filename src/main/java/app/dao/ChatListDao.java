package app.dao;

import java.util.List;

import app.database.DBOperation;
import app.entities.Message;

public class ChatListDao {
    public ChatListDao() {
    }

    public List<Message> loadChatList(String username) {
        try {
            return DBOperation.loadChatList(username);
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
