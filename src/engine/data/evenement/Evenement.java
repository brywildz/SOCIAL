package engine.data.evenement;
import engine.data.carte.Time;
import engine.data.carte.Clock;
import engine.data.individu.Etat;

/**
 * Classe abstraite représentant un événement dans le système
 * Cette classe sert de base pour implémenter différents types d'événements
 * Spécifiques dans les classes filles
 *
 * @author Manseri Dylan Bawol Amadou
 * @version 0.1
 */
public abstract class Evenement {
    private String id;
    private Time debut;
    private Time fin;
    private String description;
    private Etat etat;

    public Evenement(String id, Time debut, Time fin, String description, Etat etat) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
        this.etat = etat;
    }

    public Time getDebut() {
        return debut;
    }

    public Time getFin() {
        return fin;
    }

    public String getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "L'énennement "+ id +" debute à "+debut+" fini à " +fin+ " et a pour description"+description;
    }

    public Etat getEtat() {
        return etat;
    }

    public boolean isFinish(){
        return fin.equals(Clock.getInstance().getHoraire());
    }
}
