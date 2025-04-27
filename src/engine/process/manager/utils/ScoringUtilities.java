package engine.process.manager.utils;

import engine.data.event.WeatherEvent;
import engine.data.map.Map;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;

import static engine.process.builder.GameBuilder.random;

public class ScoringUtilities {
    private static Map map = Map.getInstance();

    public static Time createWakeTimeWeekEnd(Personality per) {
        PersonalityTrait maxPerso = per.getMaxPerso();
        if(maxPerso instanceof Conscientiousness){
            return new Time(6,0,0);
        }
        else if(maxPerso instanceof Extraversion){
            return new Time(random(8,12), 0,0);
        }
        else if(maxPerso instanceof Neuroticism){
            return new Time(5,0,0);
        }
        else if(maxPerso instanceof Agreeableness){
            return new Time(8,0,0);
        }
        return new Time(10,0,0);
    }

    public static Time createSleepTimeWeekEnd(Personality per) {
        PersonalityTrait maxPerso = per.getMaxPerso();
        if(maxPerso instanceof Conscientiousness){
            return new Time(22,0,0);
        }
        else if(maxPerso instanceof Extraversion){
            return new Time(4, 0,0);
        }
        else if(maxPerso instanceof  Neuroticism){
            return new Time(4,0,0);
        }
        else if(maxPerso instanceof Agreeableness){
            return new Time(23,0,0);
        }
        return new Time(2,0,0);
    }

    /**
     * verifie si l'action va se faire dehors ou en interieur selon la meteo
     */
    public static boolean weatherCheck(Person person) {
        WeatherEvent weather = map.getWeather();
        if(weather.getId().equals("rain")){
            return rainCheck(person);
        }
        if(weather.getId().equals("snow")){
            return snowCheck(person);
        }
        else{
            return sunCheck(person);
        }
    }

    private static boolean sunCheck(Person person) {
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            return true;
        }
        else if(maxPerso instanceof Openness){
            return true;
        }
        else if(maxPerso instanceof Agreeableness){
            return true;
        }
        else if(maxPerso instanceof Conscientiousness){
            return random(2) == 0;
        }
        else{ //nervosité
            return random(4) == 0;
        }
    }

    private static boolean rainCheck(Person person) {
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            return true;
        }
        else if(maxPerso instanceof Openness){
            return true;
        }
        else if(maxPerso instanceof Agreeableness){
            return random(2) == 0;
        }
        else if(maxPerso instanceof Conscientiousness){
            return false;
        }
        else{ //nervosité
            return random(4) == 0;
        }
    }

    private static boolean snowCheck(Person person) {
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            return true;
        }
        else if(maxPerso instanceof Openness){
            return true;
        }
        else if(maxPerso instanceof Agreeableness){
            return random(2) == 0;
        }
        else if(maxPerso instanceof Conscientiousness){
            return false;
        }
        else{ //nervosité
            return false;
        }
    }

    public static double eventScore(String type, Personality perso){
        double agr = perso.getAgreabilite().getLevel();
        double op = perso.getOuverture().getLevel();
        double cons = perso.getConscienciosite().getLevel();
        double ne = perso.getNeuroticisme().getLevel();
        double ex = perso.getExtraversion().getLevel();
        switch (type) {
            case "meet":
                return 0.4 * ex + 0.3 * op + 0.15 * agr + 0.15 * (1 - ne);
            case "success":
                return 0.15 * ex + 0.2 * op + 0.1 * (1 - ne) * 0.45 * cons + 0.1 * agr;
            case "party":
                return 0.45 * ex + 0.25 * op + 0.15 * agr + 0.10 * (1 - ne) + 0.05 * (1 - cons);
            case "walk":
                    return 0.35 * op + 0.15 * (1 - ex) + 0.3 * ne + 0.20 * cons + 0.05 * agr;
            case "family_dinner":
                return 0.1*op+0.05*ex+0.1*(1-ne)+0.25*cons+0.5*agr;
            default :
                return 0.1 * op + 0.25 * ex + 0.1 * (1 - ne) + 0.35 * cons + 0.2 * agr;
        }
    }

    public static boolean isCompatible(Person person, Person possibleFriend, String type) {
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        PersonalityTrait maxPerso2 = possibleFriend.getPersonality().getMaxPerso();
        PersonalityTrait minPerso = person.getPersonality().getMinPerso();
        PersonalityTrait minPerso2 = possibleFriend.getPersonality().getMinPerso();
        int max = Math.max(person.getAge(), possibleFriend.getAge());
        int min = Math.min(person.getAge(), possibleFriend.getAge());
        boolean compatible = false;
        if(maxPerso instanceof Extraversion){
            if(maxPerso2 instanceof Extraversion || maxPerso2 instanceof Agreeableness || maxPerso2 instanceof Openness){
                compatible =  random(100) > 20;
            }
            if(!compatible && max-min <= 20){
                compatible = random(3) == 1;
            }
            if(!compatible && type.equals("friends") && person.getSocialState().getInfrastructure().getPersons().contains(possibleFriend)){
                compatible = random(5) == 1;
            }
        }
        else if(maxPerso instanceof Agreeableness){
            if(maxPerso2 instanceof Extraversion || maxPerso2 instanceof Conscientiousness || maxPerso2 instanceof Agreeableness){
                compatible =  random(100) > 20;
            }
            if(!compatible && max-min <= 10){
                compatible = random(3) == 1;
            }
            if(!compatible && type.equals("friends") && person.getSocialState().getInfrastructure().getPersons().contains(possibleFriend)){
                compatible = random(5) == 1;
            }
        }
        else if(maxPerso instanceof Openness){
            if(maxPerso2 instanceof Extraversion || maxPerso2 instanceof Openness || maxPerso2 instanceof Agreeableness){
                compatible = random(100) > 20;
            }
            if(!compatible && max-min <= 50){
                compatible = random(3) == 1;
            }
            if(!compatible && type.equals("friends") && person.getSocialState().getInfrastructure().getPersons().contains(possibleFriend)){
                compatible = random(5) == 1;
            }
        }
        else if(maxPerso instanceof Conscientiousness){
            if(maxPerso2 instanceof Agreeableness || maxPerso2 instanceof Openness || maxPerso2 instanceof Conscientiousness){
                compatible = random(100) > 20;
            }
            if(!compatible && max-min <= 5){
                compatible = random(3) == 1;
            }
            if(!compatible && type.equals("friends") && person.getSocialState().getInfrastructure().getPersons().contains(possibleFriend)){
                compatible = random(100) > 20;
            }
        }
        else if(minPerso instanceof Neuroticism){
            if(maxPerso2 instanceof Agreeableness || maxPerso2 instanceof Openness || maxPerso2 instanceof Conscientiousness){
                compatible = random(100) > 20;
            }
            if(!compatible && max-min <= 30){
                compatible = random(3) == 1;
            }
            if(!compatible && type.equals("friends") && person.getSocialState().getInfrastructure().getPersons().contains(possibleFriend)){
                compatible = random(5) == 1;
            }
        }
        return compatible;
    }
}
