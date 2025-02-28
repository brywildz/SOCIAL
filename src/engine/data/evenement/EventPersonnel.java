package engine.data.evenement;
import engine.data.carte.Time;
import engine.data.individu.Individu;
import engine.data.individu.Etat;
/**
 * Classe représentant un événement personnel affectant un individu spécifique
 * Cette classe hérite de @Evenement  et permet de gérer les événements individuels
 *
 * @author Manseri Bawol
 * @version 0.1
 */

public class EventPersonnel extends Evenement {
    private Individu individu;

    public EventPersonnel(String id, Time debut, Time fin, String description, Etat etat, Individu individu) {
        super(id, debut, fin, description, etat);
        this.individu = individu;
    }

    @Override
    public String toString() {
        return super.toString() + "concerne l'individu : " + individu.getNom();
    }
}


