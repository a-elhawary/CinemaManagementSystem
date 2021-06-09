package GUI.SharedComponents;

import GUI.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SingleRow extends JPanel {
    public SingleRow(JComponent elements[]){
        this.setLayout(new GridLayout(1, 0));
        this.setBackground(Color.white);
        for(JComponent element: elements){
            this.add(element);
        }
    }
}
