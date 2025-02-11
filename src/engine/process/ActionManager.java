package engine.process;

import engine.data.carte.Block;
import engine.data.evenement.Evenement;
import engine.data.individu.Individu;

import java.util.Random;

/**
 * classe qui gere les deplacement des individus
 */

public class ActionManager {
    private Evenement evenement;
    private Individu individu;

    public ActionManager(Evenement evenement, Individu individu) {
        this.evenement = evenement;
        this.individu = individu;
        if(evenement.getType().equals("nature")){
            naturalEvent();
        }
    }

    private void naturalEvent() {
        Random rand = new Random();
        int randomNumber1=rand.nextInt(10);
        int randomNumber2=rand.nextInt(10);
        individu.setLocation(new Block(randomNumber1,randomNumber2));
    }
}
