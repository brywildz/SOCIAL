package engine.process;

import engine.data.map.Block;
import engine.data.event.Event;
import engine.data.event.WeatherEvent;
import engine.data.event.PersonalEvent;
import engine.data.event.SocialEvent;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.vitality.*;
import engine.data.person.personalityTraits.PersonalityTrait;
import engine.data.person.personalityTraits.Openness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe de traitement traitant les différents reaction d'un individu dépendament de son caractère
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

    public PersonState getExpectedState(Person ind, Event ev){
        PersonalityTrait maxPerso = ind.getPersonality().getMaxPerso();
        PersonState personState = createEtat();
        if(maxPerso instanceof Openness){
            if(ev instanceof WeatherEvent){
                personState.getList().get("humeur").setNiveau(+2);
                personState.getList().get("sommeil").setNiveau(+2);
            }
            if(ev instanceof PersonalEvent){
                personState.getList().get("humeur").setNiveau(+2);
            }
            if(ev instanceof SocialEvent){
                personState.getList().get("humeur").setNiveau(+1);
                personState.getList().get("faim").setNiveau(-2);
                personState.getList().get("sante").setNiveau(-4);
                personState.getList().get("sommeil").setNiveau(-4);
            }
            if(ev.getId().equals("pluie")){
                goHome(ind);
            }
        }
        return personState;
    }

    /**
     * Methode s'occupant du changement d'etat d'un individu en fonction de son etat initial pris dans l'instance d'Individu
     * et de l'etat "attendu" dependant du caractere de l'individu, elle change l'etat initial en modifiant la valeur à modifier
     * @param expectedState
     * @param actualState
     */
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
        HashMap<String, Vitality> etat = new HashMap<>();
        etat.put("hunger", hunger); etat.put("mood", mood); etat.put("health", health); etat.put("sleep", sleep);
        return new PersonState(etat);
    }

    private void goHome(Person ind) {
        Block previousLocation = ind.getLocation();
        //IndividuRepository.getInstance().setNewLocation(ind, previousLocation,new Block(GameConfiguration.HOUSE_X/GameConfiguration.BLOCK_SIZE,GameConfiguration.HOUSE_Y/GameConfiguration.BLOCK_SIZE));
    }

    public void goWork(Person ind) {

    }

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
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();

        return true;
    }


    public void refreshLifeStyle() {

    }
}
