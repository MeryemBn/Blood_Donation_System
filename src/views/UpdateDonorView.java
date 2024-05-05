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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utils.DraggableFrameUtil;

public class UpdateDonorView extends JFrame {
	JButton saveButtonUpdate;
	JButton cancelButtonUpdate;
	JTextField fullNameField;
	JRadioButton femaleRadioButton;
	JRadioButton maleRadioButton;
	JTextField ageField;
	JTextField addressField;
	JTextField phoneNumberField;

	JComboBox<String> bloodGroupComboBox;
	// Define an array of blood types
	String[] bloodTypes = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };

	public UpdateDonorView() {
		super("Update Donor");

		setSize(500, 300);
		// Ensure the frame can't be resized smaller than its preferred size
		setMinimumSize(new Dimension(500, 300));
		setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181))); 
        
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.white);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// create the form panel
		JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		formPanel.setBackground(new Color(255, 255, 255));

		// create labels and fields
		JLabel fullNameLabel = new JLabel("Full Name:");
		fullNameLabel.setFont(new Font("Arial", Font.BOLD ,17));
		fullNameField = new JTextField();
		fullNameField.setFont(new Font("Arial", Font.PLAIN, 16));
		fullNameField.setPreferredSize(new Dimension(fullNameField.getPreferredSize().width, 30)); // Adjust height

		JLabel bloodGroupLabel = new JLabel("Blood Group:");
		bloodGroupLabel.setFont(new Font("Arial", Font.BOLD ,17));
		// Create a JComboBox for blood type
		bloodGroupComboBox = new JComboBox<>(bloodTypes);
		bloodGroupComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		bloodGroupComboBox.setBackground(Color.WHITE);
		bloodGroupComboBox.setForeground(Color.BLACK);

		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setFont(new Font("Arial", Font.BOLD ,17));

		// Create radio buttons
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setBackground(new Color(255, 255, 255));
		femaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 16));
		femaleRadioButton.setForeground(Color.BLACK);
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setBackground(new Color(255, 255, 255));
		maleRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
		maleRadioButton.setForeground(Color.BLACK);

		// Create a JPanel to hold the radio buttons
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.setBackground(new Color(255, 255, 255));
		genderPanel.add(femaleRadioButton);
		genderPanel.add(maleRadioButton);

		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setFont(new Font("Arial", Font.BOLD ,17));
		ageField = new JTextField();
		ageField.setFont(new Font("Arial", Font.PLAIN, 16));
		ageField.setPreferredSize(new Dimension(ageField.getPreferredSize().width, 30));

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setFont(new Font("Arial", Font.BOLD ,17));
		addressField = new JTextField();
		addressField.setFont(new Font("Arial", Font.PLAIN, 16));
		addressField.setPreferredSize(new Dimension(addressField.getPreferredSize().width, 30));

		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setFont(new Font("Arial", Font.BOLD ,17));
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 16));
		phoneNumberField.setPreferredSize(new Dimension(phoneNumberField.getPreferredSize().width, 30));

		// Add labels and fields to form panel
		formPanel.add(fullNameLabel);
		formPanel.add(fullNameField);
		formPanel.add(bloodGroupLabel);
		formPanel.add(bloodGroupComboBox);
		formPanel.add(genderLabel);
		formPanel.add(genderPanel);
		formPanel.add(ageLabel);
		formPanel.add(ageField);
		formPanel.add(addressLabel);
		formPanel.add(addressField);
		formPanel.add(phoneNumberLabel);
		formPanel.add(phoneNumberField);

		saveButtonUpdate = createButton("Save");
		cancelButtonUpdate = createButton("Cancel");

		// create button panel for add and cancel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.add(saveButtonUpdate);
		buttonPanel.add(cancelButtonUpdate);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

		pack();
		setLocationRelativeTo(null);
		DraggableFrameUtil.makeDraggable(this);
	}

	public JTextField getFullNameField() {
		return fullNameField;
	}

	public void setFullNameField(JTextField fullNameField) {
		this.fullNameField = fullNameField;
	}

	public JComboBox<String> getBloodGroupComboBox() {
		return bloodGroupComboBox;
	}

	public void setBloodGroupComboBox(JComboBox<String> bloodGroupComboBox) {
		this.bloodGroupComboBox = bloodGroupComboBox;
	}

	public JRadioButton getFemaleRadioButton() {
		return femaleRadioButton;
	}

	public void setFemaleRadioButton(JRadioButton femaleRadioButton) {
		this.femaleRadioButton = femaleRadioButton;
	}

	public JRadioButton getMaleRadioButton() {
		return maleRadioButton;
	}

	public void setMaleRadioButton(JRadioButton maleRadioButton) {
		this.maleRadioButton = maleRadioButton;
	}

	public JTextField getAgeField() {
		return ageField;
	}

	public void setAgeField(JTextField ageField) {
		this.ageField = ageField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public JButton getSaveButtonUpdate() {
		return saveButtonUpdate;
	}

	public void setSaveButtonUpdate(JButton saveButtonUpdate) {
		this.saveButtonUpdate = saveButtonUpdate;
	}

	public JButton getCancelButtonUpdate() {
		return cancelButtonUpdate;
	}

	public void setCancelButtonUpdate(JButton cancelButtonUpdate) {
		this.cancelButtonUpdate = cancelButtonUpdate;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public JTextField getPhoneNumberField() {
		return phoneNumberField;
	}

	public void setPhoneNumberField(JTextField phoneNumberField) {
		this.phoneNumberField = phoneNumberField;
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
		SwingUtilities.invokeLater(UpdateDonorView::new);
	}

}