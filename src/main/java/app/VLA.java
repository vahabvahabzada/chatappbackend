package app;

import java.util.EnumSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import app.controllers.AuthServlet;
import app.controllers.ChatListServlet;
import app.controllers.LogoutController;
import app.controllers.MessageServlet;
import app.controllers.MsgHistoryServlet;
import app.controllers.PartialMsgServlet;
import app.controllers.SearchServlet;
import app.controllers.SignUp;
import app.controllers.UpdateTokenServlet;
import app.dao.AuthDAO;
import app.dao.ChatListDao;
import app.dao.LogoutDao;
import app.dao.MessageDAO;
import app.dao.MsgHisDAO;
import app.dao.PartialMsgHisDAO;
import app.dao.SearchDAO;
import app.dao.SignUpDAO;
import app.dao.UpdateTokenDao;
import app.services.AuthService;
import app.services.ChatListService;
import app.services.Logoutservice;
import app.services.MessageService;
import app.services.MsgHisService;
import app.services.PartialMsgHisService;
import app.services.SearchService;
import app.services.SignUPService;
import app.services.UpdateTokenService;
import jakarta.servlet.DispatcherType;

public class VLA {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new SignUp((new SignUPService(new SignUpDAO())))), "/signup");
        handler.addServlet(new ServletHolder(new AuthServlet(new AuthService(new AuthDAO()))), "/auth");
        handler.addServlet(new ServletHolder(new SearchServlet(new SearchService(new SearchDAO()))), "/search");
        handler.addServlet(new ServletHolder(new MessageServlet(new MessageService(new MessageDAO()))), "/messaging");
        handler.addServlet(new ServletHolder(new MsgHistoryServlet(new MsgHisService(new MsgHisDAO()))), "/msghis");
        handler.addServlet(new ServletHolder(new PartialMsgServlet(new PartialMsgHisService(new PartialMsgHisDAO()))), "/msgprtlhis");
        handler.addServlet(new ServletHolder(new ChatListServlet(new ChatListService(new ChatListDao()))), "/ldchatlist");
        handler.addServlet(new ServletHolder(new UpdateTokenServlet(new UpdateTokenService(new UpdateTokenDao()))), "/updatetoken");
        handler.addServlet(new ServletHolder(new LogoutController(new Logoutservice(new LogoutDao()))), "/logout");

        FilterHolder corsFilter = new FilterHolder(new CrossOriginFilter());
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM,"https://frontendvla.onrender.com");
        corsFilter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,"X-Requested-With,Content-Type,Accept,Origin,Authorization");
        handler.addFilter(corsFilter, "/*", EnumSet.of(DispatcherType.REQUEST));

        // Create a server that listens on port 8080
        Server server = new Server(8080);
        server.setHandler(handler);

        // Start the server
        server.start();
        System.out.println("Server started!");

        // Keep the main theread alive when the server is running
        server.join();
    }
}