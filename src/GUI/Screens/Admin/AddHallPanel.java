package GUI.Screens.Admin;

import cinema.exceptions.BlankDataEnteredException;
import cinema.model.Hall;
import GUI.AppColors;
import GUI.SharedComponents.LabeledField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddHallPanel extends JPanel {

    public AddHallPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JLabel title = new JLabel("Add Hall");
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
        LabeledField nameField = new LabeledField("Hall Name:");
        whiteContainer.add(errorLabel);
        whiteContainer.add(nameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField descriptionField = new LabeledField("Number of Seats:");
        whiteContainer.add(descriptionField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));

        JButton addMovie = new JButton();
        addMovie.setText("Add Hall");
        addMovie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMovie.setForeground(Color.white);
        addMovie.setBackground(AppColors.primary);
        addMovie.setOpaque(true);
        addMovie.setBorderPainted(false);
        addMovie.addActionListener(e -> {
            try {
                Hall currentHall = new Hall(nameField.textField.getText(), Integer.parseInt(descriptionField.textField.getText()));
                currentHall.save();
                errorLabel.setText("");
            }catch (BlankDataEnteredException error){
                errorLabel.setText(error.getMessage());
            }catch (NumberFormatException error){
                errorLabel.setText("Seats Number must be a Number");
            }
            nameField.textField.setText("");
            descriptionField.textField.setText("");
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
