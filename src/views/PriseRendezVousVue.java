package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import controllers.PriseRendezVousControleur;
import views.CustomComboBoxRenderer;

public class PriseRendezVousVue extends JFrame {
	private JTextField nomField;
	private JTextField prenomField;
	private JComboBox<String> groupeSanguinComboBox;
	private JSpinner dateRendezVousSpinner;
	private JSpinner heureRendezVousSpinner;
	private PriseRendezVousControleur controleur;
	private BackgroundPanel backgroundPanel;

	public PriseRendezVousVue(PriseRendezVousControleur controleur) {
		super("Prise de rendez-vous");
		this.controleur = controleur;

		JLabel titleLabel1 = new JLabel("Make an Appointment, Save Lives");
		titleLabel1.setFont(new Font("Brush Script MT", Font.BOLD, 22));
		titleLabel1.setForeground(new Color(255, 0, 0));

		JLabel nomLabel = new JLabel("Last Name: ");
		JLabel prenomLabel = new JLabel("First Name : ");
		JLabel groupeSanguinLabel = new JLabel("Blood Group : ");
		JLabel dateRendezVousLabel = new JLabel("Date of Appointment : ");
		JLabel heureRendezVousLabel = new JLabel("Time of Appointment : ");

		Font labelFont = new Font("Arial", Font.BOLD, 16);
		nomLabel.setFont(labelFont);
		prenomLabel.setFont(labelFont);
		groupeSanguinLabel.setFont(labelFont);
		dateRendezVousLabel.setFont(labelFont);
		heureRendezVousLabel.setFont(labelFont);

		nomLabel.setForeground(new Color(63, 81, 181));
		prenomLabel.setForeground(new Color(63, 81, 181));
		groupeSanguinLabel.setForeground(new Color(63, 81, 181));
		dateRendezVousLabel.setForeground(new Color(63, 81, 181));
		heureRendezVousLabel.setForeground(new Color(63, 81, 181));

		setSize(900, 600);

		backgroundPanel = new BackgroundPanel();
		backgroundPanel.setLayout(new BorderLayout());

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		formPanel.setPreferredSize(new Dimension(550, 300));

		//creation de formulaire
		nomField = new JTextField(20);
		prenomField = new JTextField(20);
		groupeSanguinComboBox = new JComboBox<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" });

		SpinnerDateModel dateModel = new SpinnerDateModel();
		dateRendezVousSpinner = new JSpinner(dateModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateRendezVousSpinner, "yyyy-MM-dd");
		dateRendezVousSpinner.setEditor(dateEditor);

		SpinnerDateModel heureModel = new SpinnerDateModel();
		heureRendezVousSpinner = new JSpinner(heureModel);
		JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureRendezVousSpinner, "HH:mm");
		heureRendezVousSpinner.setEditor(heureEditor);

		customizeSpinnerArrow(dateRendezVousSpinner);
		customizeSpinnerArrow(heureRendezVousSpinner);

		JButton prendreRendezVousButton = new JButton("Make an Appointment");
		prendreRendezVousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prendreRendezVous();
			}
		});
		prendreRendezVousButton.setMargin(new Insets(10, 20, 10, 20));
		prendreRendezVousButton.setFont(new Font("Arial", Font.BOLD, 16));

		
		nomField.setPreferredSize(new Dimension(200, 30)); 
		prenomField.setPreferredSize(new Dimension(200, 30)); 
		groupeSanguinComboBox.setPreferredSize(new Dimension(200, 30));
		dateRendezVousSpinner.setPreferredSize(new Dimension(200, 30));
		heureRendezVousSpinner.setPreferredSize(new Dimension(200, 30));
		((JSpinner.DefaultEditor) dateRendezVousSpinner.getEditor()).getTextField()
				.setPreferredSize(new Dimension(200, 30));
		((JSpinner.DefaultEditor) heureRendezVousSpinner.getEditor()).getTextField()
				.setPreferredSize(new Dimension(200, 30));
		Font spinnerFont = ((JSpinner.DefaultEditor) dateRendezVousSpinner.getEditor()).getTextField().getFont();
		nomField.setFont(spinnerFont);
		prenomField.setFont(spinnerFont);

		// Pour la dateRendezVousSpinner
		JComponent dateEditorComponent = dateRendezVousSpinner.getEditor();
		if (dateEditorComponent instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) dateEditorComponent;
			JTextField textField = spinnerEditor.getTextField();
			if (textField != null) {
				textField.setBackground(Color.WHITE);
			}
		}

		
		nomField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// Changer la couleur de la bordure en bleu lorsque le champ a le focus
				nomField.setBorder(BorderFactory.createLineBorder(new Color(45, 191, 255)));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Changer la couleur de la bordure en noir lorsque le champ perd le focus
				nomField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		});

		// Ajoutez un FocusListener pour le prénom
		prenomField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				prenomField.setBorder(BorderFactory.createLineBorder(new Color(45, 191, 255)));
			}

			@Override
			public void focusLost(FocusEvent e) {
				prenomField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		});

		Color lightBlue = new Color(173, 216, 230);
		groupeSanguinComboBox.setRenderer(new CustomComboBoxRenderer(new Color(45, 191, 255)));
		groupeSanguinComboBox.setBackground(Color.WHITE);
		dateEditor.getTextField().setBackground(Color.WHITE);
		heureEditor.getTextField().setBackground(Color.WHITE);
		prendreRendezVousButton.setBackground(Color.WHITE);
		prendreRendezVousButton.setForeground(new Color(0xD0, 0x2E, 0x3B));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(7, 5, 5, 5);

		formPanel.add(titleLabel1, gbc);
		gbc.gridy++;
		formPanel.add(nomLabel, gbc);
		gbc.gridy++;
		formPanel.add(nomField, gbc);
		gbc.gridy++;
		formPanel.add(prenomLabel, gbc);
		gbc.gridy++;
		formPanel.add(prenomField, gbc);
		gbc.gridy++;
		formPanel.add(groupeSanguinLabel, gbc);
		gbc.gridy++;
		formPanel.add(groupeSanguinComboBox, gbc);
		gbc.gridy++;
		formPanel.add(dateRendezVousLabel, gbc);
		gbc.gridy++;
		formPanel.add(dateRendezVousSpinner, gbc);
		gbc.gridy++;
		formPanel.add(heureRendezVousLabel, gbc);
		gbc.gridy++;
		formPanel.add(heureRendezVousSpinner, gbc);
		gbc.gridy++;
		formPanel.add(prendreRendezVousButton, gbc);

		formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		gbc.insets = new Insets(10, 10, 10, 10);

		setBackground(new Color(0xFA, 0xFA, 0xFA));
		formPanel.setBackground(new Color(0xFA, 0xFA, 0xFA));
		formPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(63, 81, 181), 2), 
		BorderFactory.createEmptyBorder(20, 20, 20, 20)));
		backgroundPanel.add(formPanel, BorderLayout.EAST);
		getContentPane().add(backgroundPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void prendreRendezVous() {
		String nom = nomField.getText();
		String prenom = prenomField.getText();
		String groupeSanguin = (String) groupeSanguinComboBox.getSelectedItem();
		LocalDate dateRendezVous = ((java.util.Date) dateRendezVousSpinner.getValue()).toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		LocalTime heureRendezVous = ((java.util.Date) heureRendezVousSpinner.getValue()).toInstant()
				.atZone(ZoneId.systemDefault()).toLocalTime();

		controleur.prendreRendezVouss(nom, prenom, groupeSanguin, dateRendezVous, heureRendezVous);
	}

	class BackgroundPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon(getClass().getResource("/images/background8.png")).getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public JPanel getMainPanel() {
		return backgroundPanel;
	}

	private void customizeSpinnerArrow(JSpinner spinner) {
		Component editor = spinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
			spinnerEditor.getTextField().setBackground(new Color(63, 81, 181));
			Component[] components = spinner.getComponents();
			for (Component comp : components) {
				if (comp instanceof JButton) {
					JButton button = (JButton) comp;
					if (spinner.getComponent(0) == comp) { 
						button.setBackground(new Color(63, 81, 181));
						button.setForeground(Color.white);
					} else { 
						button.setBackground(new Color(63, 81, 181));
						button.setForeground(Color.white);
					}
				}
			}
		}
	}
}
