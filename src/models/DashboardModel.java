package models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.data.general.DefaultPieDataset;

public class DashboardModel {
    public int getCount(String tableName) {
        int count = 0;
        try {
            // Connect to the database
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            // Execute query to get count from the specified table
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            if (rs.next()) {
                count = rs.getInt(1);
            }
            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            // Connect to the database
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            // Execute query to fetch blood group and quantity data
            ResultSet rs = stmt.executeQuery("SELECT groupe_sanguin, SUM(qte) AS total_quantity FROM pack_disponible GROUP BY groupe_sanguin");

            // Calculate total quantity and fill the dataset
            int totalQuantity = 0;
            while (rs.next()) {
                String bloodGroup = rs.getString("groupe_sanguin");
                int quantity = rs.getInt("total_quantity");
                totalQuantity += quantity;
                dataset.setValue(bloodGroup, quantity);
            }

            // Calculate percentage for each blood group and update the dataset
            for (Object key : dataset.getKeys()) {
                Comparable<?> comparableKey = (Comparable<?>) key;
                double quantity = dataset.getValue(comparableKey).doubleValue();
                double percentage = (quantity / totalQuantity) * 100;
                dataset.setValue(comparableKey, percentage);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}    