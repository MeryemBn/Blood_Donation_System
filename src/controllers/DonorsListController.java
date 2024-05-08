package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.DashboardModel;
import models.Donor;
import models.DonorsListModel;
import views.AddDonorView;
import views.DashboardView;
import views.DonorsListView;
import views.UpdateDonorView;

public class DonorsListController implements ActionListener {
	private DonorsListView view;
	private DonorsListModel model;
	private AddDonorView addView;
	private UpdateDonorView updateDonorView;
	int selectedId;

	public DonorsListController(DonorsListView view, AddDonorView addView, UpdateDonorView updateDonorView,
			DonorsListModel model) {
		this.view = view;
		this.model = model;
		initView();
		this.addView = addView;
		addView.getFemaleRadioButton().setSelected(true);
		this.updateDonorView = updateDonorView;

		view.getAddButton().addActionListener(this);
		view.getUpdateButton().addActionListener(this);
		view.getDeleteButton().addActionListener(this);

		addView.getSaveButton().addActionListener(this);
		addView.getCancelButton().addActionListener(this);

		addView.getFemaleRadioButton().addActionListener(e -> handleFemaleRadioButton());
		addView.getMaleRadioButton().addActionListener(e -> handleMaleRadioButton());

		updateDonorView.getFemaleRadioButton().addActionListener(e -> handleFemaleRadioButtonUpdate());
		updateDonorView.getMaleRadioButton().addActionListener(e -> handleMaleRadioButtonUpdate());

		updateDonorView.getSaveButtonUpdate().addActionListener(this);
		updateDonorView.getCancelButtonUpdate().addActionListener(this);

	}

	private void initView() {
		List<Donor> donors = model.getDonors();
		view.displayDonors(donors);
	}

	private void initAddView() {
		// Clear all text fields
		addView.getFullNameField().setText("");
		addView.getAddressField().setText("");
		addView.getFemaleRadioButton().setSelected(true);
		addView.getMaleRadioButton().setSelected(false);
		addView.getAgeField().setText("");
		addView.getAddressField().setText("");
		addView.getPhoneNumberField().setText("");
	}

	private void handleFemaleRadioButton() {
		if (addView.getFemaleRadioButton().isSelected()) {
			addView.getMaleRadioButton().setSelected(false);
		}
	}

	private void handleMaleRadioButton() {
		if (addView.getMaleRadioButton().isSelected()) {
			addView.getFemaleRadioButton().setSelected(false);
		}
	}

	private void handleFemaleRadioButtonUpdate() {
		if (updateDonorView.getFemaleRadioButton().isSelected()) {
			updateDonorView.getMaleRadioButton().setSelected(false);
		}
	}

	private void handleMaleRadioButtonUpdate() {
		if (updateDonorView.getMaleRadioButton().isSelected()) {
			updateDonorView.getFemaleRadioButton().setSelected(false);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source == view.getAddButton()) {
			showAddDonorView();
		} else if (source == view.getUpdateButton()) {
			onUpdateButtonClicked();
		} else if (source == view.getDeleteButton()) {
			onDeleteButtonClicked();
		} else if (source == addView.getSaveButton()) {
			onAddButtonClicked();
		} else if (source == addView.getCancelButton()) {
			initAddView();
			addView.dispose();
		} else if (source == updateDonorView.getSaveButtonUpdate()) {
			onSaveButtonClicked();
		} else {
			updateDonorView.dispose();
		}
	}

	public void onAddButtonClicked() {
		// Check if any field is empty
		if (addView.getFullNameField().getText().isEmpty() || addView.getAgeField().getText().isEmpty()
				|| addView.getAddressField().getText().isEmpty() || addView.getPhoneNumberField().getText().isEmpty()) {
			JOptionPane.showMessageDialog(addView, "All fields are required.", "Missing Information",
					JOptionPane.ERROR_MESSAGE);
		} else {

			String gender = "";
			if (addView.getFemaleRadioButton().isSelected()) {
				gender = "Female";
			} else if (addView.getMaleRadioButton().isSelected()) {
				gender = "Male";
			}

			// Insert data into the database
			boolean inserted = model.insertDonor(addView.getFullNameField().getText(),
					(String) addView.getBloodGroupComboBox().getSelectedItem(), gender,
					Integer.parseInt(addView.getAgeField().getText()), addView.getAddressField().getText(),
					Long.parseLong(addView.getPhoneNumberField().getText()));

			if (inserted) {
				JOptionPane.showMessageDialog(addView, "Donor added successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				initAddView();
				addView.dispose();

				// Create a new instance of DashboardView and update its data
			} else {
				JOptionPane.showMessageDialog(addView, "Failed to add donor.", "Error", JOptionPane.ERROR_MESSAGE);
			}

			initView();
		}
	}

	public void onDeleteButtonClicked() {
		// Check if a row is selected
		int selectedRow = view.getDonorTable().getSelectedRow();
		if (selectedRow == -1) {
			// If no row is selected, show a message
			JOptionPane.showMessageDialog(view, "Please select a donor to delete.", "No donor Selected",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete the selected donor?",
					"Confirm Delete", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				// If a row is selected, retrieve the ID
				int id = (int) view.getDonorTable().getValueAt(selectedRow, 0);
				// Delete the row from the database
				boolean deleted = model.deleteDonor(id);
				if (deleted) {
					// If the row is successfully deleted, remove it from the table
					((DefaultTableModel) view.getDonorTable().getModel()).removeRow(selectedRow);
					JOptionPane.showMessageDialog(view, "Donor deleted successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// If there is an error during deletion, show an error message
					JOptionPane.showMessageDialog(view, "Failed to delete donor.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void onUpdateButtonClicked() {
		// Check if a row is selected
		int selectedRow = view.getDonorTable().getSelectedRow();
		if (selectedRow == -1) {
			// If no row is selected, show a message
			JOptionPane.showMessageDialog(view, "Please select a Donor to update.", "No Donor Selected",
					JOptionPane.WARNING_MESSAGE);
		} else {

			// If a row is selected, retrieve the information of the selected row
			selectedId = (int) view.getDonorTable().getValueAt(selectedRow, 0);
			String fullName = (String) view.getDonorTable().getValueAt(selectedRow, 1);
			String bloodGroup = (String) view.getDonorTable().getValueAt(selectedRow, 2);
			String gender = (String) view.getDonorTable().getValueAt(selectedRow, 3);
			int age = (int) view.getDonorTable().getValueAt(selectedRow, 4);
			String address = (String) view.getDonorTable().getValueAt(selectedRow, 5);
			long phoneNumber = (long) view.getDonorTable().getValueAt(selectedRow, 6);

			// Create and display the UpdateDonorView with the retrieved information
			updateDonorView.getFullNameField().setText(fullName);
			updateDonorView.getBloodGroupComboBox().setSelectedItem(bloodGroup);
			if (gender.equals("Female")) {
				updateDonorView.getFemaleRadioButton().setSelected(true);
				updateDonorView.getMaleRadioButton().setSelected(false);

			} else if (gender.equals("Male")) {
				updateDonorView.getMaleRadioButton().setSelected(true);
				updateDonorView.getFemaleRadioButton().setSelected(false);
			}
			updateDonorView.getAgeField().setText(String.valueOf(age));
			updateDonorView.getAddressField().setText(address);
			updateDonorView.getPhoneNumberField().setText(String.valueOf(phoneNumber));

			updateDonorView.setVisible(true);
		}
	}

	public void onSaveButtonClicked() {
		// Check if any field is empty
		if (updateDonorView.getFullNameField().getText().isEmpty() || updateDonorView.getAgeField().getText().isEmpty()
				|| updateDonorView.getAddressField().getText().isEmpty()
				|| updateDonorView.getPhoneNumberField().getText().isEmpty()) {
			JOptionPane.showMessageDialog(addView, "All fields are required.", "Field(s) Empty",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String fullName = updateDonorView.getFullNameField().getText();
			String bloodGroup = (String) updateDonorView.getBloodGroupComboBox().getSelectedItem();

			String gender = "";
			if (updateDonorView.getFemaleRadioButton().isSelected()) {
				gender = "Female";
			} else if (updateDonorView.getMaleRadioButton().isSelected()) {
				gender = "Male";
			}

			int age = Integer.parseInt(updateDonorView.getAgeField().getText());
			String address = updateDonorView.getAddressField().getText();
			long phoneNumber = Long.parseLong(updateDonorView.getPhoneNumberField().getText());

			// Call the updateDonor function
			boolean updated = model.updateDonor(selectedId, fullName, bloodGroup, gender, age, address, phoneNumber);

			if (updated) {
				JOptionPane.showMessageDialog(addView, "Donor updated successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				updateDonorView.dispose();
				initView();

			} else {
				JOptionPane.showMessageDialog(view, "Failed to update donor.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void showAddDonorView() {
		addView.setVisible(true);
	}
}
