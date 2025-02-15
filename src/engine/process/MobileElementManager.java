package engine.process;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.carte.Horloge;
import engine.data.evenement.Evenement;
import engine.data.individu.Individu;
import engine.data.individu.Reaction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * classe qui gere les deplacement des individus
 */

public class MobileElementManager implements MobileInterface {

    private Carte carte;
    private HashMap<Block, Individu> individus = IndividuRepository.getInstance().getIndividus();

    public MobileElementManager(Evenement evenement, HashMap<Block, Individu> individu) {
        this.individus = individu;
        /*if(evenement.getType().equals("nature")){
            rainEvent();
        }*/
    }

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

    public void action(Individu ind, Evenement ev) {
        Reaction react = new Reaction(ind, ev);

    }

    public void nextSecond(){
        //individu.action (fais agir les individu donc mettre cela dans un boucle)
        Horloge.getInstance().newSecond();
    }

    public void naturalEvent() {
        Iterator<Individu> it = individus.values().iterator();
        while(it.hasNext()){
            Individu ind = it.next();
            Random rand = new Random();
            int randomNumber1=rand.nextInt(10);
            int randomNumber2=rand.nextInt(10);
            ind.setLocation(new Block(randomNumber1, randomNumber2));
        }
    }
}
