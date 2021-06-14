package model;

import exceptions.BlankDataEnteredException;
import exceptions.PasswordsMustMatchException;
import exceptions.UserNotFoundException;
import exceptions.UsernameExistsException;
import helper.Database;
import enums.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class User extends Model{
    static private int loggedInUserId;
    static private Level loggedInUserLevel;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;

    public User(String userName, String password){
        super("Users", 0);
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String lastName, String userName, String password, String confirmPassword){
        this(userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.confirmPassword = confirmPassword;
    }

    public User(int id, String firstName, String lastName, String userName, String password){
        super("Users", id);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void register(Level level) throws BlankDataEnteredException, UsernameExistsException, PasswordsMustMatchException {
        if( firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){ throw new BlankDataEnteredException();}
        int rowCount = 0;
        try{
            Connection c = Database.getCon();
            PreparedStatement s = c.prepareStatement("Select user_name from Users where user_name = ?");
            s.setString(1, userName);
            ResultSet r = s.executeQuery();
            while(r.next()){
                rowCount++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(rowCount != 0){ throw new UsernameExistsException(); }
        if(!password.equals(confirmPassword)){ throw new PasswordsMustMatchException();}
        try {
            Connection c = Database.getCon();
            PreparedStatement insert = c.prepareStatement("insert into Users values (default, ?, ?, ?, ?, ?)");
            insert.setString(1, firstName);
            insert.setString(2, lastName);
            insert.setString(3, userName);
            insert.setString(4, password);
            insert.setInt(5, level.ordinal());
            insert.execute();
            loggedInUserLevel = level;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login() throws BlankDataEnteredException, UserNotFoundException{
        boolean state = false;
        if(userName.isEmpty() || password.isEmpty()){ throw new BlankDataEnteredException();}
        try{
            Connection c = Database.getCon();
            PreparedStatement s = c.prepareStatement("Select * from Users WHERE user_name = ?");
            s.setString(1, this.userName);
            ResultSet r = s.executeQuery();
            while(r.next()){
               if(r.getString("password").equals(this.password)){
                   loggedInUserId = r.getInt("id");
                   loggedInUserLevel = Level.values()[r.getInt("level")];
                   state = true;
               }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!state) throw new UserNotFoundException();
    }

    public static ArrayList<User> getCashiers(){
        ArrayList<User> users = new ArrayList<User>();
        try {
            Connection c = Database.getCon();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from Users where level = 1");
            while(r.next()){
                User currentUser = new User(r.getInt("id"), r.getString("first_name"), r.getString("last_name"), r.getString("user_name"),r.getString("password"));
                users.add(currentUser);
            }
            return users;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static Level getLoggedInUserLevel() {
        return loggedInUserLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }
}
