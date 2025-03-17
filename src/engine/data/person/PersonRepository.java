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

    public void randomCreation() {
        InfrastructureRepository ir = InfrastructureRepository.getInstance();
        String[] prenoms = {
                "Alice", "Bob", "Charlie", "David", "Emma", "Felix", "Gabriel", "Hugo", "Isabelle", "Jules",
                "Katia", "Léo", "Marie", "Nathan", "Olivia", "Paul", "Quentin", "Rose", "Samuel", "Thomas",
                "Ursula", "Victor", "William", "Xavier", "Yasmine", "Zoé", "Antoine", "Bruno", "Clara", "Damien",
                "Elodie", "François", "Gisèle", "Henri", "Inès", "Julien", "Karine", "Louis", "Manon", "Nicolas",
                "Océane", "Pierre", "Raphaël", "Sophie", "Théo", "Ugo", "Valentin", "Wendy", "Xenia", "Yohan",
                "Zacharie", "Adrien", "Bernard", "Camille", "Dorian", "Estelle", "Florian", "Guillaume", "Hélène",
                "Ibrahim", "Joséphine", "Kevin", "Laurent", "Margaux", "Noémie", "Olivier", "Pascal", "Rebecca",
                "Simon", "Tiffany", "Ulrich", "Violette", "Warren", "Xander", "Yvette", "Zinedine"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(prenoms.length);
        String name = prenoms[randomIndex];
        int age = random.nextInt(60);
        randomIndex = random.nextInt(2);
        SocialState ss = null;
        if(randomIndex == 0){
            int[] min = {0,15,30,45};
            Time start = new Time(random.nextInt(7,12), min[random.nextInt(min.length)],0);
            Time end = new Time(random.nextInt(17,22), min[random.nextInt(min.length)],0);
            ss = new Worker(ir.randomWorkingPlace(), start, end);
        }
        if(randomIndex == 1){
            Time start = new Time(8,30,0);
            Time end = new Time(17,0,0);
            ss = new Pupil(ir.get("école"), start, end);
        }
        Personality personality = new Personality(random.nextInt(10),random.nextInt(10),
                random.nextInt(10), random.nextInt(10), random.nextInt(10));
        Health h = new Health(random.nextInt(10),0, false);
        Hunger hun = new Hunger(random.nextInt(10), "undefined", "undefined");
        Mood m = new Mood(random.nextInt(10), "undefined");
        Sleep s = new Sleep(random.nextInt(10), false);
        PersonState ps = new PersonState(h,s,m,hun);
        Infrastructure house = ir.getRandomHouse();
        Block location = house.getBase();
        Person per = new Person(name, age, ss, ps, null, location, random.nextInt(10),
                random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10));
        per.setHouse(house);
        Reaction.createPersonState(per);
        PersonRepository.getInstance().addIndividu(per);
        PersonRepository.getInstance().setNewLocation(per, per.getHouse().getRandomBlock());
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
