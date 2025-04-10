package engine.process;

import engine.data.event.WeatherEvent;
import engine.data.map.Clock;
import engine.data.map.Map;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.process.manager.WeekEndManager;
import engine.process.manager.WeekManager;
import engine.process.repository.PersonRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.LifeUtilities.refreshLocation;

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

    public void nextSecond(){
        Clock.getInstance().getTime().addMinute(15);
        refreshWeather();
        List<Person> personList = new ArrayList<>(personRepo.getPersons().values());
        for(Person person : personList){
            refreshLifeStyle(person);
            //refreshEvent(person);
            refreshState(person);
            refreshLocation(person);
        }
    }

    private void refreshWeather() {
        int nbr = random(8);
        Time start = Clock.getInstance().getActualTime();
        Time end = Clock.getInstance().getActualTime();
        if(map.getWeather().isFinish()){
            if(nbr < 5){
                end.addHour(random(1,3));
                map.setWeather(new WeatherEvent("normal", start, end, "Le temps est normal actuellement."));
            }
            if(nbr == 5){
                end.addHour(random(1,4));
                map.setWeather(new WeatherEvent("snow", start, end, "Il neige, c'est beau."));
            }
            else{
                end.addHour(random(1,2));
                map.setWeather(new WeatherEvent("rain", start, end, "Il pleut abondement"));
            }
        }
    }

    private void refreshLifeStyle(Person person) {
        WeekManager wm = new WeekManager(person);
        if(!Clock.isWeekend()){
            wm.refreshRoutine();
        }
        else{
            WeekEndManager wem = new WeekEndManager(person);
            wem.lifeIsGood();
        }
    }

    /*private void refreshEvent(Person person) {
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        if(weatherReact(person) && lifeStyleReact(person) && randomIndex == 1 && person.getEvent()==null){
            Event e = EventRepository.getRandomEvent();
            person.setEvent(e);
        }
        else if (person.getEvent()==null) {
            return;
        } else{
            Event e = person.getEvent();
            if(e.isFinish() || Clock.getInstance().getTime().isHigherThan(e.getFin())){
                person.setEvent(null);
            }
        }
    }*/

    private void refreshState(Person person) {
        if(person.getEvent() == null){
            return;
        }
        if(person.getHobby().hasStart()){
            Reaction react = new Reaction(person);
            react.changeState(person.getHobby());
        }
        /*if(person.getEvent().getDebut().equals(Clock.getInstance().getTime())){
            Reaction react = new Reaction(person, person.getEvent());
            react.changeState();
        }*/
    }

}

