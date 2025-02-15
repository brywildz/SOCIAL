package engine.data.evenement;


import java.util.HashMap;
import java.util.Iterator;

public class EventRepository {
    private HashMap<String, Evenement> evenements = new HashMap<>();
    private static EventRepository instance = new EventRepository();

    private EventRepository() {}

    public static EventRepository getInstance() {
        return instance;
    }

    public void ajouterEvenement(Evenement evenement) {
        evenements.put(evenement.getNom(), evenement);
    }

    public void gestionEvenements() {
        Iterator<Evenement> it = evenements.values().iterator();
        while (it.hasNext()) {
            Evenement event = it.next();
            event.afficherEffet();
            event.decrementerDuree();
            if (event.endEvent()) {
                it.remove();
            }
        }
    }
}
