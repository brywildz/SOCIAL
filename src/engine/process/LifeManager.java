package engine.process;

import engine.data.event.Action;
import engine.data.event.ActionRepository;
import engine.data.map.Clock;
import engine.data.person.Person;
import engine.data.person.personalityTraits.Conscientiousness;
import engine.data.person.personalityTraits.Neuroticism;
import engine.data.person.personalityTraits.Openness;
import engine.data.person.personalityTraits.PersonalityTrait;

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
}
