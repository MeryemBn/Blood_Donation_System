package views;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import controllers.PriseRendezVousControleur;

public class PriseRendezVousVue extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JComboBox<String> groupeSanguinComboBox;
    private JSpinner dateRendezVousSpinner;
    private JSpinner heureRendezVousSpinner;
    private PriseRendezVousControleur controleur;
    private  BackgroundPanel backgroundPanel ;
   

    public PriseRendezVousVue(PriseRendezVousControleur controleur) {
        super("Prise de rendez-vous");
        this.controleur = controleur;
        
     
        JLabel nomLabel = new JLabel("Nom : ");
        JLabel prenomLabel = new JLabel("Prénom : ");
        JLabel groupeSanguinLabel = new JLabel("Groupe sanguin : ");
        JLabel dateRendezVousLabel = new JLabel("Date du rendez-vous (AAAA-MM-JJ) : ");
        JLabel heureRendezVousLabel = new JLabel("Heure du rendez-vous (HH:MM) : ");
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        nomLabel.setFont(labelFont);
        prenomLabel.setFont(labelFont);
        groupeSanguinLabel.setFont(labelFont);
        dateRendezVousLabel.setFont(labelFont);
        heureRendezVousLabel.setFont(labelFont);

        nomLabel.setForeground(new Color(0xD0, 0x2E, 0x3B));
        prenomLabel.setForeground(new Color(0xD0, 0x2E, 0x3B));
        groupeSanguinLabel.setForeground(new Color(0xD0, 0x2E, 0x3B));
        dateRendezVousLabel.setForeground(new Color(0xD0, 0x2E, 0x3B));
        heureRendezVousLabel.setForeground(new Color(0xD0, 0x2E, 0x3B));

        
        

        // Configuration de la taille de la fenêtre
        setSize(900, 600); // Définition d'une taille plus grande

        // Création du panneau principal avec une image de fond
        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout()); // Utilisation d'un layout BorderLayout

       
        // Création du panneau pour le formulaire
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Utilisation d'un layout BoxLayout vertical
        formPanel.setPreferredSize(new Dimension(300, 400)); // Définition de la taille maximale du panneau
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajout de marges

        // Création des composants du formulaire
        nomField = new JTextField(10);
        prenomField = new JTextField(10);
        groupeSanguinComboBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});

        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateRendezVousSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateRendezVousSpinner, "yyyy-MM-dd");
        dateRendezVousSpinner.setEditor(dateEditor);

        SpinnerDateModel heureModel = new SpinnerDateModel();
        heureRendezVousSpinner = new JSpinner(heureModel);
        JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureRendezVousSpinner, "HH:mm");
        heureRendezVousSpinner.setEditor(heureEditor);
        
        // Création du bouton pour prendre rendez-vous
        JButton prendreRendezVousButton = new JButton("Prendre rendez-vous");
        prendreRendezVousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prendreRendezVous();
            }
        });
        
        prendreRendezVousButton.setBackground(Color.WHITE);
        prendreRendezVousButton.setForeground(new Color(0xD0, 0x2E, 0x3B));
       
        formPanel.setBackground(new Color(0xD0, 0x2E, 0x3B));
        // Ajout des composants au panneau de formulaire
        formPanel.add(createFormField(nomLabel, nomField));
        formPanel.add(Box.createVerticalStrut(5)); // Espace vertical
        formPanel.add(createFormField(prenomLabel, prenomField));
        formPanel.add(Box.createVerticalStrut(5)); // Espace vertical
        formPanel.add(createFormField(groupeSanguinLabel, groupeSanguinComboBox));
        formPanel.add(Box.createVerticalStrut(5)); // Espace vertical
        formPanel.add(createFormField(dateRendezVousLabel, dateRendezVousSpinner));
        formPanel.add(Box.createVerticalStrut(5)); // Espace vertical
        formPanel.add(createFormField(heureRendezVousLabel, heureRendezVousSpinner));
        formPanel.add(Box.createVerticalStrut(5)); // Espace vertical
        formPanel.add(prendreRendezVousButton);

        // Ajout du formulaire au panneau principal
        backgroundPanel.add(formPanel, BorderLayout.WEST);

        // Ajout du panneau principal à la fenêtre
        getContentPane().add(backgroundPanel);

        
    }
    
    

    // Méthode utilitaire pour créer un champ de formulaire avec une étiquette
    private JPanel createFormField(JLabel label, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label, BorderLayout.NORTH); // Ajout du JLabel passé en paramètre
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    // Méthode pour prendre rendez-vous
    private void prendreRendezVous() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String groupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
        LocalDate dateRendezVous = ((java.util.Date) dateRendezVousSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime heureRendezVous = ((java.util.Date) heureRendezVousSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        controleur.prendreRendezVouss(nom, prenom, groupeSanguin, dateRendezVous, heureRendezVous);
    }

    // Classe pour dessiner une image de fond
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Charger l'image de fond
            backgroundImage = new ImageIcon(getClass().getResource("/images/backgroundrv4.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessiner l'image de fond
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        
    }
    
    public JPanel getMainPanel() {
        return backgroundPanel;
    }
    
}
