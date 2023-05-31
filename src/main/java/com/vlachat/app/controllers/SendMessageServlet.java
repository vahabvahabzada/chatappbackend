package com.vlachat.app.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.mappers.MessageMapper;
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
        // {"from":"","to":"","body":""} tipinde bir data gelecek request-de
        MessageMapper mapper=new MessageMapper();
        Gson gson=new Gson();
        MessageDto mesage=gson.fromJson(req.getReader(), MessageDto.class);
        resp.getWriter().println(mapper.entityToDto(sendMessageService.sendMessage(mesage)));// M-5
    }
}

// M-2