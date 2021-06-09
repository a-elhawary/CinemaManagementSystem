package GUI.Screens.Admin;

import Cinema.Objects.Movie;
import GUI.AppColors;
import GUI.SharedComponents.LabeledField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AddMoviesPanel extends JPanel {
    File selectedFile;

    public AddMoviesPanel(){
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

        JPanel fileChooserContainer = new JPanel();
        fileChooserContainer.setBackground(null);
        fileChooserContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));

        JButton chooseFile = new JButton();
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

        JLabel label = new JLabel("Image: ");
        label.setForeground(AppColors.darkGrey);
        fileChooserContainer.add(label);
        fileChooserContainer.add(chooseFile);

        JPanel whiteContainer = new JPanel();
        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);
        whiteContainer.setBackground(Color.white);
        whiteContainer.setBorder(new EmptyBorder(20,20,20,20));
        whiteContainer.setLayout(new BoxLayout(whiteContainer, BoxLayout.Y_AXIS));
        LabeledField nameField = new LabeledField("Movie Name:");
        whiteContainer.add(errorLabel);
        whiteContainer.add(nameField);
        whiteContainer.add(Box.createRigidArea(new Dimension(0,10)));
        LabeledField descriptionField = new LabeledField("Description:");
        whiteContainer.add(descriptionField);
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
            Movie currentMovie = new Movie(nameField.textField.getText(), descriptionField.textField.getText(), selectedFile);
            if(!currentMovie.save()){
               errorLabel.setText("Please enter all Data");
            }else{
                errorLabel.setText("");
            }
            nameField.textField.setText("");
            descriptionField.textField.setText("");
            selectedFile = null;
            fileChooserContainer.removeAll();
            fileChooserContainer.add(label);
            fileChooserContainer.add(chooseFile);
            fileChooserContainer.revalidate();
            fileChooserContainer.repaint();
        });
        whiteContainer.add(addMovie);

        greyContainer.add(whiteContainer);
        mainContainer.add(greyContainer, BorderLayout.CENTER);

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }
}
