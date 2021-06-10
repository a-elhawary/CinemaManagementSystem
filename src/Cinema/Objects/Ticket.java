package Cinema.Objects;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ticket extends Model{
    public int userId;
    public String seat_number;
    public double price;
    public Date date;
    public String showing;

    public Ticket(int id) {
        super("Tickets", id);
    }

    public Ticket(int user_id, String seat_number, double price, Date date, String showing){
        this(0);
        this.userId = user_id;
        this.seat_number = seat_number;
        this.price = price;
        this.date = date;
        this.showing = showing;
    }

    public Ticket(int id, int userId, String seat_number, double price, Date date, String showing){
        this(id);
        this.userId = userId;
        this.seat_number = seat_number;
        this.price = price;
        this.date = date;
        this.showing = showing;
    }

    public boolean save(){
        if(this.seat_number.isEmpty() || this.showing.isEmpty()) return false;
        try{
            Connection c = Database.getCon();
            PreparedStatement insert = c.prepareStatement("insert into Tickets Values (default,?,?,?,?,?)");
            insert.setInt(1, userId);
            insert.setString(2, seat_number);
            insert.setDouble(3, price);
            insert.setDate(4, date);
            insert.setString(5 ,showing);
            insert.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Ticket> getTicketsBy(Date choosenDate, String choosenShowing){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection c = Database.getCon();
            PreparedStatement select = c.prepareStatement("select distinct * from Tickets where date = ? and showing = ?");
            select.setDate(1, choosenDate);
            select.setString(2, choosenShowing);
            ResultSet r = select.executeQuery();
            while(r.next()){
               tickets.add(new Ticket(r.getInt("id"), r.getInt("user_id"), r.getString("seat_number"),r.getDouble("price"), r.getDate("date"), r.getString("showing"))) ;
            }
        }catch (Exception e){
          e.printStackTrace();
        }
        return tickets;
    }
}
