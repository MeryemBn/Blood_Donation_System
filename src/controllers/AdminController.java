package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.AdminModel;
import models.LoginModel;
import controllers.LoginController;
import views.AdminView;
import views.Login;

public class AdminController implements ActionListener {
    private AdminView adminview;
    private AdminModel model;
    private Login loginview;

    public AdminController(AdminView view, AdminModel model) {
        this.model = model;
        this.adminview = view;

        // Add action listeners to view components
        view.getButton1().addActionListener(this);
        view.getButton2().addActionListener(this);
        view.getButton3().addActionListener(this);
        view.getButton4().addActionListener(this);
        view.getButton5().addActionListener(this);
        view.getCloseButton().addActionListener(this);
        view.getLogoutButton().addActionListener(this);
        view.buttonClicked(view.getButton1());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminview.getButton1()) {
        	adminview.getTabbedPane().setSelectedIndex(0); // Afficher le premier onglet
        	adminview.buttonClicked(adminview.getButton1());
        	adminview.buttonReleased(adminview.getButton2());
        	adminview.buttonReleased(adminview.getButton3());
        	adminview.buttonReleased(adminview.getButton4());
        	adminview.buttonReleased(adminview.getButton5());
        } else if (e.getSource() == adminview.getButton2()) {
        	adminview.getTabbedPane().setSelectedIndex(1); // Afficher le deuxième onglet
        	adminview.buttonClicked(adminview.getButton2());
        	adminview.buttonReleased(adminview.getButton1());
        	adminview.buttonReleased(adminview.getButton3());
        	adminview.buttonReleased(adminview.getButton4());
        	adminview.buttonReleased(adminview.getButton5());
        } else if (e.getSource() == adminview.getButton3()) {
        	adminview.getTabbedPane().setSelectedIndex(2); // Afficher le troisième onglet
        	adminview.buttonClicked(adminview.getButton3());
        	adminview.buttonReleased(adminview.getButton2());
        	adminview.buttonReleased(adminview.getButton1());
        	adminview.buttonReleased(adminview.getButton4());
        	adminview.buttonReleased(adminview.getButton5());
        }else if (e.getSource() == adminview.getButton4()) {
        	adminview.getTabbedPane().setSelectedIndex(3); // Afficher le troisième onglet
        	adminview.buttonClicked(adminview.getButton4());
        	adminview.buttonReleased(adminview.getButton2());
        	adminview.buttonReleased(adminview.getButton1());
        	adminview.buttonReleased(adminview.getButton3());
        	adminview.buttonReleased(adminview.getButton5());
        }else if (e.getSource() == adminview.getButton5()) {
        	adminview.getTabbedPane().setSelectedIndex(4); // Afficher le troisième onglet
        	adminview.buttonClicked(adminview.getButton5());
        	adminview.buttonReleased(adminview.getButton2());
        	adminview.buttonReleased(adminview.getButton1());
        	adminview.buttonReleased(adminview.getButton4());
        	adminview.buttonReleased(adminview.getButton3());
        }
        else if (e.getSource() == adminview.getCloseButton()) {
            System.exit(0);
        } else if (e.getSource() == adminview.getLogoutButton()) {
        	int option = JOptionPane.showConfirmDialog(adminview, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
        	if (option == JOptionPane.YES_OPTION) {
				// Dispose of or hide the DonorView
			    adminview.AdminViewSetInvisible();
			    adminview = null;

			    // Show the Login view
			    Login loginV = new Login();
			    LoginModel loginM = new LoginModel();
			    new LoginController(loginV, loginM);	        
			}
        }
    }

}
