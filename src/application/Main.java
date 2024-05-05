package application;

import views.Acceuil;
import controllers.AcceuilController;

public class Main {
    public static void main(String[] args) {
        // Create the login model
    	Acceuil view = new Acceuil();
    	new AcceuilController(view);
    	view.setVisible();
    }
}
