package engine.process.manager;

import engine.data.event.Hobby;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.process.builder.HobbyBuilder;
import engine.process.repository.HobbyRepository;
import engine.process.repository.PersonRepository;
import static engine.process.manager.utils.LifeUtilities.*;

public class WeekEndManager {

    private Person person;
    private PersonRepository personRepo = PersonRepository.getInstance();
    private HobbyRepository actionRepo = HobbyRepository.getInstance();
    private Time time = Clock.getInstance().getTime();

    public WeekEndManager(Person person) {
        this.person = person;
    }


    public void lifeIsGood() {
        Personality personality = person.getPersonality();
        if(time.equals(personality.getWakeUpTimeWeekEnd())){
            person.getPersonState().getSleep().setSleeping(false);
            person.setHobby(null);
        }
        else if(time.equals(personality.getSleepTimeWeekEnd())){
            goSleep(person, true);
        }
        if(person.getHobby()==null){
            goHaveAGoodDay();
        }
        else if(person.getHobby().isFinished()){
            person.setHobby(null);
            goHaveAGoodDay();
        }
    }

    private void goHaveAGoodDay(){
        HobbyBuilder hobbyBuilder = new HobbyBuilder(person);
        Hobby hobby = hobbyBuilder.buildHobby();
        person.setHobby(hobby);
    }
}
