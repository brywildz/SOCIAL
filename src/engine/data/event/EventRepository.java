package engine.data.event;

import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;
import engine.data.map.Time;
import engine.data.person.PersonState;
import engine.data.person.bienetre.*;

import java.util.*;

/**
 * Classe de donnée stockant tous les évennement et leur réaction PAR DEFAUT
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventRepository {
    private static HashMap<String, Event> evenements = new HashMap<>();
    private static EventRepository instance = new EventRepository();
    private EventRepository() { //permet d'avoir déjà des evenements au lancement du jeu
        Time rainStart = new Time(12, 0,0);
        Time rainEnd = new Time(16, 0,0);
        addEvent(new WeatherEvent("rain", rainStart, rainEnd, "Il pleut abondement", createEtatPluie()));

        Time startRomanticBreakup = new Time(23, 0,0);
        Time endRomanticBreakup = new Time(9, 0,0);
        addEvent(new PersonalEvent("romantic_breakup", startRomanticBreakup, endRomanticBreakup, "Ils ont rompu, leur couple ne marchait pas", createEtatCambriolage(), null, 90));

        Time startParty = new Time(18, 0,0);
        Time endParty = new Time(23, 0,0);
        Infrastructure event_hall = InfrastructureRepository.getInstance().get("event_hall");
        addEvent(new SocialEvent("party", startParty, endParty, "Banquet de la ville", createEtatFete(), null, event_hall));
    }
    public static EventRepository getInstance() {
        return instance;
    }

    //<editor-fold> desc="creation des etats"
    public PersonState createEtatPluie(){
        Faim fPluie = new Faim(0, null, null);
        Humeur hPluie = new Humeur(-4, "Mauvaise");
        Sante sPluie = new Sante(-1, 0, null);
        Sommeil soPlluie = new Sommeil(-1, null);
        return createEtat(fPluie, hPluie, sPluie, soPlluie);
    }

    public PersonState createEtatFete(){
        Faim fFete = new Faim(-5, null, null);
        Humeur hFete = new Humeur(+4, "Bonne");
        Sante sFete = new Sante(1, 0, null);
        Sommeil soFete = new Sommeil(-3, null);
        return createEtat(fFete, hFete, sFete, soFete);
    }

    public PersonState createEtatCambriolage(){
        Faim fCambriolage = new Faim(0, null, null);
        Humeur hCambriolage = new Humeur(-5, "Bonne");
        Sante sCambriolage = new Sante(-2, 0, null);
        Sommeil soCambriolage = new Sommeil(-2, null);
        return createEtat(fCambriolage, hCambriolage, sCambriolage, soCambriolage);
    }

    private PersonState createEtat(Faim f, Humeur h, Sante s, Sommeil so) {
        HashMap<String, BienEtre> etat = new HashMap<>();
        etat.put("faim", f);
        etat.put("humeur", h);
        etat.put("sante", s);
        etat.put("sommeil", so);
        return new PersonState(etat);
    }
    //</editor-fold>

    public void addEvent(Event event) {
        evenements.put(event.getId(), event);
    }

    public Event getEvent(String id){
        return evenements.get(id);
    }

    public PersonState getEtat(String id){
        PersonState e = getEvent(id).getEtat();
        return e;
    }

    public static Event getRandomEvent(){
        List<Event> events = new ArrayList<>(evenements.values());
        Random r = new Random();
        return events.get(r.nextInt(events.size()));
    }

}
