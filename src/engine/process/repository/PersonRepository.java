package engine.process.repository;

import engine.data.map.Block;
import engine.data.map.Infrastructure;
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
    private InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();

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

    public synchronized void movePerson(Person person, Block newLocation){
        if(person.getPlace()!=null){
            person.getPlace().removePerson(person);
        }
        person.setLocation(newLocation);
        Infrastructure infra = infraRepo.getInfrastructure(newLocation);
        infra.addPerson(person);
        person.setPlace(infra);
    }

    public synchronized void movePerson(Person person, Block newLocation, Infrastructure infrastructure){
        if(person.getPlace()!=null){
            person.getPlace().removePerson(person);
        }
        person.setLocation(newLocation);
        infrastructure.addPerson(person);
        person.setPlace(infrastructure);
    }

    public synchronized void movePerson(Person person, Person neighbour){
        Block block = neighbour.getLocation();
        Block block1 = new Block(block.getLine(), block.getColumn()+1);
        movePerson(person, block1, person.getPlace());
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
