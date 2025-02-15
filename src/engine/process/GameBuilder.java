package engine.process;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Carte;
import engine.data.individu.Individu;

public class GameBuilder {

    public static Carte buildCarte() {
        return new Carte(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
    }

    public static MobileInterface buildInitMobile(Carte carte){
        MobileInterface manager = new MobileElementManager(carte);
        initializeIndividu(carte, manager);
        return manager;
    }

    public static void initializeIndividu(Carte carte, MobileInterface mouvement){
        Block block = carte.getBlock(3,3);
        Individu individu = new Individu("Dylan", 20, "Etudiant", null, null, null, block);
        mouvement.set(IndividuRepository.getInstance().getIndividus());
    }
}
