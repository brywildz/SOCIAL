package engine.process.builder;

import engine.data.event.Event;
import engine.data.event.PersonalEvent;
import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.process.repository.EventRepository;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.PersonRepository;

import java.util.Iterator;

import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.utils.LifeUtilities.goHome;
import static engine.process.manager.utils.LifeUtilities.goHospital;
import static engine.process.manager.utils.ScoringUtilities.eventScore;
import static engine.process.manager.utils.ScoringUtilities.isCompatible;

public class EventBuilder {
    InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    PersonRepository personRepo = PersonRepository.getInstance();
    EventRepository eventRepo = EventRepository.getInstance();
    Person person;
    Event event;

    public EventBuilder(Person person, Event event) {
        this.person = person;
        this.event = event;
    }

    public void build() {
        person.setHobby(null);
        if(event instanceof PersonalEvent){
            buildPersonalEvent();
        }
        else{
            buildSocialEvent();
        }
        person.setEvent(event);
    }

    /**
     * Construit un événement personnel dépendamment du trait majoritaire de l'individu.
     * Ils se distinguent par trois niveaux : Maladie, Rencontre, Succès (ordre de priorité).
     * Ils sont prioritaires aux événements sociaux.
     */
    private void buildPersonalEvent() {
        double score;
        Personality perso = person.getPersonality();
        /*
         * La maladie est l’événement qui est prioritaire (obligation biologique).
         * Viens ensuite la nouvelle rencontre (probabilité naturelle)
         * Enfin vient la réussite qui est la plus rare et qui a le plus gros impact
         */
        if(person.getLevelSickness() == 1){
            int sickProba = random(0,4);
            //L’individu peut ne pas remarquer qu'il tombe malade.
            if(sickProba == 0){
                buildSick();
                return;
            }
        }
        //Il est vraiment très malade, il doit aller à l’hôpital
        else if(person.getLevelSickness() == 2){
            buildVerySick();
            return;
        }
        else{
            //Pas de maladie, on essaye pour la nouvelle rencontre.
            if(!person.isAtHome()){
                int meetProba = random(0,10);
                score = eventScore("meet", perso);
                if(score>=0.7 && meetProba >= 5){
                    buildNewMeet();
                    return;
                }
                else if(score>=0.5 && meetProba >= 6){
                    buildNewMeet();
                    return;
                }
            }
        }
        int successProba = random(0,10);
        //Quelqu'un de très consciencieux aura plus de chance de réussir dans son domaine.
        score = eventScore("success", perso);
        if(score>=0.7 && successProba>=5){
            buildSuccess();
        }
        else if(successProba == 0){
            buildSuccess();
        }
    }

    private void buildSuccess() {
        event = eventRepo.get("success");

    }

    private void buildNewMeet() {
        Infrastructure place = person.getPlace();
        Iterator<Person> it = person.getPlace().getPersons().iterator();
        boolean found = false;
        while(it.hasNext() && !found){
            Person possibleFriend = it.next();
            if(isCompatible(this.person, possibleFriend, "friends")){
                person.getRelation().getAmicale().add(possibleFriend);
                personRepo.movePerson(person, possibleFriend);
                event = eventRepo.get("meet");
                found = true;
            }
        }
    }

    private void buildVerySick() {
        goHospital(person);
        person.setSick(true);
        person.getPersonState().getMood().add(-3);
        event = eventRepo.get("sick");
    }

    private void buildSick() {
        if(!person.isAtHome()){
            goHome(person);
        }
        person.setSick(true);
        person.getPersonState().getMood().add(-2);
        event = eventRepo.get("sick");
    }


    private void buildSocialEvent() {
        double score;
        Personality perso = person.getPersonality();
        Time time = Clock.getInstance().getTime();
        int persoProba = random(0,10);
        int eventProba = random(0,2);
        //Événement social choisit aléatoirement.
        if (eventProba == 0) {
            if(time.getHour() < 5 || time.getHour() > 19){
                score = eventScore("party", perso);
                if(score>=0.7 && persoProba <= 5){
                    buildParty();
                }
                else if((score>=0.5) && persoProba <= 3){
                    buildParty();
                }
            }
            return;
        } else {
            if(!(time.getHour() < 5 || time.getHour() > 19)){
                score = eventScore("walk", perso);
                if(score>=0.7 && persoProba <= 5){
                    buildWalk();
                }
                else if((score>=0.5) && persoProba <= 3){
                    buildWalk();
                }
            }
            return;
        }
        String day = time.getDate().getDayName();
        if(day.equals("Dimanche")){
            if (persoProba <= 5) {
                score = eventScore("family_dinner", perso);
                if(score>=0.7 && persoProba <= 5){
                    buildFamilyDinner();
                }
            }
        }
        else if(!Clock.isWeekend()){
            if(persoProba >= 5){
                score = eventScore("work_dinner", perso);
                if(score>=0.7) {
                    buildWorkDinner();
                }
            }
        }
    }





}
