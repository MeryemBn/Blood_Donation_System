package controllers;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.DonationHistory;
import models.DonationsHistoryModel;
import views.DonationsHistoryView;

public class DonationsHistoryController {
	private DonationsHistoryView view;
	private DonationsHistoryModel model;

	public DonationsHistoryController(DonationsHistoryView view, DonationsHistoryModel model) {
		this.view = view;
		this.model = model;
	}

	// Method to fetch and update donation history
	public void fetchDonationHistory(int donorId) {
		try {
			List<DonationHistory> donationHistory = model.getDonationHistory(donorId);
			view.updateView(donationHistory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}