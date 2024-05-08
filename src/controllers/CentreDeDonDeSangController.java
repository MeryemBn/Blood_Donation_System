package controllers;

import java.io.File;
import models.CentreDeDonDeSangModel;
import views.CentreDeDonDeSangView;

public class CentreDeDonDeSangController {
    private CentreDeDonDeSangModel model;
    private CentreDeDonDeSangView view;

    public CentreDeDonDeSangController(CentreDeDonDeSangModel model, CentreDeDonDeSangView view) {
        this.model = model;
        this.view = view;
    }

    public void chargerCentreDeDonDeSangDepuisXML(File xmlFile) {
        model.chargerCentreDeDonDeSangDepuisXML(xmlFile);
        view.afficherCentreDeDonDeSang(model.getCentreDeDonDeSang());
    }
    
    public CentreDeDonDeSangView getCentreDeDonDeSangView() {
        return view;
    }
}