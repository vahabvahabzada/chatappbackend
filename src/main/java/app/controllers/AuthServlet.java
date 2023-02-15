package app.controllers;

import java.io.IOException;
import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

//import app.dao.AuthDAO;
import app.entities.User;
import app.services.AuthService;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    //private AuthService service = new AuthService(new AuthDAO());
    private AuthService service;
    public AuthServlet(AuthService service){//Bu konstruktoru,cagirmaliyiq,github-da tinder app daki kimi
        this.service=service;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String authHeader = req.getHeader("Authorization");
        System.out.println(authHeader);
        String encodedAuth = authHeader.substring(authHeader.indexOf(' ') + 1);
        String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
        String username = decodedAuth.substring(0, decodedAuth.indexOf(':'));
        String password = decodedAuth.substring(decodedAuth.indexOf(':') + 1);
        System.out.println("AuthServlet.java ----> " + username);
        User user = service.getUser(username);// search for corresponding user for given username
        // make sure user is in our data
        System.out.print(username + ":" + password);
        if (user == null) {
            System.out.print("Nulldu");
            resp.getOutputStream().print("null");
            // resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // make sure password is valid
        // use hashed passwords in real life!
        /*
         * if(!password.equals(user.getPassword())){
         * resp.getOutputStream().print("null");
         * //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
         * return;
         * }
         */
        if (!(BCrypt.checkpw(password, user.getPassword()))) {
            System.out.println("password null gelir...");
            resp.getOutputStream().print("null");
            return;
        }

        String token = service.putToken(username);

        // the body of the response is just the token
        resp.getOutputStream().print(token);
    }
}