package controllers;

import models.DonorModel;
import models.loginModel;
import views.login;
import views.DonorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginController implements ActionListener {
    private login loginview;
    private loginModel model;
    private DonorView donorView;

    public loginController(login view, loginModel model) {
        this.loginview = view;
        this.model = model;
        // Add action listeners to view components
        view.getLoginButton().addActionListener(this);
        view.getCloseButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginview.getLoginButton()) {
            String username = loginview.getUsernameField().getText();
            String password = new String(loginview.getPasswordField().getPassword());
            String userType = "";

            if (loginview.getDonorRadioButton().isSelected()) {
                userType = "Donor";
            } else if (loginview.getAdminRadioButton().isSelected()) {
                userType = "Admin";
            }

            if (userType.isEmpty()) {
                JOptionPane.showMessageDialog(loginview, "Please select a user login.");
                return;
            }

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginview, "Please fill the login fields.");
                return;
            }

            if (model.authenticateUser(username, password, userType)) {
                JOptionPane.showMessageDialog(loginview, "Login successful!");
                loginview.setVisible(false);
                DonorView donorView = new DonorView();
                DonorModel donorModel = new DonorModel();
                DonorController donorController = new DonorController(donorView, donorModel);// Show the donorView upon successful login
            } else {
                JOptionPane.showMessageDialog(loginview, "Invalid username or password. Please try again.");
                // Clear fields or handle login failure
            }
        } else if (e.getSource() == loginview.getCloseButton()) {
            // Close button action
        	loginview.dispose(); // Close the login window
        }
    }
}
