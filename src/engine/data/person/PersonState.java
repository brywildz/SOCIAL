package engine.data.person;
import engine.data.map.Time;
import engine.data.person.vitality.*;

import java.util.HashMap;

/**
 * Classe de donnée regroupant toutes les instance de Vitality d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class PersonState {
    private Health health;
    private Hunger hunger;
    private Mood mood;
    private Sleep sleep;


    public PersonState(Health health, Sleep sleep, Mood mood, Hunger hunger) {
        this.health = health;
        this.sleep = sleep;
        this.mood = mood;
        this.hunger = hunger;
    }


    @Override
    public String toString() {
        return "Santé=" + health.getNiveau() +
                ", Faim=" + hunger.getNiveau() +
                ", Humeur=" + mood.getNiveau() +
                ", Sommeil=" + sleep.getNiveau();
    }

    public Health getHealth() {
        return health;
    }

    public Hunger getHunger() {
        return hunger;
    }

    public Mood getMood() {
        return mood;
    }

    public Sleep getSleep() {
        return sleep;
    }

}
