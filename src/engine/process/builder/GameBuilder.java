package engine.process.builder;

import engine.data.event.WeatherEvent;
import engine.data.map.Clock;
import engine.data.map.Map;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.process.MobileElementManager;
import engine.process.MobileInterface;
import engine.process.repository.PersonRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Classe de traitement qui instancie les objets cruciaux au bon fonctionnement du logiciel
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class GameBuilder {

    public static void buildCarte() {
        Map m = Map.getInstance();
        Time start = Clock.getInstance().getActualTime();
        Time end = new Time(start.getHour(), start.getMinute(), start.getSecond());
        end.addHour(2);
        m.setWeather(new WeatherEvent("normal", start, end, "Le temps est normal actuellement."));
    }

    public static MobileInterface buildInitMobile(Map map) throws FileNotFoundException {
        MobileInterface manager = new MobileElementManager(map);
        initializePersons(manager);
        return manager;
    }

    public static void initializePersons(MobileInterface mouvement) throws FileNotFoundException {
        ArrayList<PersonBuilder> personBuilders = new ArrayList<PersonBuilder>();
        for(int i=0; i<51; i++){
            Person person = new Person();
            PersonBuilder pb = new PersonBuilder();
            personBuilders.add(pb);
            person = pb.buildFirstPerson();
        }
        for(PersonBuilder pb : personBuilders){
            pb.createRelationList();
        }
        mouvement.set(PersonRepository.getInstance().getPersons());
    }

    /**
     * max non inclus
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max){
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max){
        return random(0,max);
    }
}
