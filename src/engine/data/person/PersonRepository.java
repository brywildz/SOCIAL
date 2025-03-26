package engine.data.person;

import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;
import engine.data.map.Time;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Worker;
import engine.data.person.vitality.Health;
import engine.data.person.vitality.Hunger;
import engine.data.person.vitality.Mood;
import engine.data.person.vitality.Sleep;
import engine.process.Reaction;

import java.util.HashMap;
import java.util.Random;

/**
 * Singleton gerant le stockage tous les individus présents sur la carte
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class PersonRepository {
    private HashMap<String, Person> individus = new HashMap<>();
    private static PersonRepository instance;

    private PersonRepository() {}

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public HashMap<String, Person> getIndividus() {
        return individus;
    }

    public void addIndividu(Person person) {
        individus.put(person.getNom(), person);
    }

    public Person getIndividu(Block block) {
        return individus.get(block);
    }

    public void setNewLocation(Person person, Block newLocation){
        if(individus.containsKey(person.getNom())){
            person.setLocation(newLocation);
        }
    }

    public void setIndividus(HashMap<String, Person> persons) {
        individus = persons;
    }



    public Person isHere(Block location) {
        for(Person p : individus.values()){
            if(p.getLocation().equals(location)){
                return p;
            }
        }
        return null;
    }
}
