package GUI.SharedComponents;

import GUI.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Seat extends JPanel {
    private boolean isSelected;

    public Seat(String label){
        isSelected = false;
        this.setBackground(null);
        this.setBorder(new EmptyBorder(1,1,1,1));
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createLineBorder(Color.black));
        container.setBackground(AppColors.grey);
        JButton seat = new JButton();
        seat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        seat.addActionListener(e ->{
            if(isSelected == false){
               container.setBackground(AppColors.primary);
               seat.setBackground(AppColors.primary);
               seat.setForeground(Color.white);
            }else{
                container.setBackground(AppColors.grey);
                seat.setBackground(AppColors.grey);
                seat.setForeground(Color.black);
            }
            isSelected = !isSelected;
            seat.revalidate();
            seat.repaint();
        });
        seat.setText(label);
        seat.setBackground(AppColors.grey);
        seat.setOpaque(true);
        seat.setBorder(new EmptyBorder(10,10,10,10));
        container.add(seat);
        this.add(container);
    }
}
