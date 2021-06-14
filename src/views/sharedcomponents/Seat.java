package views.sharedcomponents;

import views.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Seat extends JPanel {
    private boolean isSelected;
    private Color background;

    public Seat(String label, boolean isReserved, SeatsActionListener seatsActionListener){
        isSelected = false;
        if(!isReserved){
            background = AppColors.grey;
        }else{
            background = AppColors.red;
        }
        this.setBackground(null);
        this.setBorder(new EmptyBorder(1,1,1,1));
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createLineBorder(Color.black));
        container.setBackground(background);
        JButton seat = new JButton();
        seat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if(!isReserved){
            seat.addActionListener(e ->{
                if(!isSelected){
                    container.setBackground(AppColors.primary);
                    seat.setBackground(AppColors.primary);
                    seat.setForeground(Color.white);
                }else{
                    container.setBackground(background);
                    seat.setBackground(background);
                    seat.setForeground(Color.black);
                }
                isSelected = !isSelected;
                seat.revalidate();
                seat.repaint();
                seatsActionListener.execute(label, isSelected);
            });
        }
        seat.setText(label);
        seat.setBackground(background);
        seat.setOpaque(true);
        seat.setBorder(new EmptyBorder(10,10,10,10));
        container.add(seat);
        this.add(container);
    }
}
