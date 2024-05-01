package models;

import java.sql.*;
import models.DBConnection;


import javax.swing.JOptionPane;

public class loginModel {
     static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found!");
        }
    }


    public boolean authenticateUser(String username, String password, String userType) {
        try {
        	Connection connection = DBConnection.getConnection();
        	if (connection != null) {
            String query = "";
            if (userType.equals("Donor")) {
                query = "SELECT * FROM donneur WHERE username = ? AND password = ?";
            } else if (userType.equals("Admin")) {
                query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            }

            if (!query.isEmpty()) {
                if (!username.isEmpty() && !password.isEmpty()) {
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, username);
                        statement.setString(2, password);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            return resultSet.next();
                        }
                    }
                }
            }
        	} else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
