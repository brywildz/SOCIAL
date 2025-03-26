package engine.data.person;

import java.util.ArrayList;

public class PersonRelationships {
    private ArrayList<Person> pro;
    private ArrayList<Person> familiale;
    private ArrayList<Person> amicale;

    public PersonRelationships(ArrayList<Person> amicale, ArrayList<Person> familiale, ArrayList<Person> pro) {
        this.amicale = amicale;
        this.familiale = familiale;
        this.pro = pro;
    }

    public void addFriend(Person person) {
        amicale.add(person);
    }
}
