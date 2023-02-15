package app.services;

import java.util.List;

import app.dao.ChatListDao;
import app.entities.Message;

public class ChatListService {
    private final ChatListDao daoChatList;

    public ChatListService(ChatListDao daoChatList) {
        this.daoChatList = daoChatList;
    }

    public List<Message> loadChatList(String username) {
        return daoChatList.loadChatList(username);
    }

    public String getUserName(String token) {
        return daoChatList.getUserName(token);
    }
}
