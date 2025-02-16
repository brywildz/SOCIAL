package engine.process;

import engine.data.carte.Block;
import engine.data.individu.Individu;

import java.util.HashMap;

public interface MobileInterface {

    public HashMap<Block, Individu> getIndividus();

    void set(HashMap<Block, Individu> individus);

    void nextSecond();
}
