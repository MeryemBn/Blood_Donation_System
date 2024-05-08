package models;

import java.io.File;
import models.CentreDeDonDeSang;

public class CentreDeDonDeSangModel {
    private CentreDeDonDeSang centre;

    public void chargerCentreDeDonDeSangDepuisXML(File xmlFile) {
        try {
            centre = CentreDeDonDeSang.parseXML(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CentreDeDonDeSang getCentreDeDonDeSang() {
        return centre;
    }
}
