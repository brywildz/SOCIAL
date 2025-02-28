package engine.data.evenement;

import engine.data.carte.Time;
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

    public EventSocial(String id, Time debut, Time fin, String description, Etat etat, ArrayList<Individu> individus) {
        super(id, debut, fin, description, etat);
        this.individus = individus;
    }

    @Override
    public String toString() {
        return super.toString() + "concerne les individus : " + individus;
    }
}
