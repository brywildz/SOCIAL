package engine.process.builder;

import engine.data.event.Hobby;
import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.personalityTraits.PersonalityTrait;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.Worker;
import engine.process.repository.HobbyRepository;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.PersonRepository;

import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.LifeUtilities.weatherCheck;

public class HobbyBuilder {
    InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    PersonRepository personRepo = PersonRepository.getInstance();
    HobbyRepository actionRepo = HobbyRepository.getInstance();
    Person person;
    Hobby hobby;
    boolean preferred;
    Time time = Clock.getInstance().getTime();

    public HobbyBuilder(Person person){
        this.person = person;
        hobby = new Hobby();
    }

    /**
     * dans les cas géneraux
     * @return
     */
    public Hobby buildHobby(){
        hobby.setOutside(weatherCheck(person));
        hobby.setId(createId());
        hobby.setStart(createStart());
        hobby.setEnd(createEnd());
        hobby.setPlace(createInfrastructure());

        return hobby;
    }

    public Hobby buildWork(){
        hobby.setOutside(false);
        hobby.setId("work");
        hobby.setStart(createStartWork());
        hobby.setEnd(createEndWork());
        hobby.setPlace(createInfrastructureWork());
        return hobby;
    }

    public Hobby buildStudent(){
        hobby.setOutside(false);
        hobby.setId("work");
        hobby.setStart(createStartStudent());
        hobby.setEnd(createEndStudent());
        hobby.setPlace(createInfrastructureStudent());
        return hobby;
    }

    public Hobby buildAfterWorkHobby(){
        hobby.setOutside(false);
        hobby.setId(createAfterWorkId());
        hobby.setStart(createStart());
        hobby.setEnd(createEnd());
        hobby.setPlace(createInfrastructure());
        return hobby;
    }

    public Hobby buildSleepHobby(){
        hobby.setOutside(false);
        hobby.setId("sleep");
        hobby.setStart(createStartSleep(false));
        hobby.setEnd(createEndSleep(false));
        hobby.setPlace(createInfrastructure());
        return hobby;
    }

    public Hobby buildSleepHobbyWeekEnd(){
        hobby.setOutside(false);
        hobby.setId("sleep");
        hobby.setStart(createStartSleep(true));
        hobby.setEnd(createEndSleep(true));
        hobby.setPlace(createInfrastructure());
        return hobby;
    }

    private Time createEndSleep(boolean weekEnd) {
        if(weekEnd){
            return person.getPersonality().getWakeUpTimeWeekEnd();
        }
        return person.getPersonState().getSleep().getWakeUpTime();
    }

    private Time createStartSleep(boolean weekEnd) {
        if(weekEnd){
            return person.getPersonality().getSleepTimeWeekEnd();
        }
        return person.getPersonState().getSleep().getSleepTime();
    }

    private String createAfterWorkId() {
        if(person.isPupil()){
            return "devoirs";
        }
        return createId();
    }

    private Infrastructure createInfrastructureStudent() {
        return person.getSocialState().getInfrastructure();
    }

    private Time createEndStudent() {
        Pupil s = (Pupil) person.getSocialState();
        return s.getEndTime();
    }

    private Time createStartStudent() {
        Pupil s = (Pupil) person.getSocialState();
        return s.getStartTime();
    }

    private Infrastructure createInfrastructureWork() {
        return person.getSocialState().getInfrastructure();
    }

    private Time createEndWork() {
        Worker work = (Worker) person.getSocialState();
        return work.getEndTime();
    }

    private Time createStartWork() {
        Worker work = (Worker) person.getSocialState();
        return work.getStartTime();
    }

    /**
     * dans les cas météorologique/humeur necessesite de rester en interieur
     * verification de l'humeur a faire au préalable
     * @return
     */
    private Hobby buildActionInside(){
        return null;
    }

    /**
     * dans les cas météorologique/humeur encourageant à sortir
     * verification de l'humeur a faire au préalable
     * @return
     */
    private Hobby buildActionOutside(){
        return null;
    }

    private String createId() {
        String id;
        if(random(3)==0){
            id = actionRepo.getRandomAction().getId();
            preferred = false;
        }
        else{
            PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
            id = actionRepo.getPreferredAction(maxPerso).getId();
            preferred = true;
        }
        return id;
    }

    /**
     * Risque d'avoir copié time pas sûr
     * @return
     */
    private Time createStart(){
        return new Time(time.getHour(), time.getMinute(), time.getSecond());
    }

    private Time createEnd(){
        Time end;
        if(preferred){
            end = new Time(time.getHour(), time.getMinute(), time.getSecond());
            end.addMinute(actionRepo.getMaxTime(hobby.getId()));
        }
        else{
            end = actionRepo.getRandomEnd(hobby.getId(), hobby.getStart());
        }
        return end;
    }

    private Infrastructure createInfrastructure() {
        if(!hobby.isOutside()){
            return person.getHouse();
        }
        String id = hobby.getId();
        switch (id) {
            case "volunteering" -> {
                return infraRepo.get("city");
            }
            case "learning" -> {
                return infraRepo.get("bibliothèque");
            }
            case "cultural_activityculturelle" -> {
                return infraRepo.get("museum");
            }
            case "art" -> {
                return infraRepo.get("cinéma");
            }
            case "team_game" -> {
                return infraRepo.get("parc");
            }
            case "sport" -> {
                return infraRepo.get("gym");
            }
            default -> {    //dodo
                return person.getHouse();
            }
        }
    }
}
