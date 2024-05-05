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
        titlePanel.setBackground(new Color(206, 0, 0)); // Set the background color
        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set the font and size
        titleLabel.setForeground(Color.WHITE); // Set the foreground color
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
        //backButton.setBounds(15, 0, 59, 54);
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
        URL imageURL = getClass().getResource("/images/homeimg.jpg"); // Replace "your_image.jpg" with the path to your image
        ImageIcon imageIcon = new ImageIcon(imageURL);
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(370, 468, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImg); // transform it back
        imageLabel.setIcon(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 5, 5));
        formPanel.setBackground(Color.WHITE); // Set background color
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        nomField = new JTextField();
        nomField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel groupeSanguinLabel = new JLabel("Groupe Sanguin:");
        groupeSanguinLabel.setFont(new Font("Dubai", Font.ITALIC |Font.BOLD, 16)); // Set font and size
        String[] groupeSanguinOptions = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        groupeSanguinComboBox = new JComboBox<>(groupeSanguinOptions);
        groupeSanguinComboBox.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        ageField = new JTextField();
        ageField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        adresseField = new JTextField();
        adresseField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel numTelLabel = new JLabel("Numéro de Téléphone:");
        numTelLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        numTelField = new JTextField();
        numTelField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        usernameField = new JTextField();
        usernameField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Dubai", Font.ITALIC|Font.BOLD, 16)); // Set font and size
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dubai", Font.ITALIC, 16)); // Set font and size
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dubai", Font.ITALIC |Font.BOLD, 22)); // Set font and size
        registerButton.setBackground(new Color(206, 0, 0));
        registerButton.setForeground(Color.WHITE);

        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(groupeSanguinLabel);
        formPanel.add(groupeSanguinComboBox);
        formPanel.add(sexLabel);
        formPanel.add(maleRadioButton);
        formPanel.add(new JLabel()); // Empty label for alignment
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
        formPanel.add(new JLabel()); // Empty label for alignment
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
