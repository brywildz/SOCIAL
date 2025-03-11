package engine.data.event;

import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.personalityTraits.Extraversion;
import engine.data.person.personalityTraits.PersonalityTrait;

import java.util.HashMap;
import java.util.Random;

public class ActionRepository {
    private HashMap<String, Action> insideActions = new HashMap<>();
    private HashMap<String, Action> outsideActions = new HashMap<>();
    private static ActionRepository instance;
    private ActionRepository() {
        insideActions.put("méditation", new Action("meditation", null, null));
        insideActions.put("arts créatifs", new Action("arts créatifs", null, null));
        insideActions.put("apprentissage", new Action("apprentissage", null, null));
        insideActions.put("dormir", new Action("dormir", null, null));

        outsideActions.put("sport d'équipe", new Action("sport d'équipe", null, null));
        outsideActions.put("travail", new Action("travail", null, null));
        outsideActions.put("activité bénévole", new Action("activité bénévole", null, null));
        outsideActions.put("activité culturelle", new Action("activité culturelle", null, null));
    }

    public HashMap<String, Action> getInsideActions() {
        return insideActions;
    }

    public HashMap<String, Action> getOutsideActions() {
        return outsideActions;
    }

    public static ActionRepository getInstance() {
        return instance;
    }

    /**
     * Methode utilisé dans le cas où une personne n'a pas d'action, une nouvelle doit lui être assigné
     * @param id nom de l'action
     * @return l'heure où l'action sera terminé.
     */
    public Time getRandomTimer(Person p, String id){
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();
        Clock clock = Clock.getInstance();
        Random rand = new Random();
        Time t = new Time(clock.getHoraire().getHour(), clock.getHoraire().getMinute(), clock.getHoraire().getSecond());
        if(id.equals("meditation")){
            int randomInt = rand.nextInt(60);
            t.addMinute(randomInt);
            return t;
        }
        else if(id.equals("arts créatifs")){
            int randomInt = rand.nextInt(120);
            t.addMinute(randomInt);
            return t;
        }
        else if(id.equals("apprentissage")){
            int randomInt = rand.nextInt(240);
            t.addMinute(randomInt);
            return t;
        }
        else if(id.equals("dormir")){
            int randomInt = rand.nextInt(10);
            t.addHour(randomInt);
            return t;
        }
        else if(id.equals("sport d'équipe")){
            int randomInt = rand.nextInt(4);
            t.addHour(randomInt);
            return t;
        }
        else if(id.equals("travail")){
            int randomInt = rand.nextInt(7);
            t.addHour(randomInt);
            return t;
        }
        else if (id.equals("activité bénévole")) {
            int randomInt = rand.nextInt(7);
            t.addHour(randomInt);
            return t;
        }
        else{ //activité culturelle
            int randomInt = rand.nextInt(4);
            t.addHour(randomInt);
            return t;
        }
    }

}
