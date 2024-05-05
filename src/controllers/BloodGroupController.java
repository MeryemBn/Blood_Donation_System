package controllers;

import javax.swing.*;


import models.BloodGroupList;
import models.BloodGroupListModel;
import models.BloodListModel;
import views.BloodGroupView;
import views.EditPackView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BloodGroupController {
    private BloodGroupView bloodView;
    private BloodGroupListModel bloodModel;

    public BloodGroupController(BloodGroupView bloodView, BloodGroupListModel bloodModel) {
        this.bloodView = bloodView;
        this.bloodModel = bloodModel;

        // Add action listeners to view components
        bloodView.addEditButtonListener(new EditButtonListener());

        // Load initial data into the table
        bloodView.setTableModel(new BloodListModel(bloodModel.getAllPacks()));
    }
    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the selected row from the table
            int selectedRowIndex = bloodView.getSelectedRowIndex();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(bloodView, "Please select a row to edit.");
                return;
            }

            // Get the BloodGroupList object at the selected row index
            BloodGroupList selectedPack = bloodView.getSelectedPackDisponible();
            if (selectedPack == null) {
                JOptionPane.showMessageDialog(bloodView, "Error retrieving selected pack.");
                return;
            }

            // Display the edit pack disponible form with the selected pack data
            EditPackView editForm = new EditPackView(bloodView, selectedPack);
            EditBloodController editController = new EditBloodController(editForm,bloodModel,bloodView);
            editForm.setVisible(true);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the model, view, and controller
        	
            BloodGroupListModel bloodModel = new BloodGroupListModel();
            BloodGroupView bloodView = new BloodGroupView(bloodModel.getAllPacks());
            BloodGroupController bloodController = new BloodGroupController(bloodView, bloodModel);

            // Display the admin view
            bloodView.setVisible(true);
        });
    }
}
