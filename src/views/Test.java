package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test implements ActionListener{
    private JFrame frame;
    private JButton button1;
    private JTabbedPane tabbedPane;
    private JButton button2;
    private JButton button3;
    private JButton closeButton;
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel menuPanel;
    private JLabel menuLabel;
    private JPanel tabPanel1;
    private JPanel tabPanel2;
    private JPanel tabPanel3;

    public Test(String username) {
        // Création et configuration de la fenêtre.
        frame = new JFrame("Accueil Donneur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(1100, 670);

        // Create a panel for the title bar
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align components to the right
        titlePanel.setBackground(new Color(206, 0, 0)); // Set background color of the title bar
        titlePanel.setPreferredSize(new Dimension(1100,45));
        
        titleLabel = new JLabel("Blood Donation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        // Ajouter le label au centre du titlePanel
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(400)); // Ajouter de la marge à droite pour centrer le label


        // Add a close button to the title bar
        closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
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
        menuPanel.add(Box.createVerticalStrut(160));

        // Create the buttons
        button1 = createButton("Home");
        button2 = createButton("Take Appointment");
        button3 = createButton("View Donation History");
        button1.setBorder(BorderFactory.createEmptyBorder(15, 108, 15, 108));
        button2.setBorder(BorderFactory.createEmptyBorder(15, 49, 15, 49));
        button3.setBorder(BorderFactory.createEmptyBorder(15,26, 15, 34));

        // Add the buttons to the menuPanel
        menuPanel.add(button1);
        menuPanel.add(Box.createVerticalStrut(30));
        menuPanel.add(button2);
        menuPanel.add(Box.createVerticalStrut(30));
        menuPanel.add(button3);

        menuPanel.add(Box.createVerticalStrut(160), BorderLayout.NORTH);

     // Créez le bouton de déconnexion avec une icône
     JButton logoutButton = new JButton("Logout", new ImageIcon(getClass().getResource("/images/logout.png")));
     logoutButton.setFont(new Font("Arial", Font.BOLD, 19));
     logoutButton.setForeground(Color.WHITE);
     logoutButton.setBackground(new Color(206, 0, 0));
     logoutButton.setFocusPainted(false);
     logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 87, 10, 87));
     logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

     // Ajoutez un ActionListener au bouton de déconnexion
     logoutButton.addActionListener(this);

     // Ajoutez le bouton de déconnexion au sud (en bas) du menuPanel
     menuPanel.add(logoutButton, BorderLayout.SOUTH);
        
        // Add the menuPanel to the frame
        frame.getContentPane().add(menuPanel, BorderLayout.WEST);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(-9, -2,-2, -2));
        tabPanel1 = createTabPanel(Color.WHITE);
        tabPanel2 = createTabPanel(Color.BLUE);
        tabPanel3 = createTabPanel(Color.GREEN);

        tabbedPane.addTab("1", tabPanel1);
        tabbedPane.addTab("2", tabPanel2);
        tabbedPane.addTab("3", tabPanel3);

        // Hide the default navigation buttons
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setTabComponentAt(i, new JLabel()); // Use an empty JLabel as the tab component
        }
        

        frame.add(tabbedPane, BorderLayout.CENTER);

        // Add action listeners to the buttons
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        closeButton.addActionListener(this);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
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

    // Méthode appelée lorsqu'un bouton est cliqué
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            tabbedPane.setSelectedIndex(0); // Afficher le premier onglet
            buttonClicked(button1);
            buttonReleased(button2);
            buttonReleased(button3);
        } else if (e.getSource() == button2) {
            tabbedPane.setSelectedIndex(1); // Afficher le deuxième onglet
            buttonClicked(button2);
            buttonReleased(button1);
            buttonReleased(button3);
        } else if (e.getSource() == button3) {
            tabbedPane.setSelectedIndex(2); // Afficher le troisième onglet
            buttonClicked(button3);
            buttonReleased(button2);
            buttonReleased(button1);
        } else if (e.getSource() == closeButton) {
            System.exit(0);
        }
    }
    public void buttonClicked(JButton source) {
        source.setBackground(new Color(153, 0, 0));
    }
    public void buttonReleased(JButton source) {
        source.setBackground(new Color(206, 0, 0));
    }

   
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	String username = "";
                new Test(username);
            }
        });
    }
    }

