package app.dao;

import app.database.DBOperation;

public class LogoutDao {
    public LogoutDao(){}
    public void logout(String token){
        try{
            DBOperation.logout(token);
        }
        catch(Exception ignored){}
    }
}
