package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.Date;
import java.util.List;
import models.RendezVousListe;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import models.RendezvousListeModel;
import utils.DraggableFrameUtil;

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
		mainPanelListe = new JPanel(new BorderLayout()); 
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
		titlePanel.add(titleLabel, BorderLayout.WEST);

		// Add button
		JButton addButton = new JButton("+ New Appointment");
		addButton.setFont(new Font("Arial", Font.PLAIN, 18));
		addButton.setBackground(new Color(63, 81, 181));
		addButton.setForeground(Color.WHITE);
		addButton.setFocusPainted(false);
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
		
		titlePanel.add(addButton, BorderLayout.EAST); 
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		
		mainPanelListe.add(titlePanel, BorderLayout.NORTH);

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
		mainPanelListe.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout());

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
		mainPanelListe.add(buttonsPanel, BorderLayout.SOUTH); 

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Création de la fenêtre ou boîte de dialogue pour le formulaire d'ajout de
				// rendez-vous
				JFrame addAppointmentFrame = new JFrame("Add Appointment");
				// taille de la fenêtre
				addAppointmentFrame.setMinimumSize(new Dimension(500, 300));
				addAppointmentFrame.setUndecorated(true);
				addAppointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
				
				
				addAppointmentFrame.getContentPane().setBackground(Color.WHITE);

				addAppointmentFrame.getRootPane()
						.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181)));

				
				((JComponent) addAppointmentFrame.getContentPane())
						.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

				// Création des composants du formulaire
				JLabel nomLabel = new JLabel("Last Name: ");
				nomLabel.setFont(new Font("Arial", Font.BOLD, 17));
				JTextField nomField = new JTextField();
				nomField.setFont(new Font("Arial", Font.PLAIN, 16));
				nomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 30));

				JLabel prenomLabel = new JLabel("First Name: ");
				prenomLabel.setFont(new Font("Arial", Font.BOLD, 17));
				JTextField prenomField = new JTextField(20);
				prenomField.setFont(new Font("Arial", Font.PLAIN, 16));
				prenomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 30));

				JLabel groupeSanguinLabel = new JLabel("Blood Group: ");
				groupeSanguinLabel.setFont(new Font("Arial", Font.BOLD, 17));
				JComboBox<String> groupeSanguinComboBox = new JComboBox<>(
						new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" });
				groupeSanguinComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
				groupeSanguinComboBox.setForeground(Color.BLACK);
				groupeSanguinComboBox.setBackground(Color.WHITE);

				JLabel dateRendezVousLabel = new JLabel("Date of Appointment: ");
				dateRendezVousLabel.setFont(new Font("Arial", Font.BOLD, 17));
				SpinnerDateModel dateModel = new SpinnerDateModel();
				dateRendezVousSpinner = new JSpinner(dateModel);
				JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateRendezVousSpinner, "yyyy-MM-dd");
				dateRendezVousSpinner.setEditor(dateEditor);

				JLabel heureRendezVousLabel = new JLabel("Time of Appointment: ");
				heureRendezVousLabel.setFont(new Font("Arial", Font.BOLD, 17));
				SpinnerDateModel heureModel = new SpinnerDateModel();
				heureRendezVousSpinner = new JSpinner(heureModel);
				JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureRendezVousSpinner, "HH:mm");
				heureRendezVousSpinner.setEditor(heureEditor);

				// Récupération de la valeur du Spinner pour la date
				java.util.Date selectedDate = (java.util.Date) dateRendezVousSpinner.getValue();
				java.util.Date selectedTime = (java.util.Date) heureRendezVousSpinner.getValue();

				// Ajout d'un bouton "Add" pour ajouter le rendez-vous
				JButton addButton = createButton("Add");
				JButton cancelButton = createButton("Cancel");

				// create button panel for add and cancel
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				buttonPanel.setBackground(new Color(255, 255, 255));
				buttonPanel.add(addButton);
				buttonPanel.add(cancelButton);
				buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

				addButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (nomField.getText().isEmpty() || prenomField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(addAppointmentFrame, "Please fill in all fields",
									"Missing Information", JOptionPane.WARNING_MESSAGE);
						} else {
							// Récupération des données des champs du formulaire
							String nom = nomField.getText();
							String prenom = prenomField.getText();
							String groupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
							// Conversion des objets Date en LocalDate et LocalTime
							LocalDate dateRendezVous = selectedDate.toInstant().atZone(ZoneId.systemDefault())
									.toLocalDate();
							LocalTime heureRendezVous = selectedTime.toInstant().atZone(ZoneId.systemDefault())
									.toLocalTime();

							// Création d'un nouvel objet Rendezvous avec les données récupérées
							RendezVousListe rendezvous = new RendezVousListe(nom, prenom, groupeSanguin, dateRendezVous,
									heureRendezVous);

							// Ajout du rendez-vous à la base de données via le modèle
							model.addRendezvous(rendezvous);

							model.updateRendezvousList(tableModel);
							nomField.setText(null);
							prenomField.setText(null);
							JOptionPane.showMessageDialog(addAppointmentFrame, "Appointment added successfully", "Success",
									JOptionPane.INFORMATION_MESSAGE);
	
						}
					}
				});

				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addAppointmentFrame.dispose();
					}
				});

				addAppointmentFrame.setLayout(new BorderLayout());
				
				// create the form panel
				JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
				formPanel.setBackground(new Color(255, 255, 255));
				// Ajout des composants au conteneur de la fenêtre
				formPanel.add(nomLabel);
				formPanel.add(nomField);
				formPanel.add(prenomLabel);
				formPanel.add(prenomField);
				formPanel.add(groupeSanguinLabel);
				formPanel.add(groupeSanguinComboBox);
				formPanel.add(dateRendezVousLabel);
				formPanel.add(dateRendezVousSpinner);
				formPanel.add(heureRendezVousLabel);
				formPanel.add(heureRendezVousSpinner);

				addAppointmentFrame.add(formPanel, BorderLayout.CENTER);
				addAppointmentFrame.add(buttonPanel, BorderLayout.SOUTH);
				// Affichage de la fenêtre
				addAppointmentFrame.pack();
				addAppointmentFrame.setLocationRelativeTo(null);
				addAppointmentFrame.setVisible(true);
				
				DraggableFrameUtil.makeDraggable(addAppointmentFrame);
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
					// la taille de la fenêtre
					updateAppointmentFrame.setMinimumSize(new Dimension(500, 300));
					updateAppointmentFrame.setUndecorated(true);
					updateAppointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
					updateAppointmentFrame.getContentPane().setBackground(Color.WHITE); 
					updateAppointmentFrame.getRootPane()
							.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(63, 81, 181))); 
					((JComponent) updateAppointmentFrame.getContentPane())
							.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

					// Création des composants du formulaire
					JLabel nomLabel = new JLabel("Last Name: ");
					nomLabel.setFont(new Font("Arial", Font.BOLD, 17));
					JTextField nomField = new JTextField();
					nomField.setFont(new Font("Arial", Font.PLAIN, 16));
					nomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 30));
					nomField.setText(nom); 

					JLabel prenomLabel = new JLabel("First Name: ");
					prenomLabel.setFont(new Font("Arial", Font.BOLD, 17));
					JTextField prenomField = new JTextField(20);
					prenomField.setFont(new Font("Arial", Font.PLAIN, 16));
					prenomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 30));
					prenomField.setText(prenom); 

					JLabel groupeSanguinLabel = new JLabel("Blood Group: ");
					groupeSanguinLabel.setFont(new Font("Arial", Font.BOLD, 17));
					JComboBox<String> groupeSanguinComboBox = new JComboBox<>(
							new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" });
					groupeSanguinComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
					groupeSanguinComboBox.setForeground(Color.BLACK);
					groupeSanguinComboBox.setBackground(Color.WHITE);
					groupeSanguinComboBox.setSelectedItem(groupeSanguin); 
					
					JLabel dateRendezVousLabel = new JLabel("Date of Appointment: ");
					dateRendezVousLabel.setFont(new Font("Arial", Font.BOLD, 17));
					SpinnerDateModel dateModel = new SpinnerDateModel();
					dateRendezVousSpinner = new JSpinner(dateModel);
					JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateRendezVousSpinner, "yyyy-MM-dd");
					dateRendezVousSpinner.setEditor(dateEditor);
		            dateRendezVousSpinner.setValue(Date.from(dateRendezvous.atStartOfDay(ZoneId.systemDefault()).toInstant()));

					JLabel heureRendezVousLabel = new JLabel("Time of Appointment: ");
					heureRendezVousLabel.setFont(new Font("Arial", Font.BOLD, 17));
					SpinnerDateModel heureModel = new SpinnerDateModel();
					heureRendezVousSpinner = new JSpinner(heureModel);
					JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureRendezVousSpinner, "HH:mm");
					heureRendezVousSpinner.setEditor(heureEditor);
		            heureRendezVousSpinner.setValue(Date.from(heureRendezvous.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));

					
					JButton updateButton = createButton("Save");
					JButton cancelButton = createButton("Cancel");

					// create button panel for add and cancel
					JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					buttonPanel.setBackground(new Color(255, 255, 255));
					buttonPanel.add(updateButton);
					buttonPanel.add(cancelButton);
					buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));


					updateButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (nomField.getText().isEmpty() || prenomField.getText().isEmpty()) {
								JOptionPane.showMessageDialog(updateAppointmentFrame, "Please fill in all fields",
										"Missing Information", JOptionPane.WARNING_MESSAGE);
							} else {
								// Récupérer les nouvelles valeurs des champs du formulaire
								String newNom = nomField.getText();
								String newPrenom = prenomField.getText();
								String newGroupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
		                        Date newDateRendezvous = (Date) dateRendezVousSpinner.getValue();
		                        Date newHeureRendezvous = (Date) heureRendezVousSpinner.getValue();

		                     // Convertir la date de type String en LocalDate
		                        LocalDate newDate = Instant.ofEpochMilli(newDateRendezvous.getTime())
		                                .atZone(ZoneId.systemDefault()).toLocalDate();
		                        // Convertir l'heure de type String en LocalTime
		                        LocalTime newTime = Instant.ofEpochMilli(newHeureRendezvous.getTime())
		                                .atZone(ZoneId.systemDefault()).toLocalTime();
								// Mettre à jour la ligne correspondante dans le modèle de la table
								tableModel.setValueAt(newNom, selectedRow, 1);
								tableModel.setValueAt(newPrenom, selectedRow, 2);
								tableModel.setValueAt(newGroupeSanguin, selectedRow, 3);
								tableModel.setValueAt(newDate, selectedRow, 4);
								tableModel.setValueAt(newTime, selectedRow, 5);

								// Mettre à jour les données dans la base de données
								RendezvousListeModel rendezvousModel = new RendezvousListeModel();
								RendezVousListe rendezvousToUpdate = new RendezVousListe();
								rendezvousToUpdate.setId(id); 
								rendezvousToUpdate.setNom(newNom);
								rendezvousToUpdate.setPrenom(newPrenom);
								rendezvousToUpdate.setGroupeSanguin(newGroupeSanguin);
								rendezvousToUpdate.setDateRendezvous(newDate);
								rendezvousToUpdate.setHeureRendezvous(newTime);
								rendezvousModel.updateRendezvous(rendezvousToUpdate);

								JOptionPane.showMessageDialog(updateAppointmentFrame, "Appointment updated successfully", "Success",
										JOptionPane.INFORMATION_MESSAGE);
								// Fermer la fenêtre de mise à jour
								updateAppointmentFrame.dispose();
							}
						}
					});

					cancelButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							updateAppointmentFrame.dispose();
						}
					});
					// Configuration du layout pour la fenêtre
					updateAppointmentFrame.setLayout(new BorderLayout()); 

					JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
					formPanel.setBackground(new Color(255, 255, 255));
					
					// Ajout des composants au conteneur de la fenêtre
					formPanel.add(nomLabel);
					formPanel.add(nomField);
					formPanel.add(prenomLabel);
					formPanel.add(prenomField);
					formPanel.add(groupeSanguinLabel);
					formPanel.add(groupeSanguinComboBox);
					formPanel.add(dateRendezVousLabel);
		            formPanel.add(dateRendezVousSpinner);
					formPanel.add(heureRendezVousLabel);
		            formPanel.add(heureRendezVousSpinner);
					
					updateAppointmentFrame.add(formPanel, BorderLayout.CENTER);
					updateAppointmentFrame.add(buttonPanel, BorderLayout.SOUTH);

					// Affichage de la fenêtre
					updateAppointmentFrame.pack();
					updateAppointmentFrame.setLocationRelativeTo(null); // Centrer la fenêtre
					updateAppointmentFrame.setVisible(true);
					DraggableFrameUtil.makeDraggable(updateAppointmentFrame);

					
				} else {
					JOptionPane.showMessageDialog(RendezvousListeView.this, "Please select a row to update.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = AppointmentsList.getSelectedRow();
				if (selectedRow != -1) {
					int confirm = JOptionPane.showConfirmDialog(RendezvousListeView.this,
							"Are you sure you want to delete this entry?", "Delete Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						int rendezvousID = (int) tableModel.getValueAt(selectedRow, 0); 
						model.deleteRendezvous(rendezvousID); 
						tableModel.removeRow(selectedRow); 
						JOptionPane.showMessageDialog(RendezvousListeView.this,"Appointment deleted successfully", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(RendezvousListeView.this, "Please select a row to delete.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(mainPanelListe, BorderLayout.CENTER); 
	}

	public JPanel getMainPanel() {
		return mainPanelListe;
	}

	public void displayRendezvousList(List<RendezVousListe> rendezvousList) {
		tableModel.setRowCount(0); // Clear previous data
		for (RendezVousListe rendezvous : rendezvousList) {
			tableModel.addRow(new Object[] { rendezvous.getId(), rendezvous.getNom(), rendezvous.getPrenom(),
					rendezvous.getGroupeSanguin(), rendezvous.getDateRendezvous(), rendezvous.getHeureRendezvous() });
		}
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 18));
		button.setBackground(new Color(63, 81, 181));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return button;
	}
}