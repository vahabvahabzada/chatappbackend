package com.vlachat.app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.vlachat.app.entities.Message;
import com.vlachat.app.entities.User;

public class DBOperation {
    private static final String USER_TABLE="users";
    private static final String MESSAGE_TABLE="messages";


    public static boolean addUserToDB(User user) throws SQLException {//username-ler unique-di
        Connection conn = DBConnection.getElaqe();

        String CHECK_IF_USERNAME_ALREADY_USED = "select * from "+USER_TABLE+" where name=\'" + user.getName() + "\'";
        System.out.println(CHECK_IF_USERNAME_ALREADY_USED);
        
        PreparedStatement statement = conn.prepareStatement(CHECK_IF_USERNAME_ALREADY_USED);//PreparedStatement XSS ve SQL injection-in qarsisini almaq ucundur
        
        ResultSet netice = statement.executeQuery();
        
        if (!netice.next()) {// istifadeci adi tekrar deyil
            String INSERT_USER_INTO_DB="insert into "+USER_TABLE+"(name,password)"+" values (\'" + user.getName() + "\',\'" + user.getPassword()+ "\')";
            System.out.println(INSERT_USER_INTO_DB);
            statement=conn.prepareStatement(INSERT_USER_INTO_DB);
            statement.execute();
            return true;
        }
        return false;
    }


    public static User getUser(String username) throws SQLException {// username-ler unique-di
        Connection conn = DBConnection.getElaqe();
        String GET_USER_BY_NAME = "select * from "+USER_TABLE+" where name=\'" + username + "\'";
        
        PreparedStatement statement = conn.prepareStatement(GET_USER_BY_NAME);
        ResultSet netice = statement.executeQuery();
        System.out.println("Works!");
        
        if (netice.next()) {
            return new User(Long.valueOf(netice.getString("id")),netice.getString("name"), netice.getString("password"));
        }
        else {
            return null;
        }
    }


    public static Message sendMessage(Message message) throws SQLException{
        Connection connection=DBConnection.getElaqe();
        String SEND_MESSAGE="insert into "+MESSAGE_TABLE+"(id,mfrom,mto,mbody) values("+message.getUser().getId()+",\'"+message.getFrom()+"\',\'"+message.getTo()+"\',\'"+message.getBody()+"\')";
        System.out.println("Target QUERY : "+SEND_MESSAGE);
        PreparedStatement preparedStatement=connection.prepareStatement(SEND_MESSAGE);
        preparedStatement.execute();
        return message;
    }// M-3


    public static List<Message> getMessageHistory(String from,String to) throws SQLException{
        Connection connection=DBConnection.getElaqe();
        String GET_MESSAGES="select * from "+MESSAGE_TABLE+" where mfrom=\'"+from+"\' and mto=\'"+to+"\'";
        System.out.println("QUERY:"+GET_MESSAGES);
        PreparedStatement preparedStatement=connection.prepareStatement(GET_MESSAGES);
        ResultSet netice=preparedStatement.executeQuery();
        List<Message> messages=new LinkedList<>();
        while(netice.next()){
            messages.add(new Message(netice.getString("mfrom"), netice.getString("mto"),netice.getString("mbody")));
        }
        return messages;// L-1
    }

    public static List<Message> getNewestMessages(String from,String to,long bound) throws SQLException{
        Connection connection=DBConnection.getElaqe();
        String GET_NEWEST_MESSAGES="select * from "+MESSAGE_TABLE+" where mfrom=\'"+from+"\' and mto=\'"+to+"\' offset "+bound;
        PreparedStatement preparedStatement=connection.prepareStatement(GET_NEWEST_MESSAGES);
        ResultSet netice=preparedStatement.executeQuery();
        LinkedList<Message> newestMessages=new LinkedList<>();
        while(netice.next()){
            newestMessages.add(new Message(netice.getString("mfrom"), netice.getString("mto"),netice.getString("mbody")));
        }
        return newestMessages;
    } // N-1
}