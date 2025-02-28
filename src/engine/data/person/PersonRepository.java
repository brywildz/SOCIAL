package engine.data.person;

import engine.data.map.Block;

import java.util.HashMap;

/**
 * Singleton gerant le stockage tout les individus présent sur la carte
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class PersonRepository {
    private HashMap<Block, Person> individus = new HashMap<>();
    private static PersonRepository instance;

    private PersonRepository() {}

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public HashMap<Block, Person> getIndividus() {
        return individus;
    }

    public void addIndividu(Person person) {
        individus.put(person.getLocation(), person);
    }

    public Person getIndividu(Block block) {
        return individus.get(block);
    }

    public void setNewLocation(Person person, Block previousLocation, Block newLocation){
        if(individus.containsKey(previousLocation)){
            individus.remove(previousLocation);
            person.setLocation(newLocation);
            addIndividu(person);
        }
    }
}
