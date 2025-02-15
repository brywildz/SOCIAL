package engine.data.evenement;

import engine.data.carte.Block;
import engine.data.individu.Individu;
import engine.process.IndividuRepository;

import java.util.HashMap;

public class EventMeteo extends Evenement {
    int duree;
    HashMap<Block, Individu> individus = IndividuRepository.getInstance().getIndividus();

    public EventMeteo(String nom, String description, int duree) {
        super(nom, description, duree);
    }


    public String afficherEtat(){
        System.out.println("L'évenement en cours :"+ nom +",description:"+description+"sont impact meteo :"+impactMeteo+"la durée global de l'event"+duree);
        return "";
    }

    @Override
    public void afficherEffet() {

    }
}
