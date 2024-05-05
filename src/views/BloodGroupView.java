package views;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.BloodGroupList;
import models.BloodListModel;
import java.util.List;

public class BloodGroupView extends JFrame {
	private JPanel mainPanel;
	private JTable dataTable;
	private JButton editButton;
	private AbstractTableModel tableModel;

	public BloodGroupView(List<BloodGroupList> data) {
		setTitle("Admin Space");
		setSize(844, 660);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		mainPanel = new JPanel(new BorderLayout());

		// Create the table model with the data
		BloodListModel tableModel = new BloodListModel(data);
		dataTable = new JTable(tableModel);

		// Add the table to a JScrollPane for scrolling
		JScrollPane scrollPane = new JScrollPane(dataTable);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.setBackground(new Color(240, 240, 240)); // Light grey
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20)); // Add padding

		// Create a panel for the title with BorderLayout
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(new Color(240, 240, 240));

		// Add title label to the left
		JLabel titleLabel = new JLabel("<html><u>Blood Group List</u></html>");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		titleLabel.setForeground(Color.red);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		mainPanel.add(titlePanel, BorderLayout.NORTH);
		JTableHeader header = dataTable.getTableHeader();
		header.setBackground(new Color(63, 81, 181));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 18));
		dataTable.setRowHeight(30);
		dataTable.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));

		dataTable.setDefaultEditor(Object.class, null); // Make the table non-editable

		// Disable selection
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Create buttons
		editButton = new JButton("update stock");
		editButton.setFont(new Font("Arial", Font.PLAIN, 18));
		editButton.setBackground(new Color(63, 81, 181));
		editButton.setForeground(Color.WHITE);
		editButton.setFocusPainted(false);
		editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		// Add buttons to a JPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 60, 10));
		buttonPanel.add(editButton);

		// Add the button JPanel to the bottom of the window
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel);
		//setVisible(true);
	}

	// Methods to add listeners to the buttons
	public void addEditButtonListener(ActionListener listener) {
		editButton.addActionListener(listener);
	}

	// Method to get the selected row index in the table
	public int getSelectedRowIndex() {
		return dataTable.getSelectedRow();
	}

	public JButton getEditButton() {
		return editButton;
	}

	// Method to get the selected BloodGroupList from the table
	public BloodGroupList getSelectedPackDisponible() {
		int rowIndex = getSelectedRowIndex();
		if (rowIndex != -1) {
			BloodListModel tableModel = (BloodListModel) dataTable.getModel();
			return tableModel.getBloodGroupAt(rowIndex);
		}
		return null;
	}

	// Method to set the table model
	public void setTableModel(AbstractTableModel model) {
		this.tableModel = model;
		dataTable.setModel(model);
	}
	public JPanel getBloodPanel() {
		return mainPanel;
	}

}
