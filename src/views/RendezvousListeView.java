package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;
import models.RendezVousListe;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import models.RendezvousListeModel;

public class RendezvousListeView extends JPanel {
    private JTable AppointmentsList;
    private DefaultTableModel tableModel;
    private JPanel mainPanelListe;
    private RendezvousListeModel model;
    private JTextField nomField;
    private JTextField prenomField;
    private JComboBox<String> groupeSanguinComboBox;
    private JSpinner dateRendezVousSpinner;
    private JSpinner heureRendezVousSpinner;

    public RendezvousListeView() {
        mainPanelListe = new JPanel(new BorderLayout()); // Change to BorderLayout
        mainPanelListe.setBackground(new Color(240, 240, 240));
        mainPanelListe.setBorder(BorderFactory.createEmptyBorder(70, 10, 10, 10));
        model = new RendezvousListeModel();
      
		
     // Create a panel for the title with BorderLayout
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(240, 240, 240));

        // Title label
        JLabel titleLabel = new JLabel("<html><u>Appointments List</u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.red);
        titlePanel.add(titleLabel, BorderLayout.WEST); // Add title label to the left of titlePanel

        // Add button
        JButton addButton = new JButton("+ Add Appointment");
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addButton.setBackground(new Color(63, 81, 181));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        titlePanel.add(addButton, BorderLayout.EAST); // Add add button to the right of titlePanel

        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15)); // Add padding to titlePanel

        mainPanelListe.add(titlePanel, BorderLayout.NORTH); // Add titlePanel to the NORTH of mainPanelListe

     

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(255, 255, 255));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        // Création du modèle de tableau avec des noms de colonnes en anglais
        tableModel.addColumn("ID");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Blood Group");
        tableModel.addColumn("Date of Appointment");
        tableModel.addColumn("Time of Appointment");
        
        AppointmentsList = new JTable(tableModel);
     // Apply styling to the table
     		JTableHeader header = AppointmentsList.getTableHeader();
     		header.setBackground(new Color(63, 81, 181));
     		header.setForeground(Color.WHITE);
     		header.setFont(new Font("Arial", Font.PLAIN, 18));
     		AppointmentsList.setRowHeight(30);
     		AppointmentsList.setFont(new Font("Arial", Font.PLAIN, 16));
     		AppointmentsList.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        // Customizing table appearance...
        JScrollPane scrollPane = new JScrollPane(AppointmentsList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        mainPanelListe.add(scrollPane, BorderLayout.CENTER); // Add scrollPane to the CENTER of mainPanelListe

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        //buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 40, 0));
        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        updateButton.setBackground(new Color(63, 81, 181));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.setBackground(new Color(63, 81, 181));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
        mainPanelListe.add(buttonsPanel, BorderLayout.SOUTH); // Add buttonsPanel to the SOUTH of mainPanelListe

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Création de la fenêtre ou boîte de dialogue pour le formulaire d'ajout de rendez-vous
                JFrame addAppointmentFrame = new JFrame("Add Appointment");
                addAppointmentFrame.setPreferredSize(new Dimension(400, 300)); // Définir la taille de la fenêtre
                addAppointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre actuelle
                // Définir la couleur de fond du formulaire en blanc
                addAppointmentFrame.getContentPane().setBackground(Color.WHITE);
                
              

                addAppointmentFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181)));
                
                // Ajouter des marges au contenu de la fenêtre
                ((JComponent) addAppointmentFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                // Création des composants du formulaire
                JLabel nomLabel = new JLabel("Last Name: ");
                JTextField nomField = new JTextField(20);
                JLabel prenomLabel = new JLabel("First Name: ");
                JTextField prenomField = new JTextField(20);
                JLabel groupeSanguinLabel = new JLabel("Blood Group: ");
                JComboBox<String> groupeSanguinComboBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
                groupeSanguinComboBox.setBackground(Color.WHITE);
                JLabel dateRendezVousLabel = new JLabel("Date of Appointment: ");
                SpinnerDateModel dateModel = new SpinnerDateModel();
                dateRendezVousSpinner = new JSpinner(dateModel);
                JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateRendezVousSpinner, "yyyy-MM-dd");
                dateRendezVousSpinner.setEditor(dateEditor);
                JLabel heureRendezVousLabel = new JLabel("Time of Appointment: ");
                SpinnerDateModel heureModel = new SpinnerDateModel();
                heureRendezVousSpinner = new JSpinner(heureModel);
                JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureRendezVousSpinner, "HH:mm");
                heureRendezVousSpinner.setEditor(heureEditor);
                
                
          
               
             // Récupération de la valeur du Spinner pour la date
                java.util.Date selectedDate = (java.util.Date) dateRendezVousSpinner.getValue();
                java.util.Date selectedTime = (java.util.Date) heureRendezVousSpinner.getValue();



                // Ajout d'un bouton "Add" pour ajouter le rendez-vous
                JButton addButton = new JButton("Add");
                addButton.setFont(new Font("Arial", Font.PLAIN, 18));
                addButton.setBackground(new Color(63, 81, 181));
                addButton.setForeground(Color.WHITE);
                addButton.setFocusPainted(false);
                addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Récupération des données des champs du formulaire
                        String nom = nomField.getText();
                        String prenom = prenomField.getText();
                        String groupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
                        // Conversion des objets Date en LocalDate et LocalTime
                        LocalDate dateRendezVous = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalTime heureRendezVous = selectedTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();


                     // Création d'un nouvel objet Rendezvous avec les données récupérées
                        RendezVousListe rendezvous = new RendezVousListe (nom, prenom, groupeSanguin, dateRendezVous, heureRendezVous);

                

                        // Ajout du rendez-vous à la base de données via le modèle
                        model.addRendezvous(rendezvous);
                        
                        model.updateRendezvousList(tableModel);

                        // Fermeture de la fenêtre ou boîte de dialogue
                        addAppointmentFrame.dispose();
                    }
                });

                // Configuration du layout pour la fenêtre
                addAppointmentFrame.setLayout(new GridLayout(0, 2, 10, 10)); // 2 colonnes, espacement de 10 pixels

                // Ajout des composants au conteneur de la fenêtre
                addAppointmentFrame.add(nomLabel);
                addAppointmentFrame.add(nomField);
                addAppointmentFrame.add(prenomLabel);
                addAppointmentFrame.add(prenomField);
                addAppointmentFrame.add(groupeSanguinLabel);
                addAppointmentFrame.add(groupeSanguinComboBox);
                addAppointmentFrame.add(dateRendezVousLabel);
                addAppointmentFrame.add(dateRendezVousSpinner);
                addAppointmentFrame.add(heureRendezVousLabel);
                addAppointmentFrame.add(heureRendezVousSpinner);
                addAppointmentFrame.add(addButton);

                // Affichage de la fenêtre
                addAppointmentFrame.pack();
                addAppointmentFrame.setLocationRelativeTo(null);
                addAppointmentFrame.setVisible(true);
            }
        });

        
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = AppointmentsList.getSelectedRow();
                if (selectedRow != -1) {
                    // Récupérer les informations de la ligne sélectionnée
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String nom = (String) tableModel.getValueAt(selectedRow, 1);
                    String prenom = (String) tableModel.getValueAt(selectedRow, 2);
                    String groupeSanguin = (String) tableModel.getValueAt(selectedRow, 3);
                    LocalDate dateRendezvous = (LocalDate) tableModel.getValueAt(selectedRow, 4);
                    LocalTime heureRendezvous = (LocalTime) tableModel.getValueAt(selectedRow, 5);
                    
                    // Créer un nouveau formulaire de mise à jour et l'afficher
                    JFrame updateAppointmentFrame = new JFrame("Update Appointment");
                    updateAppointmentFrame.setPreferredSize(new Dimension(400, 300)); // Définir la taille de la fenêtre
                    updateAppointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre actuelle
                    updateAppointmentFrame.getContentPane().setBackground(Color.WHITE); // Définir la couleur de fond du formulaire en blanc
                    updateAppointmentFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181))); // Ajouter des marges au contenu de la fenêtre
                    ((JComponent) updateAppointmentFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajouter des marges au contenu de la fenêtre

                    // Création des composants du formulaire
                    JLabel nomLabel = new JLabel("Last Name: ");
                    JTextField nomField = new JTextField(20);
                    nomField.setText(nom); // Remplir le champ avec la valeur existante
                    JLabel prenomLabel = new JLabel("First Name: ");
                    JTextField prenomField = new JTextField(20);
                    prenomField.setText(prenom); // Remplir le champ avec la valeur existante
                    JLabel groupeSanguinLabel = new JLabel("Blood Group: ");
                    JComboBox<String> groupeSanguinComboBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
                    groupeSanguinComboBox.setSelectedItem(groupeSanguin); // Sélectionner la valeur existante dans la liste déroulante
                    groupeSanguinComboBox.setBackground(Color.WHITE);
                    JLabel dateRendezVousLabel = new JLabel("Date of Appointment: ");
                    JTextField dateRendezVousField = new JTextField(20);
                    dateRendezVousField.setText(dateRendezvous.toString()); // Afficher la date existante
                    JLabel heureRendezVousLabel = new JLabel("Time of Appointment: ");
                    JTextField heureRendezVousField = new JTextField(20);
                    heureRendezVousField.setText(heureRendezvous.toString()); // Afficher l'heure existante
                    
                    // Ajout d'un bouton "Update" pour mettre à jour le rendez-vous
                    JButton updateButton = new JButton("Update");
                    updateButton.setFont(new Font("Arial", Font.PLAIN, 18));
                    updateButton.setBackground(new Color(63, 81, 181));
                    updateButton.setForeground(Color.WHITE);
                    updateButton.setFocusPainted(false);
                    updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    updateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                    updateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Récupérer les nouvelles valeurs des champs du formulaire
                            String newNom = nomField.getText();
                            String newPrenom = prenomField.getText();
                            String newGroupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
                            String newDateRendezvousText = dateRendezVousField.getText();
                            String newHeureRendezvousText = heureRendezVousField.getText();
                            
                            // Convertir la date de type String en LocalDate
                            LocalDate newDateRendezvous = LocalDate.parse(newDateRendezvousText);
                            // Convertir l'heure de type String en LocalTime
                            LocalTime newHeureRendezvous = LocalTime.parse(newHeureRendezvousText);
                            
                            // Mettre à jour la ligne correspondante dans le modèle de la table
                            tableModel.setValueAt(newNom, selectedRow, 1);
                            tableModel.setValueAt(newPrenom, selectedRow, 2);
                            tableModel.setValueAt(newGroupeSanguin, selectedRow, 3);
                            tableModel.setValueAt(newDateRendezvous, selectedRow, 4);
                            tableModel.setValueAt(newHeureRendezvous, selectedRow, 5);
                            
                         // Mettre à jour les données dans la base de données
                            RendezvousListeModel rendezvousModel = new RendezvousListeModel();
                            RendezVousListe rendezvousToUpdate = new RendezVousListe();
                            rendezvousToUpdate.setId(id); // Récupérer l'ID du rendez-vous à partir de la table
                            rendezvousToUpdate.setNom(newNom);
                            rendezvousToUpdate.setPrenom(newPrenom);
                            rendezvousToUpdate.setGroupeSanguin(newGroupeSanguin);
                            rendezvousToUpdate.setDateRendezvous(newDateRendezvous);
                            rendezvousToUpdate.setHeureRendezvous(newHeureRendezvous);
                            rendezvousModel.updateRendezvous(rendezvousToUpdate);
                            
                            
                            
                            // Fermer la fenêtre de mise à jour
                            updateAppointmentFrame.dispose();
                        }
                    });

                    // Configuration du layout pour la fenêtre
                    updateAppointmentFrame.setLayout(new GridLayout(0, 2, 10, 10)); // 2 colonnes, espacement de 10 pixels

                    // Ajout des composants au conteneur de la fenêtre
                    updateAppointmentFrame.add(nomLabel);
                    updateAppointmentFrame.add(nomField);
                    updateAppointmentFrame.add(prenomLabel);
                    updateAppointmentFrame.add(prenomField);
                    updateAppointmentFrame.add(groupeSanguinLabel);
                    updateAppointmentFrame.add(groupeSanguinComboBox);
                    updateAppointmentFrame.add(dateRendezVousLabel);
                    updateAppointmentFrame.add(dateRendezVousField);
                    updateAppointmentFrame.add(heureRendezVousLabel);
                    updateAppointmentFrame.add(heureRendezVousField);
                    updateAppointmentFrame.add(updateButton);

                    // Affichage de la fenêtre
                    updateAppointmentFrame.pack();
                    updateAppointmentFrame.setLocationRelativeTo(null); // Centrer la fenêtre
                    updateAppointmentFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(RendezvousListeView.this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = AppointmentsList.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(RendezvousListeView.this, "Are you sure you want to delete this entry?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int rendezvousID = (int) tableModel.getValueAt(selectedRow, 0); // Supposons que la colonne 0 contient l'ID du rendez-vous
                        model.deleteRendezvous(rendezvousID); // Suppression du rendez-vous de la base de données
                        tableModel.removeRow(selectedRow); // Suppression du rendez-vous de la liste
                    }
                } else {
                    JOptionPane.showMessageDialog(RendezvousListeView.this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        add(mainPanelListe, BorderLayout.CENTER); // Add mainPanelListe to the JPanel (this)
    }

    public JPanel getMainPanel() {
        return mainPanelListe;
    }

    public void displayRendezvousList(List<RendezVousListe> rendezvousList) {
        tableModel.setRowCount(0); // Clear previous data
        for (RendezVousListe rendezvous : rendezvousList) {
            tableModel.addRow(new Object[]{rendezvous.getId(), rendezvous.getNom(), rendezvous.getPrenom(),
                    rendezvous.getGroupeSanguin(), rendezvous.getDateRendezvous(), rendezvous.getHeureRendezvous()});
        }
    }
}