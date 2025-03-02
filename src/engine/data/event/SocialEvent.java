package engine.data.event;

import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;
import java.util.ArrayList;
import engine.data.person.PersonState;

/**
 * Classe de donnée stockant les information liée aux évennement sociaux (impliquant plusieurs personnes)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class SocialEvent extends Event {
    private ArrayList<Person> persons;
    private Infrastructure infrastructure;

    public SocialEvent(String id, Time debut, Time fin, String description, PersonState personState, ArrayList<Person> persons, Infrastructure i) {
        super(id, debut, fin, description, personState);
        this.persons = persons;
        this.infrastructure = i;

    }

    @Override
    public String toString() {
        return super.toString() + "concerne les individus : " + persons;
    }
}
