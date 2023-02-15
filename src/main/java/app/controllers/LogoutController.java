package app.controllers;

//import app.dao.LogoutDao;
import app.services.Logoutservice;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet{
    //private Logoutservice service=new Logoutservice(new LogoutDao());
    private Logoutservice service;
    public LogoutController(Logoutservice service){
        this.service=service;
    }
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse resp){
        String targettoken=req.getHeader("authorization");
        targettoken=targettoken.substring(targettoken.indexOf(' ')+1);
        service.logout(targettoken);
    }
}
