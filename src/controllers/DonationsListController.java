package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import models.BloodGroupListModel;
import models.BloodListModel;
import models.DonationHistory;
import models.DonationsListModel;
import views.AddDonationsView;
import views.BloodGroupView;
import views.DonationsListView;
import views.UpdateDonationsView;

public class DonationsListController implements ActionListener {
	private DonationsListView view;
	private DonationsListModel model;
	private AddDonationsView addDonation;
	private UpdateDonationsView updateDonation;
	private BloodGroupView bloodView;
	private BloodGroupListModel modelBloodView;
	int selectedId;

	public DonationsListController(DonationsListView view, AddDonationsView AddDonation,
			UpdateDonationsView updateDonation, BloodGroupView bloodView, BloodGroupListModel modelBloodView,
			DonationsListModel model) {
		this.view = view;
		this.model = model;
		this.addDonation = AddDonation;
		this.updateDonation = updateDonation;
		this.bloodView = bloodView;
		this.modelBloodView = modelBloodView;

		initView();

		view.getAddButton().addActionListener(this);
		view.getUpdateButton().addActionListener(this);

		addDonation.getDonorsNamesComboBox().addActionListener(this);
		addDonation.getSaveButton().addActionListener(this);
		addDonation.getCancelButton().addActionListener(this);

		updateDonation.getSaveButton().addActionListener(this);
		updateDonation.getCancelButton().addActionListener(this);
	}

	private void initView() {
		List<DonationHistory> donations = model.getAllDonations();
		view.displayDonations(donations);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == view.getAddButton()) {
			showAddDonationView();
		} else if (source == view.getUpdateButton()) {
			showUpdateDonationView();
		} else if (source == addDonation.getDonorsNamesComboBox()) {
			// Get the selected donor name from the combo box
			String selectedDonorName = (String) addDonation.getDonorsNamesComboBox().getSelectedItem();

			// Get the blood group for the selected donor name
			String bloodGroup = model.getBloodGroupForDonor(selectedDonorName);

			// Set the blood group field text
			addDonation.getBloodGroupField().setText(bloodGroup);
		} else if (source == addDonation.getSaveButton()) {
			addDonation();
		} else if (source == addDonation.getCancelButton()) {
			addDonation.dispose();
		} else if (source == updateDonation.getSaveButton()) {
			updateDonation();
		} else {
			updateDonation.dispose();
		}
	}

	private void updateDonation() {
		// Get the values from the input fields
		String donationDate = ((JSpinner.DateEditor) updateDonation.getDonationDateSpinner().getEditor()).getTextField()
				.getText();
		String donorName = updateDonation.getDonorNameField().getText();
		String bloodGroup = updateDonation.getBloodGroupField().getText();
		String amountString = updateDonation.getAmountField().getText();

		// Check if any field is empty
		if (donationDate.isEmpty() || amountString.isEmpty()) {
			// Display a message if any field is empty
			JOptionPane.showMessageDialog(updateDonation, "Please fill in all fields", "Missing Information",
					JOptionPane.WARNING_MESSAGE);
			return; // Exit the method
		}

		// Parse the amount to an integer
		int amount;
		try {
			amount = Integer.parseInt(amountString);
		} catch (NumberFormatException e) {
			// Display an error message if amount is not a valid integer
			JOptionPane.showMessageDialog(updateDonation, "Please enter a valid amount", "Invalid Amount",
					JOptionPane.ERROR_MESSAGE);
			return; // Exit the method
		}

		// Insert the donation into the database
		boolean success = model.updateDonationAndUpdateQuantity(selectedId, donationDate, quantityGiven, amount);
		if (success) {
			// Display a success message if insertion is successful
			JOptionPane.showMessageDialog(updateDonation, "Donation updated successfully", "Success",
					JOptionPane.INFORMATION_MESSAGE);

			updateDonation.dispose();
			initView();
			// Update the table model in the BloodGroupView
			bloodView.setTableModel(new BloodListModel(modelBloodView.getAllPacks()));
		} else {
			// Display an error message if insertion fails
			JOptionPane.showMessageDialog(updateDonation, "Failed to update donation", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void addDonation() {
		// Get the values from the input fields
		String donationDate = ((JSpinner.DateEditor) addDonation.getDonationDateSpinner().getEditor()).getTextField()
				.getText();
		String donorName = (String) addDonation.getDonorsNamesComboBox().getSelectedItem();
		String bloodGroup = addDonation.getBloodGroupField().getText();
		String amountString = addDonation.getAmountField().getText();

		// Check if any field is empty
		if (donationDate.isEmpty() || donorName == null || bloodGroup.isEmpty() || amountString.isEmpty()) {
			// Display a message if any field is empty
			JOptionPane.showMessageDialog(addDonation, "Please fill in all fields", "Missing Information",
					JOptionPane.WARNING_MESSAGE);
			return; // Exit the method
		}

		// Parse the amount to an integer
		int amount;
		try {
			amount = Integer.parseInt(amountString);
		} catch (NumberFormatException e) {
			// Display an error message if amount is not a valid integer
			JOptionPane.showMessageDialog(addDonation, "Please enter a valid amount", "Invalid Amount",
					JOptionPane.ERROR_MESSAGE);
			return; // Exit the method
		}

		// Insert the donation into the database and update qte in pack_disponible table
		boolean success = model.insertDonationAndUpdateQuantity(donorName, donationDate, bloodGroup, amount);
		if (success) {
			// Display a success message if insertion is successful
			JOptionPane.showMessageDialog(addDonation, "Donation added successfully", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			addDonation.getAmountField().setText(null);
			initView();
			// Update the table model in the BloodGroupView
			bloodView.setTableModel(new BloodListModel(modelBloodView.getAllPacks()));
		} else {
			// Display an error message if insertion fails
			JOptionPane.showMessageDialog(addDonation, "Failed to add donation", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showAddDonationView() {
		addDonation.setVisible(true);
	}

	int quantityGiven;

	private void showUpdateDonationView() {
		// Check if a row is selected
		int selectedRow = view.getDonationsTable().getSelectedRow();
		if (selectedRow == -1) {
			// If no row is selected, show a message
			JOptionPane.showMessageDialog(view, "Please select a Donor to update.", "No Donor Selected",
					JOptionPane.WARNING_MESSAGE);
		} else {

			// If a row is selected, retrieve the information of the selected row
			selectedId = (int) view.getDonationsTable().getValueAt(selectedRow, 0);
			Date donationDate = (Date) view.getDonationsTable().getValueAt(selectedRow, 1);
			String donorName = (String) view.getDonationsTable().getValueAt(selectedRow, 2);
			String bloodGroup = (String) view.getDonationsTable().getValueAt(selectedRow, 3);
			quantityGiven = (int) view.getDonationsTable().getValueAt(selectedRow, 4);

			updateDonation.getDonorNameField().setText(donorName);
			updateDonation.getBloodGroupField().setText(bloodGroup);
			updateDonation.getAmountField().setText(String.valueOf(quantityGiven));
			updateDonation.getDonationDateSpinner().setValue(donationDate);
			updateDonation.setVisible(true);
		}
	}

}
