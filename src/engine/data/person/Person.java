package engine.data.person;

import engine.data.event.Hobby;
import engine.data.map.Block;
import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.event.Event;
import engine.data.map.Time;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Unemployed;
import engine.data.person.socialState.Worker;
import engine.data.person.personalityTraits.*;
import engine.data.person.vitality.Health;
import engine.process.repository.HobbyRepository;

/**
 * Classe de donnée stockant l'entierté des informations liées à un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Person {
    private String name;
    private int age;
    private SocialState socialState;
    private Personality personality;
    private PersonState personState;
    private PersonRelationships personRelationships;
    private Block location;
    private Event event = null;
    private Hobby hobby = null;
    private Infrastructure house;
    private Infrastructure place;

    public Person(String nom, int age, SocialState socialState, PersonState personState, PersonRelationships personRelationships,
                  Block location, int agr, int cons, int extra, int neuro, int ouvert) {
        this.name = nom;
        this.age = age;
        this.socialState = socialState;
        this.personState = personState;
        this.personRelationships = personRelationships;
        this.location = location;
        personality = new Personality(agr, cons, extra, neuro, ouvert);

    }

    public Person(){}

    // <editor-fold> desc="getter&setter"
    public SocialState getSocialState() {
        return socialState;
    }

    public Infrastructure getHouse() {
        return house;
    }

    public void setSocialState(SocialState socialState) {
        this.socialState = socialState;
    }

    public Block getLocation() {
        return location;
    }

    public void setLocation(Block location) {
        this.location = location;
    }

    public PersonRelationships getRelation() {
        return personRelationships;
    }

    public void setRelation(PersonRelationships personRelationships) {
        this.personRelationships = personRelationships;
    }

    public PersonState getPersonState() {
        return personState;
    }

    public void setPersonState(PersonState personState) {
        this.personState = personState;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setEvent(Event currentEvent) {
        this.event = currentEvent;
    }

    public Event getEvent() {
        return event;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public void setHouse(Infrastructure house) {
        this.house = house;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public Infrastructure getPlace() {
        return place;
    }

    public void setPlace(Infrastructure place) {
        this.place = place;
    }

    public void setSick(boolean sick){
        personState.getHealth().setMalade(sick);
    }

    // </editor-fold>

    //<editor-fold> desc="personnalité"

    public Agreeableness getAgreeableness(){
        return this.personality.getAgreabilite();
    }

    public Conscientiousness getConscientiousness(){
        return this.personality.getConscienciosite();
    }

    public Extraversion getExtraversion(){
        return this.personality.getExtraversion();
    }

    public Neuroticism getNeuroticism(){
        return this.personality.getNeuroticisme();
    }

    public Openness getOpenness(){
        return this.personality.getOuverture();
    }

    //</editor-fold>

    // <editor-fold> desc="questions"
    public boolean isAtHome() {
        return house.contains(location);
    }

    public boolean isWorker(){
        return socialState instanceof Worker;
    }

    public boolean isWorking(){
        if(isWorker()){
            Time t = Clock.getInstance().getTime();
            Worker w = (Worker) socialState;
            Time start = w.getStartTime();
            Time end = w.getEndTime();
            return t.isDuring(start, end);
        }
        else if (isPupil()) {
            Time t = Clock.getInstance().getTime();
            Pupil w = (Pupil) socialState;
            Time start = w.getStartTime();
            Time end = w.getEndTime();
            return t.isDuring(start, end);
        } else{
            return false;
        }
    }

    public boolean isPreferred(){
        PersonalityTrait maxPerso = personality.getMaxPerso();
        HobbyRepository hobbyRepo = HobbyRepository.getInstance();
        return hobbyRepo.getPreferredHobby(maxPerso).getId().equals(hobby.getId());
    }

    public boolean isPupil(){
        return socialState instanceof Pupil;
    }

    public boolean isUnemployed(){
        return socialState instanceof Unemployed;
    }

    public boolean isSleeping() {
        return personState.getSleep().isSleeping();
    }

    public int getLevelSickness() {
        Health health = personState.getHealth();
        if(health.getNiveau() <= 3 && health.getNiveau() > 1){
            return 1;
        }
        if(health.getNiveau() < 1){
            return 2;
        }
        else {
            return 0;
        }
    }

    public boolean isSick() {
        Health health = personState.getHealth();
        return health.isMalade();
    }
    // </editor-fold>

    public String toStringForPane() {
        String s = "Nom : " + name + ", age : " + age + ", Statut social : "+socialState + ", Occupation : "+ hobby.getId();
        return s;
    }
    @Override
    public String toString() {
        String s = "Nom : " + name + ", age : " + age + ", Statut social : "+socialState;
        if(hobby != null){
            s+="\nHobby : "+ hobby.getId();
        }
        if(event != null){
            s+="\nEvent : " + event.getId();
        }
        s+="\n"+ personality;
        s+="\n" + personState;
        return s;
    }
}
