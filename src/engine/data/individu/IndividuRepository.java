package engine.data.individu;

import engine.data.carte.Block;

import java.util.HashMap;

/**
 * Singleton gerant le stockage tout les individus présent sur la carte
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class IndividuRepository {
    private HashMap<Block, Individu> individus = new HashMap<>();
    private static IndividuRepository instance;

    private IndividuRepository() {}

    public static IndividuRepository getInstance() {
        if (instance == null) {
            instance = new IndividuRepository();
        }
        return instance;
    }

    public HashMap<Block, Individu> getIndividus() {
        return individus;
    }

    public void addIndividu(Individu individu) {
        individus.put(individu.getLocation(), individu);
    }

    public Individu getIndividu(Block block) {
        return individus.get(block);
    }

    public void setNewLocation(Individu individu, Block previousLocation, Block newLocation){
        if(individus.containsKey(previousLocation)){
            individus.remove(previousLocation);
            individu.setLocation(newLocation);
            addIndividu(individu);
        }
    }
}
