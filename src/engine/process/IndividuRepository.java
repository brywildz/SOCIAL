package engine.process;

import engine.data.carte.Block;
import engine.data.individu.Individu;

import java.util.HashMap;

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
}
