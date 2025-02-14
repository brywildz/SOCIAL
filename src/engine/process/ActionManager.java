package engine.process;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.evenement.Evenement;
import engine.data.individu.Individu;

import java.util.Random;

/**
 * classe qui gere les deplacement des individus
 */

public class ActionManager implements MouvementIndividu{

    private Carte carte;
    private Evenement evenement;
    private Individu individu;

    public ActionManager(Evenement evenement, Individu individu) {
        this.evenement = evenement;
        this.individu = individu;
        if(evenement.getType().equals("nature")){
            naturalEvent();
        }
    }

    public ActionManager(Carte carte){
        this.carte=carte;
    }

    public void naturalEvent() {
        Random rand = new Random();
        int randomNumber1=rand.nextInt(10);
        int randomNumber2=rand.nextInt(10);
        individu.setLocation(new Block(randomNumber1,randomNumber2));
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Individu getIndividu() {
        return individu;
    }

    @Override
    public void set(Individu individu) {
        this.individu = individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }
}
