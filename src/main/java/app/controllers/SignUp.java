package app.controllers;

import java.io.IOException;
//import app.dao.SignUpDAO;
import app.entities.User;
import app.services.SignUPService;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

//@WebServlet("/signup")
public class SignUp extends HttpServlet {
    //private SignUPService service = new SignUPService(new SignUpDAO());
    private SignUPService service;
    public SignUp(SignUPService service){
        this.service=service;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String salt = BCrypt.gensalt();// generate salt
        System.out.println("Salt yoxlamasi:" + salt);// yoxlama ucun

        System.out.print(req);
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("password"));

        String hash = BCrypt.hashpw(req.getParameter("password"), salt);
        System.out.println("Hash yoxlamasi:" + hash);

        // service.addUserToDB(new User(req.getParameter("name"),
        // req.getParameter("password"), null, null));

        boolean status = service.addUserToDB(new User(req.getParameter("name"), hash, null, null));
        if (status) {
            resp.getOutputStream().print("success");
        } else {
            resp.getOutputStream().print("failure");//println() olmaz!!!!
        }
    }
}
// Step-5