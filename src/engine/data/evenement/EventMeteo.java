package engine.data.evenement;

import engine.data.carte.Block;
import engine.data.carte.Time;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;
import engine.data.individu.Etat;

import java.util.HashMap;
/**
 * Classe représentant un événement météorologique dans le système
 * Cette classe hérite de {@link Evenement} et permet de gérer les événements liés à la météo
 *
 * @author Manseri Dylan  Amadou Bawolu
 * @version 0.1
 */

public class EventMeteo extends Evenement {
    HashMap<Block, Individu> individus = IndividuRepository.getInstance().getIndividus();

    public EventMeteo(String id, Time debut, Time fin, String description, Etat etat, HashMap<Block, Individu> individus) {
        super(id, debut, fin, description, etat);
        this.individus = individus;
    }
}
