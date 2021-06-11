package GUI.SharedComponents;

import Cinema.Objects.Level;
import Cinema.Objects.Ticket;
import Cinema.Objects.User;
import GUI.AppColors;
import GUI.Screen;
import GUI.Screens.Admin.AdminScreen;
import GUI.Screens.HomeScreen;
import GUI.Screens.LoginScreen;
import GUI.Screens.ViewTicketScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

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
                parent.previousScreen.refresh();
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
        if(parent instanceof HomeScreen && User.loggedInUserLevel == Level.CASHIER){
            JTextField ticketId = new JTextField();
            ticketId.setColumns(20);
            JButton verifyTicket = new JButton();
            verifyTicket.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            verifyTicket.setText("Verify Ticket");
            verifyTicket.addActionListener(e ->{
                String[] id = ticketId.getText().split("-");
                if(id.length == 4) {
                    ArrayList<Ticket> tickets = Ticket.getTicketsBy(Integer.parseInt(id[0]), Integer.parseInt(id[1]), new Date(Long.parseLong(id[2])), id[3]);
                    if (tickets.size() == 0) {
                        showError();
                    } else {
                        parent.navigateTo(new ViewTicketScreen(parent, tickets));
                    }
                }else{
                    showError();
                }
            });
            verifyTicket.setOpaque(true);
            verifyTicket.setBorderPainted(false);
            verifyTicket.setForeground(AppColors.primary);
            verifyTicket.setBackground(Color.white);
            rightContainer.add(ticketId);
            rightContainer.add(verifyTicket);
        }
        rightContainer.add(logoutButton);

        this.add(leftContainer, BorderLayout.WEST);
        this.add(rightContainer, BorderLayout.EAST);
    }
    private void showError(){
        JFrame frame = new JFrame();
        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(new JLabel("Invalid Ticket ID"));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
