package engine.data.event;

import engine.data.map.Block;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.PersonRepository;
import engine.data.person.PersonState;

import java.util.HashMap;
/**
 * Classe représentant un événement météorologique dans le système
 * Cette classe hérite de {@link Event} et permet de gérer les événements liés à la météo
 *
 * @author Manseri Dylan  Amadou Bawolu
 * @version 0.1
 */

public class WeatherEvent extends Event {
    HashMap<Block, Person> individus = PersonRepository.getInstance().getIndividus();

    public WeatherEvent(String id, Time debut, Time fin, String description, PersonState personState) {
        super(id, debut, fin, description, personState);
        this.individus = individus;
    }
}
