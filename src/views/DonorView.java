package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import controllers.PriseRendezVousControleur;
import utils.DraggableFrameUtil;

public class DonorView extends JFrame {
	private JFrame frame;
	private JButton button1;
	private JTabbedPane tabbedPane;
	private JButton button2;
	private JButton button3;
	private JButton closeButton;
	private JLabel titleLabel;
	private JPanel titlePanel;
	private JPanel menuPanel;
	private JButton logoutButton;
	
	private HomeView homeView;
	private PriseRendezVousVue donationsAppointmentView;
	private PriseRendezVousControleur controleur;
	
	private DonationsHistoryView donationsHistoryView;
	
	public DonorView(String username) {
		frame = new JFrame("Accueil Donneur");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(1540, 830);

		// Create a panel for the title bar
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		titlePanel.setBackground(new Color(206, 0, 0));
		titlePanel.setPreferredSize(new Dimension(1100, 45)); 

		titleLabel = new JLabel("Blood Donation System");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBorder(new EmptyBorder(4, 0, 0, 230));

		// Ajouter le label au centre du titlePanel
		titlePanel.add(titleLabel);
		titlePanel.add(Box.createHorizontalStrut(380));

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
		menuPanel.add(Box.createVerticalStrut(240));

		// Create the buttons
		button1 = createButton("Home");
		button2 = createButton("Take Appointment");
		button3 = createButton("Donation History");
		button1.setBorder(BorderFactory.createEmptyBorder(15, 108, 15, 108));
		button2.setBorder(BorderFactory.createEmptyBorder(15, 49, 15, 49));
        button3.setBorder(BorderFactory.createEmptyBorder(15,50, 15, 60));

		// Add the buttons to the menuPanel
		menuPanel.add(button1);
		menuPanel.add(Box.createVerticalStrut(30));
		menuPanel.add(button2);
		menuPanel.add(Box.createVerticalStrut(30));
		menuPanel.add(button3);

		menuPanel.add(Box.createVerticalStrut(245));

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
		
		
		homeView =new HomeView(username);
        tabbedPane.addTab("1",null,homeView.getMainPanel(),null);
      
        controleur= new PriseRendezVousControleur();
        donationsAppointmentView = new PriseRendezVousVue(controleur);
        tabbedPane.addTab("2", null, donationsAppointmentView.getMainPanel(), null);
        
        
        donationsHistoryView = new DonationsHistoryView();
        tabbedPane.addTab("3", null, donationsHistoryView.getMainPanel(), null);
        
		
		// Hide the default navigation buttons
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			tabbedPane.setTabComponentAt(i, new JLabel());
		}

		frame.add(tabbedPane, BorderLayout.CENTER);

		frame.setLocationRelativeTo(null);
		
		DraggableFrameUtil.makeDraggable(frame);
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

	public JButton getCloseButton() {
		return closeButton;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public void display() {
		SwingUtilities.invokeLater(() -> frame.setVisible(true));
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
	
	public DonationsHistoryView getDonationsHistoryView() {
	    return donationsHistoryView;
	}
   public void DonorViewSetVisible(){
	   frame.setVisible(true);
   }
   public void DonorViewSetInvisible(){
	   frame.setVisible(false);
   }
   }

