package views.screens.Admin;

import controller.AdminScreenController;
import views.AppColors;
import views.sharedcomponents.LabeledField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddCashierPanel extends Panel {
    LabeledField firstNameField;
    LabeledField lastNameField;
    LabeledField userNameField;
    LabeledField passwordField;
    LabeledField confirmPasswordField;
    JLabel errorLabel;

    public AddCashierPanel(AdminScreenController controller){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JLabel title = new JLabel("Add Cashier");
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

        JPanel whiteContainer = new JPanel();
        errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);
        whiteContainer.setBackground(Color.white);
        whiteContainer.setBorder(new EmptyBorder(20,20,20,20));
        whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
        firstNameField = new LabeledField("First Name:");
        whiteContainer.add(errorLabel);
        whiteContainer.add(firstNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        lastNameField = new LabeledField("Last Name:");
        whiteContainer.add(lastNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        userNameField = new LabeledField("User Name:");
        whiteContainer.add(userNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        passwordField = new LabeledField("Password:");
        whiteContainer.add(passwordField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        confirmPasswordField = new LabeledField("Confirm Password:");
        whiteContainer.add(confirmPasswordField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));

        JButton addMovie = new JButton();
        addMovie.setText("Add Cashier");
        addMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMovie.setForeground(Color.white);
        addMovie.setBackground(AppColors.primary);
        addMovie.setOpaque(true);
        addMovie.setBorderPainted(false);
        addMovie.addActionListener(e -> {
            controller.addCashier();
        });
        whiteContainer.add(addMovie);

        greyContainer.add(whiteContainer);
        mainContainer.add(greyContainer, BorderLayout.CENTER);

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    public String getFirstName() {
        return firstNameField.textField.getText();
    }

    public String getLastName() {
        return lastNameField.textField.getText();
    }

    public String getUserName() {
        return userNameField.textField.getText();
    }

    public String getPassword() {
        return passwordField.textField.getText();
    }

    public String getConfirmPassword() {
        return confirmPasswordField.textField.getText();
    }

    public void blankOutFields(){
        firstNameField.textField.setText("");
        lastNameField.textField.setText("");
        userNameField.textField.setText("");
        passwordField.textField.setText("");
        confirmPasswordField.textField.setText("");
    }

    public void showErrorMessage(String msg){
        errorLabel.setText(msg);
    }

    public void refresh(){
        blankOutFields();
        this.revalidate();
        this.repaint();
    }
}