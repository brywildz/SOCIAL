package engine.process.manager.utils;

import engine.data.event.Event;
import engine.data.event.Hobby;
import engine.data.event.PersonalEvent;
import engine.data.event.SocialEvent;
import engine.data.map.*;
import engine.data.person.Person;
import engine.data.person.personalityTraits.*;
import engine.process.builder.HobbyBuilder;
import engine.process.repository.HobbyRepository;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static engine.process.builder.GameBuilder.random;

public class LifeUtilities {
    private static PersonRepository personRepo = PersonRepository.getInstance();
    private static HobbyRepository actionRepo = HobbyRepository.getInstance();
    private static InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    private static Map map = Map.getInstance();

    /**
     * Attribut à un individu l'état de sommeil en appelant le HobbyBuilder correspondant.
     * Reinitialise la valeur de IsDayOff car il n'y a qu'un jour de congé après être sortis de maladie.
     * Même chose pour l'événement, parce que son impact ne va pas au-delà d'une journée.
     * @param person La personne qui doit dormir
     * @param weekEnd Booléen indiquant si nous sommes le week-end, car les heures de sommeils sont différentes.
     */
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
        person.getSocialState().setIsTodayOff(false);
        person.setEvent(null);
        personRepo.movePerson(person,person.getHouse().getRandomBlock());
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
            List<Hobby> l = new ArrayList<>(HobbyRepository.getInstance().getInsideActions().values());
            a = l.get(random(l.size()));

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
        person.setHobby(a);
    }

    public static void goHome(Person person){
        HobbyBuilder hb = new HobbyBuilder(person);
        if(person.isSick()){
            Hobby h = hb.buildHobby();
            person.setHobby(h);
        }
        else{
            Hobby h = hb.buildAfterWorkHobby();
            person.setHobby(h);
        }
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

    /**
     * Déplace la personne à l'hôpital en cas de grave état de santé.
     * Si l'hôpital est saturé la personne est renvoyé chez elle.
     * @param person la personne qui doit être prise en charge.
     */
    public static void goHospital(Person person) {
        Block loc = infraRepo.get("hospital").getEmptyBlock();
        person.getSocialState().setIsTodayOff(true);
        if(loc == null){
            personRepo.movePerson(person, person.getHouse().getRandomBlock());
        }
        else{
            personRepo.movePerson(person, infraRepo.get("hospital").getRandomBlock());
        }
    }

    /**
     * Doit être refait avec les event.
     * @param person
     */
    public synchronized static void refreshLocation(Person person) {
        if(person.isInSocialEvent()) {
            refreshEventLocation(person);
        }
        else if(!person.isSleeping() && !person.isInPersonalEvent() && person.getHobby()!=null){
            personRepo.movePerson(person,person.getHobby().getPlace().getRandomBlock());
        }
    }

    private static void refreshEventLocation(Person person) {
        SocialEvent event = (SocialEvent) person.getEvent();
        if(event.getId().equals("party")){
            personRepo.movePerson(person,infraRepo.get("night_club").getRandomBlock());
        }
        else if(event.getId().equals("walk")){
            Person leader = event.getLeader();
            if(leader.getName().equals(person.getName())){
                personRepo.movePerson(person,infraRepo.get("forest").getRandomBlock());
                Block b = leader.getLocation();
                Iterator<Person> it = event.getPersons().iterator();
                int line = 0;
                int column = 0;
                Block neighbour;
                while(it.hasNext()){
                    Person p = it.next();
                    if(line < 3){
                        neighbour  = new Block(b.getLine() + line, b.getColumn());
                        line++;
                    }
                    else{
                        neighbour  = new Block(b.getLine() + line--, b.getColumn() + column);
                        column++;
                    }
                    personRepo.movePerson(p,neighbour, infraRepo.get("forest"));
                }
            }
        }
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

    public static boolean eventCheck(Person person){
        return person.getEvent() == null;
    }


}
