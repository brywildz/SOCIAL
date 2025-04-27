package engine.data.event;

import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

import java.util.HashMap;
/**
 * Classe représentant un événement météorologique dans le système
 * Cette classe hérite de {@link Event} et permet de gérer les événements liés à la météo
 *
 * @author Manseri Dylan  Amadou Bawolu
 * @version 0.1
 */

public class WeatherEvent extends Event {
    private HashMap<String, Person> individus = PersonRepository.getInstance().getPersons();
    private Time debut;
    private Time fin;

    public WeatherEvent(String id, Time debut, Time fin, String description) {
        super(id, description);
        this.debut = debut;
        this.fin = fin;
    }

    public WeatherEvent(){
        super();
    }

    public boolean isFinish(){
        return fin.equals(Clock.getInstance().getTime());
    }
}
