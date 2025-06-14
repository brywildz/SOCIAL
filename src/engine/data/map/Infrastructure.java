package engine.data.map;

import engine.data.person.Person;

import java.util.ArrayList;
import java.util.Iterator;

import static engine.process.builder.GameBuilder.random;

/**
 * Classe de donnée stockant les informations liée à une infrastructure
 */

public class Infrastructure {
    private String nom;  // Ajout du nom du lieu
    private Block base;
    private Block[][] zone;
    private ArrayList<Person> persons = new ArrayList<>();

    public Infrastructure(String nom, int column, int line, int width, int height) {
        this.nom = nom;
        base = new Block(line, column);
        zone = new Block[height][width];
        for(int i=0; i < height; i++){
            for(int j = 0; j < width; j++){
                zone[i][j] = new Block(line+i, column+j);
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public Block getBase() {
        return base;
    }

    public Block[][] getZone() {
        return zone;
    }

    public boolean contains(Block block) {
        for(int i=0; i < zone.length; i++){
            for(int j = 0; j < zone[i].length; j++){
                if(zone[i][j].equals(block)){
                    return true;
                }
            }
        }
        return false;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBase(Block base) {
        this.base = base;
    }

    public void setZone(Block[][] zone) {
        this.zone = zone;
    }

    public Block getRandomBlock(){
        int randomLine = random(zone.length);
        int randomCol = random(zone[randomLine].length);
        return zone[randomLine][randomCol];
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person per) {
        persons.add(per);
    }

    public void removePerson(Person per) {
        persons.remove(per);
    }

    public Block getEmptyBlock() {
        for (Block[] blocks : zone) {
            for (Block block : blocks) {
                Iterator<Person> it = persons.iterator();
                boolean found = false;
                Person person;
                while (it.hasNext() && !found) {
                    person = it.next();
                    if (person.getLocation().equals(block)) {
                        found = true;
                    }
                }
                if (!found) {
                    return block;
                }
            }
        }
        return null;
    }
}
