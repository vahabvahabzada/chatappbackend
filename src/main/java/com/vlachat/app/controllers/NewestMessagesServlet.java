package com.vlachat.app.controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.vlachat.app.dtos.GetNewestMessagesDto;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.services.NewestMessagesService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewestMessagesServlet extends HttpServlet{
    private NewestMessagesService newestMessagesService;
    public NewestMessagesServlet(NewestMessagesService newestMessagesService){
        this.newestMessagesService=newestMessagesService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        GetNewestMessagesDto dto=gson.fromJson(req.getReader(), GetNewestMessagesDto.class);
        List<MessageDto> messages=newestMessagesService.getNewestMessages(dto);//N-3
        resp.getWriter().print(messages);
    }
}// N-1
