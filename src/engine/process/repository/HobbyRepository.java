package engine.process.repository;

import engine.data.event.Hobby;
import engine.data.map.Time;
import engine.data.person.personalityTraits.*;
import java.util.ArrayList;
import java.util.HashMap;
import static engine.process.GameBuilder.random;

public class HobbyRepository {
    private final HashMap<String, Hobby> insideActions = new HashMap<>();
    private final HashMap<String, Hobby> outsideActions = new HashMap<>();
    public static HobbyRepository instance = new HobbyRepository();

    /**
     * Mettre toutes les actions dans une seule hashmap la localité sera geré par le builder
     * et délocaliser les méthodes de traitements si c'est pertinent.
     */
    private HobbyRepository() {
        insideActions.put("sport", new Hobby("sport", false, null, null));
        insideActions.put("art", new Hobby("art", false, null, null));
        insideActions.put("learning", new Hobby("learning", false, null, null));
        insideActions.put("sleep", new Hobby("sleep", false, null, null));

        outsideActions.put("team_game", new Hobby("team_game", true, null, null));
        outsideActions.put("work", new Hobby("work", true, null, null));
        outsideActions.put("volunteering", new Hobby("volunteering", true, null, null));
        outsideActions.put("cultural_activity", new Hobby("cultural_activity", true, null, null));
    }

    public HashMap<String, Hobby> getInsideActions() {
        return insideActions;
    }

    public HashMap<String, Hobby> getOutsideActions() {
        return outsideActions;
    }

    public Hobby getAction(String actionName) {
        if (insideActions.containsKey(actionName)) {
            return new Hobby(insideActions.get(actionName));
        }
        if (outsideActions.containsKey(actionName)) {
            return new Hobby(outsideActions.get(actionName));
        }
        return null;
    }

    public static HobbyRepository getInstance() {
        return instance;
    }

    /**
     * Methode utilisé dans le cas où une personne n'a pas d'action, une nouvelle doit lui être assigné
     *
     * @param id nom de l'action
     * @return l'heure où l'action sera terminé.
     */
    public Time getRandomEnd(String id, Time start) { //il manque l'adaptabilité
        Time end = new Time(start.getHour(), start.getMinute(), start.getSecond());
        switch (id) {
            case "sport" -> {
                int randomInt = random(1, 60);
                end.addMinute(randomInt);
                return end;
            }
            case "art" -> {
                int randomInt = random(1, 120);
                end.addMinute(randomInt);
                return end;
            }
            case "learning" -> {
                int randomInt = random(1, 240);
                end.addMinute(randomInt);
                return end;
            }
            case "sleep" -> {
                int randomInt = random(1, 10);
                end.addHour(randomInt);
                return end;
            }
            case "team_game" -> {
                int randomInt = random(1, 4);
                end.addHour(randomInt);
                return end;
            }
            case "work", "volunteering" -> {
                int randomInt = random(1, 7);
                end.addHour(randomInt);
                return end;
            }
            case "devoirs" -> {
                int randomInt = random(1, 80);
                end.addMinute(randomInt);
                return end;
            }
            default -> { //cultural_activityculturelle
                int randomInt = random(1, 4);
                end.addHour(randomInt);
                return end;
            }
        }
    }

    public Hobby getPreferredAction(PersonalityTrait pt) {
        if (pt instanceof Agreeableness) {
            return getAction("volunteering");
        }
        if (pt instanceof Conscientiousness) {
            return getAction("learning");
        }
        if (pt instanceof Extraversion) {
            return getAction("team_game");
        }
        if (pt instanceof Neuroticism) {
            return getAction("sport");
        }
        return getAction("art"); //ouverture
    }

    public Hobby getRandomInsideAction() {
        ArrayList<Hobby> hobbies = new ArrayList<>(insideActions.values());
        return hobbies.get(random(hobbies.size()));
    }

    public Hobby getRandomOutsideAction() {
        ArrayList<Hobby> hobbies = new ArrayList<>(outsideActions.values());
        return hobbies.get(random(hobbies.size()));
    }

    public int getMaxTime(String id) {
        return switch (id) {
            case "sport" -> 60;
            case "art" -> 120;
            case "learning" -> 240;
            case "sleep" -> 600;
            case "team_game" -> 240;
            case "work", "volunteering" -> 420;
            case "devoirs" -> 180;
            default ->  //cultural_activityculturelle
                    240;
        };
    }

    public Hobby getRandomAction(){
        Hobby hobby = new Hobby("sleep", false,null, null);
        Hobby hobby1;
        Hobby hobby2;
        while(hobby.getId().equals("work") || hobby.getId().equals("sleep")){
            hobby1 = getRandomInsideAction();
            hobby2 = getRandomOutsideAction();
            if(random(2) == 0){
                hobby.setId(hobby1.getId());
            }
            else{
                hobby.setId(hobby2.getId());
            }
        }
        return hobby;
    }
}

