package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationsHistoryModel {
	public List<DonationHistory> getDonationHistory(int donorName) throws SQLException {
		List<DonationHistory> donationHistory = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement("SELECT date, heure,qte_donnee FROM historiquedonation  WHERE donneur = ?");
			statement.setInt(1, donorName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				DonationHistory donation = new DonationHistory();
				donation.setDate(resultSet.getDate("date"));
				donation.setTime(resultSet.getString("heure"));
				donation.setAmountDonated(resultSet.getInt("qte_donnee"));
				donationHistory.add(donation);
			}

		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return donationHistory;
	}
}