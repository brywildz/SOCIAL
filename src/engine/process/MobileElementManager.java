package engine.process;

import engine.data.event.Event;
import engine.data.event.SocialEvent;
import engine.data.map.Clock;
import engine.data.map.Map;
import engine.data.person.Person;
import engine.process.manager.EventManager;
import engine.process.manager.WeekEndManager;
import engine.process.manager.WeekManager;
import engine.process.repository.PersonRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static engine.process.manager.utils.LifeUtilities.*;

/**
 * Classe de traitement gérant les déplacements des individus sur la carte
 * (Classe obsolete son utilité doit être rediscuté)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MobileElementManager implements MobileInterface {
    private Map map;
    private final PersonRepository personRepo = PersonRepository.getInstance();
    private EventManager eventManager = new EventManager();

    public MobileElementManager(Map map){
        this.map = map;
    }

    public HashMap<String, Person> getIndividus() {
        return personRepo.getPersons();
    }

    @Override
    public void set(HashMap<String, Person> individus) {
        this.personRepo.setPersons(individus);
    }

    public void nextSecond() throws InterruptedException {
        Clock.getInstance().getTime().addMinute(15);
        refreshWeather();
        List<Person> personList = new ArrayList<>(personRepo.getPersons().values());
        for(Person person : personList){
            refreshLifeStyle(person);
            refreshEvent(person);
            refreshState(person);
            refreshLocation(person);
        }
    }

    private void refreshWeather() {
        eventManager.refreshWeather();
    }

    private void refreshLifeStyle(Person person){
        if(!(person.getEvent() != null && (person.getEvent() instanceof SocialEvent))) {
            if(!Clock.isWeekend()){
                WeekManager wm = new WeekManager(person);
                wm.refreshRoutine();
            }
            else{
                WeekEndManager wem = new WeekEndManager(person);
                wem.lifeIsGood();
            }
        }

    }

    private void refreshEvent(Person person) {
        if(!person.isSleeping()){
            eventManager.refresh(person);
        }
        if(person.getEvent()!=null){
            eventManager.reset(person);
        }
        if(person.getHobby() == null){
            refreshLifeStyle(person);
        }
    }

    public synchronized static void refreshState(Person person) {
        Reaction react = new Reaction(person);
        Event event = person.getEvent();
        if(event != null){
            react.eventReact(event.getId());
        }
        if(person.getHobby() != null && person.getHobby().hasStart()){
            react.changeState(person.getHobby());
        }
    }

}

