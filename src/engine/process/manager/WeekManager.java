package engine.process.manager;

import engine.data.event.Hobby;
import engine.process.builder.HobbyBuilder;
import engine.process.repository.HobbyRepository;
import engine.data.map.*;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;
import engine.data.person.PersonState;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.Worker;
import static engine.process.manager.utils.LifeUtilities.*;


/**
 * Classe de traitement gerant les evennements et les actions de Life Style, c'est-à-dire travail hobbies...
 */
public class WeekManager {
    private Person person;
    private PersonRepository personRepo = PersonRepository.getInstance();
    private HobbyRepository actionRepo = HobbyRepository.getInstance();
    private Time time = Clock.getInstance().getTime();

    public WeekManager(Person person) {
        this.person = person;
    }


    public void refreshRoutine(){
        PersonState ps = person.getPersonState();
        if ((time.equals(ps.getSleep().getSleepTime()))) {
            goSleep(person, false);
        }
        else{
            if(!person.isSick()){
                if (person.isWorker()) workerRoutine();
                else if (person.isPupil()) pupilRoutine();
                else if (person.isUnemployed()) lifeIsGood();
            }
            else if(person.getSocialState().getIsTodayOff() && !person.isInSocialEvent()){
                lifeIsGood();
            }
        }
    }

    public void workerRoutine(){
        Worker w = (Worker) person.getSocialState();
        if (time.equals(w.getStartTime())) {
            goWork(person);
        } else if (time.equals(w.getEndTime())) {
            goHome(person);
        } else if (person.getHobby() == null) {
            HobbyBuilder hb = new HobbyBuilder(person);
            Hobby h = hb.buildHobby();
            person.setHobby(h);
        }
    }

    public void pupilRoutine(){
        PersonState ps = person.getPersonState();
        Pupil p = (Pupil) person.getSocialState();
        if (time.equals(p.getStartTime())) {
            goWork(person);
        } else if (time.equals(p.getEndTime())) {
            goHome(person);
        } else if (time.equals(ps.getSleep().getSleepTime())) {
            goSleep(person, false);
        } else if (person.getHobby() == null) {
            HobbyBuilder hb = new HobbyBuilder(person);
            Hobby h = hb.buildHobby();
            person.setHobby(h);
        }
    }

    public void lifeIsGood(){}


}
