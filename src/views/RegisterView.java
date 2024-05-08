package views;
import javax.swing.*;

import utils.DraggableFrameUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class RegisterView extends JFrame {
    private JTextField nomField;
    private JComboBox<String> groupeSanguinComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField ageField;
    private JTextField adresseField;
    private JTextField numTelField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JButton closeButton;

    public RegisterView() {
        setTitle("Register");
        setSize(766, 468);
        setUndecorated(true); // Make the frame undecorated
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel for the title bar
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(206, 0, 0)); 
        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 300, 10, 10));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(6, 20, 6, 20));

        // Close button
        closeButton = new JButton(new ImageIcon(getClass().getResource("/images/closeIcon.png")));
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(206, 0, 0));
        closeButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        backButton= new JButton(new ImageIcon(getClass().getResource("/images/backIcon.png")));
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        titlePanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(closeButton, BorderLayout.EAST);

        getContentPane().add(titlePanel, BorderLayout.NORTH);

        // Panel for the image
        JPanel imagePanel = new JPanel(); 
        imagePanel.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel();
        imageLabel.setBackground(new Color(156, 183, 243));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        URL imageURL = getClass().getResource("/images/homeimg.jpg"); 
        ImageIcon imageIcon = new ImageIcon(imageURL);
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(370, 468, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImg); // transform it back
        imageLabel.setIcon(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 5, 5));
        formPanel.setBackground(Color.WHITE); 
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16));
        nomField = new JTextField();
        nomField.setFont(new Font("Dubai", Font.ITALIC, 16));
        JLabel groupeSanguinLabel = new JLabel("Groupe Sanguin:");
        groupeSanguinLabel.setFont(new Font("Dubai", Font.ITALIC |Font.BOLD, 16));
        String[] groupeSanguinOptions = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        groupeSanguinComboBox = new JComboBox<>(groupeSanguinOptions);
        groupeSanguinComboBox.setFont(new Font("Dubai", Font.ITALIC, 16));
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); 
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Dubai", Font.ITALIC, 16));
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setFont(new Font("Dubai", Font.ITALIC, 16));
        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16));
        ageField = new JTextField();
        ageField.setFont(new Font("Dubai", Font.ITALIC, 16)); 
        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); 
        adresseField = new JTextField();
        adresseField.setFont(new Font("Dubai", Font.ITALIC, 16)); 
        JLabel numTelLabel = new JLabel("Numéro de Téléphone:");
        numTelLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); 
        numTelField = new JTextField();
        numTelField.setFont(new Font("Dubai", Font.ITALIC, 16)); 
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); 
        usernameField = new JTextField();
        usernameField.setFont(new Font("Dubai", Font.ITALIC, 16)); 
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); 
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dubai", Font.ITALIC, 16));
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dubai", Font.ITALIC |Font.BOLD, 22));
        registerButton.setBackground(new Color(206, 0, 0));
        registerButton.setForeground(Color.WHITE);

        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(groupeSanguinLabel);
        formPanel.add(groupeSanguinComboBox);
        formPanel.add(sexLabel);
        formPanel.add(maleRadioButton);
        formPanel.add(new JLabel()); 
        formPanel.add(femaleRadioButton);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(adresseLabel);
        formPanel.add(adresseField);
        formPanel.add(numTelLabel);
        formPanel.add(numTelField);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(new JLabel()); 
        formPanel.add(registerButton);

        getContentPane().add(imagePanel, BorderLayout.WEST);
        getContentPane().add(formPanel, BorderLayout.CENTER);
        DraggableFrameUtil.makeDraggable(this);
    }

    public JTextField getNomField() {
        return nomField;
    }

    public JComboBox<String> getGroupeSanguinComboBox() {
        return groupeSanguinComboBox;
    }

    public JRadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public JRadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getAdresseField() {
        return adresseField;
    }

    public JTextField getNumTelField() {
        return numTelField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
    public JButton getCloseButton() {
        return closeButton;
    }
}
