package com.vlachat.app.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.security.JwtGenerator;
import com.vlachat.app.services.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {
    private AuthService service;
    private JwtGenerator jwtGenerator;

    public AuthServlet(AuthService service,JwtGenerator jwtGenerator) {// dependency injection
        this.service = service;
        this.jwtGenerator=jwtGenerator;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Gson gson=new Gson();
        UserDto currentUser=gson.fromJson(req.getReader(), UserDto.class);

        if(service.login(currentUser)){
            // eger login ugurludusa,response-da Bearer token qaytar
            String token=jwtGenerator.generateToken(currentUser.getName());
            resp.getOutputStream().print("Bearer "+token);
        }
        else{
            resp.getOutputStream().print(null);
        }
    }
}

//Step-6.1