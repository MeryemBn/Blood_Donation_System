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

	public boolean insertDonation(String donorName, String donationDate, String bloodGroup, int amount) {
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "INSERT INTO historiquedonation (groupe_sanguin, donneur, date, qte_donnee) VALUES (?, ?, ?, ?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {

				// Retrieve donor ID based on the donor name
				int donorId = getDonorIdByName(donorName);

				statement.setString(1, bloodGroup);
				statement.setInt(2, donorId);
				statement.setString(3, donationDate);
				statement.setInt(4, amount);

				// Execute the SQL statement
				int rowsAffected = statement.executeUpdate();
				return rowsAffected > 0; // Return true if insertion was successful
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateDonation(int donationId, String donationDate, int quantityGiven) {
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "UPDATE historiquedonation SET date = ?, qte_donnee = ? WHERE id = ?";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setString(1, donationDate);
				statement.setInt(2, quantityGiven);
				statement.setInt(3, donationId);
				// Execute the SQL statement
				int rowsAffected = statement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}
}
