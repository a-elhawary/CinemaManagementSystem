package helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String DATABASE_HOST = "206.81.8.63";
    private static final String DATABASE_NAME = "Cinema";
    private static final String DATABASE_PORT = "3306";
    private static final String DATABASE_USER = "remote";
    private static final String DATABASE_PASS = "gfIIb100%s";

    public static Connection getCon(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection c = DriverManager.getConnection("jdbc:mysql://"+DATABASE_HOST+":"+DATABASE_PORT+"/"+DATABASE_NAME, DATABASE_USER, DATABASE_PASS);
           return c;
       }catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
}
