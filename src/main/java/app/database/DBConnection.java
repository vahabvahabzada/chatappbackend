package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection elaqe;// neyin ki instance-ini yaratmaq lazim deyil,onu static saxlamaq meqsedeuygundur

    public static Connection getElaqe() throws SQLException{
        if (elaqe == null) {
            System.out.println("Connecting to database...");
            elaqe = DriverManager.getConnection("jdbc:postgresql://url/databasename", "username", "password");
            System.out.println("Connected to database");
        }
        return elaqe;
    }
}
//mvn clean compile assembly:single or mvn clean package
//mvn exec:java