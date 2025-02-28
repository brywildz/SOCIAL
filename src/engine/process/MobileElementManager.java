package engine.process;

import engine.data.carte.Block;
import engine.data.carte.Map;
import engine.data.carte.Clock;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;

import java.util.*;

/**
 * Classe de traitement gérant les déplacements des individus sur la carte
 * (Classe obselète son utilité doit être rediscuté)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MobileElementManager implements MobileInterface {
    private Map map;
    private HashMap<Block, Individu> individus = IndividuRepository.getInstance().getIndividus();

    public MobileElementManager(Map map){
        this.map = map;
    }

    public HashMap<Block, Individu> getIndividus() {
        return individus;
    }

    @Override
    public void set(HashMap<Block, Individu> individus) {
        this.individus = individus;
    }

    public void nextSecond(){
        Clock.getInstance().newSecond();

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
