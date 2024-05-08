package controllers;

import org.jfree.data.general.DefaultPieDataset;

import models.DashboardModel;
import views.DashboardView;

public class DashboardController {
    private DashboardModel model;
    private DashboardView view;

    public DashboardController(DashboardView view,DashboardModel model) {
        this.model = model;
        this.view = view;
    }
    public void updateData() {
        // Retrieve fresh data from the database
        int donneurCount = model.getCount("donneur");
        int donationCount = model.getCount("historiquedonation");
        int rendezvousCount = model.getCount("rendezvous");
        int packCount = model.getCount("pack_disponible");
        DefaultPieDataset dataset = model.createDataset();

        // Update the DashboardView with the fresh data
        view.updateData(donneurCount, donationCount, rendezvousCount, packCount,dataset);
    }
}