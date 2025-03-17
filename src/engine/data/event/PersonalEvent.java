package engine.data.event;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.PersonState;
/**
 * Classe représentant un événement personnel affectant un individu spécifique
 * Cette classe hérite de @Evenement  et permet de gérer les événements individuels
 *
 * @author Manseri Bawol
 * @version 0.1
 */

public class PersonalEvent extends Event {
    private Person person;
    private int day; //nombre de jours où le pnj subit l'event

    public PersonalEvent(String id, Time debut, Time fin, String description,  Person person, int day) {
        super(id, debut, fin, description);
        this.person = person;
        this.day = day;
    }

    @Override
    public String toString() {
        return super.toString() + "concerne l'individu : " + person.getNom();
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}


