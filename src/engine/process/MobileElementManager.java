package engine.process;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.carte.Horloge;
import engine.data.evenement.Evenement;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe de traitement gérant les déplacements des individus sur la carte
 * (Classe obselète son utilité doit être rediscuté)
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class MobileElementManager implements MobileInterface {
    private Carte carte;
    private HashMap<Block, Individu> individus = IndividuRepository.getInstance().getIndividus();

    public MobileElementManager(Carte carte){
        this.carte=carte;
    }

    public HashMap<Block, Individu> getIndividus() {
        return individus;
    }

    @Override
    public void set(HashMap<Block, Individu> individus) {
        this.individus = individus;
    }

    public void nextSecond(){
        Horloge.getInstance().newSecond();
    }

}
