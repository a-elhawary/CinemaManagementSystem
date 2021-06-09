package GUI;

import javax.swing.*;

public class Screen extends JPanel {
    public Window parentWindow;
    public Screen previousScreen;

    public Screen(GUI.Window parentWindow, Screen previousScreen){
        this.parentWindow = parentWindow;
        this.previousScreen = previousScreen;
    }

    public void navigateTo(Screen newScreen){
       parentWindow.swap(this, newScreen);
    }
}