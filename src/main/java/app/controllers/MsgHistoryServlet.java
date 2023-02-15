package app.controllers;

import java.io.IOException;
import java.util.List;

//import app.dao.MsgHisDAO;
import app.entities.Message;
import app.services.MsgHisService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MsgHistoryServlet extends HttpServlet {
    //private MsgHisService service = new MsgHisService(new MsgHisDAO());
    private MsgHisService service;
    public MsgHistoryServlet(MsgHisService service){
        this.service=service;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authHeader = req.getHeader("authorization");

        authHeader = authHeader.substring(authHeader.indexOf(' ') + 1);// en teze elave etdim, 2/2/2023

        String kimden = service.getUserName(authHeader);
        String kime = req.getParameter("kime");
        List</* String */Message> mesajlar = service.getMessageHistory(kimden, kime);
        String response = "[";
        System.out.print("Yoxlama : ");
        System.out.println(mesajlar);
        if (mesajlar != null && mesajlar.size() != 0) {// teze tazdim
            /*
             * for(String mesaj:mesajlar){
             * response+=("\'"+mesaj+"\',");
             * }
             */
            for (int i = 0; i < mesajlar.size(); i++) {
                // response+="\""+(mesajlar.get(i)+"\","); // 1/24/2023 elave etdim
                response += "{";
                response += "\"from\":" + "\"" + mesajlar.get(i).getFrom() + "\",";
                response += "\"body\":" + "\"" + mesajlar.get(i).getMessageBody() + "\",";
                response += "\"to\":" + "\"" + mesajlar.get(i).getTo() + "\"";
                response += "},";
            }
            response = response.substring(0, response.length() - 1);// axirdaki vergulu xaric etmek ucun
            response += "]";
            System.out.println("Mesaj tarixcesi:  " + response);
            resp.getOutputStream().println(response);
            System.out.println("Mesajlar null deyil!");
        } else {
            resp.getOutputStream().println("[]");// en teze uncomment etdim,/*1/22/2023*/
        }
    }
}
