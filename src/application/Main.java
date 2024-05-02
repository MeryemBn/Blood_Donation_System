package application;

import views.Login;

import views.DonorView;
import models.LoginModel;
import controllers.LoginController;
import controllers.DonorController;

public class Main {
    public static void main(String[] args) {
        // Create the login model
        LoginModel loginModel = new LoginModel();

        // Create the login view
        Login loginView = new Login();

        // Create the login controller
        LoginController loginController = new LoginController(loginView, loginModel);

        // Display the login view
        loginView.setVisible(true);   
    }
}
