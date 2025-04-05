package engine.process;

import engine.data.event.Event;
import engine.process.repository.EventRepository;
import engine.data.event.WeatherEvent;
import engine.data.map.Map;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

import java.util.*;

import static engine.process.Reaction.lifeStyleReact;
import static engine.process.Reaction.weatherReact;

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
    private WeatherEvent weather;

    public MobileElementManager(Map map){

        this.map = map;
        Time start = Clock.getInstance().getActualTime();
        Time end = start;
        end.addHour(2);
        weather = new WeatherEvent("soleil", start, end, "il fait beau");
    }

    public HashMap<String, Person> getIndividus() {
        return personRepo.getIndividus();
    }

    @Override
    public void set(HashMap<String, Person> individus) {
        this.personRepo.setIndividus(individus);
    }

    public void nextSecond(){
        Clock.getInstance().getHoraire().addMinute(15);
        List<Person> personList = new ArrayList<>(personRepo.getIndividus().values());
        for(Person person : personList){
            refreshLifeStyle(person);
            refreshEvent(person);
            refreshState(person);
        }
    }

    private void refreshLifeStyle(Person person) {
        LifeManager lf = new LifeManager(person);
        lf.refreshRoutine();
    }

    private void refreshEvent(Person person) {
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        if(weatherReact(person, weather) && lifeStyleReact(person) && randomIndex == 1 && person.getEvent()==null){
            Event e = EventRepository.getRandomEvent();
            person.setEvent(e);
        }
        else if (person.getEvent()==null) {
            return;
        } else{
            Event e = person.getEvent();
            if(e.isFinish() || Clock.getInstance().getHoraire().isHigherThan(e.getFin())){
                person.setEvent(null);
            }
        }
    }

    private void refreshState(Person person) {
        if(person.getEvent() == null){
            return;
        }
        if(person.getEvent().getDebut().equals(Clock.getInstance().getHoraire())){
            Reaction react = new Reaction(person, person.getEvent());
            //react.changeState();
        }
    }

}

