package views;
import views.screens.LoginScreen;

import javax.swing.JFrame;

public class Window  extends JFrame{
    public static int width = 1200;
    public static int height = 720;

    public Window(){
        super();
        this.setTitle("Cinema Management System");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(new LoginScreen(this, null));
        this.setVisible(true);
    }

    public void swap(Screen from, Screen to){
        this.remove(from);
        this.add(to);
        this.revalidate();
        this.repaint();
    }

    public static void main(String args[]){
        new Window();
    }

}
