package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;

import utils.DraggableFrameUtil;

public class UpdateDonationsView extends JFrame {

	private JSpinner donationDateSpinner;
	private JTextField bloodGroupField;
	private JTextField amountField;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextField DonorNameField;

	public UpdateDonationsView() {
		super("Update Donation");
		setMinimumSize(new Dimension(500, 300));
		setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181))); 
        
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.white);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// create the form panel
		JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		formPanel.setBackground(new Color(255, 255, 255));
		getContentPane().add(mainPanel);

		JLabel donationDateLabel = new JLabel("Donation Date:");
		donationDateLabel.setFont(new Font("Arial", Font.BOLD ,17));
		SpinnerDateModel dateModel = new SpinnerDateModel();
		donationDateSpinner = new JSpinner(dateModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(donationDateSpinner, "yyyy-MM-dd");
		donationDateSpinner.setEditor(dateEditor);

		JLabel DonorNameLabel = new JLabel("Donor Name:");
		DonorNameLabel.setFont(new Font("Arial", Font.BOLD ,17));
		DonorNameField = new JTextField();
		DonorNameField.setFont(new Font("Arial", Font.PLAIN, 16));
		DonorNameField.setPreferredSize(new Dimension(DonorNameField.getPreferredSize().width, 30)); // Adjust height
		DonorNameField.setEnabled(false);

		JLabel bloodGroupLabel = new JLabel("Blood Group:");
		bloodGroupLabel.setFont(new Font("Arial", Font.BOLD ,17));
		bloodGroupField = new JTextField();
		bloodGroupField.setFont(new Font("Arial", Font.PLAIN, 16));
		bloodGroupField.setPreferredSize(new Dimension(bloodGroupField.getPreferredSize().width, 30)); 
		bloodGroupField.setEnabled(false);

		// create labels and fields
		JLabel amountLabel = new JLabel("Amount Donated:");
		amountLabel.setFont(new Font("Arial", Font.BOLD ,17));
		amountField = new JTextField();
		amountField.setFont(new Font("Arial", Font.PLAIN, 16));
		amountField.setPreferredSize(new Dimension(amountLabel.getPreferredSize().width, 30)); 

		formPanel.add(donationDateLabel);
		formPanel.add(donationDateSpinner);
		formPanel.add(DonorNameLabel);
		formPanel.add(DonorNameField);
		formPanel.add(bloodGroupLabel);
		formPanel.add(bloodGroupField);
		formPanel.add(amountLabel);
		formPanel.add(amountField);

		saveButton = createButton("Save");
		cancelButton = createButton("Cancel");

		// create button panel for add and cancel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		DraggableFrameUtil.makeDraggable(this);
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 18));
		button.setBackground(new Color(63, 81, 181));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return button;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(UpdateDonationsView::new);
	}

	public JSpinner getDonationDateSpinner() {
		return donationDateSpinner;
	}

	public void setDonationDateSpinner(JSpinner donationDateSpinner) {
		this.donationDateSpinner = donationDateSpinner;
	}

	public JTextField getBloodGroupField() {
		return bloodGroupField;
	}

	public void setBloodGroupField(JTextField bloodGroupField) {
		this.bloodGroupField = bloodGroupField;
	}

	public JTextField getAmountField() {
		return amountField;
	}

	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JTextField getDonorNameField() {
		return DonorNameField;
	}

	public void setDonorNameField(JTextField donorNameField) {
		DonorNameField = donorNameField;
	}

}
