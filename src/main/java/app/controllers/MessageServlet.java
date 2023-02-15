package app.controllers;

import java.io.IOException;
//import java.util.Base64;

//import app.dao.MessageDAO;
import app.entities.Message;
import app.services.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet {
    //private MessageService service = new MessageService(new MessageDAO());
    private MessageService service;
    public MessageServlet(MessageService service){
        this.service=service;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authHeader = req.getHeader("authorization");
        /*
         * String encodedToken=authHeader.substring(authHeader.indexOf(' ')+1);
         * String decodedToken=new String(Base64.getDecoder().decode(encodedToken));
         * String messageFrom=service.getUserName(decodedToken);
         */

        authHeader = authHeader.substring(authHeader.indexOf(' ') + 1);// en teze elave etdim, 2/2/2023

        String messageFrom = service.getUserName(authHeader);
        String messageBody = req.getParameter("messageBody");
        String messageTo = req.getParameter("messageTo");

        // String responseResult=service.sendMessage(new Message(messageFrom, messageTo,
        // messageBody));
        service.sendMessage(new Message(messageFrom, messageTo, messageBody));
        // resp.getOutputStream().println(responseResult);
    }
}
