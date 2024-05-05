package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BloodGroupListModel {
	    

	List<BloodGroupList> packs;
    // Method to retrieve all records from the pack_disponible table
    public List<BloodGroupList> getAllPacks() {
        List<BloodGroupList> packs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection(); 
            String sql = "SELECT * FROM pack_disponible";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                BloodGroupList pack = new BloodGroupList();
                pack.setId(rs.getInt("id"));
                pack.setBloodGroup(rs.getString("groupe_sanguin"));
                pack.setQuantity(rs.getInt("qte"));
                packs.add(pack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return packs;
    }
    // Method to update a record in the pack_disponible table
    public void updatePack(BloodGroupList pack) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection(); // Get a connection to the database
            String sql = "UPDATE pack_disponible SET qte = ? WHERE groupe_sanguin = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pack.getQuantity());
            stmt.setString(2, pack.getBloodGroup());
            stmt.executeUpdate(); // Execute the SQL query to update the record
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
}
