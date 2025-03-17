package engine.process;

import config.GameConfiguration;
import engine.data.event.Action;
import engine.data.map.*;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.PersonRepository;
import engine.data.person.Personality;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
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
        InfrastructureRepository ir = InfrastructureRepository.getInstance();
        Infrastructure taff = ir.get("école");
        Infrastructure house = ir.get("apartment1");
        Personality p = new Personality(8, 6, 2, 5, 5);
        Hunger hu = new Hunger(0, "couscous", "lentilles");
        Mood m = new Mood(6, "joviale");
        Health h = new Health(2, 56, false);
        Sleep s = new Sleep(5, false);
        PersonState personState = new PersonState(h,s,m,hu);
        Pupil pupil = new Pupil(ir.get("école"),new Time(8,30,0), new Time(17,0,0));
        Person per = new Person("Dylan", 20, pupil, personState,null,
                house.getBase(), 8 ,6 ,2 ,5 ,5 );
        per.setHouse(house);
        per.getSocialState().setInfrastructure(taff);
        Reaction.createPersonState(per);
        return per;
    }
}
