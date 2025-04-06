package engine.process.repository;

import engine.data.event.Action;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.personalityTraits.*;
import java.util.ArrayList;
import java.util.HashMap;
import static engine.process.GameBuilder.random;

public class ActionRepository {
    private final HashMap<String, Action> insideActions = new HashMap<>();
    private final HashMap<String, Action> outsideActions = new HashMap<>();
    public static ActionRepository instance = new ActionRepository();

    private ActionRepository() {
        insideActions.put("sport intense", new Action("sport intense", false, null, null));
        insideActions.put("arts créatifs", new Action("arts créatifs", false, null, null));
        insideActions.put("apprentissage", new Action("apprentissage", false, null, null));
        insideActions.put("dormir", new Action("dormir", false, null, null));

        outsideActions.put("jeux d'équipes", new Action("jeux d'équipes", true, null, null));
        outsideActions.put("travail", new Action("travail", true, null, null));
        outsideActions.put("activité bénévole", new Action("activité bénévole", true, null, null));
        outsideActions.put("activité culturelle", new Action("activité culturelle", true, null, null));
    }

    public HashMap<String, Action> getInsideActions() {
        return insideActions;
    }

    public HashMap<String, Action> getOutsideActions() {
        return outsideActions;
    }

    public Action getAction(String actionName) {
        if (insideActions.containsKey(actionName)) {
            return new Action(insideActions.get(actionName));
        }
        if (outsideActions.containsKey(actionName)) {
            return new Action(outsideActions.get(actionName));
        }
        return null;
    }

    public static ActionRepository getInstance() {
        return instance;
    }

    /**
     * Methode utilisé dans le cas où une personne n'a pas d'action, une nouvelle doit lui être assigné
     *
     * @param id nom de l'action
     * @return l'heure où l'action sera terminé.
     */
    public Time getRandomTimer(Person p, String id) { //il manque l'adaptabilité
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();
        Clock clock = Clock.getInstance();
        Time t = clock.getActualTime();
        if (id.equals("sport intense")) {
            int randomInt = random(1,60);
            t.addMinute(randomInt);
            return t;
        } else if (id.equals("arts créatifs")) {
            int randomInt = random(1,120);
            t.addMinute(randomInt);
            return t;
        } else if (id.equals("apprentissage")) {
            int randomInt = random(1,240);
            t.addMinute(randomInt);
            return t;
        } else if (id.equals("dormir")) {
            int randomInt = random(1,10);
            t.addHour(randomInt);
            return t;
        } else if (id.equals("jeux d'équipes")) {
            int randomInt = random(1,4);
            t.addHour(randomInt);
            return t;
        } else if (id.equals("travail")) {
            int randomInt = random(1,7);
            t.addHour(randomInt);
            return t;
        } else if (id.equals("activité bénévole")) {
            int randomInt = random(1,7);
            t.addHour(randomInt);
            return t;
        } else if (id.equals("devoirs")) {
            int randomInt = random(1,80);
            t.addMinute(randomInt);
            return t;
        } else { //activité culturelle
            int randomInt = random(1,4);
            t.addHour(randomInt);
            return t;
        }
    }

    public Action getPreferredAction(PersonalityTrait pt) {
        if (pt instanceof Agreeableness) {
            return getAction("activité bénévole");
        }
        if (pt instanceof Conscientiousness) {
            return getAction("apprentissage");
        }
        if (pt instanceof Extraversion) {
            return getAction("jeux d'équipes");
        }
        if (pt instanceof Neuroticism) {
            return getAction("sport intense");
        }
        return getAction("arts créatifs"); //ouverture
    }

    public Action getRandomInsideAction() {
        ArrayList<Action> actions = new ArrayList<>(insideActions.values());
        return actions.get(random(actions.size()));
    }

    public Action getRandomOutsideAction() {
        ArrayList<Action> actions = new ArrayList<>(outsideActions.values());
        return actions.get(random(actions.size()));
    }

    public int getMaxTime(String id) {
        return switch (id) {
            case "sport intense" -> 60;
            case "arts créatifs" -> 120;
            case "apprentissage" -> 240;
            case "dormir" -> 600;
            case "jeux d'équipes" -> 240;
            case "travail", "activité bénévole" -> 420;
            case "devoirs" -> 180;
            default ->  //activité culturelle
                    240;
        };
        }
    }

