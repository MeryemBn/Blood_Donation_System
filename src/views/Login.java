package views;

import javax.swing.*;

import utils.DraggableFrameUtil;

import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JRadioButton donorRadioButton;
    private JRadioButton adminRadioButton;
    private JButton closeButton;
    private JButton backButton;

    public Login() {
        setTitle("Login");
        setSize(766, 468);
        setUndecorated(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        closeButton =new JButton(new ImageIcon(getClass().getResource("/images/closeIcon.png")));
        closeButton.setBounds(707, 0, 59, 54);
        closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainPanel.add(closeButton);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setBounds(372, 146, 148, 30);
        mainPanel.add(usernameLabel); 

        usernameField = new JTextField();
        usernameField.setBounds(516, 152, 200, 25);
        mainPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setBounds(374, 205, 100, 25);
        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(516, 205, 200, 25);
        mainPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(449, 353, 200, 40);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.RED);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        loginButton.setFocusPainted(false);
        mainPanel.add(loginButton);
        
        backButton= new JButton(new ImageIcon(getClass().getResource("/images/backIcon.png")));
        backButton.setBounds(-5, 0, 59, 54);
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainPanel.add(backButton);
        
        ButtonGroup buttonGroup = new ButtonGroup();

        donorRadioButton = new JRadioButton("Donor Login");
        donorRadioButton.setForeground(Color.WHITE);
        donorRadioButton.setFont(new Font("Arial", Font.ITALIC, 14));
        donorRadioButton.setOpaque(false);
        donorRadioButton.setBounds(391, 277, 120, 25);
        buttonGroup.add(donorRadioButton);
        mainPanel.add(donorRadioButton);

        adminRadioButton = new JRadioButton("Admin Login");
        adminRadioButton.setForeground(Color.WHITE);
        adminRadioButton.setFont(new Font("Arial", Font.ITALIC, 14));
        adminRadioButton.setOpaque(false);
        adminRadioButton.setBounds(541, 277, 120, 25);
        buttonGroup.add(adminRadioButton);
        mainPanel.add(adminRadioButton);

        getContentPane().add(mainPanel);
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/background.jpg")));
        lblNewLabel.setBounds(-125, 0, 891, 468);
        mainPanel.add(lblNewLabel);
        DraggableFrameUtil.makeDraggable(this);
        setVisible(true);

    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JRadioButton getDonorRadioButton() {
        return donorRadioButton;
    }

    public JRadioButton getAdminRadioButton() {
        return adminRadioButton;
    }
 
    public JButton getCloseButton() {
        return closeButton;
    }
    public JButton getBackButton() {
        return backButton;
    }
}
