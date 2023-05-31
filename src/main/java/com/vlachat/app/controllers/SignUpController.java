package com.vlachat.app.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.services.SignUpService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpController extends HttpServlet {
    private SignUpService service;
    public SignUpController(SignUpService service){
        this.service=service;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Gson gson=new Gson();// Gson-u Step-9 da elave etdim
        UserDto user=gson.fromJson(req.getReader(),UserDto.class);
        boolean status = service.addUserToDB(user);
        if (status) {
            resp.getOutputStream().print("success");
        } else {
            resp.getOutputStream().print("failure");
        }
    }
}
