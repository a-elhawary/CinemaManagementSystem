package views.sharedcomponents;

import views.AppColors;

import javax.swing.*;

public class LabeledField extends JPanel {
    public JTextField textField;
    public JLabel label;

    public LabeledField(String labelText){
       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        textField = new JTextField("", 15);
        label = new JLabel(labelText);
        label.setForeground(AppColors.darkGrey);
        this.add(label);
        this.add(textField);
        this.setBackground(null);
    }
}
