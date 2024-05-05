package controllers;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.BloodGroupList;
import models.BloodGroupListModel;
import models.BloodListModel;
import views.BloodGroupView;
import views.EditPackView;
import views.DashboardView;
import models.DashboardModel;
import controllers.DashboardController;

public class EditBloodController implements ActionListener{
    private EditPackView view;
    private BloodGroupListModel model;
    private BloodGroupView bloodView;

    public EditBloodController(EditPackView view, BloodGroupListModel model ,BloodGroupView bloodView) {
        this.view = view;
        this.model = model;
        this.bloodView=bloodView;
        view.getEditButton().addActionListener(this);
        view.getCancelButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getEditButton()) {
            String bloodGroup = view.getBloodGroup();
            int quantity = view.getQuantity();
            if (!bloodGroup.isEmpty() && quantity != 0) {
            	editPack(bloodGroup, quantity);
            } else {
            	JOptionPane.showMessageDialog(view, "Quantity Required", "Missing Information",
						JOptionPane.WARNING_MESSAGE);
            }
        }else if (e.getSource() == view.getCancelButton()) {
        	view.dispose();
        }
    }

    public void editPack(String bloodGroup, int quantity) {
        // Get the original blood group from the view
        String originalBloodGroup = view.getBloodGroup();

        // Call the model method to update the pack
        model.updatePack(new BloodGroupList(originalBloodGroup, quantity));

        // Update the table model in the BloodGroupView
        bloodView.setTableModel(new BloodListModel(model.getAllPacks()));

        // Notify the view that the pack has been successfully updated
        view.displaySuccessMessage("Pack updated successfully.");

        // Hide the edit pack view
        view.setVisible(false);
       
    }
}

