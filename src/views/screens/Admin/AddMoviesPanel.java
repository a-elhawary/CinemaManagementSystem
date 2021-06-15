package views.screens.Admin;

import controller.AdminScreenController;
import model.Hall;
import helper.Months;
import model.Movie;
import views.AppColors;
import views.sharedcomponents.DatePicker;
import views.sharedcomponents.LabeledField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.sql.Date;

public class AddMoviesPanel extends Panel {
    File selectedFile;
    JLabel errorLabel;
    DatePicker startDate;
    DatePicker endDate;
    JComboBox comboBox;
    JPanel fileChooserContainer;
    JLabel label;
    JButton chooseFile;
    LabeledField descriptionField;
    LabeledField nameField;
    AdminScreenController controller;

    public AddMoviesPanel(AdminScreenController controller){
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        JLabel title = new JLabel("Add Movie");
        title.setForeground(AppColors.primary);
        title.setFont(new Font("Arial", Font.PLAIN, 18));
        title.setBorder(new EmptyBorder(20,20,0,20));

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(null);
        mainContainer.setBorder(new EmptyBorder(20,20,20,20));

        JPanel greyContainer = new JPanel();
        greyContainer.setLayout(new GridBagLayout());
        greyContainer.setBackground(AppColors.grey);

        fileChooserContainer = new JPanel();
        fileChooserContainer.setBackground(null);
        fileChooserContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));

        chooseFile = new JButton();
        chooseFile.setText("Choose a File");
        chooseFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chooseFile.setForeground(Color.white);
        chooseFile.setBackground(AppColors.primary);
        chooseFile.setOpaque(true);
        chooseFile.setBorderPainted(false);
        chooseFile.addActionListener(e ->{
            if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
               fileChooserContainer.remove(chooseFile);
               selectedFile = fileChooser.getSelectedFile();
               fileChooserContainer.add(new JLabel(selectedFile.getName()));
               fileChooserContainer.revalidate();
               fileChooserContainer.repaint();
            }
        });

        label = new JLabel("Image: ");
        label.setForeground(AppColors.darkGrey);
        fileChooserContainer.add(label);
        fileChooserContainer.add(chooseFile);

        JPanel chooseHallContainer = new JPanel();
        chooseHallContainer.setBackground(null);
        chooseHallContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboBox = new JComboBox(controller.getHallNames());
        JLabel chooseHallLabel = new JLabel("Choose Hall:");
        chooseHallLabel.setForeground(AppColors.darkGrey);
        chooseHallContainer.add(chooseHallLabel);
        chooseHallContainer.add(comboBox);

        startDate = new DatePicker("Start Date:");
        endDate = new DatePicker("End Date:");

        JPanel whiteContainer = new JPanel();
        errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);
        whiteContainer.setBackground(Color.white);
        whiteContainer.setBorder(new EmptyBorder(20,20,20,20));
        whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
        nameField = new LabeledField("Movie Name:");
        whiteContainer.add(errorLabel);
        whiteContainer.add(nameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        descriptionField = new LabeledField("Description:");
        whiteContainer.add(descriptionField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        whiteContainer.add(chooseHallContainer);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        whiteContainer.add(startDate);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        whiteContainer.add(endDate);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        whiteContainer.add(fileChooserContainer);

        JButton addMovie = new JButton();
        addMovie.setText("Add Movie");
        addMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMovie.setForeground(Color.white);
        addMovie.setBackground(AppColors.primary);
        addMovie.setOpaque(true);
        addMovie.setBorderPainted(false);
        addMovie.addActionListener(e -> {
            controller.addMovie();
        });
        whiteContainer.add(addMovie);

        greyContainer.add(whiteContainer);
        mainContainer.add(greyContainer, BorderLayout.CENTER);

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    public String getMovieName(){
        return nameField.textField.getText();
    }

    public String getDescription(){
        return descriptionField.textField.getText();
    }

    public File getSelectedFile(){
        return selectedFile;
    }

    public Date getStartDate(){
       return startDate.getDate();
    }

    public Date getEndDate(){
        return endDate.getDate();
    }

    public int getSelectedHallIndex(){
        return comboBox.getSelectedIndex();
    }


    public void blankOutFields(){
        selectedFile = null;
        nameField.textField.setText("");
        descriptionField.textField.setText("");
        comboBox.setSelectedIndex(0);
        startDate.reset();
        endDate.reset();
        fileChooserContainer.removeAll();
        fileChooserContainer.add(label);
        fileChooserContainer.add(chooseFile);
        fileChooserContainer.revalidate();
        fileChooserContainer.repaint();
    }

    public void showErrorMessage(String msg){
       errorLabel.setText(msg);
    }

    public void refresh(){
        comboBox.removeAllItems();
        for(String hall: controller.getHallNames()){
            comboBox.addItem(hall);
        }
        comboBox.revalidate();
        comboBox.repaint();
        blankOutFields();
        this.revalidate();
        this.repaint();
    }
}

