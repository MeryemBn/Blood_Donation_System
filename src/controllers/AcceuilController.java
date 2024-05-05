package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.LoginModel;
import views.Acceuil;
import views.RegisterView;
import views.Login;

public class AcceuilController implements ActionListener{
	
	private Acceuil Acceuilview;
	
	
	public AcceuilController(Acceuil view) {
		this.Acceuilview = view;
		view.getLoginButton().addActionListener(this);
		view.getRegisterButton().addActionListener(this);
		view.getCloseButton().addActionListener(this);
	}
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Acceuilview.getLoginButton()) {
        	 Login loginV= new Login();
             LoginModel loginM = new LoginModel();
             LoginController loginC = new LoginController(loginV,loginM);
             Acceuilview.dispose();
        }else if (e.getSource() == Acceuilview.getRegisterButton()) {
        	RegisterView registerView = new RegisterView();
        	new RegisterController(registerView);
        	Acceuilview.dispose();
        	registerView.setVisible(true);
        	
        }else if (e.getSource() == Acceuilview.getCloseButton()) {
        	Acceuilview.dispose();       
        	}

}}