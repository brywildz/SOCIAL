package engine.data.event;

import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;

import java.util.ArrayList;

/**
 * Classe de donnée stockant les information liée aux évennement sociaux (impliquant plusieurs personnes)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class SocialEvent extends Event {
    private ArrayList<Person> persons;
    private Infrastructure infrastructure;

    public SocialEvent(String id, Time debut, Time fin, String description, ArrayList<Person> persons, Infrastructure i) {
        super(id, debut, fin, description);
        this.persons = persons;
        this.infrastructure = i;

    }

    public SocialEvent(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "concerne les individus : " + persons;
    }
}
