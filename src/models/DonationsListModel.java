package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationsListModel {

	public List<DonationHistory> getAllDonations() {
		List<DonationHistory> donations = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM historiquedonation";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						DonationHistory donation = new DonationHistory();
						donation.setId(resultSet.getInt("id"));
						donation.setBloodGroup(resultSet.getString("groupe_sanguin"));
						donation.setDonorId(resultSet.getInt("donneur"));
						donation.setDate(resultSet.getDate("date"));
						donation.setTime(resultSet.getString("heure"));
						donation.setAmountDonated(resultSet.getInt("qte_donnee"));
						donations.add(donation);

					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donations;
	}

	public static String getDonorName(int donorId) {
		String donorName = null;
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT nom FROM donneur WHERE id = ?";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setInt(1, donorId);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						donorName = resultSet.getString("nom");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donorName;
	}

	public static String[] getAllDonorNames() {
		List<String> donorNames = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT nom FROM donneur";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String name = resultSet.getString("nom");
						donorNames.add(name);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Convert the list to an array of strings
		return donorNames.toArray(new String[0]);
	}

	public String getBloodGroupForDonor(String donorName) {
		String bloodGroup = null;
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT groupe_sanguin FROM donneur WHERE nom = ?";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setString(1, donorName);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						bloodGroup = resultSet.getString("groupe_sanguin");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bloodGroup;
	}

	public int getDonorIdByName(String donorName) {
		int donorId = -1; // Initialize with a default value

		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT id FROM donneur WHERE nom = ?";

			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setString(1, donorName);

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						donorId = resultSet.getInt("id");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return donorId;
	}

	public boolean insertDonationAndUpdateQuantity(String donorName, String donationDate, String bloodGroup,
			int amount) {
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false); // Start transaction

			// Step 1: Insert into historiquedonation table
			String insertDonationSql = "INSERT INTO historiquedonation (groupe_sanguin, donneur, date, qte_donnee) VALUES (?, ?, ?, ?)";
			try (PreparedStatement insertDonationStatement = conn.prepareStatement(insertDonationSql)) {
				// Retrieve donor ID based on the donor name
				int donorId = getDonorIdByName(donorName);

				insertDonationStatement.setString(1, bloodGroup);
				insertDonationStatement.setInt(2, donorId);
				insertDonationStatement.setString(3, donationDate);
				insertDonationStatement.setInt(4, amount);

				// Execute the insertion into historiquedonation table
				int rowsAffected = insertDonationStatement.executeUpdate();

				if (rowsAffected <= 0) {
					conn.rollback(); // Rollback transaction if insertion fails
					return false;
				}
			}

			// Step 2: Update qte in pack_disponible table
			String updateQteSql = "UPDATE pack_disponible SET qte = qte + ? WHERE groupe_sanguin = ?";
			try (PreparedStatement updateQteStatement = conn.prepareStatement(updateQteSql)) {
				updateQteStatement.setInt(1, amount);
				updateQteStatement.setString(2, bloodGroup);

				// Execute the update statement
				int rowsUpdated = updateQteStatement.executeUpdate();

				if (rowsUpdated <= 0) {
					conn.rollback(); // Rollback transaction if update fails
					return false;
				}
			}

			conn.commit(); // Commit transaction if all operations are successful
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateDonationAndUpdateQuantity(int donationId, String donationDate, int oldQuantityGiven,
			int newQuantityGiven) {
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false); // Start transaction

			// Step 1: Update historiquedonation table
			String updateDonationSql = "UPDATE historiquedonation SET date = ?, qte_donnee = ? WHERE id = ?";
			String bloodGroupSql = "SELECT groupe_sanguin FROM historiquedonation WHERE id = ?";
			String updateQteSql = "UPDATE pack_disponible SET qte = qte + ? WHERE groupe_sanguin = ?";

			try (PreparedStatement updateDonationStatement = conn.prepareStatement(updateDonationSql);
					PreparedStatement bloodGroupStatement = conn.prepareStatement(bloodGroupSql);
					PreparedStatement updateQteStatement = conn.prepareStatement(updateQteSql)) {

				// Update donation
				updateDonationStatement.setString(1, donationDate);
				updateDonationStatement.setInt(2, newQuantityGiven);
				updateDonationStatement.setInt(3, donationId);

				// Execute the update statement
				int rowsAffected = updateDonationStatement.executeUpdate();
				if (rowsAffected <= 0) {
					conn.rollback(); // Rollback transaction if update fails
					return false;
				}

				// Update qte in pack_disponible table
				bloodGroupStatement.setInt(1, donationId);
				ResultSet resultSet = bloodGroupStatement.executeQuery();
				if (resultSet.next()) {
					String bloodGroup = resultSet.getString("groupe_sanguin");
					int qteDifference = newQuantityGiven - oldQuantityGiven;
					updateQteStatement.setInt(1, qteDifference);
					updateQteStatement.setString(2, bloodGroup);

					// Execute the update statement
					int rowsUpdated = updateQteStatement.executeUpdate();
					if (rowsUpdated <= 0) {
						conn.rollback(); // Rollback transaction if update fails
						return false;
					}
				} else {
					conn.rollback(); // Rollback transaction if blood group not found
					return false;
				}
			}

			conn.commit(); // Commit transaction if all operations are successful
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
