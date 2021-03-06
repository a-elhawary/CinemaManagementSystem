package model;

import exceptions.BlankDataEnteredException;
import helper.Database;

import java.sql.*;
import java.util.ArrayList;

public class Ticket extends Model{
    private int userId;
    private int movieId;
    private String seat_number;
    private double price;
    private Date date;
    private String showing;

    public Ticket(int id) {
        super("Tickets", id);
    }

    public Ticket(int user_id, int movieId, String seat_number, double price, Date date, String showing){
        this(0);
        this.userId = user_id;
        this.movieId = movieId;
        this.seat_number = seat_number;
        this.price = price;
        this.date = date;
        this.showing = showing;
    }

    public Ticket(int id, int userId, int movieId, String seat_number, double price, Date date, String showing){
        this(id);
        this.userId = userId;
        this.movieId = movieId;
        this.seat_number = seat_number;
        this.price = price;
        this.date = date;
        this.showing = showing;
    }

    public void save() throws BlankDataEnteredException{
        if(this.seat_number.isEmpty() || this.showing.isEmpty()) throw new BlankDataEnteredException();
        try{
            Connection c = Database.getCon();
            PreparedStatement insert = c.prepareStatement("insert into Tickets Values (default,?,?,?,?,?,?)");
            insert.setInt(1, userId);
            insert.setInt(2, movieId);
            insert.setString(3, seat_number);
            insert.setDouble(4, price);
            insert.setDate(5, date);
            insert.setString(6 ,showing);
            insert.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Ticket> getTicketsBy(int movieId, Date choosenDate, String choosenShowing){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection c = Database.getCon();
            PreparedStatement select = c.prepareStatement("select distinct * from Tickets where movie_id = ? and date = ? and showing = ?");
            select.setInt(1, movieId);
            select.setDate(2, choosenDate);
            select.setString(3, choosenShowing);
            ResultSet r = select.executeQuery();
            while(r.next()){
               tickets.add(new Ticket(r.getInt("id"), r.getInt("user_id"), r.getInt("movie_id"), r.getString("seat_number"),r.getDouble("price"), r.getDate("date"), r.getString("showing"))) ;
            }
        }catch (Exception e){
          e.printStackTrace();
        }
        return tickets;
    }

    public static ArrayList<Ticket> getTicketsBy(int userId, int movieId, Date choosenDate, String choosenShowing){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection c = Database.getCon();
            PreparedStatement select = c.prepareStatement("select * from Tickets where user_id = ? and movie_id = ? and date = ? and showing = ?");
            select.setInt(1, userId);
            select.setInt(2, movieId);
            select.setDate(3, choosenDate);
            select.setString(4, choosenShowing);
            ResultSet r = select.executeQuery();
            while(r.next()){
                tickets.add(new Ticket(r.getInt("id"), r.getInt("user_id"), r.getInt("movie_id"), r.getString("seat_number"),r.getDouble("price"), r.getDate("date"), r.getString("showing"))) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tickets;
    }

    public static ArrayList<Ticket> getTickets(){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection c = Database.getCon();
            Statement select = c.createStatement();
            ResultSet r = select.executeQuery("select * from Tickets");
            while(r.next()){
                tickets.add(new Ticket(r.getInt("id"), r.getInt("user_id"),r.getInt("movie_id"),  r.getString("seat_number"),r.getDouble("price"), r.getDate("date"), r.getString("showing"))) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tickets;
    }

    public int getMovieId() {
        return movieId;
    }
    public double getPrice() {
        return price;
    }

    public int getUserId() {
        return userId;
    }

    public String getSeatNumber() {
        return seat_number;
    }

    public Date getDate() {
        return date;
    }

    public String getShowing() {
        return showing;
    }
}
