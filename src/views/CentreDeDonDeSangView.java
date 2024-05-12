package views;

import javax.swing.*;
import java.awt.*;
import models.CentreDeDonDeSang;

public class CentreDeDonDeSangView extends JPanel {
	
    public CentreDeDonDeSangView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE); // Définir le fond en blanc

        add(createLabelPanel("Center Name: ", "", Color.BLUE));
        add(createLabelPanel("Creation Date: ", "", Color.BLUE));
        add(createLabelPanel("Phone Number: ", "", Color.BLUE));
        add(createLabelPanel("Working Hours: ", "", Color.BLUE));

        setPreferredSize(new Dimension(300, 100)); // Ajuster la taille préférée du panneau
    }

    private JPanel createLabelPanel(String labelText, String infoText, Color labelColor) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        JLabel infoLabel = new JLabel(infoText);
        label.setForeground(labelColor); // Couleur spécifiée pour les labels
        infoLabel.setForeground(Color.BLACK); // Couleur noire pour le texte d'information
        labelPanel.add(label);
        labelPanel.add(Box.createRigidArea(new Dimension(-5,-10 ))); // Ajouter un padding horizontal
        labelPanel.add(infoLabel);
        
        labelPanel.setBackground(Color.WHITE); // Définir le fond du panel en blanc
        //labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Ajouter un padding vertical
        
        return labelPanel;
    }

    public void afficherCentreDeDonDeSang(CentreDeDonDeSang centre) {
        removeAll(); // Nettoyer le panneau

        add(createLabelPanel("Center Name: ", centre.getNom(), new Color(206, 0, 0)));
        add(createLabelPanel("Creation Date: ", centre.getDateCreation(), new Color(206, 0, 0)));
        add(createLabelPanel("Numéro de Téléphone: ", centre.getNumeroTelephone(), new Color(206, 0, 0)));
        add(createLabelPanel("Working Hours: ", centre.getHeureTravail(), new Color(206, 0, 0)));

        revalidate(); // Redessiner le panneau
    }
}
