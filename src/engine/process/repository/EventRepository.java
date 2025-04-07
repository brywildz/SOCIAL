package engine.process.repository;

import engine.data.event.Event;
import engine.data.event.PersonalEvent;
import engine.data.event.SocialEvent;
import engine.data.event.WeatherEvent;
import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.PersonState;
import engine.data.person.vitality.*;

import java.util.*;

import static engine.process.builder.GameBuilder.random;

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
        addEvent(new WeatherEvent("rain", rainStart, rainEnd, "Il pleut abondement"));

        Time startRomanticBreakup = new Time(23, 0,0);
        Time endRomanticBreakup = new Time(9, 0,0);
        addEvent(new PersonalEvent("romantic_breakup", startRomanticBreakup, endRomanticBreakup, "Ils ont rompu, leur couple ne marchait pas", null, 90));

        Time startParty = new Time(18, 0,0);
        Time endParty = new Time(23, 0,0);
        Infrastructure event_hall = InfrastructureRepository.getInstance().get("event_hall");
        addEvent(new SocialEvent("party", startParty, endParty, "Banquet de la ville", null, event_hall));
    }
    public static EventRepository getInstance() {
        return instance;
    }

    //<editor-fold> desc="creation des etats"
    public PersonState createEtatPluie(){
        Hunger fPluie = new Hunger(0, null, null);
        Mood hPluie = new Mood(-4, "Mauvaise");
        Health sPluie = new Health(-1, 0, null);
        Sleep soPlluie = new Sleep(-1, null);
        return createEtat(fPluie, hPluie, sPluie, soPlluie);
    }

    public PersonState createEtatFete(){
        Hunger fFete = new Hunger(-5, null, null);
        Mood hFete = new Mood(+4, "Bonne");
        Health sFete = new Health(1, 0, null);
        Sleep soFete = new Sleep(-3, null);
        return createEtat(fFete, hFete, sFete, soFete);
    }

    public PersonState createEtatCambriolage(){
        Hunger fCambriolage = new Hunger(0, null, null);
        Mood hCambriolage = new Mood(-5, "Bonne");
        Health sCambriolage = new Health(-2, 0, null);
        Sleep soCambriolage = new Sleep(-2, null);
        return createEtat(fCambriolage, hCambriolage, sCambriolage, soCambriolage);
    }

    private PersonState createEtat(Hunger hu, Mood m, Health h, Sleep s) {
        return new PersonState(h,s,m,hu);
    }
    //</editor-fold>

    public void addEvent(Event event) {
        evenements.put(event.getId(), event);
    }

    public Event getEvent(String id){
        return evenements.get(id);
    }

    public static Event getRandomEvent(){
        List<Event> events = new ArrayList<>(evenements.values());
        return events.get(random(events.size()));
    }

}
