package application;

import views.login;
import views.DonorView;
import models.loginModel;
import models.DonorModel;
import controllers.loginController;
import controllers.DonorController;

public class main {
    public static void main(String[] args) {
        // Create the login model
        loginModel loginModel = new loginModel();


        // Create the login view
        login loginView = new login();

        // Create the login controller
        loginController loginController = new loginController(loginView, loginModel);

        // Create the donor controller
        // Display the login view
        loginView.setVisible(true);   
    }
}
