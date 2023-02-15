package app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import app.dao.ChatListDao;
import app.entities.Message;
import app.services.ChatListService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatListServlet extends HttpServlet {
    //private ChatListService service = new ChatListService(new ChatListDao());
    private ChatListService service;
    public ChatListServlet(ChatListService service){
        this.service=service;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authHeader = req.getHeader("authorization");
        authHeader = authHeader.substring(authHeader.indexOf(' ') + 1);
        String username = service.getUserName(authHeader);
        System.out.println("username:" + username);
        List<Message> chatList = new ArrayList<>();
        chatList = service.loadChatList(username);
        if (chatList != null && chatList.size() != 0) {
            String response = "[";
            for (int i = 0; i < chatList.size(); i++) {
                response += "{";
                if (chatList.get(i).getFrom().equals(username)) {
                    response += ("\"contactname\":" + "\"" + chatList.get(i).getTo() + "\",");
                } else {
                    response += ("\"contactname\":" + "\"" + chatList.get(i).getFrom() + "\",");
                }

                response += ("\"mesgpreview\":" + "\"" + chatList.get(i).getMessageBody() + "\"");
                response += "},";
            }
            response = response.substring(0, response.length() - 1);
            response += "]";
            resp.getOutputStream().println(response);
        }

        else {
            resp.getOutputStream().println("[]");
        }
    }
}