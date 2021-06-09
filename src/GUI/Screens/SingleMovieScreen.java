package GUI.Screens;

import Cinema.Objects.Movie;
import GUI.AppColors;
import GUI.Screen;
import GUI.SharedComponents.AppBar;
import GUI.SharedComponents.ImageHandler;
import GUI.SharedComponents.Seat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SingleMovieScreen extends Screen {

    private int hallSize = 60;
    private String names[] = {"A", "B", "C", "D"};
    private double ratios[] = {6, 3, 6, 3};

    public SingleMovieScreen(GUI.Window parentWindow, Screen previousScreen, int movieId){
        super(parentWindow, previousScreen);
        Movie currentMovie = new Movie(movieId);
        this.setLayout(new BorderLayout());
        JPanel topBar = new AppBar(this);
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setBackground(AppColors.grey);
        sideBar.setBorder(new EmptyBorder(20,20,20,20));

        ImageIcon image = ImageHandler.scaleImage(new ImageIcon(currentMovie.imageData), (int)(GUI.Window.width * 0.2));
        JLabel movieTitle = new JLabel(currentMovie.name);
        movieTitle.setForeground(AppColors.primary);
        movieTitle.setIcon(image);
        movieTitle.setHorizontalTextPosition(JLabel.CENTER);
        movieTitle.setVerticalTextPosition(JLabel.BOTTOM);

        sideBar.add(movieTitle);

        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(Color.white);
        mainContainer.setBorder(new EmptyBorder(10,10,10,10));
        mainContainer.setLayout(new GridBagLayout());
        JPanel screenDirection = new JPanel();
        screenDirection.setBorder(BorderFactory.createLineBorder(Color.black));
        screenDirection.setBackground(null);

        JLabel screen = new JLabel("SCREEN");
        screen.setBorder(new EmptyBorder(20,20,20,20));
        screenDirection.add(screen);

        JPanel theatreContainer = new JPanel();
        theatreContainer.setLayout(new BorderLayout(20, 20));
        theatreContainer.setBackground(null);

        JPanel[] seatsContainers = new JPanel[4];
        JPanel[] parents = new JPanel[4];
        for(int i = 0; i < seatsContainers.length; i++){
            parents[i] = new JPanel();
            parents[i].setBackground(null);
            parents[i].setLayout(new GridBagLayout());
            seatsContainers[i] = new JPanel();
            seatsContainers[i].setBackground(null);
            seatsContainers[i].setLayout(new GridLayout((int)(hallSize / ratios[0] / 2), 2));
            parents[i].add(seatsContainers[i]);
        }
        seatsContainers[3].setLayout(new GridLayout(2, (int)(hallSize / ratios[0] / 2)));

        for(int i = 0; i < seatsContainers.length; i++){
            for(int j = 0; j < hallSize / ratios[i]; j++){
                if(j < 10){
                    seatsContainers[i].add(new Seat(names[i] + "0" + j));
                }else{
                    seatsContainers[i].add(new Seat(names[i] + j));
                }
            }
        }
        theatreContainer.add(screenDirection, BorderLayout.NORTH);
        theatreContainer.add(parents[0], BorderLayout.WEST);
        theatreContainer.add(parents[1], BorderLayout.CENTER);
        theatreContainer.add(parents[2], BorderLayout.EAST);
        theatreContainer.add(parents[3], BorderLayout.SOUTH);

        mainContainer.add(theatreContainer);

        this.add(topBar, BorderLayout.NORTH);
        this.add(sideBar, BorderLayout.WEST);
        this.add(mainContainer, BorderLayout.CENTER);
    }
}
