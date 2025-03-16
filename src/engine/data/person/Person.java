package engine.data.person;

import engine.data.event.Action;
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

/**
 * Classe de donnée stockant l'entierté des informations liée à un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Person {
    private final String nom;
    private final int age;
    private SocialState socialState;
    private final Personality personality;
    private PersonState personState;
    private PersonRelationships personRelationships;
    private Block location;
    private Event currentEvent = null;
    private Action currentAction = null;
    private Infrastructure house;

    public Person(String nom, int age, SocialState socialState, PersonState personState, PersonRelationships personRelationships,
                  Block location, int agr, int cons, int extra, int neuro, int ouvert) {
        this.nom = nom;
        this.age = age;
        this.socialState = socialState;
        this.personState = personState;
        this.personRelationships = personRelationships;
        this.location = location;
        personality = new Personality(agr, cons, extra, neuro, ouvert);

    }

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

    public String getNom() {
        return nom;
    }

    public void setEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public Event getEvent() {
        return currentEvent;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
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

    @Override
    public String toString() {
        return "Individu{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", socialState='" + socialState + '\'' +
                ", personality=" + personality +
                ", etat=" + personState +
                ", relation=" + personRelationships +
                ", location=" + location +
                ", currentEvent=" + currentEvent +
                '}';
    }

    public boolean isInHisHouse() {
        return true;
    }

    public boolean isWorker(){
        return socialState instanceof Worker;
    }

    public boolean isWorking(){
        if(isWorker() || isPupil()){
            Time t = Clock.getInstance().getHoraire();
            Worker w = (Worker) socialState;
            Time start = w.getStartTime();
            Time end = w.getEndTime();
            return t.isDuring(start, end);
        }
        else{
            return false;
        }
    }

    public boolean isPupil(){
        return socialState instanceof Pupil;
    }

    public boolean isUnemployed(){
        return socialState instanceof Unemployed;
    }

    public boolean isSleeping() {
        return true;
    }
}
