package cinema.model;

import cinema.exceptions.BlankDataEnteredException;
import cinema.helper.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Hall extends Model{
    public String name;
    public int seatsNumber;

    public Hall(int id){
        super("Halls", id);
        try{
            Connection c = Database.getCon();
            PreparedStatement select = c.prepareStatement("select * from Halls where id = ?");
            select.setInt(1, this.id);
            ResultSet r = select.executeQuery();
            while (r.next()){
                this.name = r.getString("name");
                this.seatsNumber = r.getInt("seats_number");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Hall(String name, int seatsNumber){
        super("Halls", 0);
        this.name = name;
        this.seatsNumber = seatsNumber;
    }

    public Hall(int id, String name, int seatsNumber){
        super("Halls", id);
        this.name = name;
        this.seatsNumber = seatsNumber;
    }



    public void save() throws BlankDataEnteredException {
        if(this.name.isEmpty()) throw new BlankDataEnteredException();
        try{
            Connection c = Database.getCon();
            PreparedStatement insert = c.prepareStatement("insert into Halls Values (default, ?,?)");
            insert.setString(1, name);
            insert.setInt(2, seatsNumber);
            insert.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Hall> getHalls(){
        ArrayList<Hall> halls = new ArrayList<Hall>();
        try {
            Connection c = Database.getCon();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from Halls");
            while(r.next()){
                Hall currentHall = new Hall(r.getInt("id"), r.getString("name"), r.getInt("seats_number"));
                halls.add(currentHall);
            }
            return halls;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Hall getHallById(int id){
        ArrayList<Hall> halls = new ArrayList<Hall>();
        try {
            Connection c = Database.getCon();
            PreparedStatement s = c.prepareStatement("select * from Halls where id = ?");
            s.setInt(1, id);
            ResultSet r = s.executeQuery();
            while(r.next()){
                Hall currentHall = new Hall(r.getInt("id"), r.getString("name"), r.getInt("seats_number"));
                halls.add(currentHall);
            }
            return halls.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
