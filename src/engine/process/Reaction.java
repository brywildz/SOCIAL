package engine.process;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.evenement.Evenement;
import engine.data.evenement.EventMeteo;
import engine.data.evenement.EventPersonnel;
import engine.data.evenement.EventSocial;
import engine.data.individu.Etat;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;
import engine.data.individu.bienetre.*;

import java.util.HashMap;

/**
 * Classe de traitement traitant les différents reaction d'un individu dépendament de son caractère
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Reaction {
    private Individu individu;
    private Evenement evenement;

    public Reaction(Individu individu, Evenement evenement) {
        this.individu = individu;
        this.evenement = evenement;
    }


    public Etat analyze(Individu ind, Evenement ev){
        String type = ind.getPerso().getType();
        Etat etat = createEtat();
        if(type.equals("ouverture")){
            if(ev instanceof EventMeteo){
                etat.getList().get("humeur").setNiveau(+2);
                etat.getList().get("sommeil").setNiveau(+2);
            }
            if(ev instanceof EventPersonnel){
                etat.getList().get("humeur").setNiveau(+2);
            }
            if(ev instanceof EventSocial){
                etat.getList().get("humeur").setNiveau(+1);
                etat.getList().get("faim").setNiveau(-2);
                etat.getList().get("sante").setNiveau(-4);
                etat.getList().get("sommeil").setNiveau(-4);
            }
            if(ev.getId().equals("pluie")){
                goHome(ind);
            }
        }
        return etat;
    }

    public Etat createEtat(){
        Faim faim = new Faim(0, null, null);
        Humeur humeur = new Humeur(0, null);
        Sante sante = new Sante(0, 0, null);
        Sommeil sommeil = new Sommeil(0, null);
        HashMap<String, BienEtre> etat = new HashMap<>();
        etat.put("faim",faim); etat.put("humeur",humeur); etat.put("sante",sante); etat.put("sommeil",sommeil);
        return new Etat(etat);
    }

    private void goHome(Individu ind) {
        Block previousLocation = ind.getLocation();
        IndividuRepository.getInstance().setNewLocation(ind, previousLocation,new Block(GameConfiguration.HOUSE_X/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_Y/GameConfiguration.BLOCK_SIZE));
    }



}
