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

    public void addFamily(Person person) {
        familiale.add(person);
    }

    public void addPro(Person person) {
        pro.add(person);
    }

    public ArrayList<Person> getPro() {
        return pro;
    }

    public ArrayList<Person> getFamiliale() {
        return familiale;
    }

    public ArrayList<Person> getAmicale() {
        return amicale;
    }

    public boolean isFull(String type, int number){
        return switch (type) {
            case "friends" ->
                    amicale.size() == number;
            case "work" ->
                    pro.size() == number;
            case "family" -> familiale.size() == number;
            default -> false;
        };
    }
}
