package views.screens.Admin;

import controller.AdminScreenController;
import enums.Level;
import model.Ticket;
import model.User;
import views.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class RequestReport extends Panel {
    public RequestReport(AdminScreenController controller){
        ArrayList<Ticket> tickets = Ticket.getTickets();
        double totalRevenue = 0;
        int numOfTickets = 0;
        double averageRevenuePerTicket = 0;
        for(Ticket ticket : tickets){
            totalRevenue += ticket.getPrice();
            numOfTickets += 1;
        }
        averageRevenuePerTicket = totalRevenue / numOfTickets;

        ArrayList<User> customers = User.getUsersWithLevel(Level.CUSTOMER);
        ArrayList<User> cashiers = User.getUsersWithLevel(Level.CASHIER);
        ArrayList<User> admins = User.getUsersWithLevel(Level.ADMIN);

        this.setLayout(new GridBagLayout());
        this.setBackground(null);

        JPanel reportsContainer = new JPanel();
        reportsContainer.setLayout(new BoxLayout(reportsContainer, BoxLayout.Y_AXIS));

        JPanel whiteContainer = new JPanel();
        whiteContainer.setBorder(new EmptyBorder(10,10,10,10));
        whiteContainer.setBackground(Color.white);
        whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Revenue Report");
        title.setForeground(AppColors.primary);
        whiteContainer.add(title);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        whiteContainer.add(new JLabel("Total Tickets: " + numOfTickets));
        whiteContainer.add(Box.createRigidArea(new Dimension(0,5)));
        whiteContainer.add(new JLabel("Total Revenue: " + totalRevenue));
        whiteContainer.add(Box.createRigidArea(new Dimension(0,5)));
        whiteContainer.add(new JLabel("Average Price per Ticker: " + averageRevenuePerTicket));

        JPanel secondReport = new JPanel();
        secondReport.setBorder(new EmptyBorder(10,10,10,10));
        secondReport.setBackground(Color.white);
        secondReport.setLayout(new BoxLayout(secondReport, BoxLayout.Y_AXIS));
        JLabel secondTitle = new JLabel("Users Report");
        secondTitle.setForeground(AppColors.primary);
        secondReport.add(secondTitle);
        secondReport.add(Box.createRigidArea(new Dimension(0,10)));
        secondReport.add(new JLabel("Registered Customer Count: " + customers.size()));
        secondReport.add(Box.createRigidArea(new Dimension(0,5)));
        secondReport.add(new JLabel("Registered Cashiers Count: " + cashiers.size()));
        secondReport.add(Box.createRigidArea(new Dimension(0,5)));
        secondReport.add(new JLabel("Registered Admins Count: " + admins.size()));

        reportsContainer.add(whiteContainer);
        reportsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        reportsContainer.add(secondReport);
        this.add(reportsContainer);
    }

    public void refresh(){}
}
