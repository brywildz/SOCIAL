package engine.data.evenement;

import engine.data.individu.Individu;
import java.util.ArrayList;
import engine.data.individu.Etat;

/**
 * Classe de donnée stockant les information liée aux évennement sociaux (impliquant plusieurs personnes)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventSocial extends Evenement {
    private ArrayList<Individu> individus;

    public EventSocial(String id, int duree, String description, ArrayList<Individu> individus, Etat etat) {
        super(id, duree, description, etat);
        this.individus = individus;
    }

    @Override
    public String toString() {
        return super.toString() + "concerne les individus : " + individus;
    }
}
