package engine.process;

import engine.data.carte.Block;
import engine.data.evenement.Evenement;
import engine.data.evenement.EventMeteo;
import engine.data.evenement.EventRepository;
import engine.data.individu.Individu;
import engine.data.individu.IndividuRepository;
import engine.data.individu.bienetre.BienEtre;
import engine.data.individu.bienetre.Sante;
import engine.data.individu.bienetre.Sommeil;
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
    private Individu ind;
    private String idEvent;
    private IndividuRepository indRepo = IndividuRepository.getInstance();
    private EventRepository eventRepo = EventRepository.getInstance();

    public EventManager(Individu ind, String idEvent) {
        this.ind = ind;
        this.idEvent = idEvent;
    }

    public EventManager(String idEvent){
        this.idEvent = idEvent;
        HashMap<Block, Individu> listInd = indRepo.getIndividus();
        EventMeteo em = (EventMeteo) eventRepo.getEvenement(idEvent);
        Iterator<Individu> it = listInd.values().iterator();
        while(it.hasNext()){
            Individu ind = it.next();
            executeEvent(ind, em);
        }
    }

    /**
     * Methode s'occupant du changement d'etat d'un individu en fonction de son etat initial pris dans l'instance d'Individu
     * et de l'etat "attendu" dependant du caractere de l'individu, elle change l'etat initila en modifiant la valeur à modifier
     * @param expectedState
     * @param actualState
     */
    public void changeState(ArrayList<BienEtre> expectedState, HashMap<String, BienEtre> actualState){
        Iterator<BienEtre> it = actualState.values().iterator();
        int i = 0;
        while(it.hasNext()){
            BienEtre actualIt = it.next();
            BienEtre beAttendu = expectedState.get(i);
            actualIt.setNiveau(actualIt.getNiveau() + expectedState.get(i).getNiveau()); //faire à l'avenir une methode pour sommer dans individu pour gerer les max
            if(actualIt instanceof Sommeil && beAttendu instanceof Sommeil){
                ((Sommeil) actualIt).setSleeping(((Sommeil) beAttendu).isSleeping());
            }
            if(actualIt instanceof Sante && beAttendu instanceof Sante){
                ((Sante) actualIt).setMalade(((Sante) beAttendu).isMalade());
            }
            i++;
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
    private void stayAtLocation() {
    }

    /**
     * Methode centralisant l'ensemble des methode liée à l'execution d'un evenennement donc le changement de reaction et
     * le changement de localisation
     * @param ind
     * @param event
     */
    public void executeEvent(Individu ind, Evenement event)  {
        if(ind.getCurrentEvent()==null){
            ind.setCurrentEvent(event);
            Reaction react = new Reaction(ind, event);
            ArrayList<BienEtre> expectedState = new ArrayList<>(react.getExpectedState(ind, event).getList().values());
            HashMap<String, BienEtre> actualState = ind.getEtat().getList();
            changeState(expectedState, actualState);
        }
        else{
            if(ind.getCurrentEvent().isFinish()){
                ind.setCurrentEvent(null);
            }
            changeLocation(false);
        }

    }


}
