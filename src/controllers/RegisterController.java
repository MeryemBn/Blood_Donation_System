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
                String sex = registerView.getMaleRadioButton().isSelected() ? "Male" : "Female";
                String ageText = registerView.getAgeField().getText().trim();
                String adresse = registerView.getAdresseField().getText().trim();
                String numTel = registerView.getNumTelField().getText().trim();
                String username = registerView.getUsernameField().getText().trim();
                String password = new String(registerView.getPasswordField().getPassword());

                // Check if any field is empty
                if (nom.isEmpty() || ageText.isEmpty() || adresse.isEmpty() || numTel.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerView, "Please fill in all fields.");
                    return; // Exit the method if any field is empty
                }

                // Convert age to integer
                int age = 0;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(registerView, "Please enter a valid age.");
                    return; // Exit the method if age is not a valid integer
                }

                // Create Donneur object and save to database
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
                    JOptionPane.showMessageDialog(registerView, "Error occurred while registering. Please try again.");
                }
            }else if(e.getSource() == registerView.getBackButton()) {
    			Acceuil acc = new Acceuil();
    			acc.setVisible();
    			AcceuilController accController = new AcceuilController(acc);
    			registerView.dispose();
    		}else if (e.getSource() == registerView.getCloseButton()) {
    			System.exit(0); 
    		}
        }
    
}
