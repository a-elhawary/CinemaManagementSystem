package Cinema.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

public class Movie extends Model{
    public String name;
    public String description;
    File image;
    public BufferedImage imageData;
    public double rating;
    public Date startDate;
    public Date endDate;
    public int hallId;

    public Movie(int id){
        super("Movies", id);
        try{
           Connection c = Database.getCon();
           PreparedStatement select = c.prepareStatement("select * from movies where id = ?");
           select.setInt(1, this.id);
           ResultSet r = select.executeQuery();
           while (r.next()){
               this.name = r.getString("name");
               this.description = r.getString("description");
               this.imageData = ImageIO.read(r.getBinaryStream("image"));
               this.rating = r.getDouble("rating");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Movie(String name, String description,File image, Date startDate, Date endDate, int hallId){
        super("Movies", 0);
        this.name = name;
        this.description = description;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hallId = hallId;
    }

    public Movie(int id, String name, String description,BufferedImage image,double rating, Date startDate, Date endDate, int hallId){
        super("Movies", id);
        this.name = name;
        this.description = description;
        this.imageData = image;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hallId = hallId;
    }

    public boolean save(){
        if(this.name.isEmpty() || this.description.isEmpty() || this.image == null || startDate == null || endDate == null) return false;
        try{
            Connection c = Database.getCon();
            PreparedStatement insert = c.prepareStatement("insert into Movies Values (default, ?,0,?,?,?,?,?)");
            insert.setString(1, name);
            insert.setString(2, description);
            insert.setBlob(3, new FileInputStream(image));
            insert.setDate(4 ,startDate);
            insert.setDate(5, endDate);
            insert.setInt(6, hallId);
            insert.execute();
            return true;
       }catch(Exception e){
           e.printStackTrace();
       }
       return false;
    }

    public static ArrayList<Movie> getMovies(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            Connection c = Database.getCon();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from Movies");
            while(r.next()){
                Movie currentMovie = new Movie(r.getInt("id"), r.getString("name"), r.getString("description"),ImageIO.read(r.getBinaryStream("image")),r.getDouble("rating"), new Date(r.getDate("start_date").getTime()), new Date(r.getDate("end_date").getTime()), r.getInt("hall_id"));
                movies.add(currentMovie);
            }
            return movies;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Movie getMovieBy(int id){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            Connection c = Database.getCon();
            PreparedStatement s = c.prepareStatement("select * from Movies where id = ?");
            s.setInt(1, id);
            ResultSet r = s.executeQuery();
            while(r.next()){
                Movie currentMovie = new Movie(r.getInt("id"), r.getString("name"), r.getString("description"),ImageIO.read(r.getBinaryStream("image")),r.getDouble("rating"), new Date(r.getDate("start_date").getTime()), new Date(r.getDate("end_date").getTime()), r.getInt("hall_id"));
                movies.add(currentMovie);
            }
            return movies.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
