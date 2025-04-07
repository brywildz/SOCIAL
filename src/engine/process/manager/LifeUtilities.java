package engine.process.manager;

import engine.data.event.Hobby;
import engine.data.event.WeatherEvent;
import engine.data.map.*;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;
import engine.process.HobbyBuilder;
import engine.process.repository.HobbyRepository;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static engine.process.GameBuilder.random;

public class LifeUtilities {
    private static PersonRepository personRepo = PersonRepository.getInstance();
    private static HobbyRepository actionRepo = HobbyRepository.getInstance();
    private static InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    private static Map map = Map.getInstance();


    public static void goSleep(Person person, boolean weekEnd) {
        Hobby h;
        if(weekEnd){
            HobbyBuilder hb = new HobbyBuilder(person);
            h = hb.buildSleepHobbyWeekEnd();
        }
        else{
            HobbyBuilder hb = new HobbyBuilder(person);
            h = hb.buildSleepHobby();
        }
        person.setHobby(h);
        person.getPersonState().getSleep().setSleeping(true);
        personRepo.setNewLocation(person,person.getHobby().getPlace().getRandomBlock());
    }

    /**
     * Methode pour attribuer un nouveau hobbie à une personne en fonction de sa personnalité
     */
    public static void setNewActionInside(Person person) {
        Hobby a = new Hobby(null, false,null, null);
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Neuroticism){
            a.setId("sport");
        }
        if(maxPerso instanceof Openness){
            a.setId("art");
        }
        if(maxPerso instanceof Conscientiousness){
            a.setId("learning");
        }
        else{
            Random rand = new Random();
            List<Hobby> l = new ArrayList<>(HobbyRepository.getInstance().getInsideActions().values());
            a = l.get(rand.nextInt(l.size()));

        }
        a.setStart(Clock.getInstance().getTime());
        a.setEnd(HobbyRepository.getInstance().getRandomEnd(a.getId(), a.getStart()));
        person.setHobby(a);
    }

    /**
     * Methode pour attribuer un nouveau hobbie à une personne en fonction de sa personnalité mais en exterieur
     */
    public static void setNewActionOutside(Person person, Hobby hobby) {
        Hobby a;
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            a = actionRepo.getAction("sport");
        }
        else if(maxPerso instanceof Agreeableness){
            a = actionRepo.getAction("volunteering");
        }
        else{
            List<Hobby> l = new ArrayList<>(HobbyRepository.getInstance().getOutsideActions().values());
            a = actionRepo.getAction((l.get(random(l.size()))).getId());

        }
        //a.setStart(Clock.getInstance().getTime());
        //a.setEnd(HobbyRepository.getInstance().getRandomTimer(person, a.getId()));
        person.setHobby(a);
    }

    public static void goHome(Person person){
        HobbyBuilder hb = new HobbyBuilder(person);
        Hobby h = hb.buildAfterWorkHobby();
        person.setHobby(h);
    }

    public static void goWork(Person person) {
        person.getPersonState().getSleep().setSleeping(false);
        Hobby h;
        if(person.isWorker()){
            HobbyBuilder hb = new HobbyBuilder(person);
            h = hb.buildWork();
        }
        else{
            HobbyBuilder hb = new HobbyBuilder(person);
            h = hb.buildStudent();
        }
        person.setHobby(h);
    }

    public static Time createWakeTimeWeekEnd(Personality per) {
        PersonalityTrait maxPerso = per.getMaxPerso();
        if(maxPerso instanceof Conscientiousness){
            return new Time(6,0,0);
        }
        else if(maxPerso instanceof Extraversion){
            return new Time(random(8,12), 0,0);
        }
        else if(maxPerso instanceof  Neuroticism){
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
     * Doit être refait avec les event.
     * @param person
     */
    public static void refreshLocation(Person person) {
        if(person.isSleeping()){
            return;
        }
        personRepo.setNewLocation(person,person.getHobby().getPlace().getRandomBlock());
    }

    /**
     * Décide si le hobby doit être effectué en intérieur ou à l'extérieur.
     * @param person
     * @param hobby
     */
    public static void setNewAction(Person person, Hobby hobby) {
        int randomNumber = random(2);
        if(randomNumber == 4){
            setNewActionOutside(person, hobby);
        }
        else{
            setNewActionInside(person);
        }
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
}
