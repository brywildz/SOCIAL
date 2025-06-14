package engine.process.builder;

import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.PersonRelationships;
import engine.data.person.PersonState;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Unemployed;
import engine.data.person.socialState.Worker;
import engine.data.person.vitality.Health;
import engine.data.person.vitality.Hunger;
import engine.data.person.vitality.Mood;
import engine.data.person.vitality.Sleep;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.NameRepository;
import engine.process.repository.PersonRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.utils.ScoringUtilities.*;

public class PersonBuilder {
    InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    PersonRepository personRepo = PersonRepository.getInstance();
    Person person;

    public PersonBuilder(){
        person = new Person();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Person> coworker = new ArrayList<>();
        ArrayList<Person> family = new ArrayList<>();
        PersonRelationships pr = new PersonRelationships(friends, family, coworker);
        person.setRelation(pr);
    }

    public Person buildPerson() throws FileNotFoundException {
        person.setName(createName());
        person.setAge(createAge());
        person.setSocialState(createSocialState());
        person.setPersonality(createPersonality());
        person.setPersonState(createPersonState());
        person.setHouse(createHouse());
        person.setLocation(person.getHouse().getBase());
        createRelationList();
        createPersonLifeStyle();

        personRepo.addIndividu(person);
        infraRepo.addPerson(person,person.getSocialState().getInfrastructure());
        personRepo.movePerson(person, person.getHouse().getRandomBlock());

        return person;
    }

    public Person buildFirstPerson() throws FileNotFoundException {
        person.setName(createName());
        person.setAge(createAge());
        person.setSocialState(createSocialState());
        person.setPersonality(createPersonality());
        person.setPersonState(createPersonState());
        person.setHouse(createHouse());
        person.setLocation(person.getHouse().getBase());
        createPersonLifeStyle();

        personRepo.addIndividu(person);
        infraRepo.addPerson(person,person.getSocialState().getInfrastructure());
        personRepo.movePerson(person, person.getHouse().getRandomBlock());

        return person;
    }

    public String createName() throws FileNotFoundException {
        NameRepository nameRepo = NameRepository.getInstance();
        String name = nameRepo.getNames().get(nameRepo.getNameIndex());
        nameRepo.setNameIndex(nameRepo.getNameIndex()+1);
        return name;
    }

    public int createAge(){
        return random(60);
    }

    public SocialState createSocialState(){
        double randomIndex = random(2);
        SocialState ss = null;
        if(randomIndex == 0){
            int[] min = {0,15,30,45};
            Time start = new Time(random(7,12), min[random(min.length)],0);
            Time end = new Time(random(17,21), min[random(min.length)],0);
            ss = new Worker(infraRepo.randomWorkingPlace(), start, end);
        }
        if(randomIndex == 1){
            Time start = new Time(8,30,0);
            Time end = new Time(17,0,0);
            ss = new Pupil(infraRepo.randomSchool(), start, end);
        }
        return ss;
    }

    public Personality createPersonality(){
        ArrayList<Integer> pers = new ArrayList<>();
        while(pers.size()<5){
            int randomValue = random(1,11);
            if(!pers.contains(randomValue)){
                pers.add(randomValue);
            }
        }
        Personality per = new Personality(pers.get(0),pers.get(1), pers.get(2), pers.get(3), pers.get(4));
        Time wakeUpTimeWeekEnd = createWakeTimeWeekEnd(per);
        Time sleepTimeWeekEnd = createSleepTimeWeekEnd(per);
        per.setWakeUpTimeWeekEnd(wakeUpTimeWeekEnd);
        per.setSleepTimeWeekEnd(sleepTimeWeekEnd);
        return per;
    }

    public PersonState createPersonState(){
        Health h = new Health(random(1,11),0, false);
        Hunger hun = new Hunger(random(1,11), "undefined", "undefined");
        Mood m = new Mood(random(1,11), "undefined");
        Sleep s = new Sleep(random(1,11), false);
        return new PersonState(h,s,m,hun);
    }

    public Infrastructure createHouse(){
        return infraRepo.getRandomHouse();
    }

    public void createPersonLifeStyle(){
        Time start = person.getPersonState().getSleep().getSleepTime();
        Time end = person.getPersonState().getSleep().getWakeUpTime();
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            start.setNew(new Time(2,0,0));
            end.setNew(new Time(10,0,0));
        }
        if(maxPerso instanceof Agreeableness){
            start.setNew(new Time(23,0,0));
            end.setNew(new Time(8,0,0));
        }
        if(maxPerso instanceof Conscientiousness){
            start.setNew(new Time(22,30,0));
            end.setNew(new Time(6,30,0));
        }
        if(maxPerso instanceof Neuroticism){
            start.setNew(new Time(2,30,0));
            end.setNew(new Time(8,0,0));
        }
        if(maxPerso instanceof Openness){
            start.setNew(new Time(3,0,0));
            end.setNew(new Time(11,0,0));
        }
    }

    public int getIndexRelationShip(Personality personality, String type){
        int randomIndex=0;
        double extraversion = personality.getExtraversion().getLevel();
        double agreeableness = personality.getAgreabilite().getLevel();
        double neuroticism = personality.getNeuroticisme().getLevel();
        double conscientiousness = personality.getConscienciosite().getLevel();
        double openness = personality.getOuverture().getLevel();
        int friendsBase = 2;
        int proBase = 2;
        switch (type) {
            case "friends" : {
                int nbAmis = (int) Math.round(friendsBase + extraversion * 0.6 + agreeableness * 0.4 - neuroticism * 0.6);
                return nbAmis = Math.max(1,nbAmis);
            }
            case "work" : {
                int nbPro = (int) Math.round(proBase + extraversion * 0.4 + conscientiousness * 0.6 + openness * 0.3 - neuroticism * 0.4);
                return nbPro = Math.max(1,nbPro);
            }
            case "family" : randomIndex = random(1, 10);
        }
        return randomIndex;
    }

    public void createRelationList(){
        createFriendsList();;
        createFamilyList();
        if(!(person.getSocialState() instanceof Unemployed)){
            createProList();
        }
    }

    public void createFriendsList(){
        int number = getIndexRelationShip(person.getPersonality(),"friends");
        List<Person> listPerson = new ArrayList<>(personRepo.getPersons().values());
        Collections.shuffle(listPerson);
        boolean stop = false;
        int index = 0;
        while(person.getRelation().getAmicale().size() < number){
            if(index == listPerson.size() && !stop){
                stop = true;
                index = 0;
                Collections.shuffle(listPerson);
            }
            Person p = listPerson.get(index);
            if(!(person.getRelation().getAmicale().contains(p)) && p!=person){
                if((isCompatible(person, p, "friends") || stop) && !(p.getRelation().isFull("friends", number))){
                    person.getRelation().getAmicale().add(p);
                    p.getRelation().getAmicale().add(person);
                }
                else if(random(5) == 0){
                    person.getRelation().getAmicale().add(p);
                    p.getRelation().getAmicale().add(person);
                }
            }
            index++;
        }
    }

    public void createFamilyList(){
        int number = random(1,21);
        List<Person> listPerson = new ArrayList<>(personRepo.getPersons().values());
        while(person.getRelation().getFamiliale().size() < number){
            int index = random(1, listPerson.size());
            Person p = listPerson.get(index);
            if((!person.getRelation().getAmicale().contains(p)) && p!=person){
                if(!(p.getRelation().isFull("family", number))){
                    person.getRelation().getFamiliale().add(p);
                    p.getRelation().getAmicale().add(person);
                }
            }
        }
    }

    public void createProList(){
        int number = getIndexRelationShip(person.getPersonality(),"work");
        List<Person> listPerson = new ArrayList<>(person.getSocialState().getInfrastructure().getPersons());
        int coworker = person.getSocialState().getInfrastructure().getPersons().size();
        Collections.shuffle(listPerson);
        boolean stop = false;
        int index = 0;
        while(person.getRelation().getPro().size() < Math.min(coworker,number)-1){
            if(index == listPerson.size() && !stop){
                stop = true;
                index = 0;
                Collections.shuffle(listPerson);
            }
            Person p = listPerson.get(index);
            if(!(person.getRelation().getPro().contains(p)) && p!=person){
                if((isCompatible(person, p, "work") || stop) && !(p.getRelation().isFull("work", number))){
                    person.getRelation().getPro().add(p);
                    p.getRelation().getPro().add(person);
                }
                else if(random(5) == 0){
                    person.getRelation().getPro().add(p);
                    p.getRelation().getPro().add(person);
                }
            }
            index++;
        }
    }

}
