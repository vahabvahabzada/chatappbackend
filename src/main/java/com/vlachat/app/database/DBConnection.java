package com.vlachat.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection elaqe;

    public static Connection getElaqe() throws SQLException{
        if (elaqe == null) {
            System.out.println("Connecting to database...");
            try {
                Class.forName("org.postgresql.Driver");
                elaqe = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chatappbackendservlet", "postgres", "root");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connected to database");
        }
        //System.out.println("Elaqe:"+elaqe);
        return elaqe;
    }
}
