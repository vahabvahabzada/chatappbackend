package com.vlachat.app.controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.dtos.SenderAndReceiverDto;
import com.vlachat.app.services.LoadChatService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoadChatController extends HttpServlet{
    private LoadChatService loadChatService;
    public LoadChatController(LoadChatService loadChatService){
        this.loadChatService=loadChatService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        SenderAndReceiverDto senderAndReceiverDto=gson.fromJson(req.getReader(), SenderAndReceiverDto.class);
        List<MessageDto> messages=loadChatService.getMessageHistory(senderAndReceiverDto);// L-3
        resp.getWriter().print(messages);// L-3
    }
}
// L-1