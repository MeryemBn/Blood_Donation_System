package views;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonneurAccueil extends JFrame implements ActionListener{
    private JButton appointmentButton;
    private JButton historyButton;
    private JButton logoutButton;
    private JButton closeButton; 
    private JLabel welcomeLabel;

    public DonneurAccueil(String username) {
        setTitle("Donor Screen");
        setSize(1066, 668);
        setLocationRelativeTo(null); 
        setUndecorated(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255)); // White background

        // Top navigation bar
        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(178, 34, 34)); // Red color
        navBar.setPreferredSize(new Dimension(getWidth(), 40)); // Set height of nav bar
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        closeButton = new JButton("X");
        closeButton.setBounds(1007, -7, 59, 54);
        closeButton.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust position and size
        closeButton.addActionListener(this);
        closeButton.setFocusPainted(false); // Remove focus indication
        closeButton.setContentAreaFilled(false); // Make the button transparent
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainPanel.add(closeButton);
        // Appointment button
        appointmentButton = new JButton("Take Appointment");
        appointmentButton.setFont(new Font("Arial", Font.BOLD, 16));
        appointmentButton.setBackground(new Color(255, 255, 255)); // White color
        appointmentButton.setForeground(new Color(178, 34, 34)); // Red color
        appointmentButton.addActionListener(this);
        navBar.add(appointmentButton);

        // History button
        historyButton = new JButton("View Donation History");
        historyButton.setFont(new Font("Arial", Font.BOLD, 16));
        historyButton.setBackground(new Color(255, 255, 255)); // White color
        historyButton.setForeground(new Color(178, 34, 34)); // Red color
        historyButton.addActionListener(this);
        navBar.add(historyButton);

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 255, 255)); // White color
        logoutButton.setForeground(new Color(178, 34, 34)); // Red color
        logoutButton.addActionListener(this);
        navBar.add(logoutButton);

        mainPanel.add(navBar, BorderLayout.NORTH);
        navBar.add(Box.createHorizontalStrut(100));
        
        JLabel titleLabel = new JLabel("Blood donation system");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        navBar.add(titleLabel);

        // Panel for slogans and center presentation
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.setBackground(new Color(255, 255, 255)); // White background
        
        welcomeLabel = new JLabel("Welcome " + username);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLACK);
        centerPanel.add(welcomeLabel);
        // Slogans concerning the benefits of blood donation
        JLabel slogan1 = new JLabel("Your blood donation can save lives!");
        slogan1.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(slogan1);

        JLabel slogan2 = new JLabel("Be a hero, donate blood!");
        slogan2.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(slogan2);

        JLabel slogan3 = new JLabel("Every drop counts!");
        slogan3.setFont(new Font("Arial", Font.BOLD, 24));
        centerPanel.add(slogan3);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setVisible(true);
    }   
        public void actionPerformed(ActionEvent e) {
             if (e.getSource() == closeButton) {
                // Close button action
                dispose(); // Close the JFrame
            }
             if (e.getSource() == logoutButton) {
            	 int option = JOptionPane.showConfirmDialog(DonneurAccueil.this,
                         "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                 
                 // Vérifier si l'utilisateur a choisi "Yes"
                 if (option == JOptionPane.YES_OPTION) {
                     dispose(); // Fermer le frame actuel
                     // Ouvrir le frame de connexion
                     new login().setVisible(true);
                 }

             }
        }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	String username = "";
                new DonneurAccueil(username);
            }
        });
    }
}