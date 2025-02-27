package engine.process;

import config.GameConfiguration;
import engine.data.carte.Block;
import engine.data.carte.Map;
import engine.data.individu.Etat;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;
import engine.data.individu.Personnalite;
import engine.data.individu.bienetre.*;
import engine.data.individu.caractere.*;

import java.util.HashMap;

/**
 * Classe de traitement qui instancie les objets cruciaux au bon fonctionnement du logiciel
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class GameBuilder {

    public static Map buildCarte() {
        return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
    }

    public static MobileInterface buildInitMobile(Map map){
        MobileInterface manager = new MobileElementManager(map);
        initializeIndividu(map, manager);
        return manager;
    }

    public static void initializeIndividu(Map map, MobileInterface mouvement){
        Individu individu = createIndividuTest();
        IndividuRepository.getInstance().addIndividu(individu);
        mouvement.set(IndividuRepository.getInstance().getIndividus());
    }

    public static Individu createIndividuTest(){
        Ouverture o = new Ouverture(8);
        Extraversion e = new Extraversion(6);
        Neuroticisme n = new Neuroticisme(2);
        Agreabilite a = new Agreabilite(5);
        Conscienciosite c = new Conscienciosite(5);
        Personnalite p = new Personnalite("ouverture", e, n, a, c, o);

        Faim f = new Faim(0, "couscous", "lentilles");
        Humeur h = new Humeur(6, "joviale");
        Sante s = new Sante(2, 56, false);
        Sommeil so = new Sommeil(5, false);
        HashMap<String, BienEtre> etatList = new HashMap<>();
        etatList.put("faim", f); etatList.put("humeur", h); etatList.put("sante", s); etatList.put("sommeil", so);
        Etat etat = new Etat(etatList);

        return new Individu("Dylan,", 20, "clochard", etat,null, new Block(20,20), 8 ,6 ,2 ,5 ,5 );
    }
}
