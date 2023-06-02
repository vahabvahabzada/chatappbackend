package com.vlachat.app.controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.vlachat.app.dtos.UserDto;
import com.vlachat.app.services.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet{
    private SearchService searchService;
    public SearchServlet(SearchService searchService){
        this.searchService=searchService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        String searchText=gson.fromJson(req.getReader(),String.class);
        List<UserDto> users=searchService.searchUser(searchText);
        resp.getWriter().print(users);// S-3
    }
}
// S-1