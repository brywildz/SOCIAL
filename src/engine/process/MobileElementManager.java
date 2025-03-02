package engine.process;

import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.map.Clock;
import engine.data.person.Person;
import engine.data.person.PersonRepository;

import java.util.*;

/**
 * Classe de traitement gérant les déplacements des individus sur la carte
 * (Classe obsolete son utilité doit être rediscuté)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MobileElementManager implements MobileInterface {
    private Map map;
    private HashMap<Block, Person> individus = PersonRepository.getInstance().getIndividus();

    public MobileElementManager(Map map){
        this.map = map;
    }

    public HashMap<Block, Person> getIndividus() {
        return individus;
    }

    @Override
    public void set(HashMap<Block, Person> individus) {
        this.individus = individus;
    }

    public void nextSecond(){
        Clock.getInstance().newSecond();
        Iterator<Person> it = individus.values().iterator();
        while(it.hasNext()){
            Person ind = it.next();
            if(ind.getCurrentEvent()==null){

            }
        }
    }

    /*private void moveEnemies() {

        for (Individu individu : individus.values()) {
            Block position = individu.getLocation();

            if (!map.isOnBottom(position)) {
                Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
                enemy.setPosition(newPosition);
            } else {
                outOfBoundEnemies.add(enemy);
            }

        }

        for (Enemy enemy : outOfBoundEnemies) {
            enemies.remove(enemy);
        }

    }*/

}
