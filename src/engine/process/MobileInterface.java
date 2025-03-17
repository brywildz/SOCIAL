package engine.process;

import engine.data.map.Block;
import engine.data.person.Person;

import java.util.HashMap;

public interface MobileInterface {

    public HashMap<String, Person> getIndividus();

    void set(HashMap<String, Person> individus);

    void nextSecond();
}
