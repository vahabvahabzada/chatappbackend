package com.vlachat.app.controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.vlachat.app.dtos.MessageDto;
import com.vlachat.app.services.LoadMessageNotificationsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoadMessageNotificationsServlet extends HttpServlet {
    private LoadMessageNotificationsService service;

    public LoadMessageNotificationsServlet(LoadMessageNotificationsService service) {
        this.service = service;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String currentUser = gson.fromJson(req.getReader(), String.class);
        List<MessageDto> messages=service.loadMessageBoxes(currentUser);// NF-4
        resp.getWriter().print(messages);
    }
}
// NF-1