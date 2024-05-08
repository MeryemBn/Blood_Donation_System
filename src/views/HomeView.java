package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class HomeView extends JFrame {
	private JPanel homePanel;
	private JLabel textLabel;

	public HomeView(String username) {
		Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 20);
		homePanel = new JPanel();
		homePanel.setLayout(new BorderLayout());
		homePanel.setBackground(new Color(255, 255, 255));
		homePanel.setBorder(new EmptyBorder(20, 0, 20, 0));

		textLabel = new JLabel("Welcome " + username + ", take an appointment and save many lives!");
		textLabel.setFont(f4);
		textLabel.setForeground(new Color(63, 81, 181));
		textLabel.setBorder(BorderFactory.createEmptyBorder(70, 80, 0, 0));

		homePanel.add(textLabel, BorderLayout.NORTH);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/home3.jpg"));

		// Resize the image
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(370, 250, Image.SCALE_SMOOTH); // Adjust the size as needed

		// Create a new ImageIcon with the scaled image
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

		// Create a JLabel for the image
		JLabel imageLabel = new JLabel(scaledImageIcon);

		// Set other properties for the imageLabel
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.WHITE);
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
		imageLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE),
				BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Add border radius
		imageLabel.setPreferredSize(new Dimension(370, 250)); // Set preferred size

		// Create a panel to contain the image label and add margin to the right
		JPanel imagePanel = new JPanel(new BorderLayout());
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		imagePanel.setBackground(getForeground());
		imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 120)); // Add 20 pixels margin to the right

		// Add the panel containing the image label to the homePanel
		homePanel.add(imagePanel, BorderLayout.EAST);

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new BorderLayout());
		descriptionPanel.setBackground(new Color(255, 255, 255));

		JTextArea descriptionArea1 = new JTextArea();
		descriptionArea1.setLineWrap(true); // Enable line wrapping
		descriptionArea1.setWrapStyleWord(true); // Wrap at word boundaries
		descriptionArea1.setColumns(38);
		descriptionArea1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		descriptionArea1.setText("Is your group's stock in bad shape? Plan a donation if you can.\r\n"
				+ "Is your drop smiling? We're counting on your next donation to keep it that way!"); // Set the text
		descriptionArea1.setEditable(false); // Make the text area read-only
		descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
		descriptionPanel.add(descriptionArea1, BorderLayout.SOUTH);

		JLabel quoteLabel = new JLabel("A thousand reasons to give blood");
		quoteLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 22));

		quoteLabel.setForeground(new Color(206, 0, 0));
		descriptionPanel.add(quoteLabel, BorderLayout.NORTH);
		descriptionArea1.setBorder(BorderFactory.createEmptyBorder(0, 10, 90, 0));
		quoteLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 0));

		homePanel.add(descriptionPanel, BorderLayout.WEST);
		// Create a panel for the blood groups

		JPanel bloodGroupPanel = new JPanel();
		bloodGroupPanel.setLayout(new GridLayout(4, 2)); // 2 rows, 4 columns
		bloodGroupPanel.setBackground(new Color(255, 255, 255));

		// Array of blood groups and their descriptions
		String[] bloodGroups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		String[] descriptions = {
				"Wow! Our reserves for this group are doing very well! Life for Life - thank you for your generosity!",
				"Our reserves for this group are scarce. We rely on you to raise our stock!",
				"Wow! Our reserves for this group are doing very well!",
				"Our blood stocks for this blood group are critical. The outlook is very poor",
				"Wow! Our reserves for this group are doing very well! Life for Life - thank you for your generosity!",
				"Our blood stocks for this blood group are critical. The outlook is very poor. ",
				"Our reserves for this group are okay, but we could do better.",
				"Our blood stocks for this blood group are critical. The outlook is very poor." };
		JTextArea[] descriptionAreas = new JTextArea[descriptions.length];
		for (int i = 0; i < descriptions.length; i++) {
			JTextArea descriptionArea = new JTextArea(descriptions[i]);
			descriptionArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			descriptionArea.setLineWrap(true); // Enable line wrapping
			descriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
			descriptionArea.setEditable(false); // Make the text area read-only
			descriptionArea.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add right padding
			descriptionAreas[i] = descriptionArea;
		}

		// Create JLabels for each blood group and description
		for (int i = 0; i < bloodGroups.length; i++) {
			JLabel bloodGroupLabel = new JLabel(bloodGroups[i]);
			JLabel descriptionLabel = new JLabel(descriptions[i]);

			bloodGroupLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set font size and style
			bloodGroupLabel.setForeground(Color.RED);
			bloodGroupLabel.setPreferredSize(new Dimension(10, 10));
			descriptionLabel.setPreferredSize(new Dimension(10, 10));
			bloodGroupLabel.setBorder(BorderFactory.createEmptyBorder(20, 160, 0, 0));

			bloodGroupPanel.add(bloodGroupLabel);

			// Add description area to blood group panel
			bloodGroupPanel.add(descriptionAreas[i]);

		}
		bloodGroupPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 80, 170));
		// Add the blood group panel to the homePanel

		homePanel.add(bloodGroupPanel, BorderLayout.SOUTH);
		// Add the content panel to the first tab

	}

	public JPanel getMainPanel() {
		return homePanel;
	}
}