package engine.data.event;

import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;

import java.util.ArrayList;

/**
 * Classe de donnée stockant les informations liée aux événements sociaux (impliquant plusieurs personnes)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class SocialEvent extends Event {
    private ArrayList<Person> persons;
    private Infrastructure infrastructure;
    private Person leader;
    private Time startTime;
    private Time endTime;

    public SocialEvent(String id, String description, ArrayList<Person> persons, Infrastructure i) {
        super(id, description);
        this.persons = persons;
        this.infrastructure = i;

    }

    public SocialEvent(){
        super();
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public void setLeader(Person leader) {
        this.leader = leader;
    }

    public Person getLeader() {
        return leader;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isFinished() {
        Time c = Clock.getInstance().getTime();
        return c.isHigherThan(endTime);
    }

    @Override
    public String toString() {
        return super.toString() + "concerne les individus : " + persons;
    }
}
