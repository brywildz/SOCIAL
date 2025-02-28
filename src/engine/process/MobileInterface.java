package engine.process;

import engine.data.map.Block;
import engine.data.person.Person;

import java.util.HashMap;

public interface MobileInterface {

    public HashMap<Block, Person> getIndividus();

    void set(HashMap<Block, Person> individus);

    void nextSecond();
}
