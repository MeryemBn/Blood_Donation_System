package controllers;

import models.DashboardModel;
import views.DashboardView;

public class DashboardController {
    private DashboardModel model;
    private DashboardView view;

    public DashboardController(DashboardView view,DashboardModel model) {
        this.model = model;
        this.view = view;
    }

}