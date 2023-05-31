package com.vlachat.app.security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class AuthFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        JwtGenerator jwtGenerator=new JwtGenerator();
        //System.out.println("From Filter");
        if(jwtGenerator.validate(((HttpServletRequest) request).getHeader("Authorization").substring(7))){// take Bearer xxxxxxxxx.... from request header
            //System.out.println("CORRECT TOKEN");
            chain.doFilter(request, response);// valid-dise zencirdeki novbeti levele burax,hansiki indiki halda servlet olacaq
        }
        else{
            doFilter(request, response, chain);
        }
    }
}