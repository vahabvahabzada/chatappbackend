package app.services;

import app.dao.LogoutDao;

public class Logoutservice {
    private final LogoutDao daoLogout;
    public Logoutservice(LogoutDao daoLogout){
        this.daoLogout=daoLogout;
    }
    public void logout(String token){
        daoLogout.logout(token);
    }
}
