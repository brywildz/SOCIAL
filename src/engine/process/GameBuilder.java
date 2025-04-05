package engine.process;

import config.GameConfiguration;
import engine.data.map.*;
import engine.data.map.Map;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;
import engine.data.person.Personality;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Worker;
import engine.data.person.vitality.*;
import engine.process.repository.InfrastructureRepository;

import java.util.*;


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

    public static void randomCreation() {
        InfrastructureRepository ir = InfrastructureRepository.getInstance();
        String[] prenoms = {
                "Alice", "Bob", "Charlie", "David", "Emma", "Felix", "Gabriel", "Hugo", "Isabelle", "Jules",
                "Katia", "Léo", "Marie", "Nathan", "Olivia", "Paul", "Quentin", "Rose", "Samuel", "Thomas",
                "Ursula", "Victor", "William", "Xavier", "Yasmine", "Zoé", "Antoine", "Bruno", "Clara", "Damien",
                "Elodie", "François", "Gisèle", "Henri", "Inès", "Julien", "Karine", "Louis", "Manon", "Nicolas",
                "Océane", "Pierre", "Raphaël", "Sophie", "Théo", "Ugo", "Valentin", "Wendy", "Xenia", "Yohan",
                "Zacharie", "Adrien", "Bernard", "Camille", "Dorian", "Estelle", "Florian", "Guillaume", "Hélène",
                "Ibrahim", "Joséphine", "Kevin", "Laurent", "Margaux", "Noémie", "Olivier", "Pascal", "Rebecca",
                "Simon", "Tiffany", "Ulrich", "Violette", "Warren", "Xander", "Yvette", "Zinedine"
        };
        int randomIndex = random(prenoms.length);
        String name = prenoms[randomIndex];
        int age = random(60);
        randomIndex = random(2);
        SocialState ss = null;
        if(randomIndex == 0){
            int[] min = {0,15,30,45};
            Time start = new Time(random(7,12), min[random(min.length)],0);
            Time end = new Time(random(17,22), min[random(min.length)],0);
            ss = new Worker(ir.randomWorkingPlace(), start, end);
        }
        if(randomIndex == 1){
            Time start = new Time(8,30,0);
            Time end = new Time(17,0,0);
            ss = new Pupil(ir.get("école"), start, end);
        }
        Personality personality = new Personality(random(10),random(10),
                random(10), random(10), random(10));
        Health h = new Health(random(10),0, false);
        Hunger hun = new Hunger(random(10), "undefined", "undefined");
        Mood m = new Mood(random(10), "undefined");
        Sleep s = new Sleep(random(10), false);
        PersonState ps = new PersonState(h,s,m,hun);

        Infrastructure house = ir.getRandomHouse();
        Block location = house.getBase();



        Person per = new Person(name, age, ss, ps, null, location, random(10),
                random(10), random(10), random(10), random(10));
        per.setHouse(house);
        Reaction.createPersonState(per);
        PersonRepository.getInstance().addIndividu(per);
        InfrastructureRepository.getInstance().addPerson(per,per.getSocialState().getInfrastructure());

        ArrayList<Person> friends = new ArrayList<Person>();
        List<Person> listPerson = new ArrayList<>(PersonRepository.getInstance().getIndividus().values());
        Collections.shuffle(listPerson);
        if(per.getPersonality().getExtraversion().isHigh()){
            randomIndex = random(20,50);
        }
        else if(per.getPersonality().getExtraversion().isLow()){
            randomIndex = random(0,10);
        }
        else{
            randomIndex = random(5,19);
        }
        for(int i=0; i<randomIndex; i++){
            per.getRelation().addFriend(listPerson.get(i));
        }


        PersonRepository.getInstance().setNewLocation(per, per.getHouse().getRandomBlock());
    }

    public int getRandomIndexRelationShip(Personality personality, String type){
        int randomIndex=0;
        if(type.equals("friends")){
            if(personality.getExtraversion().isHigh()){
                randomIndex = random(20,50);
            }
            else if(personality.getExtraversion().isLow()){
                randomIndex = random(0,10);
            }
            else{
                randomIndex = random(5,19);
            }
        }
        else if(type.equals("work")){
            if(personality.getExtraversion().isHigh()){
                randomIndex = random(5,15);
            }
            else if(personality.getAgreabilite().isHigh()){
                randomIndex = random(5,10);
            }
            else if(personality.getAgreabilite().isLow()){
                randomIndex = random(0,1);
            }
            else if(personality.getExtraversion().isLow()){
                randomIndex = random(2,5);
            }
            else{
                randomIndex = random(3,6);
            }
        }
        else if(type.equals("family")){
            randomIndex = random(0,5);
        }
        return randomIndex;
    }

    public static int random(int min, int max){
        return (int) (Math.random() * (max + 1 - min)) + min;
    }

    public static int random(int max){
        return random(0,max);
    }
}
