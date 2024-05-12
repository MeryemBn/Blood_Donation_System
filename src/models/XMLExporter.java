package models;



import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import views.RendezvousListeView;
import models.XMLGenerator;

public class XMLExporter {
    public static void export(List<RendezVousListe> rendezVousList, String filePath) {
        String xmlContent = XMLGenerator.generateXML(rendezVousList);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(xmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

