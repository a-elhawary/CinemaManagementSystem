package controller;

import enums.Level;
import exceptions.BlankDataEnteredException;
import model.Hall;
import model.Movie;
import model.User;
import views.AppColors;
import views.screens.Admin.*;
import views.sharedcomponents.SingleRow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;

public class AdminScreenController {
    AdminScreen screen;
    ArrayList<Hall> halls;

    public AdminScreenController(AdminScreen screen){
        this.screen = screen;
        halls = Hall.getHalls();
    }

    public String[] getHallNames(){
        halls = Hall.getHalls();
        String[] hallNames = new String[halls.size()];
        for(int i = 0; i < halls.size(); i++){
            hallNames[i] = halls.get(i).getName();
        }
        return hallNames;
    }

    public void addMovie(){
        AddMoviesPanel panel = (AddMoviesPanel)  screen.getPanel();
        Movie currentMovie = new Movie(panel.getMovieName(), panel.getDescription(), panel.getSelectedFile(),panel.getStartDate(), panel.getEndDate(), halls.get(panel.getSelectedHallIndex()).getId());
        try{
            currentMovie.save();
            panel.showErrorMessage("");
            panel.blankOutFields();
        }catch (Exception error){
            panel.showErrorMessage(error.getMessage());
        }
    }

    public void addHall(){
        AddHallPanel panel = (AddHallPanel)screen.getPanel();
        try {
            Hall currentHall = new Hall(panel.getHallName(), Integer.parseInt(panel.getSeatsNumber()));
            currentHall.save();
            panel.showErrorMessage("");
        }catch (BlankDataEnteredException error){
            panel.showErrorMessage(error.getMessage());
        }catch (NumberFormatException error){
            panel.showErrorMessage("Seats Number must be a Number");
        }
        panel.blankOutFields();
        panel.revalidate();
        panel.repaint();
    }

    public void addCashier(){
        AddCashierPanel panel = (AddCashierPanel) screen.getPanel();
        User user = new User(panel.getFirstName(), panel.getLastName(),panel.getUserName(),panel.getPassword(),panel.getConfirmPassword());
        try{
            user.register(Level.CASHIER);
            panel.showErrorMessage("");
        }catch (Exception error){
            panel.showErrorMessage(error.getMessage());
        }
        panel.blankOutFields();
        panel.revalidate();
        panel.repaint();
    }

    public JScrollPane generateCashiersList(ViewCashiersPanel panel){
        ArrayList<User> cashiers = User.getCashiers();
        JPanel scrollContainer = new JPanel();
        GridLayout grid = new GridLayout(0,1);
        grid.setVgap(8);
        scrollContainer.setLayout(grid);
        scrollContainer.setBorder(new EmptyBorder(10,10,10,10));
        scrollContainer.setBackground(AppColors.grey);

        for(User cashier : cashiers){
            JLabel titleLabel = new JLabel(String.valueOf(cashier.getId()));
            titleLabel.setForeground(AppColors.primary);
            titleLabel.setBorder(new EmptyBorder(0,10,0,0));

            JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonContainer.setBackground(null);
            JButton deleteBtn = new JButton("Delete");
            deleteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            deleteBtn.setForeground(Color.white);
            deleteBtn.setBackground(AppColors.red);
            deleteBtn.setOpaque(true);
            deleteBtn.setBorderPainted(false);
            deleteBtn.addActionListener(e ->{
                cashier.delete();
                panel.remove(panel.getMainContainer());
                panel.setMainContainer(panel.buildMainContainer());
                panel.add(panel.getMainContainer(), BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
            });
            buttonContainer.add(deleteBtn);
            JComponent[] data = {titleLabel,new JLabel(cashier.getFirstName()), new JLabel(cashier.getLastName()), new JLabel(cashier.getUserName()), buttonContainer};
            scrollContainer.add(new SingleRow(data));
        }

        JScrollPane scroll;
        if(cashiers.size() < 10){
            scroll = new JScrollPane(scrollContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            for(int i = 0; i < 20; i++){
                scrollContainer.add(Box.createRigidArea(new Dimension(1,1)));
            }
        }else{
            scroll = new JScrollPane(scrollContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
        scroll.setBackground(null);
        scroll.setBorder(null);
        return scroll;
    }

}
