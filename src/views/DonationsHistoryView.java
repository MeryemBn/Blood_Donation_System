package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.DonationHistory;

public class DonationsHistoryView extends JFrame {
	private JTable donationTable;
	private DefaultTableModel tableModel;
	private JPanel mainPanel;

	public DonationsHistoryView() {
		super("Donation History");
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Title panel
		JPanel titlePanel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel("Donation History");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		titleLabel.setForeground(new Color(63, 81, 181));
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		titlePanel.add(titleLabel, BorderLayout.NORTH);

		// Quote label
		JLabel quoteLabel = new JLabel(
				"<html><div style='text-align: center;'>\"Thank you for your generous donations,<br>they are truly appreciated.\"</div></html>");

		quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quoteLabel.setFont(new Font("Bell MT", Font.ITALIC, 25));
		quoteLabel.setForeground(Color.red);
		quoteLabel.setBackground(Color.WHITE);
		quoteLabel.setOpaque(true);
		quoteLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titlePanel.add(quoteLabel, BorderLayout.SOUTH);

		GridBagConstraints gbcTitle = new GridBagConstraints();
		gbcTitle.gridx = 0;
		gbcTitle.gridy = 0;
		mainPanel.add(titlePanel, gbcTitle);

		// Table panel
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(new Color(255, 255, 255));
		tablePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); // Add top margin to table panel

		// Customizing table appearance
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Make cells non-editable
			}
		};
		tableModel.addColumn("No.Donation");
		tableModel.addColumn("Date");
		tableModel.addColumn("Time");
		tableModel.addColumn("Amount Donated");

		donationTable = new JTable(tableModel);
		donationTable.setFont(new Font("Arial", Font.BOLD, 14));
		donationTable.setRowHeight(30);
		donationTable.setShowGrid(true);
		donationTable.setGridColor(Color.black);
		donationTable.setSelectionBackground(Color.black);
		donationTable.setSelectionForeground(Color.BLACK);

		// Custom renderer for the header row to set blue background
		donationTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(new Color(63, 81, 181));
				c.setForeground(Color.WHITE);
				c.setFont(new Font("Arial", Font.PLAIN, 20));
				return c;
			}
		});

		JScrollPane scrollPane = new JScrollPane(donationTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove the border
		scrollPane.setPreferredSize(new Dimension(700, 200)); // Set preferred size for the scroll pane
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBackground(new Color(255, 255, 255));
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		GridBagConstraints gbcTable = new GridBagConstraints();
		gbcTable.gridx = 0;
		gbcTable.gridy = 1;
		mainPanel.add(tablePanel, gbcTable);

		getContentPane().add(mainPanel, BorderLayout.CENTER);

	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void updateView(List<DonationHistory> donationHistory) {
		tableModel.setRowCount(0);

		int count = 1;
		for (DonationHistory donation : donationHistory) {
			Object[] rowData = { count++, donation.getDate(), donation.getTime() != null ? donation.getTime() : "N/A",
					donation.getAmountDonated() };
			tableModel.addRow(rowData);
		}
	}

}
