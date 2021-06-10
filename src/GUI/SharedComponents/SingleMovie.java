package GUI.SharedComponents;

import Cinema.Objects.Movie;
import GUI.AppColors;
import GUI.Screen;
import GUI.Screens.SingleMovieScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SingleMovie extends JPanel {
    public SingleMovie(Movie movie, Screen parentScreen){
        this.setBackground(null);
        this.setLayout(new GridBagLayout());

        JPanel whiteContainer = new JPanel();
        whiteContainer.setLayout(new GridBagLayout());
        whiteContainer.setBackground(Color.white);
        whiteContainer.setBorder(new EmptyBorder(0,0,10,0));

        ImageIcon image = ImageHandler.scaleImage(new ImageIcon(movie.imageData), (int)(GUI.Window.width * 0.2));
        JLabel label = new JLabel(movie.name);
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(AppColors.primary);

        JButton viewMovie = new JButton();
        viewMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewMovie.addActionListener(e -> {
            parentScreen.navigateTo(new SingleMovieScreen(parentScreen.parentWindow, parentScreen, movie));
        });
        viewMovie.setBackground(AppColors.primary);
        viewMovie.setForeground(Color.white);
        viewMovie.setOpaque(true);
        viewMovie.setBorderPainted(false);
        viewMovie.setText("View Movie Details");

        whiteContainer.add(label);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        whiteContainer.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        whiteContainer.add(viewMovie, gbc);

        this.add(whiteContainer);
    }

}