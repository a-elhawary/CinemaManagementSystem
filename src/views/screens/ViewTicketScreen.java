package views.screens;

import enums.Level;
import model.Movie;
import model.Ticket;
import model.User;
import views.AppColors;
import views.Screen;
import views.sharedcomponents.AppBar;
import views.sharedcomponents.ImageHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class ViewTicketScreen extends Screen {
    public ViewTicketScreen(Screen previousScreen, Movie movie, ArrayList<String> seats, double totalPrice, Date selectedDate, String showing){
        super(previousScreen.parentWindow, previousScreen);
        this.setLayout(new BorderLayout());
        AppBar topBar = new AppBar(this);
        this.add(topBar, BorderLayout.NORTH);
        if(User.getLoggedInUserLevel() == Level.CASHIER){
            JPanel mainContainer = new JPanel();
            mainContainer.setBackground(AppColors.grey);
            mainContainer.setLayout(new GridBagLayout());

            JPanel whiteContainer = new JPanel();
            whiteContainer.setLayout(new BorderLayout());
            whiteContainer.setBorder(new EmptyBorder(10,10,10,10));

            JPanel ticketDetails = new JPanel();
            whiteContainer.add(ticketDetails, BorderLayout.CENTER);
            ticketDetails.setLayout(new BoxLayout(ticketDetails, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("Ticket");
            title.setForeground(AppColors.primary);
            title.setFont(new Font("Arial", Font.PLAIN, 20));

            String seatsString = "Seats: ";
            for(String seat: seats){
                seatsString += seat;
                seatsString += ",";
            }

            JLabel seatsLabel = new JLabel(seatsString);

            String priceString = "Total Price: " + totalPrice;
            JLabel priceLabel = new JLabel(priceString);

            ticketDetails.add(title);
            ticketDetails.add(seatsLabel);
            ticketDetails.add(priceLabel);

            JLabel movieDetails = new JLabel();
            movieDetails.setIcon(ImageHandler.scaleImage(new ImageIcon(movie.getImageData()), 100));
            movieDetails.setText(movie.getName());
            movieDetails.setHorizontalTextPosition(JLabel.CENTER);
            movieDetails.setVerticalTextPosition(JLabel.BOTTOM);

            whiteContainer.add(movieDetails, BorderLayout.EAST);

            mainContainer.add(whiteContainer);

            JPanel printContainer = new JPanel();
            printContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
            printContainer.setBackground(null);
            JButton printButton = new JButton("Print Ticket");
            printButton.setBackground(AppColors.primary);
            printButton.setForeground(Color.white);
            printButton.setBorderPainted(false);
            printButton.setOpaque(true);
            printButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            printContainer.add(printButton);
            this.add(mainContainer, BorderLayout.CENTER);
            this.add(printContainer, BorderLayout.SOUTH);
        }else{
            this.setBackground(AppColors.grey);
            JPanel whiteContainer = new JPanel();
            JPanel mainContainer = new JPanel(new GridBagLayout());
            mainContainer.setBackground(AppColors.grey);
            whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
            whiteContainer.setBorder(new EmptyBorder(10,10,10,10));
            JLabel label = new JLabel("Ticket ID");
            label.setForeground(AppColors.primary);

            whiteContainer.add(label);
            whiteContainer.add(new JLabel("Please note this id as you will need it in Cinema"));

            String ticketId = User.getLoggedInUserId() + "-" + movie.getId()  + "-" + selectedDate.getTime() + "-" + showing;
            JTextPane f = new JTextPane();
            f.setContentType("text/html"); // let the text pane know this is what you want
            f.setEditable(false); // as before
            f.setBackground(null); // this is the same as a JLabel
            f.setBorder(null);
            f.setText(ticketId);
            whiteContainer.add(f);
            mainContainer.add(whiteContainer);
            this.add(mainContainer, BorderLayout.CENTER);
        }
    }

    public ViewTicketScreen(Screen previousScreen, ArrayList<Ticket> tickets){
        super(previousScreen.parentWindow, previousScreen);
        this.setLayout(new BorderLayout());
        AppBar topBar = new AppBar(this);
        this.add(topBar, BorderLayout.NORTH);
        JPanel mainContainer = new JPanel();
        mainContainer.setBackground(AppColors.grey);
        mainContainer.setLayout(new GridBagLayout());

        JPanel whiteContainer = new JPanel();
        whiteContainer.setLayout(new BorderLayout());
        whiteContainer.setBorder(new EmptyBorder(10,10,10,10));

        JPanel ticketDetails = new JPanel();
        whiteContainer.add(ticketDetails, BorderLayout.CENTER);
        ticketDetails.setLayout(new BoxLayout(ticketDetails, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Ticket");
        title.setForeground(AppColors.primary);
        title.setFont(new Font("Arial", Font.PLAIN, 20));

        double totalPrice = 0;
        String seats = "";
        Movie movie = Movie.getMovieBy(tickets.get(0).getMovieId());

        for(Ticket ticket : tickets){
            totalPrice += ticket.getPrice();
            seats += ticket.getSeatNumber();
            seats += ",";
        }

        String seatsString = "Seats: " + seats;

        JLabel seatsLabel = new JLabel(seatsString);

        String priceString = "Total Price: " + totalPrice;
        JLabel priceLabel = new JLabel(priceString);

        ticketDetails.add(title);
        ticketDetails.add(seatsLabel);
        ticketDetails.add(priceLabel);

        JLabel movieDetails = new JLabel();
        movieDetails.setIcon(ImageHandler.scaleImage(new ImageIcon(movie.getImageData()), 100));
        movieDetails.setText(movie.getName());
        movieDetails.setHorizontalTextPosition(JLabel.CENTER);
        movieDetails.setVerticalTextPosition(JLabel.BOTTOM);

        whiteContainer.add(movieDetails, BorderLayout.EAST);

        mainContainer.add(whiteContainer);

        JPanel printContainer = new JPanel();
        printContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        printContainer.setBackground(null);
        JButton printButton = new JButton("Print Ticket");
        printButton.setBackground(AppColors.primary);
        printButton.setForeground(Color.white);
        printButton.setBorderPainted(false);
        printButton.setOpaque(true);
        printButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        printContainer.add(printButton);
        this.add(mainContainer, BorderLayout.CENTER);
        this.add(printContainer, BorderLayout.SOUTH);
    }

    @Override
    public void refresh() {

    }
}
