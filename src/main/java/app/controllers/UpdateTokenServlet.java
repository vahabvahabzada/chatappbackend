package app.controllers;

import java.io.IOException;

//import app.dao.UpdateTokenDao;
import app.services.UpdateTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateTokenServlet extends HttpServlet {
    //private UpdateTokenService service = new UpdateTokenService(new UpdateTokenDao());
    private UpdateTokenService service;
    public UpdateTokenServlet(UpdateTokenService service){
        this.service=service;
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldToken = req.getHeader("authorization");
        oldToken = oldToken.substring(oldToken.indexOf(' ') + 1);
        String newToken = service.updateToken(oldToken);
        resp.getOutputStream().println(newToken);
    }
}
