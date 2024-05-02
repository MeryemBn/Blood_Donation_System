package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import models.DBConnection;
import models.RendezVous;

public class PriseRendezVousControleur {
    public void prendreRendezVouss(String nom, String prenom, String groupeSanguin, LocalDate dateRendezVous, LocalTime heureRendezVous) {
        // Validation des données
        if (nom.isEmpty() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Création de la connexion à la base de données
        try (Connection connection = DBConnection.getConnection()) {
            // Création du rendez-vous
            RendezVous rendezVous = new RendezVous(nom, prenom, groupeSanguin, dateRendezVous, heureRendezVous);

            // Insérer les données dans la base de données
            String query = "INSERT INTO rendezvous (nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, groupeSanguin);
                statement.setDate(4, java.sql.Date.valueOf(dateRendezVous));
                statement.setTime(5, java.sql.Time.valueOf(heureRendezVous));

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Rendez-vous pris avec succès pour " + prenom + " " + nom, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion du rendez-vous", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
