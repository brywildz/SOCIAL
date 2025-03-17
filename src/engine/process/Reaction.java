package engine.process;

import engine.data.event.*;
import engine.data.map.Block;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;
import engine.data.person.vitality.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe de traitement traitant les différents reaction d'un individu dépendamment de son caractère
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Reaction {
    private Person person;
    private String action;
    private Event event;

    public Reaction(Person person, Event event) {
        this.person = person;
        this.event = event;
    }

    public Reaction(String action, Person person) {
        this.action = action;
        this.person = person;
    }

    public Reaction(Person person){
        this.person = person;
    }

    /*public PersonState getExpectedState(Person ind, Event ev){
        PersonalityTrait maxPerso = ind.getPersonality().getMaxPerso();
        PersonState personState = createEtat();
        if(maxPerso instanceof Openness){
            if(ev instanceof WeatherEvent){
                personState.getMood().add(+2);
                personState.getSleep().add(+2);
            }
            if(ev instanceof PersonalEvent){
                personState.getMood().add(+2);
            }
            if(ev instanceof SocialEvent){
                personState.getMood().add(+1);
                personState.getHunger().add(-2);
                personState.getHealth().add(-4);
                personState.getSleep().add(-4);
            }
            if(ev.getId().equals("pluie")){
                goHome(ind);
            }
        }
        return personState;
    }*/
/*
    /**
     * Methode s'occupant du changement d'etat d'un individu en fonction de son etat initial pris dans l'instance d'Individu
     * et de l'etat "attendu" dependant du caractere de l'individu, elle change l'etat initial en modifiant la valeur à modifier
     * @param expectedState
     * @param actualState
     */
    /*
    public void changeState(ArrayList<Vitality> expectedState, HashMap<String, Vitality> actualState){
        Iterator<Vitality> it = actualState.values().iterator();
        int i = 0;
        while(it.hasNext()){
            Vitality actualIt = it.next();
            Vitality beAttendu = expectedState.get(i);
            actualIt.setNiveau(actualIt.getNiveau() + expectedState.get(i).getNiveau()); //faire à l'avenir une methode pour sommer dans individu pour gerer les max
            if(actualIt instanceof Sleep && beAttendu instanceof Sleep){
                ((Sleep) actualIt).setSleeping(((Sleep) beAttendu).isSleeping());
            }
            if(actualIt instanceof Health && beAttendu instanceof Health){
                ((Health) actualIt).setMalade(((Health) beAttendu).isMalade());
            }
            i++;
        }
    }

    public void changeState(){
        ArrayList<Vitality> expectedState = new ArrayList<>(getExpectedState(person, event).getList().values());
        HashMap<String, Vitality> actualState = person.getPersonState().getList();
        Iterator<Vitality> it = actualState.values().iterator();
        int i = 0;
        while(it.hasNext()){
            Vitality actualIt = it.next();
            Vitality beAttendu = expectedState.get(i);
            actualIt.setNiveau(actualIt.getNiveau() + expectedState.get(i).getNiveau()); //faire à l'avenir une methode pour sommer dans individu pour gerer les max
            if(actualIt instanceof Sleep && beAttendu instanceof Sleep){
                ((Sleep) actualIt).setSleeping(((Sleep) beAttendu).isSleeping());
            }
            if(actualIt instanceof Health && beAttendu instanceof Health){
                ((Health) actualIt).setMalade(((Health) beAttendu).isMalade());
            }
            i++;
        }
    }


    public PersonState createEtat(){
        Hunger hunger = new Hunger(0, null, null);
        Mood mood = new Mood(0, null);
        Health health = new Health(0, 0, null);
        Sleep sleep = new Sleep(0, null);
        return new PersonState(health, sleep, mood, hunger);
    }

    private void goHome(Person ind) {
        Block previousLocation = ind.getLocation();
        //IndividuRepository.getInstance().setNewLocation(ind, previousLocation,new Block(GameConfiguration.HOUSE_X/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_Y/GameConfiguration.BLOCK_SIZE));
    }

    public void goWork(Person ind) {

    }*/

    public static boolean weatherReact(Person p, WeatherEvent w){
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();
        if(w.getId().equals("pluie")){
            if(p.getNeuroticism().isLow()){
                if(p.getOpenness().isHigh()){
                    return true;
                }
            }
        }
        if(w.getId().equals("soleil")){
            if(p.getNeuroticism().isHigh()){
                if(p.getExtraversion().isLow()){
                    if(p.getOpenness().isLow()){
                        if(p.getAgreeableness().isLow()){
                            if(p.getConscientiousness().isLow()){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        if(w.getId().equals("neige")){
            if(p.getNeuroticism().isHigh() || p.getConscientiousness().isLow()){
                return false;
            }
            if(p.getExtraversion().isHigh() || p.getOpenness().isHigh()){
                return true;
            }
            return true;
        }
        if(w.getId().equals("nuageux")){
            if(p.getNeuroticism().isHigh() && p.getExtraversion().isLow()){
                return false;
            }
        }
        return true;
    }

    public static boolean lifeStyleReact(Person p){
        ActionRepository actionRepo = ActionRepository.getInstance();
        Action prefAction = actionRepo.getPreferredAction(p.getPersonality().getMaxPerso());
        //System.out.println(p.getNom() + "action pref : "+prefAction);
        if(p.isWorker() || p.isPupil()){
            if(p.isWorking()){
                return false;
            }
        }
        if(p.isSleeping()){
            return false;
        }
        if(p.getCurrentAction().getId().equals(prefAction.getId())){
            return false;
        }
        return true;
    }


    public void refreshLifeStyle() {

    }

    public void changeState(Action action) {
        Person p = person;
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();
        Personality per = person.getPersonality();
        PersonState ps = person.getPersonState();
        Health health = ps.getHealth(); Hunger hunger = ps.getHunger(); Mood mood = ps.getMood(); Sleep sleep = ps.getSleep();
        p.setCurrentAction(action);
        if(action.getId().equals("devoirs")){
            if(per.getConscienciosite().isHigh()){
                ps.getMood().add(2);
            }
            if(per.getConscienciosite().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-1);
                ps.getHunger().add(+1);
            }
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(-1);
            }
            if(per.getExtraversion().isLow()){
                ps.getHunger().add(+1);
            }
        }
        if(action.getId().equals("jeux d'équipe")){
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getExtraversion().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getAgreabilite().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-2);
                ps.getHunger().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                ps.getHealth().add(+1);
            }
            if(per.getConscienciosite().isLow()){
                ps.getMood().add(-1);
            }
        }
        if(action.getId().equals("sport intense")){
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(+2);
                ps.getHunger().add(-2);
            }
            if(per.getNeuroticisme().isLow()){
                ps.getHealth().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                ps.getHealth().add(+1);
                ps.getMood().add(+1);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-1);
            }
            if(per.getOuverture().isHigh()){
                ps.getMood().add(+1);
            }
        }
        if(action.getId().equals("arts créatifs")){
            if(per.getOuverture().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-1);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-1);
                ps.getHunger().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
        }
        if(action.getId().equals("activité bénévole")){
            if(per.getAgreabilite().isHigh()){
                mood.add(+2);
            }
            if(per.getAgreabilite().isLow()){
                mood.add(-2);
            }
            if(per.getExtraversion().isHigh()){
                mood.add(+1);
            }
            if(per.getNeuroticisme().isHigh()){
                mood.add(-1);
            }
        }
        if(action.getId().equals("apprentissage")){
            if(per.getOuverture().isHigh()){
                mood.add(+2);
            }
            if(per.getOuverture().isLow()){
                mood.add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                hunger.add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
        }
        if(action.getId().equals("travail")){
            if(ps.getSleep().isSleeping()){
                ps.getSleep().setSleeping(false);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+2);
            }
            if(per.getConscienciosite().isLow()){
                mood.add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                hunger.add(+1);
                mood.add(-1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(-1);
            }
            if(per.getNeuroticisme().isHigh()){
                mood.add(+1);
            }
        }
        if(action.getId().equals("dormir")){
            if(per.getNeuroticisme().isHigh()){
                mood.add(+2);
            }
            if(per.getConscienciosite().isLow()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
            if(per.getOuverture().isHigh()){
                hunger.add(+1);
            }
            ps.getSleep().setSleeping(true);
        }
    }

    public static void createPersonState(Person person){
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
}
