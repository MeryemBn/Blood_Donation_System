package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.*;

import models.BloodGroupList;
import utils.DraggableFrameUtil;

public class EditPackView extends JFrame {
    private JTextField bloodGroupField;
    private JTextField quantityField;
    private JButton updateButton;
    private JButton cancelButton; 

    public EditPackView(BloodGroupView parentView, BloodGroupList bloodGroup) {
        setTitle("Edit Blood Group");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181))); 

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        updateButton.setBackground(new Color(63, 81, 181));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.setBackground(new Color(63, 81, 181));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        buttonPanel.setBackground(new Color(255, 255, 255));
        
		buttonPanel.add(updateButton);
		buttonPanel.add(cancelButton);

		
		// Create a panel for form components
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(255, 255, 255));

		// Initialize form components
		bloodGroupField = new JTextField(bloodGroup.getBloodGroup(), 10);
		bloodGroupField.setFont(new Font("Arial", Font.PLAIN, 16));
		bloodGroupField.setEnabled(false);
		bloodGroupField.setPreferredSize(new Dimension(170, 30));

		quantityField = new JTextField(String.valueOf(bloodGroup.getQuantity()), 10);
		quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
		quantityField.setPreferredSize(new Dimension(170, 30));
		
		JLabel bloodLabel = new JLabel("Blood Group:");
		bloodLabel.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Arial", Font.BOLD, 18));
		// Add components to the form panel with GridBagConstraints
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing as needed
		formPanel.add(bloodLabel, gbc);

		gbc.gridx = 1;
		formPanel.add(bloodGroupField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		formPanel.add(quantityLabel, gbc);

		gbc.gridx = 1;
		formPanel.add(quantityField, gbc);

		// Layout components
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.add(formPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		// Add panel to the frame
		add(panel);

        // Set visibility
        setVisible(true);
        DraggableFrameUtil.makeDraggable(this);
    }

    // Method to retrieve edited blood group
    public String getBloodGroup() {
        return bloodGroupField.getText();
    }

    // Method to retrieve edited quantity
    public int getQuantity() {
        try {
            return Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            return 0; // Return 0 if parsing fails
        }
    }
    public JButton getEditButton() {
    	return updateButton;
    }
    public JButton getCancelButton() {
    	return cancelButton;
    }
    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}