package GUI.Screens;

import cinema.model.Hall;
import cinema.model.Movie;
import cinema.model.Ticket;
import cinema.model.User;
import GUI.AppColors;
import GUI.Screen;
import GUI.SharedComponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class SingleMovieScreen extends Screen {

    Movie movie;
    ArrayList<String> selectedSeats;
    JPanel mainContainer;
    JButton reserveSeatButton;
    DatePicker selectDate;
    JComboBox showingComboBox;
    public SingleMovieScreen(GUI.Window parentWindow, Screen previousScreen, Movie movie){
        super(parentWindow, previousScreen);
        selectedSeats = new ArrayList<String>();
        this.movie = movie;
        this.setLayout(new BorderLayout());
        JPanel topBar = new AppBar(this);
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setBackground(AppColors.grey);
        sideBar.setBorder(new EmptyBorder(20,20,20,20));

        ImageIcon image = ImageHandler.scaleImage(new ImageIcon(movie.getImageData()), (int)(GUI.Window.width * 0.2));
        JLabel movieTitle = new JLabel(movie.getName());
        movieTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieTitle.setForeground(AppColors.primary);
        movieTitle.setIcon(image);
        movieTitle.setHorizontalTextPosition(JLabel.CENTER);
        movieTitle.setVerticalTextPosition(JLabel.BOTTOM);

        selectDate = new DatePicker("Date:", movie.getStartDate(), movie.getEndDate());

        JPanel showingPicker = new JPanel();
        showingPicker.setLayout(new FlowLayout(FlowLayout.LEFT));
        showingPicker.setBackground(null);
        JLabel showingPickerLabel = new JLabel("Showing:");
        showingPickerLabel.setForeground(AppColors.darkGrey);
        String[] showings = {"1:30", "3:30", "6:30", "8:30", "10:30"};
        showingComboBox = new JComboBox(showings);
        showingPicker.add(showingPickerLabel);
        showingPicker.add(showingComboBox);

        JPanel viewChart = new JPanel();
        viewChart.setBackground(null);
        viewChart.setLayout(new GridBagLayout());
        JButton viewChartButton = new JButton("View Chart");
        viewChartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewChartButton.setBackground(AppColors.primary);
        viewChartButton.setForeground(Color.white);
        viewChartButton.setBorderPainted(false);
        viewChartButton.setOpaque(true);
        viewChartButton.addActionListener(e ->{
            this.refresh();
        });
        viewChart.add(viewChartButton);

        JPanel reserveSeat = new JPanel();
        reserveSeat.setBackground(null);
        reserveSeat.setLayout(new GridBagLayout());
        reserveSeatButton = new JButton("Reserve Seat");
        reserveSeatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reserveSeatButton.setBackground(AppColors.primary);
        reserveSeatButton.setForeground(Color.white);
        reserveSeatButton.setBorderPainted(false);
        reserveSeatButton.setOpaque(true);
        reserveSeatButton.setBackground(AppColors.grey);
        reserveSeatButton.setEnabled(false);
        reserveSeatButton.addActionListener(e ->{
            double totalPrice = 0;
            for(String seat: selectedSeats){
                double price = 0;
                if(seat.charAt(0) == 'A' || seat.charAt(0) == 'C') price = 60;
                else if(seat.charAt(0) == 'B') price = 80;
                else price = 100;
                totalPrice += price;
                Ticket ticket = new Ticket(User.getLoggedInUserId(), movie.getId(), seat, price, selectDate.getDate(), showingComboBox.getSelectedItem().toString());
                try{
                    ticket.save();
                }catch (Exception error){
                   error.printStackTrace();
                }
            }
            this.refresh();
            this.navigateTo(new ViewTicketScreen(this, movie, selectedSeats, totalPrice, selectDate.getDate() , showingComboBox.getSelectedItem().toString()));
        });
        reserveSeat.add(reserveSeatButton);

        sideBar.add(movieTitle);
        sideBar.add(selectDate);
        sideBar.add(showingPicker);
        sideBar.add(viewChart);
        sideBar.add(reserveSeat);

        mainContainer = buildSeatChart(selectDate.getDate(), showingComboBox.getSelectedItem().toString());

        this.add(topBar, BorderLayout.NORTH);
        this.add(sideBar, BorderLayout.WEST);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    public JPanel buildSeatChart(Date choosenDate,String choosenShowing){
        ArrayList<Ticket> reservedSeats = Ticket.getTicketsBy(movie.getId(), choosenDate, choosenShowing);
        int hallSize = Hall.getHallById(movie.getHallId()).getSeatsNumber();
        String[] names = {"A", "B", "C", "D"};
        double[] ratios = {6, 3, 6, 3};

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
                String label = "";
                boolean isReserved = false;
                if(j < 10){
                    label = names[i] + "0" + j;
                }else{
                    label = names[i] + j;
                }
                for(Ticket seat: reservedSeats){
                    if(label.equals(seat.getSeatNumber())){
                       isReserved = true;
                       break;
                    }
                }
                seatsContainers[i].add(new Seat(label, isReserved, (label1, isSelected) -> {
                    if(isSelected){
                        selectedSeats.add(label1);
                    }else{
                        selectedSeats.remove(label1);
                    }

                    if(selectedSeats.size() == 0){
                        reserveSeatButton.setBackground(AppColors.grey);
                        reserveSeatButton.setEnabled(false);
                    }else{
                        reserveSeatButton.setBackground(AppColors.primary);
                        reserveSeatButton.setEnabled(true);
                    }
                }));
            }
        }
        theatreContainer.add(screenDirection, BorderLayout.NORTH);
        theatreContainer.add(parents[0], BorderLayout.WEST);
        theatreContainer.add(parents[1], BorderLayout.CENTER);
        theatreContainer.add(parents[2], BorderLayout.EAST);
        theatreContainer.add(parents[3], BorderLayout.SOUTH);

        mainContainer.add(theatreContainer);
        return mainContainer;
    }

    @Override
    public void refresh() {
        this.remove(mainContainer);
        mainContainer = buildSeatChart(selectDate.getDate(), showingComboBox.getSelectedItem().toString());
        this.add(mainContainer);
        this.revalidate();
        this.repaint();
    }
}
