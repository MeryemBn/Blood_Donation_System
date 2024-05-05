package views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

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

import controllers.BloodGroupController;
import controllers.DonorsListController;
import models.BloodGroupList;
import models.BloodGroupListModel;
import models.DonorsListModel;
import models.RendezvousListeModel;
import views.RendezvousListeView;
import controllers.RendezvousListeController;



public class AdminView extends JFrame {
	private JFrame frame;
	private JButton button1;
	private JTabbedPane tabbedPane;
	private JButton button2;
	private JButton button3;
	private JButton closeButton;
	private JLabel titleLabel;
	private JPanel titlePanel;
	private JPanel menuPanel;
	private JPanel tabPanel1;
	private JPanel tabPanel2;
	private JPanel tabPanel3;
	private JPanel tabPanel4;
	private JPanel tabPanel5;
	private JButton logoutButton;
	private JButton button4;
	private JButton button5;

	private DonorsListView donorsListView;
	private DonorsListModel donorsListModel;
	private AddDonorView addDonorView;
	private UpdateDonorView updateDonorView;
	private DonorsListController donorsListController;

	public AdminView() {
		frame = new JFrame("Accueil Admin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(1540, 830);
		/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);*/
		
		// Create a panel for the title bar
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align components to the right
		titlePanel.setBackground(new Color(206, 0, 0)); // Set background color of the title bar
		titlePanel.setPreferredSize(new Dimension(1100, 45));

		titleLabel = new JLabel("Blood Donation System");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(new EmptyBorder(4, 0, 0, 200));

		// Ajouter le label au centre du titlePanel
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createHorizontalStrut(400));

		// Add a close button to the title bar
		closeButton = new JButton("X");
		closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
		closeButton.setForeground(Color.WHITE);
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setContentAreaFilled(false);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		titlePanel.add(closeButton);
		titlePanel.add(Box.createHorizontalStrut(7));

		// Add the title bar panel to the frame's content pane
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		// Create the menu panel
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(206, 0, 0));
		menuPanel.setPreferredSize(new Dimension(270, 600));
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.add(Box.createVerticalStrut(200));

		// Create the buttons
		button1 = createButton("Dashboard");
		button2 = createButton("Blood Group List");
		button3 = createButton("Donors List");
		button4 = createButton("Donations");
		button5 = createButton("Appointments list");
		button1.setBorder(BorderFactory.createEmptyBorder(15, 84, 15, 82));
		button2.setBorder(BorderFactory.createEmptyBorder(15, 54, 15, 54));
		button3.setBorder(BorderFactory.createEmptyBorder(15, 80, 15, 80));
		button5.setBorder(BorderFactory.createEmptyBorder(15, 52, 15, 52));
		button4.setBorder(BorderFactory.createEmptyBorder(15, 86, 15, 86));

		// Add the buttons to the menuPanel
		menuPanel.add(button1);
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.add(button2);
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.add(button3);
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.add(button4);
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.add(button5);

		menuPanel.add(Box.createVerticalStrut(200), BorderLayout.NORTH);

		// Créez le bouton de déconnexion avec une icône
		logoutButton = new JButton("Logout", new ImageIcon(getClass().getResource("/images/logout.png")));
		logoutButton.setFont(new Font("Arial", Font.BOLD, 19));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(206, 0, 0));
		logoutButton.setFocusPainted(false);
		logoutButton.setBorder(BorderFactory.createEmptyBorder(18, 87, 18, 87));
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Ajoutez le bouton de déconnexion au sud (en bas) du menuPanel
		menuPanel.add(logoutButton, BorderLayout.SOUTH);

		// Add the menuPanel to the frame
		frame.getContentPane().add(menuPanel, BorderLayout.WEST);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(-9, -2, -2, -2));

		DashboardView dashboardView = new DashboardView(); // Create an instance of DashboardView
		tabbedPane.addTab("1", null, dashboardView.getMainPanel(), null); // Call getMainPanel() on the instance

		tabPanel2 = createTabPanel(Color.BLUE);

		tabPanel4 = createTabPanel(Color.RED);
		tabPanel5 = createTabPanel(Color.YELLOW);
		
		 BloodGroupListModel bloodModel = new BloodGroupListModel();
         List<BloodGroupList> data = bloodModel.getAllPacks();
         BloodGroupView bloodView = new BloodGroupView(data);
         BloodGroupController bloodController = new BloodGroupController(bloodView, bloodModel);
		
		tabbedPane.addTab("2", bloodView.getBloodPanel());
		
		// Add donors list
		donorsListView = new DonorsListView();
		addDonorView = new AddDonorView();
		updateDonorView = new UpdateDonorView();
		donorsListModel = new DonorsListModel();
		donorsListController = new DonorsListController(donorsListView, addDonorView, updateDonorView, donorsListModel);
		tabbedPane.addTab("3",donorsListView.getMainPanel());
		
		tabbedPane.addTab("4", tabPanel4);
		
		 RendezvousListeModel model = new RendezvousListeModel();
	     RendezvousListeView view = new RendezvousListeView();
	     RendezvousListeController controller = new RendezvousListeController(model, view);
	     
	     controller.displayRendezvousList();
		tabbedPane.addTab("5", view.getMainPanel());

		// Hide the default navigation buttons
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			tabbedPane.setTabComponentAt(i, new JLabel()); // Use an empty JLabel as the tab component
		}

		frame.add(tabbedPane, BorderLayout.CENTER);
		// Center the frame on the screen
		frame.setLocationRelativeTo(null);
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(206, 0, 0));
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return button;
	}

	private JPanel createTabPanel(Color color) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		return panel;
	}

	public JButton getButton1() {
		return button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public JButton getButton3() {
		return button3;
	}

	public JButton getButton4() {
		return button4;
	}

	public JButton getButton5() {
		return button5;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	// Méthodes pour mettre à jour l'interface en fonction du modèle
	public void setSelectedTab(int index) {
		tabbedPane.setSelectedIndex(index);
	}

	public void buttonClicked(JButton source) {
		source.setBackground(new Color(153, 0, 0));
	}

	public void buttonReleased(JButton source) {
		source.setBackground(new Color(206, 0, 0));
	}

	public void AdminViewSetVisible() {
		frame.setVisible(true);
	}

	public void AdminViewSetInvisible() {
		frame.setVisible(false);
	}
}
