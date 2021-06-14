package controller;

import model.Movie;
import views.AppColors;
import views.screens.HomeScreen;
import views.sharedcomponents.SingleMovie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class HomeScreenController {

    HomeScreen screen;

    public HomeScreenController(HomeScreen screen){
        this.screen = screen;
    }

    public JPanel buildList(){
        ArrayList<Movie> moviesData = Movie.getMovies();
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setBorder(new EmptyBorder(10,10,10,10));
        JLabel titleLabel = new JLabel("Our Movies");
        titleLabel.setBorder(new EmptyBorder(10,10,10,10));
        titleLabel.setForeground(AppColors.primary);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel movies = new JPanel();
        GridLayout layout;
        if(moviesData == null){
            layout = new GridLayout(3,3);
        }else{
            layout = new GridLayout((int)(Math.ceil(moviesData.size() / 3.0)),3);
        }
        layout.setVgap(20);
        movies.setLayout(layout);
        movies.setBorder(new EmptyBorder(10,10,10,10));
        JScrollPane scroll = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        if(moviesData != null){
            for (Movie movie : moviesData) {
                movies.add(new SingleMovie(movie, screen));
            }
        }

        mainContent.add(titleLabel, BorderLayout.NORTH);
        mainContent.add(scroll, BorderLayout.CENTER);
        mainContent.setBackground(Color.white);
        return mainContent;
    }
}
