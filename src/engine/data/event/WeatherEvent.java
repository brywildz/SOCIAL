package engine.data.event;

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
    HashMap<String, Person> individus = PersonRepository.getInstance().getPersons();

    public WeatherEvent(String id, Time debut, Time fin, String description) {
        super(id, debut, fin, description);
    }

    public WeatherEvent(){
        super();
    }
}
