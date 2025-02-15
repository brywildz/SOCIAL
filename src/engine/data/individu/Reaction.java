package engine.data.individu;

import engine.data.carte.Block;
import engine.data.evenement.Evenement;

import java.util.Random;

public class Reaction {
    private Individu individu;
    private Evenement evenement;

    public Reaction(Individu individu, Evenement evenement) {
        this.individu = individu;
        this.evenement = evenement;
    }

    public void rain(){
        Random rand = new Random();
        int randomNumber1=rand.nextInt(10);
        int randomNumber2=rand.nextInt(10);
        individu.setLocation(new Block(randomNumber1, randomNumber2));
    }

    public void action(Evenement ev){
        if(ev==null){
            /*verifier si l'horaire de l'evennement est passé, si c'est le cas l'enlever (attribut horaire da
            dans la classe ici et evennement
             */
        }
        else{
            individu.setCurrentEvent(ev);
            if(evenement.getNom().equals("rain")){
                rain();
            }
        }
    }
}
