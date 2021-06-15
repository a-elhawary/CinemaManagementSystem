package views.screens.Admin;

import controller.AdminScreenController;
import views.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewCashiersPanel extends Panel {

    JPanel mainContainer;
    AdminScreenController controller;

    public ViewCashiersPanel(AdminScreenController controller){
        this.setLayout(new BorderLayout());
        this.controller = controller;
        this.setBackground(Color.white);
        JLabel title = new JLabel("View Cashiers");
        title.setForeground(AppColors.primary);
        title.setFont(new Font("Arial", Font.PLAIN, 18));
        title.setBorder(new EmptyBorder(20,20,0,20));

        mainContainer = buildMainContainer();

        this.add(title, BorderLayout.NORTH);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    public JPanel buildMainContainer(){
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(null);
        mainContainer.setBorder(new EmptyBorder(20,20,20,20));
        mainContainer.add(controller.generateCashiersList(this), BorderLayout.CENTER);
        return mainContainer;
    }

    public JPanel getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(JPanel mainContainer) {
        this.mainContainer = mainContainer;
    }

    public void refresh(){
        this.remove(mainContainer);
        mainContainer = buildMainContainer();
        this.add(mainContainer);
        this.revalidate();
        this.repaint();
    }
}
