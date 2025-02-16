package engine.data.evenement;
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
    private int duree;
    private String description;
    private Etat etat;


    public Evenement(String id, int duree, String description, Etat etat) {
        this.id = id;
        this.duree = duree;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getDuree() {
        return duree;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "L'énennement "+ id +" dure "+duree+" et a pour description"+description;
    }

    public Etat getEtat() {
        return etat;
    }
}
