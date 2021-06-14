package views.screens.Admin;

import controller.AdminScreenController;
import views.AppColors;
import views.Screen;
import views.sharedcomponents.AppBar;
import views.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen extends Screen {
    JPanel panels[];
    AdminScreenController controller;
    int selectedIndex = 0;
    public AdminScreen(Window parentWindow, Screen previousScreen) {
        super(parentWindow, previousScreen);
        controller = new AdminScreenController(this);
        JPanel tmp[] = {new ViewMoviesPanel(controller), new AddMoviesPanel(controller),new ViewCashiersPanel(controller), new AddCashierPanel(controller), new ViewHallsPanel(controller), new AddHallPanel(controller), new RequestReport(controller)};
        panels = tmp;
        String actions[] = {"View Movies", "Add Movie", "View Cashiers", "Add Cashier", "View Halls", "Add Hall", "Request Report"};

        this.setLayout(new BorderLayout());

        JPanel sideBar = new JPanel();
        sideBar.setBackground(AppColors.grey);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(panels[0]);

        JPanel sideBarElements = new JPanel();
        sideBarElements.setLayout(new BoxLayout(sideBarElements, BoxLayout.Y_AXIS));
        sideBarElements.setBackground(null);

        JLabel title = new JLabel("Admin Functions");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setBackground(AppColors.darkGrey);
        GridLayout grid = new GridLayout(0,1);
        grid.setVgap(1);
        buttonsContainer.setLayout(grid);
        buttonsContainer.setBorder(new EmptyBorder(1,0,1,0));
        EmptyBorder buttonsBorder = new EmptyBorder(10,40,10,40);

        for(int i = 0; i < actions.length; i++){
            JButton temp = new JButton(actions[i]);
            temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            temp.setForeground(AppColors.primary);
            temp.setBackground(AppColors.grey);
            temp.setOpaque(true);
            temp.setBorder(buttonsBorder);
            temp.setBorderPainted(false);
            temp.addActionListener(new ChangePanelActionListener(i, this, mainContainer, panels[i]));
            buttonsContainer.add(temp);
        }

        sideBarElements.add(Box.createRigidArea(new Dimension(0,20)));
        sideBarElements.add(title);
        sideBarElements.add(Box.createRigidArea(new Dimension(0,20)));
        sideBarElements.add(buttonsContainer);

        sideBar.add(sideBarElements);

        this.add(new AppBar(this), BorderLayout.NORTH);
        this.add(sideBar, BorderLayout.WEST);
        this.add(mainContainer, BorderLayout.CENTER);
    }

    public void setSelectedIndex(int selectedIndex){
        this.selectedIndex = selectedIndex;
    }

    @Override
    public void refresh() {
    }

    public JPanel getPanel(){
        return panels[selectedIndex];
    }
}


class ChangePanelActionListener implements ActionListener {
    private final JPanel panel;
    private final JPanel container;
    private final AdminScreen screen;
    private final int index;

    public ChangePanelActionListener(int index, AdminScreen screen, JPanel container, JPanel panel){
        this.index = index;
        this.screen = screen;
        this.panel = panel;
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       this.container.removeAll();
       this.screen.setSelectedIndex(index);
       this.container.add(panel);
       this.container.revalidate();
       this.container.repaint();
    }
}
