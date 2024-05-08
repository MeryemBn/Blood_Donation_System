package views;

import javax.swing.*;
import java.awt.*;
import models.CentreDeDonDeSang;

public class CentreDeDonDeSangView extends JPanel {
    private JLabel nomLabel;
    private JLabel dateCreationLabel;
    private JLabel numeroTelephoneLabel;
    private JLabel heureTravailLabel;

    public CentreDeDonDeSangView() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nomLabel = new JLabel();
        dateCreationLabel = new JLabel();
        numeroTelephoneLabel = new JLabel();
        heureTravailLabel = new JLabel();

        setBackground(Color.WHITE); 
        //setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        // Style des labels
        nomLabel.setForeground(Color.RED); 
        dateCreationLabel.setForeground(Color.RED);
        numeroTelephoneLabel.setForeground(Color.RED);
        heureTravailLabel.setForeground(Color.RED);

        add(nomLabel);
        add(dateCreationLabel);
        add(numeroTelephoneLabel);
        add(heureTravailLabel);
    }

    public void afficherCentreDeDonDeSang(CentreDeDonDeSang centre) {
        nomLabel.setText("Nom du Centre: " + centre.getNom());
        dateCreationLabel.setText("Date de Création: " + centre.getDateCreation());
        numeroTelephoneLabel.setText("Numéro de Téléphone: " + centre.getNumeroTelephone());
        heureTravailLabel.setText("Heures de Travail: " + centre.getHeureTravail());
    }
}

