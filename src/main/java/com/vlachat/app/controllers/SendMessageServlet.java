package com.vlachat.app.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.vlachat.app.entities.Message;
import com.vlachat.app.services.SendMessageService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SendMessageServlet extends HttpServlet{
    private SendMessageService sendMessageService;
    public SendMessageServlet(SendMessageService sendMessageService){
        this.sendMessageService=sendMessageService;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // {"from":"","to":"","body":""} tipinde bir data gelecek requestde
        Gson gson=new Gson();
        Message mesage=gson.fromJson(req.getReader(), Message.class);
        resp.getWriter().println(sendMessageService.sendMessage(mesage));// M-5
    }
}

// M-2