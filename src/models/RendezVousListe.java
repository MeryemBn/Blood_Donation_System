package models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVousListe {
    private int id;
    private String nom;
    private String prenom;
    private String groupeSanguin;
    private LocalDate dateRendezvous;
    private LocalTime heureRendezvous;

    public RendezVousListe( String nom, String prenom, String groupeSanguin, LocalDate dateRendezvous, LocalTime heureRendezvous) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.groupeSanguin = groupeSanguin;
        this.dateRendezvous = dateRendezvous;
        this.heureRendezvous = heureRendezvous;
    }
    
    
    public RendezVousListe() {}

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public LocalDate getDateRendezvous() {
        return dateRendezvous;
    }

    public void setDateRendezvous(LocalDate dateRendezvous) {
        this.dateRendezvous = dateRendezvous;
    }

    public LocalTime getHeureRendezvous() {
        return heureRendezvous;
    }

    public void setHeureRendezvous(LocalTime heureRendezvous) {
        this.heureRendezvous = heureRendezvous;
    }

    
}
