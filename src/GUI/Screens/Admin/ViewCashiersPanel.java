package GUI.Screens.Admin;

import Cinema.Objects.User;
import GUI.AppColors;
import GUI.SharedComponents.SingleRow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ViewCashiersPanel extends JPanel {

    ArrayList<User> cashiers;
    JPanel mainContainer;

    public ViewCashiersPanel(){
        cashiers = User.getCashiers();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JLabel title = new JLabel("View Cashiers");
        title.setForeground(AppColors.primary);
        title.setFont(new Font("Arial", Font.PLAIN, 18));
        title.setBorder(new EmptyBorder(20,20,0,20));

        mainContainer = buildMainContainer();

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    private JPanel buildMainContainer(){
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(null);
        mainContainer.setBorder(new EmptyBorder(20,20,20,20));

        JPanel scrollContainer = new JPanel();
        GridLayout grid = new GridLayout(0,1);
        grid.setVgap(8);
        scrollContainer.setLayout(grid);
        scrollContainer.setBorder(new EmptyBorder(10,10,10,10));
        scrollContainer.setBackground(AppColors.grey);

        for(User cashier : cashiers){
            JLabel titleLabel = new JLabel(String.valueOf(cashier.id));
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
                this.cashiers = User.getCashiers();
                this.remove(this.mainContainer);
                this.mainContainer = buildMainContainer();
                this.add(this.mainContainer, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            });
            buttonContainer.add(deleteBtn);
            JComponent[] data = {titleLabel,new JLabel(cashier.firstName), buttonContainer};
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
        mainContainer.add(scroll, BorderLayout.CENTER);
        return mainContainer;
    }

}
