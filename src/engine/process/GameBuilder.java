package engine.process;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Map;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.PersonRepository;
import engine.data.person.Personality;
import engine.data.person.vitality.*;

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
        Hunger hu = new Hunger(0, "couscous", "lentilles");
        Mood m = new Mood(6, "joviale");
        Health h = new Health(2, 56, false);
        Sleep s = new Sleep(5, false);
        PersonState personState = new PersonState(h,s,m,hu);
        Person per = new Person("Dylan,", 20, null, personState,null,
                new Block(20,20), 8 ,6 ,2 ,5 ,5 );
        Reaction.createPersonState(per);
        return per;
    }
}
