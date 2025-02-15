package engine.data.evenement;

import engine.data.individu.Individu;
import java.util.ArrayList;

public class EventSocial extends Evenement {
    private int impactRelation;
    private ArrayList<Individu> individus;

    public EventSocial(String nom, String description, int duree, int impactRelation) {
        super(nom, description, duree); // Appelle le constructeur de la classe mère
        this.individus = new ArrayList<>();
        this.impactRelation = impactRelation;
    }

    public void ajoutIndividu(Individu individu) {
        individus.add(individu);
    }

    public void afficherEtat() {
        System.out.println("L'événement social " + getNom() + " impacte les relations.");
        for (Individu pnj : individus) {
            System.out.println(pnj.getNom() + " est affecté par l'événement.");
        }
    }
}
