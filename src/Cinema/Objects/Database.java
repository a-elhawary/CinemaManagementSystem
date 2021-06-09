package Cinema.Objects;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String DATABASE_NAME = "Cinema";
    private static final String DATABASE_PORT = "8889";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "root";

    public static Connection getCon(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection c = DriverManager.getConnection("jdbc:mysql://localhost:"+DATABASE_PORT+"/"+DATABASE_NAME, DATABASE_USER, DATABASE_PASS);
           return c;
       }catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
}
