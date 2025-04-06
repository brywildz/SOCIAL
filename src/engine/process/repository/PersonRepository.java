package engine.process.repository;

import engine.data.map.Block;
import engine.data.person.Person;

import java.util.HashMap;

/**
 * Singleton gerant le stockage tous les individus présents sur la carte
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class PersonRepository {
    private HashMap<String, Person> persons = new HashMap<>();
    private static PersonRepository instance;

    private PersonRepository() {}

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public HashMap<String, Person> getPersons() {
        return persons;
    }

    public void addIndividu(Person person) {
        persons.put(person.getName(), person);
    }

    public Person getIndividu(Block block) {
        return persons.get(block);
    }

    public void setNewLocation(Person person, Block newLocation){
        if(persons.containsKey(person.getName())){
            person.setLocation(newLocation);
        }
    }

    public void setPersons(HashMap<String, Person> persons) {
        this.persons = persons;
    }



    public Person isHere(Block location) {
        for(Person p : persons.values()){
            if(p.getLocation().equals(location)){
                return p;
            }
        }
        return null;
    }
}
