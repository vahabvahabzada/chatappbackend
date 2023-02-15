package app.controllers;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

//import app.dao.SearchDAO;
import app.services.SearchService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
    //private SearchService service = new SearchService(new SearchDAO());
    private SearchService service;
    public SearchServlet(SearchService service){
        this.service=service;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchText = req.getParameter("searchText");
        System.out.println("searchtext" + searchText);
        List<String> searchResult;
        searchResult = service.searchUsers(searchText);
        if (searchResult != null && searchResult.size() != 0) {
            String netice = "[";
            for (int i = 0; i < searchResult.size(); i++) {
                netice += "\"" + searchResult.get(i) + "\",";
            }
            netice = netice.substring(0, netice.length() - 1);
            netice += "]";
            System.out.println(netice);// yoxlama ucun
            resp.getOutputStream().println(netice);
        } else {
            resp.getOutputStream().println("[]");// en teze elave etdim 1/25/2023
        }
    }
}
