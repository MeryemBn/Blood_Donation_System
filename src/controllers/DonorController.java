package controllers;

import models.DonorModel;
import models.loginModel;
import views.login;
import views.DonorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DonorController implements ActionListener {
    private DonorView donorview;
    private DonorModel model;
    private login loginview;

    public DonorController(DonorView view, DonorModel model) {
        this.model = model;
        this.donorview = view;

        // Add action listeners to view components
        view.getButton1().addActionListener(this);
        view.getButton2().addActionListener(this);
        view.getButton3().addActionListener(this);
        view.getCloseButton().addActionListener(this);
        view.getLogoutButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == donorview.getButton1()) {
        	donorview.getTabbedPane().setSelectedIndex(0); // Afficher le premier onglet
        	donorview.buttonClicked(donorview.getButton1());
        	donorview.buttonReleased(donorview.getButton2());
        	donorview.buttonReleased(donorview.getButton3());
        } else if (e.getSource() == donorview.getButton2()) {
        	donorview.getTabbedPane().setSelectedIndex(1); // Afficher le deuxième onglet
        	donorview.buttonClicked(donorview.getButton2());
        	donorview.buttonReleased(donorview.getButton1());
        	donorview.buttonReleased(donorview.getButton3());
        } else if (e.getSource() == donorview.getButton3()) {
        	donorview.getTabbedPane().setSelectedIndex(2); // Afficher le troisième onglet
        	donorview.buttonClicked(donorview.getButton3());
            donorview.buttonReleased(donorview.getButton2());
            donorview.buttonReleased(donorview.getButton1());
        } else if (e.getSource() == donorview.getCloseButton()) {
            System.exit(0);
        } else if (e.getSource() == donorview.getLogoutButton()) {
        	int option = JOptionPane.showConfirmDialog(donorview, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Log out by hiding the donorview and showing the loginview
            	donorview.dispose();
            	donorview.setVisible(false);
                login loginV= new login();
                loginModel loginM = new loginModel();
                loginController loginC = new loginController(loginV,loginM);
            }
        }
    }

}
