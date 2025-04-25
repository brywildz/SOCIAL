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
    private static final HashMap<String, Event> socialEvent = new HashMap<>();
    private static final HashMap<String, Event> personnalEvent = new HashMap<>();
    private static final EventRepository instance = new EventRepository();

    private EventRepository() {
        PersonalEvent pEvent1 = new PersonalEvent();
        pEvent1.setId("sick");
        PersonalEvent pEvent2 = new PersonalEvent();
        pEvent2.setId("meet");
        PersonalEvent pEvent3 = new PersonalEvent();
        pEvent3.setId("success");

        personnalEvent.put("+",pEvent1);
        personnalEvent.put("~",pEvent2);
        personnalEvent.put("-",pEvent3);

        SocialEvent sEvent1 = new SocialEvent();
        sEvent1.setId("party");
        SocialEvent sEvent2 = new SocialEvent();
        sEvent2.setId("dinner");
        SocialEvent sEvent3 = new SocialEvent();
        sEvent2.setId("walk");

        socialEvent.put("+",sEvent1);
        socialEvent.put("~",sEvent2);
        socialEvent.put("-",sEvent3);

    }

    public static EventRepository getInstance() {
        return instance;
    }

    public Event get(String id) {
        if(socialEvent.containsKey(id)){
            return socialEvent.get(id);
        }
        if(personnalEvent.containsKey(id)){
            return personnalEvent.get(id);
        }
        return null;
    }

}
