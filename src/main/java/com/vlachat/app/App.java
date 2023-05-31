package com.vlachat.app;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import com.vlachat.app.controllers.AuthServlet;
import com.vlachat.app.controllers.SignUpController;
import com.vlachat.app.daos.AuthDao;
import com.vlachat.app.daos.SignUpDao;
import com.vlachat.app.security.AuthFilter;
import com.vlachat.app.security.JwtGenerator;
import com.vlachat.app.security.PassWordHasher;
import com.vlachat.app.services.AuthService;
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
        authFilterMap.setDispatcher(DispatcherType.REQUEST.name());
        context.addFilterMap(authFilterMap);

        
        tomcat.addServlet(context.getPath(), "signup", new SignUpController(new SignUpService(new SignUpDao(),new PassWordHasher()))).addMapping("/signup");;
        tomcat.addServlet(context.getPath(), "login", new AuthServlet(new AuthService(new AuthDao(), new PassWordHasher()), new JwtGenerator())).addMapping("/login");
        tomcat.addServlet(context.getPath(), "secretmessage", new SecretServlet()).addMapping("/secret");
        tomcat.start();
        tomcat.getServer().await();
    }
}
