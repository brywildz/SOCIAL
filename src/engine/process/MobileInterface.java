package engine.process;

import engine.data.person.Person;

import java.util.HashMap;

public interface MobileInterface {

    void set(HashMap<String, Person> individus);

    void nextSecond() throws InterruptedException;
}
