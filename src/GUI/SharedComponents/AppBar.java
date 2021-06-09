package GUI.SharedComponents;

import Cinema.Objects.Level;
import Cinema.Objects.User;
import GUI.AppColors;
import GUI.Screen;
import GUI.Screens.Admin.AdminScreen;
import GUI.Screens.LoginScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppBar extends JPanel {
    public AppBar(Screen parent){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10,10,10,10));
        this.setBackground(AppColors.primary);

        JPanel leftContainer = new JPanel();
        leftContainer.setBackground(null);
        JLabel label = new JLabel("Cinema Management System");
        label.setForeground(Color.white);
        if(parent.previousScreen != null){
            JButton viewMoviesButton = new JButton();
            viewMoviesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            viewMoviesButton.setIcon(ImageHandler.scaleImage(new ImageIcon("backIcon.png"), 10));
            viewMoviesButton.addActionListener(e ->{
                parent.navigateTo(parent.previousScreen);
            });
            viewMoviesButton.setOpaque(true);
            viewMoviesButton.setBorderPainted(false);
            viewMoviesButton.setForeground(AppColors.primary);
            viewMoviesButton.setBackground(null);

            leftContainer.add(viewMoviesButton);
        }else{
            leftContainer.setLayout(new GridBagLayout());
        }
        leftContainer.add(label);

        JPanel rightContainer = new JPanel();
        rightContainer.setBackground(null);

        JButton logoutButton = new JButton();
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setText("Logout");
        logoutButton.addActionListener(e ->{
            parent.navigateTo(new LoginScreen(parent.parentWindow, null));
        });
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        logoutButton.setForeground(AppColors.primary);
        logoutButton.setBackground(Color.white);
        if(!(parent instanceof AdminScreen) && User.loggedInUserLevel == Level.ADMIN){
            JButton adminButton = new JButton();
            adminButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            adminButton.setText("Admin Panel");
            adminButton.addActionListener(e ->{
                parent.navigateTo(new AdminScreen(parent.parentWindow, parent));
            });
            adminButton.setOpaque(true);
            adminButton.setBorderPainted(false);
            adminButton.setForeground(AppColors.primary);
            adminButton.setBackground(Color.white);

            rightContainer.add(adminButton);
        }
        rightContainer.add(logoutButton);

        this.add(leftContainer, BorderLayout.WEST);
        this.add(rightContainer, BorderLayout.EAST);
    }
}
