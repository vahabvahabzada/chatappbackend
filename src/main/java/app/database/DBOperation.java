package app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import app.entities.Message;
import app.entities.User;

public class DBOperation {
    public static boolean addUserToDB(User user) throws SQLException {
        Connection conn = DBConnection.getElaqe();

        String QUERY = "select * from users where name=\'" + user.getUserName() + "\'";
        System.out.println(QUERY);
        
        PreparedStatement statement = conn.prepareStatement(QUERY);
        System.out.println("Works!");
        ResultSet netice = statement.executeQuery();
        System.out.println("Works2!");
        //System.out.println("checking::"+netice.next());
        if (!netice.next()) {// istifadeci adi tekrar deyil
            QUERY = "insert into users values (\'" + user.getUserName() + "\',\'" + user.getPassword()+ "\',null,null)";
            System.out.println(QUERY);
            //statement.executeUpdate();// DML statements need to be run through executeUpdate() not through executeQuery()
            //conn.commit();
            statement=conn.prepareStatement(QUERY);
            statement.execute();
            //System.out.println("User has been added to the DB");
            return true;
        }
        return false;
    }

    public static String putToken(String username) throws SQLException {
        String token = UUID.randomUUID().toString();
        long exp_time = System.currentTimeMillis() + 15 * 60 * 1000;// 15 deqiqeden sonra token mehv olur
        Connection conn = DBConnection.getElaqe();
        String QUERY = "update users set token=\'" + token + "\',exp_time=" + exp_time + " where name=\'" + username + "\'";// update token
        PreparedStatement statement = conn.prepareStatement(QUERY);
        statement.executeUpdate();
        // conn.commit();
        QUERY = "select token from users where name=\'" + username + "\'";
        statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        if (netice.next()) {
            token=netice.getString("token");
            
            return token;/*netice.getString("token")*/
        }
        else {
            return null;
        }
    }

    public static User getUser(String username) throws SQLException {
        //System.out.println("DBOperation.java --> getUser():1");
        Connection conn = DBConnection.getElaqe();
        String QUERY = "select * from users where name=\'" + username + "\'";
        //System.out.println(QUERY);
        //System.out.println("DBOperation.java --> getUser():1");
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        System.out.println("Works!");
        if (netice.next()) {
            //System.out.println("bura" + netice.getString("password"));
            
            return new User(username, netice.getString("password"), netice.getString("token"),netice.getString("exp_time"));
        }
        else {
            
            return null;
        }
    }

    public static String getUserName(String token) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        String QUERY = "select name,exp_time,password from users where token=\'" + token + "\'";
        //System.out.println("Printing from getUserName(String token)--> " + token);
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        if (netice.next()) {
            if (netice.getString("name") != null) {
                if (netice.getLong("exp_time") > System.currentTimeMillis()) {
                    
                    return netice.getString("name");
                }
                /*else {
                    // the token time has expired,"delete" it(make it null in database)
                    QUERY = "update users set token=null where token=" + token;
                    statement = conn.prepareStatement(QUERY);
                    statement.executeQuery();
                }*/
            }
        }
        
        return null;
    }

    public static List<String> searchUsers(String searchText) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        String QUERY = "select name from users where name like \'" + searchText + "%\'";
        //System.out.println(QUERY);
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        ArrayList<String> results = new ArrayList<>();
        while (netice.next()) {
            results.add(netice.getString("name"));
        }
        
        return results;
    }

    public static void sendMessage(Message mesaj) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        //String QUERY = "show tables from vla where tables_in_vla like \'" + (mesaj.getFrom() + "and" + mesaj.getTo()) + "\'" + " or tables_in_vla like " + "\'" + mesaj.getTo() + "and" + mesaj.getFrom() + "\'";
        String QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and (tablename=\'"+(mesaj.getFrom() + "and" + mesaj.getTo())+"\' or tablename=\'"+(mesaj.getTo() + "and" + mesaj.getFrom())+"\')";
        System.out.println(QUERY);
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        String existedTable = "";
        Boolean boolNext = true;
        if (netice.next()) {
            existedTable = netice.getString(2);
            System.out.println("existedTable --> "+existedTable);
            boolNext = false;
        }

        if (boolNext) {// eger bele table yoxdursa,onda bele bir table yarat
            System.out.println("Trying to create table...");
            QUERY = "create table " + (mesaj.getFrom() + "and" + mesaj.getTo()) + "(mesgID serial primary key,mfrom varchar(50),mto varchar(50),mbody text);";// eger bu cute aid table yoxdursa,table yarat
            System.out.println(QUERY);
            statement = conn.prepareStatement(QUERY);
            statement.executeUpdate();
            System.out.println("Table has been created!");
            existedTable = (mesaj.getFrom() + "and" + mesaj.getTo());
        }
        QUERY = "insert into " + existedTable + "(mfrom,mto,mbody) values (\'" + mesaj.getFrom() + "\',\'" + mesaj.getTo() + "\',\'" + mesaj.getMessageBody() + "\');";// burada deyisiklik etdim ki tutaq ki user1anduser2 table-i yaranibsa user2anduser1 table-i yaranmasin
        System.out.println(QUERY);
        statement = conn.prepareStatement(QUERY);
        statement.executeUpdate();
        System.out.println("Message is sent!");
        // conn.commit();
        
    }

    public static List<Message> getMessageHistory(String kimden, String kime) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        String existedTableName;
        //String QUERY = "show tables like \'" + kimden + "and" + kime + "\'";
        String QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and tablename=\'"+kimden+"and"+kime+"\'";
        existedTableName = kimden + "and" + kime;
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet netice = statement.executeQuery();
        // Boolean boolNext=true;
        if (!netice.next()) {
            System.out.println("!netice.next()");
            //QUERY = "show tables like \'" + kime + "and" + kimden + "\'";
            QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and tablename=\'"+kime+"and"+kimden+"\'";
            existedTableName = kime + "and" + kimden;
            // boolNext=false;
        }
        statement = conn.prepareStatement(QUERY);
        netice = statement.executeQuery();
        if (!netice.next()/* boolNext */) {// demeli hele hec yazismayiblar
            System.out.println("Yazismayiblar");
            return null;
        }

        // QUERY="select mbody from "+existedTableName+";";
        QUERY = "select * from " + existedTableName + ";";
        statement = conn.prepareStatement(QUERY);
        netice = statement.executeQuery();

        List<Message> msgHistory = new ArrayList<>();
        
        while (netice.next()) {
            msgHistory.add(new Message(netice.getString("mfrom"), netice.getString("mto"), netice.getString("mbody")));
        }
        
        return msgHistory;
    }

    public static List<Message> getPartialMessageHistory(String kimden,String kime, long latestmsgId) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        //String QUERY = "show tables like \'" + kimden + "and" + kime + "\'";
        String QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and tablename=\'"+kimden+"and"+kime+"\'";
        PreparedStatement statement = conn.prepareStatement(QUERY);
        String existedTableName = kimden + "and" + kime;
        ResultSet netice = statement.executeQuery();
        if (!netice.next()) {
            //System.out.println("!netice.next()");
            //QUERY = "show tables like \'" + kime + "and" + kimden + "\'";
            QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and tablename=\'"+kime+"and"+kimden+"\'";
            existedTableName = kime + "and" + kimden;
            // boolNext=false;
        }
        statement = conn.prepareStatement(QUERY);
        netice = statement.executeQuery();
        if (!netice.next()/* boolNext */) {// demeli hele hec yazismayiblar
            //System.out.println("Yazismayiblar");
            return null;
        }

        QUERY = "select * from " + existedTableName + " where mesgID>" + latestmsgId;
        //System.out.println("Condition : " + QUERY);
        statement = conn.prepareStatement(QUERY);
        netice = statement.executeQuery();
        List<Message> newMsgs = new ArrayList<>();
        
        while (netice.next()) {
            newMsgs.add(new Message(netice.getString("mfrom"), netice.getString("mto"), netice.getString("mbody")));
            //System.out.println("Null yoxlamasi : " + netice.getString("mbody"));
        }
        
        return newMsgs;
    }

    public static List<Message> loadChatList(String username) throws SQLException {// evvelden yazdigi contactlar-i home page-de gostermek
        List<Message> chatList = new ArrayList<>();
        Connection conn = DBConnection.getElaqe();
        //String QUERY = "show tables like " + "\'%" + username + "%\'";
        String QUERY="select * from pg_catalog.pg_tables where schemaname=\'public\' and tablename like \'%"+username+"and%\' or tablename like \'%and"+username+"%\'";
        System.out.println("QUERY yoxlama1 : " + QUERY);
        PreparedStatement statement = conn.prepareStatement(QUERY);
        ResultSet neticeTable = statement.executeQuery();
        ResultSet neticeRow;
        while (neticeTable.next()) {
            QUERY = "select * from " + neticeTable.getString("tablename") + " order by mesgID desc limit 1";// verilen table-dan axrinci elementi cek
            statement=conn.prepareStatement(QUERY);
            neticeRow=statement.executeQuery();
            if (neticeRow.next()) {// bir defe
                chatList.add(new Message(neticeRow.getString("mfrom"), neticeRow.getString("mto"),neticeRow.getString("mbody")));
            }
        }
        
        return chatList;
    }

    public static String updateToken(String oldToken) throws SQLException {
        Connection conn = DBConnection.getElaqe();
        String newToken = UUID.randomUUID().toString();
        long exp_time = System.currentTimeMillis() + 15 * 60 * 1000;// 15 deqiqeden sonra token mehv olur
        conn = DBConnection.getElaqe();
        String QUERY = "update users set token=" + "\'" + newToken + "\'," + "exp_time=" +  exp_time + " where token=\'" + oldToken + "\'";
        PreparedStatement statement = conn.prepareStatement(QUERY);
        statement.executeUpdate();
        
        return newToken;
    }

    public static void logout(String token)throws SQLException{
        Connection conn=DBConnection.getElaqe();
        String QUERY="update users set token=null,exp_time=null exp_time where token=\'"+token+"\'";
        System.out.println(QUERY);
        PreparedStatement statement=conn.prepareStatement(QUERY);
        statement.executeUpdate();
        System.out.println("Logout works...");
        
    }
}