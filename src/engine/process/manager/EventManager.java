package engine.process.manager;

import engine.process.repository.EventRepository;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

/**
 * Classe inutile à ce jour, mais sera utilisé surement plus tard
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventManager {
    private Person ind;
    private String idEvent;
    private PersonRepository indRepo = PersonRepository.getInstance();
    private EventRepository eventRepo = EventRepository.getInstance();

    public EventManager(Person ind, String idEvent) {
        this.ind = ind;
        this.idEvent = idEvent;
    }


}

