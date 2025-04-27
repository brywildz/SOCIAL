package engine.data.event;

import engine.data.map.Block;
import engine.data.map.Clock;
import engine.data.map.Time;

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
    private String description;

    public Event(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Event(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Block getLocation() {
        return null;
    }

    public boolean itsEndTime() {
        if(this instanceof SocialEvent){
            Time c = Clock.getInstance().getTime();
            return c.equals(((SocialEvent) this).getEndTime());
        }
        return false;
    }

    public boolean hasMove(){
        if(this instanceof PersonalEvent){
            return ((PersonalEvent) this).getPerson().getEvent()==null;
        }
        return false;
    }

    public String toStringForPane() {
        return id;
    }
}
