package controllers;

import models.LoginModel;
import views.Login;
import views.DonorView;
import views.Acceuil;
import views.AdminView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
	private Login loginview;
	private LoginModel model;
	private DonorView donorView;
	public static int donorId;

	public LoginController(Login view, LoginModel model) {
		this.loginview = view;
		this.model = model;
		// Add action listeners to view components
		view.getLoginButton().addActionListener(this);
		view.getCloseButton().addActionListener(this);
		view.getBackButton().addActionListener(this);
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
				loginview.setVisible(false);
				if (userType.equals("Admin")) {
					// If admin login is successful, create AdminView and AdminController
					AdminView adminView = new AdminView();
					adminView.AdminViewSetVisible();
					AdminController adminController = new AdminController(adminView);
				} else if (userType.equals("Donor")) {
					// If donor login is successful, create DonorView and DonorController
					donorId = model.getDonorId(username, password);
					DonorView donorView = new DonorView(username);
					donorView.DonorViewSetVisible();
					DonorController donorController = new DonorController(donorView);
				}
			} else {
				JOptionPane.showMessageDialog(loginview, "Invalid username or password. Please try again.");
				
			}
		} else if (e.getSource() == loginview.getCloseButton()) {
			loginview.dispose(); 
		} else if (e.getSource() == loginview.getBackButton()) {
			Acceuil acc = new Acceuil();
			acc.setVisible();
			loginview.dispose();
			AcceuilController accController = new AcceuilController(acc);
		}
	}
}