package views;

import javax.swing.*;

public abstract class Screen extends JPanel {
    public Window parentWindow;
    public Screen previousScreen;

    public Screen(views.Window parentWindow, Screen previousScreen){
        this.parentWindow = parentWindow;
        this.previousScreen = previousScreen;
    }

    public void navigateTo(Screen newScreen){
       parentWindow.swap(this, newScreen);
    }

    public abstract void refresh();
}
