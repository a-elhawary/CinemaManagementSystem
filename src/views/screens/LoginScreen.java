package views.screens;

import controller.LoginScreenController;
import views.AppColors;
import views.Screen;
import views.sharedcomponents.LabeledField;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen extends Screen {

    LoginScreenController controller;

    JLabel errorLabel;

    LabeledField userNameField;
    LabeledField passwordField;

    LabeledField firstNameField;
    LabeledField lastNameField;
    LabeledField registerUserNameField;
    LabeledField registerPasswordField;
    LabeledField registerConfirmPasswordField;

    boolean isLogin = true;

    public LoginScreen(views.Window parentWindow, Screen previousScreen){
        super(parentWindow, previousScreen);
        controller = new LoginScreenController(this);
        this.setLayout(new BorderLayout());

        EmptyBorder border = new EmptyBorder(30,30, 30,30);
        JLabel label = new JLabel("<html>Cinema<br/>Management<br/>System</html>");
        label.setBackground(AppColors.primary);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBorder(border);
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(AppColors.grey);

        mainPanel.add(buildLogin(mainPanel));

        this.add(label, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    private JPanel buildLogin(JPanel parent){
        EmptyBorder border = new EmptyBorder(30,30, 30,30);
        JPanel loginContainer = new JPanel();
        loginContainer.setBackground(Color.white);
        loginContainer.setLayout(new BoxLayout(loginContainer, BoxLayout.Y_AXIS));
        loginContainer.setBorder(border);

        errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);

        userNameField = new LabeledField("Username:");
        userNameField.label.setForeground(AppColors.darkGrey);

        passwordField = new LabeledField("Password:");
        passwordField.label.setForeground(AppColors.darkGrey);

        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(e ->{
            controller.login();
        });
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(AppColors.primary);
        loginButton.setForeground(Color.white);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JPanel bottomContainer = new JPanel();
        bottomContainer.setBackground(null);
        bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));
        JButton switchToSignUp = new JButton("Sign Up");
        switchToSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switchToSignUp.setBorder(new EmptyBorder(0,5,0,0));
        switchToSignUp.setForeground(new Color(0x218ed1));
        switchToSignUp.setBorderPainted(false);
        switchToSignUp.addActionListener(e -> {
            isLogin = false;
            parent.removeAll();
            parent.add(buildRegister(parent));
            parent.revalidate();
            parent.repaint();
        });
        bottomContainer.add(new JLabel("or"));
        bottomContainer.add(switchToSignUp);

        loginContainer.add(errorLabel);
        loginContainer.add(Box.createRigidArea(new Dimension(0,10)));
        loginContainer.add(userNameField);
        loginContainer.add(Box.createRigidArea(new Dimension(0,10)));
        loginContainer.add(passwordField);
        loginContainer.add(Box.createRigidArea(new Dimension(0,15)));
        loginContainer.add(loginButton);
        loginContainer.add(Box.createRigidArea(new Dimension(0,3)));
        loginContainer.add(bottomContainer);
        return loginContainer;
    }

    private JPanel buildRegister(JPanel parent){
        EmptyBorder border = new EmptyBorder(30,30, 30,30);
        JPanel registerContainer = new JPanel();
        registerContainer.setBackground(Color.white);
        registerContainer.setLayout(new BoxLayout(registerContainer, BoxLayout.Y_AXIS));
        registerContainer.setBorder(border);

        errorLabel = new JLabel();
        errorLabel.setForeground(AppColors.red);
        firstNameField = new LabeledField("First Name: ");
        lastNameField = new LabeledField("Last Name: ");
        registerUserNameField = new LabeledField("UserName: ");
        registerPasswordField = new LabeledField("Password: ");
        registerConfirmPasswordField = new LabeledField("Confirm Pass:");

        firstNameField.label.setForeground(AppColors.darkGrey);
        lastNameField.label.setForeground(AppColors.darkGrey);
        registerUserNameField.label.setForeground(AppColors.darkGrey);
        registerPasswordField.label.setForeground(AppColors.darkGrey);
        registerConfirmPasswordField.label.setForeground(AppColors.darkGrey);

        JButton registerButton = new JButton();
        registerButton.setText("Sign Up");
        registerButton.addActionListener(e ->{
          controller.register();
        });
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(AppColors.primary);
        registerButton.setForeground(Color.white);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JPanel bottomContainerRegister = new JPanel();
        bottomContainerRegister.setBackground(null);
        bottomContainerRegister.setLayout(new BoxLayout(bottomContainerRegister, BoxLayout.X_AXIS));
        JButton switchToLogin = new JButton("Login");
        switchToLogin.addActionListener(e ->{
            isLogin = true;
            parent.removeAll();
            parent.add(buildLogin(parent));
            parent.revalidate();
            parent.repaint();
        });
        switchToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switchToLogin.setBorder(new EmptyBorder(0,5,0,0));
        switchToLogin.setForeground(new Color(0x218ed1));
        switchToLogin.setBorderPainted(false);
        bottomContainerRegister.add(new JLabel("or"));
        bottomContainerRegister.add(switchToLogin);

        registerContainer.add(errorLabel);
        registerContainer.add(Box.createRigidArea(new Dimension(0,10)));
        registerContainer.add(firstNameField);
        registerContainer.add(Box.createRigidArea(new Dimension(0,10)));
        registerContainer.add(lastNameField);
        registerContainer.add(Box.createRigidArea(new Dimension(0,10)));
        registerContainer.add(registerUserNameField);
        registerContainer.add(Box.createRigidArea(new Dimension(0,10)));
        registerContainer.add(registerPasswordField);
        registerContainer.add(Box.createRigidArea(new Dimension(0,10)));
        registerContainer.add(registerConfirmPasswordField);
        registerContainer.add(Box.createRigidArea(new Dimension(0,15)));
        registerContainer.add(registerButton);
        registerContainer.add(Box.createRigidArea(new Dimension(0,3)));
        registerContainer.add(bottomContainerRegister);
        return registerContainer;
    }

    public void showErrorMessage(String msg){
        this.errorLabel.setText(msg);
    }

    public String getUsername(){
        if(isLogin){
            return userNameField.textField.getText();
        }else{
            return registerUserNameField.textField.getText();
        }
    }

    public String getFirstName(){
       return firstNameField.textField.getText();
    }

    public String getLastName(){
        return lastNameField.textField.getText();
    }

    public String getPassword(){
        if(isLogin){
            return passwordField.textField.getText();
        }else{
            return registerPasswordField.textField.getText();
        }
    }

    public String getConfirmPassword(){
        return registerConfirmPasswordField.textField.getText();
    }
}
