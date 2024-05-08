package views;

import controllers.DashboardController;
import models.DashboardModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

public class DashboardView extends JFrame {
    private JLabel donneurLabel;
    private JLabel donationLabel;
    private JLabel rendezvousLabel;
    private JLabel packLabel;
    private JPanel mainPanel;
    private JPanel chartPanelContainer;
    private DashboardModel model;
    private int donneurCount ;
    private int donationCount;
    private int rendezvousCount;
    private int packCount;

    public DashboardView() {
        setTitle("Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DashboardModel();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());

        // Create dashboard panel
        JPanel dashboardPanel = new JPanel(new BorderLayout());

        // Create cards for each table
        JPanel gridPanel = new JPanel(new GridLayout(2, 2));
        gridPanel.setPreferredSize(new Dimension(100,350));
       gridPanel.setBorder(BorderFactory.createEmptyBorder(80, 180, 40, 180)); // Add padding
       gridPanel.setBackground(Color.WHITE);
       

        // Add border to each card
       /* Border cardBorder = BorderFactory.createLineBorder(Color.BLACK);
        gridPanel.setBorder(BorderFactory.createCompoundBorder(cardBorder, gridPanel.getBorder())); // Add border*/

        // Retrieve counts for each table
         donneurCount = model.getCount("donneur");
         donationCount = model.getCount("historiquedonation");
         rendezvousCount = model.getCount("rendezvous");
         packCount = model.getCount("pack_disponible");

        // Create icons for each card
        ImageIcon donneurIcon = new ImageIcon(getClass().getResource("/images/donor.png"));
        ImageIcon donationIcon = new ImageIcon(getClass().getResource("/images/donations.png"));
        ImageIcon rendezvousIcon = new ImageIcon(getClass().getResource("/images/appointments.png"));
        ImageIcon packIcon = new ImageIcon(getClass().getResource("/images/blood.png"));

        // Create and add labels to the grid panel
        donneurLabel = createCard("Donor", donneurCount, donneurIcon);
        donationLabel = createCard("Donation History", donationCount, donationIcon);
        rendezvousLabel = createCard("Appointement", rendezvousCount, rendezvousIcon);
        packLabel = createCard("Blood Group", packCount, packIcon);

        gridPanel.add(donneurLabel);
        gridPanel.add(donationLabel);
        gridPanel.add(rendezvousLabel);
        gridPanel.add(packLabel);

        // Add grid panel to the dashboard panel
        dashboardPanel.add(gridPanel, BorderLayout.NORTH);

        // Create chart panel
        chartPanelContainer = new JPanel(new BorderLayout());

        // Create pie chart and dataset
        DefaultPieDataset dataset = model.createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        // Add chart panel to the chart panel container
        chartPanelContainer.add(chartPanel, BorderLayout.CENTER);

        // Add chart panel container to the dashboard panel
        dashboardPanel.add(chartPanelContainer, BorderLayout.CENTER);

        // Add dashboard panel to the main panel
        mainPanel.add(dashboardPanel, BorderLayout.CENTER);

        // Set main panel as content pane
        setContentPane(mainPanel);
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Blood Group Distribution", // chart title
                dataset,                    // dataset
                true,                       // include legend
                true,
                false);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0}: {2}");
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE); // Set background color of the chart
        plot.setOutlineStroke(null);

        return chart;
    }

    private JLabel createCard(String title, int count, ImageIcon icon) {
        JLabel cardLabel = new JLabel("<html><center>" + title + "<br>" + count + "</center></html>", icon, JLabel.CENTER);
        cardLabel.setHorizontalAlignment(JLabel.CENTER);
        cardLabel.setVerticalAlignment(JLabel.CENTER);
        cardLabel.setFont(new Font("Arial", Font.BOLD, 22));
        cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return cardLabel;
    }
    public JPanel getMainPanel() {
		return mainPanel;
	}
    public void updateData(int donneurCount, int donationCount, int rendezvousCount, int packCount, DefaultPieDataset dataset) {
    	this.donneurCount=donneurCount;
    	this.donationCount=donationCount;
    	this.rendezvousCount=rendezvousCount;
    	this.packCount=packCount;
    	
    	JFreeChart chart = createChart(dataset);
        ChartPanel newChartPanel = new ChartPanel(chart);
        chartPanelContainer.removeAll();
        chartPanelContainer.add(newChartPanel, BorderLayout.CENTER);

        // Refresh the view
        revalidate();
        repaint();

    }

}