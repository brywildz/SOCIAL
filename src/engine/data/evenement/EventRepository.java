package engine.data.evenement;

import engine.data.individu.Etat;
import engine.data.individu.bienetre.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe de donnée stockant tous les évennement et leur réaction PAR DEFAUT
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventRepository {
    private HashMap<String, Evenement> evenements = new HashMap<>();
    private static EventRepository instance = new EventRepository();

    //permet d'avoir déjà des evenements au lancement du jeu
    private EventRepository() {
        //addEvent(new EventMeteo("pluie", 5, "il pleut", createEtatPluie())); refaire avec les nouveaux constructeur avec horaire
        //addEvent(new EventPersonnel("Cambriolage", 2, "miskin tu te fait dépouiller", null, createEtatCambriolage()));
        //addEvent(new EventSocial("Fête", 10, "banquet dans toute la ville", null, createEtatFete()));

    }

    public static EventRepository getInstance() {
        return instance;
    }

    public Etat createEtatPluie(){
        Faim fPluie = new Faim(0, null, null);
        Humeur hPluie = new Humeur(-4, "Mauvaise");
        Sante sPluie = new Sante(-1, 0, null);
        Sommeil soPlluie = new Sommeil(-1, null);
        return createEtat(fPluie, hPluie, sPluie, soPlluie);
    }

    public Etat createEtatFete(){
        Faim fFete = new Faim(-5, null, null);
        Humeur hFete = new Humeur(+4, "Bonne");
        Sante sFete = new Sante(1, 0, null);
        Sommeil soFete = new Sommeil(-3, null);
        return createEtat(fFete, hFete, sFete, soFete);
    }

    public Etat createEtatCambriolage(){
        Faim fCambriolage = new Faim(0, null, null);
        Humeur hCambriolage = new Humeur(-5, "Bonne");
        Sante sCambriolage = new Sante(-2, 0, null);
        Sommeil soCambriolage = new Sommeil(-2, null);
        return createEtat(fCambriolage, hCambriolage, sCambriolage, soCambriolage);
    }

    private Etat createEtat(Faim f, Humeur h, Sante s, Sommeil so) {
        HashMap<String, BienEtre> etat = new HashMap<>();
        etat.put("faim", f);
        etat.put("humeur", h);
        etat.put("sante", s);
        etat.put("sommeil", so);
        return new Etat(etat);
    }

    // ajoute des evenements depuis la hashmap
    public void addEvent(Evenement evenement) {
        evenements.put(evenement.getId(), evenement);
    }

    // recup un event grace à sont nom
    public Evenement getEvenement(String id){
        return evenements.get(id);

    }

    //pour afficher tout les events disponible
    public void afficherEvent(){
        System.out.println("Événements en cours :");
        Iterator<Evenement> iterator = evenements.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void supprimerEvent(String id){
        evenements.remove(id);
    }

    public Etat getEtat(String id){
        Etat e = getEvenement(id).getEtat();
        return e;
    }

}
