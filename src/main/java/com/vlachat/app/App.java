package com.vlachat.app;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import com.vlachat.app.controllers.AuthServlet;
import com.vlachat.app.controllers.LoadChatController;
import com.vlachat.app.controllers.LoadMessageNotificationsServlet;
import com.vlachat.app.controllers.NewestMessagesServlet;
import com.vlachat.app.controllers.SearchServlet;
import com.vlachat.app.controllers.SendMessageServlet;
import com.vlachat.app.controllers.SignUpController;
import com.vlachat.app.daos.AuthDao;
import com.vlachat.app.daos.LoadChatDao;
import com.vlachat.app.daos.LoadMessageNotificationsDao;
import com.vlachat.app.daos.NewestMessagesDao;
import com.vlachat.app.daos.SearchDao;
import com.vlachat.app.daos.SendMessageDao;
import com.vlachat.app.daos.SignUpDao;
import com.vlachat.app.security.AuthFilter;
import com.vlachat.app.security.JwtGenerator;
import com.vlachat.app.security.PassWordHasher;
import com.vlachat.app.services.AuthService;
import com.vlachat.app.services.LoadChatService;
import com.vlachat.app.services.LoadMessageNotificationsService;
import com.vlachat.app.services.NewestMessagesService;
import com.vlachat.app.services.SearchService;
import com.vlachat.app.services.SendMessageService;
import com.vlachat.app.services.SignUpService;

import jakarta.servlet.DispatcherType;

public class App 
{
    public static void main( String[] args ) throws LifecycleException
    {
        Tomcat tomcat=new Tomcat();
        tomcat.setBaseDir("/");
        tomcat.setPort(8080);
        
        tomcat.getConnector();

        Context context=tomcat.addContext("", new File(".").getAbsolutePath());

        //CORS-enabling cross origin requests
        FilterDef corsFilter=new FilterDef();
        corsFilter.setFilterName("corsFilter");
        corsFilter.setFilterClass(CorsFilter.class.getName());
        corsFilter.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_ORIGINS, "http://localhost:5500");
        corsFilter.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_METHODS, "GET,POST,PUT,DELETE");
        corsFilter.addInitParameter(CorsFilter.PARAM_CORS_EXPOSED_HEADERS, "Origin,Content-Type,Accept,Authorization");
        corsFilter.addInitParameter(CorsFilter.PARAM_CORS_SUPPORT_CREDENTIALS, "true");
        context.addFilterDef(corsFilter);

        FilterMap corsilterMap=new FilterMap();
        corsilterMap.setFilterName("corsFilter");
        corsilterMap.addURLPattern("/*");//burda ancaq hansilara tetbiq etmek isteyirikse onu yaziriq,burada cors du deye her bir rndoit-e tetbiq etmek isteyirik
        corsilterMap.setDispatcher(DispatcherType.REQUEST.name());
        context.addFilterMap(corsilterMap);


        // Custom Authorization Filter
        FilterDef authorFilter=new FilterDef();
        authorFilter.setFilterName("authorFilter");
        authorFilter.setFilterClass(AuthFilter.class.getName());
        context.addFilterDef(authorFilter);

        FilterMap authFilterMap=new FilterMap();
        authFilterMap.setFilterName("authorFilter");
        authFilterMap.addURLPattern("/secret");
        authFilterMap.addURLPattern("/sendmessage");//M-6
        authFilterMap.addURLPattern("/ldchat");//L-4
        authFilterMap.addURLPattern("/getnewestmesgs");//N-4
        authFilterMap.addURLPattern("/search");// S-4
        authFilterMap.addURLPattern("/nfboxes");// NF-4
        authFilterMap.setDispatcher(DispatcherType.REQUEST.name());
        context.addFilterMap(authFilterMap);

        
        tomcat.addServlet(context.getPath(), "signup", new SignUpController(new SignUpService(new SignUpDao(),new PassWordHasher()))).addMapping("/signup");;
        tomcat.addServlet(context.getPath(), "login", new AuthServlet(new AuthService(new AuthDao(), new PassWordHasher()), new JwtGenerator())).addMapping("/login");
        tomcat.addServlet(context.getPath(), "sendmessage", new SendMessageServlet(new SendMessageService(new SendMessageDao()))).addMapping("/sendmessage");// M-6
        tomcat.addServlet(context.getPath(), "getmessagehistory",new LoadChatController(new LoadChatService(new LoadChatDao()))).addMapping("/ldchat");
        tomcat.addServlet(context.getPath(), "getnewestmessages", new NewestMessagesServlet(new NewestMessagesService(new NewestMessagesDao()))).addMapping("/getnewestmesgs");;
        tomcat.addServlet(context.getPath(), "searchusers", new SearchServlet(new SearchService(new SearchDao()))).addMapping("/search");
        tomcat.addServlet(context.getPath(), "loadchatboxes", new LoadMessageNotificationsServlet(new LoadMessageNotificationsService(new LoadMessageNotificationsDao()))).addMapping("/nfboxes");
        tomcat.start();
        tomcat.getServer().await();
    }
}
