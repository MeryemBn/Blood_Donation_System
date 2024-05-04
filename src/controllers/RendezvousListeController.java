package controllers;

import java.util.List;
import models.RendezvousListeModel;
import views.RendezvousListeView;
import models.RendezVousListe;

public class RendezvousListeController {
    private RendezvousListeModel model;
    private RendezvousListeView view;

    public RendezvousListeController(RendezvousListeModel model, RendezvousListeView view) {
        this.model = model;
        this.view = view;
    }

    public void displayRendezvousList() {
        List<RendezVousListe> rendezvousList = model.getRendezvousList();
        view.displayRendezvousList(rendezvousList);
    }

    
}

