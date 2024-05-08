package models;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import models.RendezVousListe;

public class RendezvousListeModel {
    private Connection conn;

    public RendezvousListeModel() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blooddonation", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<RendezVousListe> getRendezvousList() {
        List<RendezVousListe> rendezvousList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rendezvous");
            while (rs.next()) {
            	RendezVousListe rendezvous = new RendezVousListe();
                rendezvous.setId(rs.getInt("id"));
                rendezvous.setNom(rs.getString("nom"));
                rendezvous.setPrenom(rs.getString("prenom"));
                rendezvous.setGroupeSanguin(rs.getString("groupe_sanguin"));
             // Conversion de java.sql.Date en LocalDate
                java.sql.Date dateSql = rs.getDate("date_rendezvous");
                LocalDate dateRendezvous = dateSql.toLocalDate();
                rendezvous.setDateRendezvous(dateRendezvous);
                
                // Conversion de java.sql.Time en LocalTime
                java.sql.Time timeSql = rs.getTime("heure_rendezvous");
                LocalTime heureRendezvous = timeSql.toLocalTime();
                rendezvous.setHeureRendezvous(heureRendezvous);
                rendezvousList.add(rendezvous);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendezvousList;
    }
    
    public void addRendezvous(RendezVousListe rendezvous) {
        try {
           
            String sql = "INSERT INTO rendezvous (nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
           
            pstmt.setString(1, rendezvous.getNom());
            pstmt.setString(2, rendezvous.getPrenom());
            pstmt.setString(3, rendezvous.getGroupeSanguin());
         // Convertir LocalDate en java.sql.Date
            LocalDate dateRendezvous = rendezvous.getDateRendezvous();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateRendezvous);

            // Convertir LocalTime en java.sql.Time
            LocalTime heureRendezvous = rendezvous.getHeureRendezvous();
            java.sql.Time sqlTime = java.sql.Time.valueOf(heureRendezvous);

            // Utiliser les objets convertis dans votre PreparedStatement
            pstmt.setDate(4, sqlDate);
            pstmt.setTime(5, sqlTime);

            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getRendezvousIDs() {
        List<Integer> rendezvousIDs = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM rendezvous");
            while (rs.next()) {
                rendezvousIDs.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendezvousIDs;
    }

    public void deleteRendezvous(int rendezvousID) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM rendezvous WHERE id = ?");
            pstmt.setInt(1, rendezvousID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void updateRendezvousList(DefaultTableModel model) {
        // Effacer toutes les lignes existantes de la table
        model.setRowCount(0);

        // Mettre à jour la liste des rendez-vous dans votre interface utilisateur
        List<RendezVousListe> rendezvousList = this.getRendezvousList();

        // Parcourir la liste des rendez-vous
        for (RendezVousListe rendezvous : rendezvousList) {
            // Ajouter une nouvelle ligne avec les données du rendez-vous
            model.addRow(new Object[] { rendezvous.getId(), rendezvous.getNom(), rendezvous.getPrenom(), rendezvous.getGroupeSanguin(), rendezvous.getDateRendezvous(), rendezvous.getHeureRendezvous() });
        }
    }


    
    public void updateRendezvous(RendezVousListe rendezvous) {
        try {
            // Préparer la requête SQL pour la mise à jour
            String sql = "UPDATE rendezvous SET nom=?, prenom=?, groupe_sanguin=?, date_rendezvous=?, heure_rendezvous=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setString(1, rendezvous.getNom());
            pstmt.setString(2, rendezvous.getPrenom());
            pstmt.setString(3, rendezvous.getGroupeSanguin());
            
            // Convertir LocalDate en java.sql.Date
            LocalDate dateRendezvous = rendezvous.getDateRendezvous();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateRendezvous);
            
            // Convertir LocalTime en java.sql.Time
            LocalTime heureRendezvous = rendezvous.getHeureRendezvous();
            java.sql.Time sqlTime = java.sql.Time.valueOf(heureRendezvous);
            
            // Utiliser les objets convertis dans votre PreparedStatement
            pstmt.setDate(4, sqlDate);
            pstmt.setTime(5, sqlTime);
            
           
            pstmt.setInt(6, rendezvous.getId());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
