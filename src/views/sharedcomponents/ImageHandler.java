package views.sharedcomponents;

import javax.swing.*;
import java.awt.*;

public class ImageHandler {
    public static ImageIcon scaleImage(ImageIcon image, int targetWidth){
        int height = image.getIconHeight();
        int width = image.getIconWidth();

        int newHieght = targetWidth * height / width;
        return new ImageIcon(image.getImage().getScaledInstance(targetWidth, newHieght, Image.SCALE_DEFAULT));
    }
}
