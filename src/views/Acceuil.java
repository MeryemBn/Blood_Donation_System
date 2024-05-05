package views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Acceuil {
    private JPanel imagePanel;
    private JFrame frame;
    private JLabel textLabel; 
    private JPanel titlePanel;
    private JButton closeButton;
    private JLabel titleLabel;
    private JPanel mainpanel;
    private JPanel welcomepanel;
    private JPanel leftPanel;
    private JPanel buttonPanel;
    private JButton loginButton;
    private JButton registerButton;
    
    
    public Acceuil() {
    	 frame = new JFrame("Accueil Donneur");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setUndecorated(true);
         frame.setSize(1540, 830);
         // Layout components
         mainpanel = new JPanel(new BorderLayout());
         mainpanel.setBackground(Color.white);
         
         titlePanel = new JPanel();
         titlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align components to the right
         titlePanel.setBackground(new Color(206, 0, 0)); // Set background color of the title bar
         titlePanel.setPreferredSize(new Dimension(1100,45));
         titlePanel.setBorder(BorderFactory.createEmptyBorder(4,0,0,0));
         
         
         titleLabel = new JLabel("Blood Donation System");
         titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
         titleLabel.setForeground(Color.WHITE);

         // Ajouter le label au centre du titlePanel
         titlePanel.add(titleLabel);
         titlePanel.add(Box.createHorizontalStrut(620));
         


         // Add a close button to the title bar
         closeButton = new JButton("X");
         closeButton.setFont(new Font("Arial", Font.BOLD, 16));
         closeButton.setForeground(Color.WHITE);
         closeButton.setBorder(BorderFactory.createEmptyBorder());
         closeButton.setContentAreaFilled(false);
         closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         titlePanel.add(closeButton);
         titlePanel.add(Box.createHorizontalStrut(7));

	    Font  f4  = new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 24);
	    imagePanel = new JPanel();
	    imagePanel.setLayout(new BorderLayout());
	    imagePanel.setBackground(new Color(255, 255, 255));  
	    
	    welcomepanel = new JPanel();
	    welcomepanel.setLayout(new BorderLayout());
	    welcomepanel.setBackground(new Color(255, 255, 255));  
	    
	    textLabel = new JLabel("Welcome , take an appointment and save many lives!");
	    textLabel.setFont(f4);
	    textLabel.setForeground(new Color(63, 81, 181));
	    textLabel.setBorder(BorderFactory.createEmptyBorder(50, 10,0, 0));
	    
	    welcomepanel.add(textLabel, BorderLayout.NORTH);
	     
	    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/bloodChart.jpg"));
	
	     // Resize the image
	     Image image = imageIcon.getImage();
	     Image scaledImage = image.getScaledInstance(600, 354, Image.SCALE_SMOOTH); // Adjust the size as needed
	
	     // Create a new ImageIcon with the scaled image
	     ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	
	     // Create a JLabel for the image
	     JLabel imageLabel = new JLabel(scaledImageIcon);	
	     // Set other properties for the imageLabel
	     imageLabel.setOpaque(true);
	     imageLabel.setBackground(Color.WHITE);
	     imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	     imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Add padding
	     imageLabel.setBorder(BorderFactory.createCompoundBorder(
	         BorderFactory.createLineBorder(Color.WHITE),
	         BorderFactory.createEmptyBorder(0, 10, 10, 10)
	     )); // Add border radius
	     imageLabel.setPreferredSize(new Dimension(600, 354)); // Set preferred size
	 // Add the components to the homePanel
	     
		 
	     buttonPanel= new JPanel();
	     buttonPanel.setLayout(new BorderLayout());
	     buttonPanel.setBackground(new Color(255, 255, 255));
	     buttonPanel.setBorder(BorderFactory.createEmptyBorder(90, 270, 0, 220));
	     
	     loginButton = new JButton("Login");
	     loginButton.setBackground(new Color(63, 81, 181)); // Set background color
	     loginButton.setForeground(Color.WHITE); // Set foreground color
	     loginButton.setFont(new Font("Arial", Font.BOLD, 16));
	     // Set font and size
	     registerButton = new JButton("Register");
	     registerButton.setBackground(new Color(206, 0, 0)); // Set background color
	     registerButton.setForeground(Color.WHITE); // Set foreground color
	     registerButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
	  
	     loginButton.setPreferredSize(new Dimension(100, 40));
	     registerButton.setPreferredSize(new Dimension(100, 40));
	     
	     buttonPanel.add(loginButton, BorderLayout.EAST);	 
	     buttonPanel.add(registerButton, BorderLayout.WEST);	
	     
	     
	     
	     leftPanel = new JPanel();
	     leftPanel.setLayout(new BorderLayout());
	     leftPanel.setBackground(new Color(255, 255, 255)); 
	     leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 100, 0));
	     
		 JPanel descriptionPanel = new JPanel();
		 descriptionPanel.setLayout(new BorderLayout());
		 descriptionPanel.setBackground(new Color(255, 255, 255)); 
		
		 JTextArea descriptionArea1 = new JTextArea();
		 descriptionArea1.setLineWrap(true); // Enable line wrapping
		 descriptionArea1.setWrapStyleWord(true); // Wrap at word boundaries
		 descriptionArea1.setColumns(38); 
		 descriptionArea1.setFont(new Font("Times New Roman", Font.PLAIN,16));
		 descriptionArea1.setText("Is your group's stock in bad shape? Plan a donation if you can.\r\n"
		         + "Is your drop smiling? We're counting on your next donation to keep it that way!"); // Set the text
		 descriptionArea1.setEditable(false); // Make the text area read-only
		
		 descriptionPanel.add(descriptionArea1, BorderLayout.SOUTH);
		
		 JLabel quoteLabel = new JLabel("A thousand reasons to give blood");
		 quoteLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 22));
		 
		 quoteLabel.setForeground(new Color(206, 0, 0));
		 descriptionPanel.add(quoteLabel, BorderLayout.WEST);
		 descriptionArea1.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 0));
		 quoteLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 0));
		
		//Create a panel for the blood groups
		// Add the content panel to the first tab
		descriptionPanel.add(welcomepanel, BorderLayout.NORTH);
		descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		 DefaultPieDataset dataset = new DefaultPieDataset();
		 dataset.setValue("O+", 36);
		 dataset.setValue("A+", 38);
	        dataset.setValue("B+", 8);
	        dataset.setValue("A-", 7);
	        dataset.setValue("O-", 6); 
	        dataset.setValue("AB+", 3);
	        dataset.setValue("B-", 1);
	        dataset.setValue("AB-", 1);

	        JFreeChart chart = ChartFactory.createPieChart("World Blood Group Statistics", dataset, true, true, false);
	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.WHITE); // Set background color of the chart
	        plot.setOutlineStroke(null); // Remove the outline stroke
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(720, 500));
	        chartPanel.setBackground(Color.WHITE);
	        chartPanel.setBorder(BorderFactory.createEmptyBorder(70, 20, 70, 0));
	        
	        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0}: {2}");
	        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
	        
	       leftPanel.add(descriptionPanel, BorderLayout.NORTH);
	       imagePanel.add(chartPanel, BorderLayout.EAST);
	       leftPanel.add(imageLabel, BorderLayout.WEST);
	       imagePanel.add(buttonPanel, BorderLayout.NORTH);
		
		mainpanel.add(titlePanel, BorderLayout.NORTH);
		mainpanel.add(imagePanel, BorderLayout.EAST);	   
		mainpanel.add(leftPanel, BorderLayout.WEST);
		frame.add(mainpanel);
		
		///////////////////////////////////////////////////////////////FIN///////////////////////////////////////////////////////////////////////////////////////
		// Center the frame on the screen
		frame.setLocationRelativeTo(null);
		//frame.setVisible(true);
		}
	   public JButton getLoginButton() {
		   return loginButton;
	   }
	   public JButton getRegisterButton() {
		   return registerButton;
	   }
	   public JButton getCloseButton() {
		   return closeButton;
	   }
	   public void dispose() {
		   frame.setVisible(false);
	   }
	   public void setVisible() {
		   frame.setVisible(true);
	   }
	   
		}