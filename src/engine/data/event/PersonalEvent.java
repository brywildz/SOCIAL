package engine.data.event;
import engine.data.person.Person;

/**
 * Classe représentant un événement personnel affectant un individu spécifique
 * Cette classe hérite de @Evenement et permet de gérer les événements individuels
 *
 * @author Manseri Bawol
 * @version 0.1
 */

public class PersonalEvent extends Event {
    private Person person;

    public PersonalEvent(String id, String description,  Person person) {
        super(id, description);
        this.person = person;
    }

    public PersonalEvent(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "concerne l'individu : " + person.getName();
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}


