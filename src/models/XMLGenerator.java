package models;

import java.util.List;

import models.RendezVousListe;

public class XMLGenerator {
    public static String generateXML(List<RendezVousListe> rendezVousList) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<rendezvous-list>\n");
        for (RendezVousListe rendezVous : rendezVousList) {
            xmlBuilder.append("  <rendezvous>\n");
            xmlBuilder.append("    <id>").append(rendezVous.getId()).append("</id>\n");
            xmlBuilder.append("    <nom>").append(rendezVous.getNom()).append("</nom>\n");
            xmlBuilder.append("    <prenom>").append(rendezVous.getPrenom()).append("</prenom>\n");
            xmlBuilder.append("    <groupe_sanguin>").append(rendezVous.getGroupeSanguin()).append("</groupe_sanguin>\n");
            xmlBuilder.append("    <date_rendezvous>").append(rendezVous.getDateRendezvous()).append("</date_rendezvous>\n");
            xmlBuilder.append("    <heure_rendezvous>").append(rendezVous.getHeureRendezvous()).append("</heure_rendezvous>\n");
            xmlBuilder.append("  </rendezvous>\n");
        }
        xmlBuilder.append("</rendezvous-list>");
        return xmlBuilder.toString();
    }

	
}
