package engine.process;

import config.GameConfiguration;
import engine.data.map.Map;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

import java.util.ArrayList;


/**
 * Classe de traitement qui instancie les objets cruciaux au bon fonctionnement du logiciel
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class GameBuilder {

    public static Map buildCarte() {
        return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
    }

    public static MobileInterface buildInitMobile(Map map){
        MobileInterface manager = new MobileElementManager(map);
        initializePersons(map, manager);
        return manager;
    }

    public static void initializePersons(Map map, MobileInterface mouvement){
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

    public static int random(int min, int max){
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max){
        return random(0,max);
    }
}
