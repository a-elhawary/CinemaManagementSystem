package views.screens;

import controller.HomeScreenController;
import views.Screen;
import views.sharedcomponents.AppBar;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends Screen {

    JPanel mainContent;

    HomeScreenController controller;

   public HomeScreen(views.Window parentWindow, Screen previousScreen){
       super(parentWindow, previousScreen);
       this.setLayout(new BorderLayout());
       controller = new HomeScreenController(this);

       JPanel topBar = new AppBar(this);

       mainContent = controller.buildList();

       this.add(topBar, BorderLayout.NORTH);
       this.add(mainContent, BorderLayout.CENTER);
   }



   public void refresh(){
       this.remove(mainContent);
       mainContent = controller.buildList();
       this.add(mainContent, BorderLayout.CENTER);
       this.revalidate();
       this.repaint();
   }
}


