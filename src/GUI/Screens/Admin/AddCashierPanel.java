package GUI.Screens.Admin;

import Cinema.Objects.Level;
import Cinema.Objects.User;
import GUI.AppColors;
import GUI.SharedComponents.LabeledField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddCashierPanel extends JPanel {

    public AddCashierPanel(){
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
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);
        whiteContainer.setBackground(Color.white);
        whiteContainer.setBorder(new EmptyBorder(20,20,20,20));
        whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
        LabeledField firstNameField = new LabeledField("First Name:");
        whiteContainer.add(errorLabel);
        whiteContainer.add(firstNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField lastNameField = new LabeledField("Last Name:");
        whiteContainer.add(lastNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField userNameField = new LabeledField("User Name:");
        whiteContainer.add(userNameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField passwordField = new LabeledField("Password:");
        whiteContainer.add(passwordField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField confirmPasswordField = new LabeledField("Confirm Password:");
        whiteContainer.add(confirmPasswordField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));

        JButton addMovie = new JButton();
        addMovie.setText("Add Hall");
        addMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMovie.setForeground(Color.white);
        addMovie.setBackground(AppColors.primary);
        addMovie.setOpaque(true);
        addMovie.setBorderPainted(false);
        addMovie.addActionListener(e -> {
            User user = new User(firstNameField.textField.getText(), lastNameField.textField.getText(), userNameField.textField.getText(), passwordField.textField.getText(), confirmPasswordField.textField.getText());
            errorLabel.setText(user.register(Level.CASHIER));
            firstNameField.textField.setText("");
            lastNameField.textField.setText("");
            userNameField.textField.setText("");
            passwordField.textField.setText("");
            confirmPasswordField.textField.setText("");
            this.revalidate();
            this.repaint();
        });
        whiteContainer.add(addMovie);

        greyContainer.add(whiteContainer);
        mainContainer.add(greyContainer, BorderLayout.CENTER);

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }
}