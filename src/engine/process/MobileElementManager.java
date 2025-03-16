package engine.process;

import engine.data.event.Event;
import engine.data.event.EventRepository;
import engine.data.event.WeatherEvent;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.map.Clock;
import engine.data.person.Person;
import engine.data.person.PersonRepository;

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
    private HashMap<Block, Person> individus = PersonRepository.getInstance().getIndividus();
    private WeatherEvent weather;

    public MobileElementManager(Map map){
        this.map = map;
    }

    public HashMap<Block, Person> getIndividus() {
        return individus;
    }

    @Override
    public void set(HashMap<Block, Person> individus) {
        this.individus = individus;
    }

    public void nextSecond(){
        Clock.getInstance().newSecond();
        List<Person> personList = new ArrayList<>(individus.values());
        for(Person person : personList){
            refreshLifeStyle(person);
            refreshEvent(person);
            refreshState(person);
            refreshLocation(person);
        }
    }

    private void refreshLifeStyle(Person person) {
        LifeManager lf = new LifeManager(person);
        if(person.getCurrentAction().isFinished()){
            person.setCurrentAction(null);
        }
        lf.refreshRoutine();
    }

    private void refreshEvent(Person person) {
        if(person.getEvent() == null && weatherReact(person, weather) && lifeStyleReact(person)){
            Event e = EventRepository.getRandomEvent();
            person.setEvent(e);
        }
        else{
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

    private void refreshLocation(Person person) {
        if(person.getEvent() == null){
            return;
        }
        if(person.getLocation() != person.getEvent().getLocation()){
            person.setLocation(person.getEvent().getLocation());
        }
    }



    /*private void moveEnemies() {

        for (Individu individu : individus.values()) {
            Block position = individu.getLocation();

            if (!map.isOnBottom(position)) {
                Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
                enemy.setPosition(newPosition);
            } else {
                outOfBoundEnemies.add(enemy);
            }

        }

        for (Enemy enemy : outOfBoundEnemies) {
            enemies.remove(enemy);
        }

    }*/

}

