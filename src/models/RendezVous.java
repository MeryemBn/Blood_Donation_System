package models;


import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVous {
    private String nom;
    private String prenom;
    private String groupeSanguin;
    private LocalDate dateRendezVous;
    private LocalTime heureRendezVous;

    public RendezVous(String nom, String prenom, String groupeSanguin, LocalDate dateRendezVous, LocalTime heureRendezVous) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupeSanguin = groupeSanguin;
        this.dateRendezVous = dateRendezVous;
        this.heureRendezVous = heureRendezVous;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public LocalDate getDateRendezVous() {
        return dateRendezVous;
    }

    public LocalTime getHeureRendezVous() {
        return heureRendezVous;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public void setDateRendezVous(LocalDate dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public void setHeureRendezVous(LocalTime heureRendezVous) {
        this.heureRendezVous = heureRendezVous;
    }
}
