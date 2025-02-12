package engine.process;

import engine.data.carte.Carte;

import java.util.ArrayList;

public class GameBuilder {

    public static Carte buildCarte() {
        return new Carte(800, 800);
    }

    public static MouvementIndividu buildInitMobile(Carte carte){
        MouvementIndividu manager = new ActionManager(carte);
    }

    public static void initializeIndividu(Carte carte, MouvementIndividu mouvement){

    }
}
