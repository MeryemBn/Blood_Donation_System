package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.DonationHistory;
import models.DonationsListModel;

public class DonationsListView extends JFrame {
	JPanel mainPanel;
	DefaultTableModel tableModel;
	JTable donationsTable;
	JButton addButton;
	JButton updateButton;

	public DonationsListView() {
		super("Recent Donations List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(844, 660);
		setLocationRelativeTo(null);

		// Create a panel with grey background and BorderLayout
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(240, 240, 240));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

		// Create a panel for the title with BorderLayout
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(new Color(240, 240, 240));

		// Add title label to the left
		JLabel titleLabel = new JLabel("<html><u>Donations List</u></html>");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		titleLabel.setForeground(Color.red);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		// Create a button and add it to the right
		addButton = createButton("+ New Donation");

		titlePanel.add(addButton, BorderLayout.EAST);

		// Add the title panel to the main panel
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		// Create table model and table
		tableModel = new DefaultTableModel();
		tableModel.addColumn("No.Donation");
		tableModel.addColumn("Date");
		tableModel.addColumn("Donor");
		tableModel.addColumn("Blood Group");
		tableModel.addColumn("Amount Donated");

		donationsTable = new JTable(tableModel);
		donationsTable.setDefaultEditor(Object.class, null); // Make the table non-editable

		// Disable selection
		donationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(donationsTable);

		// Apply styling to the table
		JTableHeader header = donationsTable.getTableHeader();
		header.setBackground(new Color(63, 81, 181));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 18));
		donationsTable.setRowHeight(30);
		donationsTable.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10)); 

		// Add table panel to main panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// create button panel for update and delete
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		updateButton = createButton("Update");

		buttonPanel.add(updateButton);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));

		// Add button panel to main panel
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add the main panel to the frame
		getContentPane().add(mainPanel);
	}

	public void displayDonations(List<DonationHistory> donations) {
		DefaultTableModel model = (DefaultTableModel) donationsTable.getModel();
		model.setRowCount(0); // Clear existing rows

		// Populate the table with data from the list of donations
		for (DonationHistory donation : donations) {
			model.addRow(new Object[] { donation.getId(), donation.getDate(),
					DonationsListModel.getDonorName(donation.getDonorId()), donation.getBloodGroup(),
					donation.getAmountDonated() });
		}
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 18));
		button.setBackground(new Color(63, 81, 181));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		return button;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(DonationsListView::new);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JTable getDonationsTable() {
		return donationsTable;
	}

	public void setDonationsTable(JTable donationsTable) {
		this.donationsTable = donationsTable;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}
}