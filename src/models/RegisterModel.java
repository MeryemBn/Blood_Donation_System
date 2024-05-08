package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterModel {
    private int id;
    private String nom;
    private String groupeSanguin;
    private String sex;
    private int age;
    private String adresse;
    private String numTel;
    private String username;
    private String password;

    public RegisterModel() {}
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean save() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO donneur (nom, groupe_sanguin, sex, age, adresse, numtel, date, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, this.nom);
            statement.setString(2, this.groupeSanguin);
            statement.setString(3, this.sex);
            statement.setInt(4, this.age); // Ensure age is set as an integer
            statement.setString(5, this.adresse);
            statement.setString(6, this.numTel);
            LocalDate currentDate = LocalDate.now();
            Date today = Date.valueOf(currentDate);
            statement.setDate(7, today);
            statement.setString(8, this.username);
            statement.setString(9, this.password);
            int rowsInserted = statement.executeUpdate();
            conn.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
