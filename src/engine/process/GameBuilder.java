package engine.process;

import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;

import java.util.ArrayList;

public class GameBuilder {

    public static Carte buildCarte() {
        return new Carte(800, 800);
    }

    public static MouvementIndividu buildInitMobile(Carte carte){
        MouvementIndividu manager = new ActionManager(carte);
        initializeIndividu(carte, manager);
        return manager;
    }

    public static void initializeIndividu(Carte carte, MouvementIndividu mouvement){
        Block block = carte.getBlock(3,3);
        Individu individu = new Individu("Dylan", 20, "Etudiant", null, null, null, block);
        mouvement.set(individu);
    }
}
