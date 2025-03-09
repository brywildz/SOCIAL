package engine.process;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.PersonRepository;
import engine.data.person.Personality;
import engine.data.person.bienetre.*;
import engine.data.person.caractere.*;

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
        Person person = createIndividuTest();
        PersonRepository.getInstance().addIndividu(person);
        mouvement.set(PersonRepository.getInstance().getIndividus());
    }

    public static Person createIndividuTest(){
        Personality p = new Personality(8, 6, 2, 5, 5);

        Faim f = new Faim(0, "couscous", "lentilles");
        Humeur h = new Humeur(6, "joviale");
        Sante s = new Sante(2, 56, false);
        Sommeil so = new Sommeil(5, false);
        HashMap<String, BienEtre> etatList = new HashMap<>();
        etatList.put("faim", f); etatList.put("humeur", h); etatList.put("sante", s); etatList.put("sommeil", so);
        PersonState personState = new PersonState(etatList);

        return new Person("Dylan,", 20, "clochard", personState,null, new Block(20,20), 8 ,6 ,2 ,5 ,5 );
    }
}
