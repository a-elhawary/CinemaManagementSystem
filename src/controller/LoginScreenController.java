package controller;

import enums.Level;
import model.User;
import views.screens.HomeScreen;
import views.screens.LoginScreen;

public class LoginScreenController {
    User user;
    LoginScreen loginScreen;

    public LoginScreenController(LoginScreen view){
       this.loginScreen = view;
    }

    public void login(){
        user = new User(loginScreen.getUsername(), loginScreen.getPassword());
        try {
            user.login();
            loginScreen.navigateTo(new HomeScreen(loginScreen.parentWindow, null));
        }catch (Exception error){
            loginScreen.showErrorMessage(error.getMessage());
        }
    }

    public void register(){
        user = new User(loginScreen.getFirstName(), loginScreen.getLastName(), loginScreen.getUsername(), loginScreen.getPassword(), loginScreen.getConfirmPassword());
        try{
            user.register(Level.CUSTOMER);
            loginScreen.navigateTo(new HomeScreen(loginScreen.parentWindow, null));
        }catch(Exception error){
            loginScreen.showErrorMessage(error.getMessage());
        }
    }
}
