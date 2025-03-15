package engine.process;

import engine.data.event.Action;
import engine.data.event.ActionRepository;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.personalityTraits.*;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe de traitement gerant les evennements et les actions de Life Style, c'est-à-dire travail hobbies...
 */
public class LifeManager {
    private Person person;

    public LifeManager(Person person) {
        this.person = person;
    }

    /**
     * Methode pour attribuer un nouveau hobbie à une personne en fonction de sa personnalité
     */
    public void setNewActionInside() {
        Action a = new Action(null, null, null);
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Neuroticism){
            a.setId("méditation");
        }
        if(maxPerso instanceof Openness){
            a.setId("arts créatifs");
        }
        if(maxPerso instanceof Conscientiousness){
            a.setId("apprentissage");
        }
        else{
            Random rand = new Random();
            List<Action> l = new ArrayList<>(ActionRepository.getInstance().getInsideActions().values());
            a = l.get(rand.nextInt(l.size()));

        }
        a.setStart(Clock.getInstance().getHoraire());
        a.setEnd(ActionRepository.getInstance().getRandomTimer(person, a.getId()));
        person.setCurrentAction(a);
    }

    public void setNewActionOutside() {
        Action a = new Action(null, null, null);
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            a.setId("sport d'équipe");
        }
        if(maxPerso instanceof Agreeableness){
            a.setId("activité bénévoles");
        }
        else{
            Random rand = new Random();
            List<Action> l = new ArrayList<>(ActionRepository.getInstance().getOutsideActions().values());
            a = l.get(rand.nextInt(l.size()));

        }
        a.setStart(Clock.getInstance().getHoraire());
        a.setEnd(ActionRepository.getInstance().getRandomTimer(person, a.getId()));
        person.setCurrentAction(a);
    }

    public void refreshRoutine() {
        ActionRepository ar = ActionRepository.getInstance();
        Time time = Clock.getInstance().getHoraire();
        SocialState ss = person.getSocialState();
        if(person.isWorker()){
            Worker w = (Worker) person.getSocialState();
            if(time.equals(w.getStartTime())){
                goWork();
                person.setCurrentAction(ar.getAction("travail"));
            }
            else if(time.equals(w.getEndTime())){
                goHome();
                person.setCurrentAction(ar.getAction("travail"));
            }
            else{
                refreshLocation(ss);
            }
        }
        if(person.isPupil()){
            Pupil p = (Pupil) person.getSocialState();
            if(time.equals(p.getStartTime())){
                goWork();
            }
            else if(time.equals(p.getEndTime())){
                goHome();
            }
        }
    }

    private void refreshLocation(SocialState ss) {

    }

    private void goHome() {
    }

    private void goWork() {
        person.setLocation(person.getSocialState().getInfrastructure().getRandomBlock());
    }

    private Action getPreferredAction(){
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Neuroticism){

        }
        return null;
    }
}
