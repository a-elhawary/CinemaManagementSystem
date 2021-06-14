package views.screens.Admin;

import controller.AdminScreenController;
import model.Ticket;
import views.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class RequestReport extends JPanel {
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
        this.setLayout(new GridBagLayout());
        this.setBackground(null);
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
        this.add(whiteContainer);
    }
}
