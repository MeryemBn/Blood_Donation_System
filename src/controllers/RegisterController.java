package controllers;

import models.LoginModel;
import models.RegisterModel;
import views.Acceuil;
import views.Login;
import views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class RegisterController implements ActionListener{
    private RegisterView registerView;

    public RegisterController(RegisterView view) {
        this.registerView = view;
        registerView.getCloseButton().addActionListener(this);
        registerView.getBackButton().addActionListener(this);
        registerView.getRegisterButton().addActionListener(this);
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerView.getRegisterButton()) {
                // Get values from text fields
                String nom = registerView.getNomField().getText().trim();
                String groupeSanguin = (String) registerView.getGroupeSanguinComboBox().getSelectedItem();
                String sex = "";
                if (registerView.getMaleRadioButton().isSelected()) {
                    sex = "Male";
                } else if (registerView.getFemaleRadioButton().isSelected()) {
                    sex = "Female";
                } else {
                	 JOptionPane.showMessageDialog(registerView, "Please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                String ageText = registerView.getAgeField().getText().trim();
                String adresse = registerView.getAdresseField().getText().trim();
                String numTel = registerView.getNumTelField().getText().trim();
                String username = registerView.getUsernameField().getText().trim();
                String password = new String(registerView.getPasswordField().getPassword());

                if (nom.isEmpty() || ageText.isEmpty() || adresse.isEmpty() || numTel.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView, "Please fill in all fields.");
                    return; 
                }

                int age = 0;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerView, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                if (age < 18) {
                    JOptionPane.showMessageDialog(registerView, "You must be at least 18 years old to register.");
                    return; // Exit the method if age is less than 18
                }

                try {
                    long phoneNumber = Long.parseLong(numTel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerView, "Please enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                RegisterModel model = new RegisterModel();
                model.setNom(nom);
                model.setGroupeSanguin(groupeSanguin);
                model.setSex(sex);
                model.setAge(age);
                model.setAdresse(adresse);
                model.setNumTel(numTel);
                model.setUsername(username);
                model.setPassword(password);

                if (model.save()) {
                    JOptionPane.showMessageDialog(registerView, "Registration successful!");
                    registerView.dispose(); // Close the registration form after successful registration
                    Login loginview = new Login();
                    LoginModel loginmodel = new LoginModel();
                    new LoginController(loginview,loginmodel);
                } else {
                    JOptionPane.showMessageDialog(registerView, "Error occurred while registering. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if(e.getSource() == registerView.getBackButton()) {
                Acceuil acc = new Acceuil();
                acc.setVisible();
                AcceuilController accController = new AcceuilController(acc);
                registerView.dispose();
            } else if (e.getSource() == registerView.getCloseButton()) {
                System.exit(0); 
            }
        }

}
