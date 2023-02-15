package app.controllers;

import java.io.IOException;
import java.util.List;

//import app.dao.PartialMsgHisDAO;
import app.entities.Message;
import app.services.PartialMsgHisService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PartialMsgServlet extends HttpServlet {
    //private PartialMsgHisService service = new PartialMsgHisService(new PartialMsgHisDAO());
    private PartialMsgHisService service;
    public PartialMsgServlet(PartialMsgHisService service){
        this.service=service;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authHeader = req.getHeader("authorization");

        authHeader = authHeader.substring(authHeader.indexOf(' ') + 1);// en teze elave etdim, 2/2/2023

        String kimden = service.getUserName(authHeader);
        String kime = req.getParameter("kime");
        long counter = Long.parseLong(req.getParameter("counter"));
        List</* String */Message> mesajlar = service.getPartialMessageHistory(kimden, kime, counter);
        String response = "[";
        System.out.print("Yoxlama : ");
        System.out.println(mesajlar);
        if (mesajlar != null && mesajlar.size() != 0/* mesajlar.size()!=0 */) {// teze tazdim //her setrden sonra
                                                                               // auto_increment novbeti setri emele
                                                                               // getirdiyi ucun
            /*
             * for(String mesaj:mesajlar){
             * response+=("\'"+mesaj+"\',");
             * }
             */
            for (int i = 0; i < mesajlar.size(); i++) {
                // response+="\""+(mesajlar.get(i)+"\",");
                response += "{";
                response += "\"from\":" + "\"" + mesajlar.get(i).getFrom() + "\",";
                response += "\"body\":" + "\"" + mesajlar.get(i).getMessageBody() + "\",";
                response += "\"to\":" + "\"" + mesajlar.get(i).getTo() + "\"";
                response += "},";
            }
            response = response.substring(0, response.length() - 1);
            response += "]";
            System.out.println("Mesaj tarixcesi:  " + response);
            resp.getOutputStream().println(response);
            System.out.println("Mesajlar null deyil partial!");
        } else {
            resp.getOutputStream().println("[]");// en teze uncomment etdim,/*1/22/2023*/
        }
    }
}
