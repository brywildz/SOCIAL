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

    public void executeEvent(Individu ind, Evenement event)  {
        Reaction react = new Reaction(ind, event);
        ArrayList<BienEtre> etatAttendu = new ArrayList<>(react.analyze(ind, event).getList().values());
        HashMap<String, BienEtre> etatIndividu = ind.getEtat().getList();
        Iterator<BienEtre> it = etatIndividu.values().iterator();
        int i = 0;
        while(it.hasNext()){
            BienEtre be = it.next();
            BienEtre beAttendu = etatAttendu.get(i);
            be.setNiveau(be.getNiveau() + etatAttendu.get(i).getNiveau()); //faire à l'avenir une methode pour sommer dans individu pour gerer les max
            if(be instanceof Sommeil && beAttendu instanceof Sommeil){
                ((Sommeil) be).setSleeping(((Sommeil) beAttendu).isSleeping());
            }
            if(be instanceof Sante && beAttendu instanceof Sante){
                ((Sante) be).setMalade(((Sante) beAttendu).isMalade());
            }
            i++;
        }
    }


}
