package engine.process;

import engine.data.map.Block;
import engine.data.event.Event;
import engine.data.event.WeatherEvent;
import engine.data.event.EventRepository;
import engine.data.person.Person;
import engine.data.person.PersonRepository;
import engine.data.person.bienetre.BienEtre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe de traitement traitant la gestion des evennement vis à vis des individus
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventManager {
    private Person ind;
    private String idEvent;
    private PersonRepository indRepo = PersonRepository.getInstance();
    private EventRepository eventRepo = EventRepository.getInstance();

    public EventManager(Person ind, String idEvent) {
        this.ind = ind;
        this.idEvent = idEvent;
    }

    public EventManager(String idEvent){
        this.idEvent = idEvent;
        HashMap<Block, Person> listInd = indRepo.getIndividus();
        WeatherEvent em = (WeatherEvent) eventRepo.getEvent(idEvent);
        Iterator<Person> it = listInd.values().iterator();
        while(it.hasNext()){
            Person ind = it.next();
            //executeEvent(ind, em);
        }
    }


    /**
     * Methode qui s'utilise pour changer la localisation d'un individu en fonction du besoin
     */
    public void changeLocation(boolean newEvent){
        if(newEvent){

        }

    }

    /**
     * Methode qui s'utilise dans le cas où l'event n'est pas terminé et que l'individu n'est pas en sitution d'immobilité
     * elle fait bouger l'individu tout en le laissant dans son lieu d'evennement (ex : bouger dans sa maison)
     */
    private void stayAtLocation(Person ind) {
        Event e = ind.getEvent();
    }

    /**
     * Methode centralisant l'ensemble des methode liée à l'execution d'un evenennement donc le changement de reaction et
     * le changement de localisation
     * @param ind
     */
    public void executeEvent(Person ind)  {
        if(ind.getEvent()==null){
            Event event = EventRepository.getRandomEvent();
            ind.setEvent(event);
            Reaction react = new Reaction(ind, event);
            ArrayList<BienEtre> expectedState = new ArrayList<>(react.getExpectedState(ind, event).getList().values());
            HashMap<String, BienEtre> actualState = ind.getEtat().getList();
            react.changeState(expectedState, actualState);
        }
        else{
            if(ind.getEvent().isFinish()){
                ind.setEvent(null);
                //CHANGEMENT DE COULEUR POUR INDIQUER UN NOUVEL EVENT
            }
            else{
                stayAtLocation(ind); //compliqué car on devra faire du cas par cas
            }
        }

    }

    public static void executeWeatherEvent(){

    }


}
