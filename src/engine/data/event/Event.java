package engine.data.event;
import engine.data.map.Block;
import engine.data.map.Time;
import engine.data.map.Clock;

/**
 * Classe abstraite représentant un événement dans le système
 * Cette classe sert de base pour implémenter différents types d'événements
 * Spécifiques dans les classes filles
 *
 * @author Manseri Dylan Bawol Amadou
 * @version 0.1
 */
public abstract class Event {
    private String id;
    private Time debut;
    private Time fin;
    private String description;

    public Event(String id, Time debut, Time fin, String description) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.description = description;
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


    public boolean isFinish(){
        return fin.equals(Clock.getInstance().getTime());
    }

    public Block getLocation() {
        return null;
    }
}
