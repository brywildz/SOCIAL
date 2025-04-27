package engine.process.repository;

import engine.data.event.Event;
import engine.data.event.PersonalEvent;
import engine.data.event.SocialEvent;
import engine.data.map.Infrastructure;
import engine.data.map.Time;

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
    private static final HashMap<String, Event> personalEvent = new HashMap<>();
    private static final EventRepository instance = new EventRepository();
    private InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();

    private EventRepository() {
        PersonalEvent pEvent1 = new PersonalEvent();
        pEvent1.setId("sick");
        PersonalEvent pEvent2 = new PersonalEvent();
        pEvent2.setId("meet");
        PersonalEvent pEvent3 = new PersonalEvent();
        pEvent3.setId("success");

        personalEvent.put("sick",pEvent1);
        personalEvent.put("meet",pEvent2);
        personalEvent.put("success",pEvent3);

        SocialEvent sEvent1 = new SocialEvent();
        sEvent1.setId("party");
        sEvent1.setEndTime(new Time(5,0,0));
        sEvent1.setInfrastructure(infraRepo.get("night_club"));
        SocialEvent sEvent2 = new SocialEvent();
        sEvent2.setId("dinner");
        sEvent2.setInfrastructure(infraRepo.get("restaurant"));
        SocialEvent sEvent3 = new SocialEvent();
        sEvent2.setId("walk");
        sEvent2.setEndTime(new Time(5,0,0));
        sEvent3.setInfrastructure(infraRepo.get("forest"));

        socialEvent.put("party",sEvent1);
        socialEvent.put("dinner",sEvent2);
        socialEvent.put("walk",sEvent3);

    }

    public static EventRepository getInstance() {
        return instance;
    }

    public Event get(String id) {
        if(socialEvent.containsKey(id)){
            return socialEvent.get(id);
        }
        if(personalEvent.containsKey(id)){
            return personalEvent.get(id);
        }
        return null;
    }

    public Event getNew(String id){
        if(socialEvent.containsKey(id)){
            SocialEvent sEvent = new SocialEvent();
            sEvent.setId(id);
            return sEvent;
        }
        if(personalEvent.containsKey(id)){
            PersonalEvent pEvent = new PersonalEvent();
            pEvent.setId(id);
            return pEvent;
        }
        return null;
    }

}
