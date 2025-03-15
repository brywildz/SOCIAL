package engine.process;

import config.GameConfiguration;
import engine.data.event.Action;
import engine.data.event.ActionRepository;
import engine.data.map.Block;
import engine.data.map.Clock;
import engine.data.map.InfrastructureRepository;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.personalityTraits.*;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe de traitement gerant les evennements et les actions de Life Style, c'est-à-dire travail hobbies...
 */
public class LifeManager {
    private Person person;

    public LifeManager(Person person) {
        this.person = person;
    }

    /**
     * Methode pour attribuer un nouveau hobbie à une personne en fonction de sa personnalité
     */
    public void setNewActionInside() {
        Action a = new Action(null, false,null, null);
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Neuroticism){
            a.setId("sport intense");
        }
        if(maxPerso instanceof Openness){
            a.setId("arts créatifs");
        }
        if(maxPerso instanceof Conscientiousness){
            a.setId("apprentissage");
        }
        else{
            Random rand = new Random();
            List<Action> l = new ArrayList<>(ActionRepository.getInstance().getInsideActions().values());
            a = l.get(rand.nextInt(l.size()));

        }
        a.setStart(Clock.getInstance().getHoraire());
        a.setEnd(ActionRepository.getInstance().getRandomTimer(person, a.getId()));
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

    /**
     * Methode pour attribuer un nouveau hobbie à une personne en fonction de sa personnalité mais en exterieur
     */
    public void setNewActionOutside() {
        Action a = new Action(null,true, null, null);
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        if(maxPerso instanceof Extraversion){
            a.setId("sport d'équipe");
        }
        if(maxPerso instanceof Agreeableness){
            a.setId("activité bénévoles");
        }
        else{
            Random rand = new Random();
            List<Action> l = new ArrayList<>(ActionRepository.getInstance().getOutsideActions().values());
            a = l.get(rand.nextInt(l.size()));

        }
        a.setStart(Clock.getInstance().getHoraire());
        a.setEnd(ActionRepository.getInstance().getRandomTimer(person, a.getId()));
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

    public void refreshRoutine() {
        Time time = Clock.getInstance().getHoraire();
        SocialState ss = person.getSocialState();
        if(person.isWorker()){
            Worker w = (Worker) person.getSocialState();
            if(time.equals(w.getStartTime())){
                goWork();
            }
            else if(time.equals(w.getEndTime())){
                goHome();
            }
            else{
                refreshLocation(ss);
            }
        }
        if(person.isPupil()){
            Pupil p = (Pupil) person.getSocialState();
            if(time.equals(p.getStartTime())){
                goWork();
            }
            else if(time.equals(p.getEndTime())){
                goHome("devoirs");
            }
        }
        if(person.isUnemployed()){
            lifeIsGood();
        }
    }

    private void lifeIsGood() {
        ActionRepository ar = ActionRepository.getInstance();
        Random random = new Random();
        PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
        PersonalityTrait minPerso = person.getPersonality().getMinPerso();
        int r = random.nextInt(3)+1;
        if(r<3){
            Action a = ar.getPreferredAction(person.getPersonality().getMaxPerso());
            if(maxPerso instanceof Neuroticism || minPerso instanceof Extraversion){
                goInside(a,false);
            }
            else{
                goOutside(a, false);
            }
        }
        else{
            if(maxPerso instanceof Neuroticism || minPerso instanceof Extraversion){
                Action a = ar.getRandomInsideAction();
                goInside(a,true);
            }
            else{
                Action a = ar.getRandomOutsideAction();
                goOutside(a, true);
            }
        }
    }

    private void goInside(Action a, boolean randomTime) {
        String id = a.getId();
        ActionRepository ar = ActionRepository.getInstance();
        if(id.equals("activité bénévole")){
            Random rand = new Random();
            int randomLine = rand.nextInt(GameConfiguration.LINE_COUNT);
            int randomCol = rand.nextInt(GameConfiguration.COLUMN_COUNT);
            person.setLocation(new Block(randomLine, randomCol));
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("apprentissage")){

            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("activité culturelle")){

            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("arts créatifs")){

            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("jeux d'équipe")){

            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("sport intense")){

            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        person.setLocation(person.getHouse().getRandomBlock());
    }

    private void goOutside(Action a, boolean randomTime) {
        String id = a.getId();
        ActionRepository ar = ActionRepository.getInstance();
        if(id.equals("activité bénévole")){
            Random rand = new Random();
            int randomLine = rand.nextInt(GameConfiguration.LINE_COUNT);
            int randomCol = rand.nextInt(GameConfiguration.COLUMN_COUNT);
            person.setLocation(new Block(randomLine, randomCol));
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("apprentissage")){
            person.setLocation(InfrastructureRepository.getInstance().get("bibliothèque").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("activité culturelle")){
            person.setLocation(InfrastructureRepository.getInstance().get("musée").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("arts créatifs")){
            person.setLocation(InfrastructureRepository.getInstance().get("Cinéma").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("jeux d'équipe")){
            person.setLocation(InfrastructureRepository.getInstance().get("Stade").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("sport intense")){
            person.setLocation(InfrastructureRepository.getInstance().get("Salle").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
    }

    private void setActionTime(Action a, boolean randomTime) {
        ActionRepository ar = ActionRepository.getInstance();
        a.setStart(Clock.getInstance().getActualTime());
        Time end;
        if(randomTime){
            end = ar.getRandomTimer(person, a.getId());
        }
        else{
            end = Clock.getInstance().getActualTime();
            end.addHour(ar.getMaxTime(a.getId()));
        }
        a.setEnd(end);
    }

    private void refreshLocation(SocialState ss) {
        person.setLocation(ss.getInfrastructure().getRandomBlock());
    }

    private void goHome() {
        person.setLocation(person.getHouse().getRandomBlock());
        setNewActionInside();
    }

    private void goHome(String id){
        person.setLocation(person.getHouse().getRandomBlock());
        Time start = Clock.getInstance().getActualTime();
        Time end = ActionRepository.getInstance().getRandomTimer(person, id);
        Action a = new Action(id, true,start, end);
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

    private void goWork() {
        person.setLocation(person.getSocialState().getInfrastructure().getRandomBlock());
        Worker ss = (Worker) person.getSocialState();
        Time start = ss.getStartTime();
        Time end = ss.getEndTime();
        Action a = new Action("travail", true,start, end);
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

}
