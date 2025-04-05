package engine.process;

import config.GameConfiguration;
import engine.data.event.Action;
import engine.process.repository.ActionRepository;
import engine.data.map.*;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;
import engine.data.person.PersonState;
import engine.data.person.personalityTraits.*;
import engine.data.person.socialState.Pupil;
import engine.data.person.socialState.SocialState;
import engine.data.person.socialState.Worker;
import engine.process.repository.InfrastructureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe de traitement gerant les evennements et les actions de Life Style, c'est-à-dire travail hobbies...
 */
public class LifeManager {
    private Person person;
    private PersonRepository personRepo = PersonRepository.getInstance();

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
        PersonState ps = person.getPersonState();
        if (!Clock.isWeekend()) {
            if(person.isWorker()){
                Worker w = (Worker) person.getSocialState();
                if(time.equals(w.getStartTime())) {
                    goWork();
                }
                else if(time.equals(w.getEndTime())){
                    goHome();
                }
                else if(time.equals(ps.getSleep().getSleepTime())){
                    goSleep();
                }
                else if(person.getCurrentAction() == null){
                    setNewAction();
                }
                else{
                    refreshLocation();
                }
            }
            else if(person.isPupil()){
                Pupil p = (Pupil) person.getSocialState();
                if(time.equals(p.getStartTime())){
                    goWork();
                }
                else if(time.equals(p.getEndTime())){
                    goHome("devoirs");
                }
                else if(time.equals(ps.getSleep().getSleepTime())){
                    goSleep();
                }
                else if(person.getCurrentAction() == null){
                    setNewAction();
                }
                else{
                    refreshLocation();
                }
            }
            else if(person.isUnemployed()){
                lifeIsGood();
            }
        }
        else{
            if (person.getCurrentAction() == null) {
                lifeIsGood();
            }
            else{
                refreshLocation();
            }
        }
    }

    private void setNewAction() {
        if(person.isInHisHouse()){
            setNewActionInside();
        }
        else{
            setNewActionOutside();
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
            personRepo.setNewLocation(person ,new Block(randomLine, randomCol));
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
        personRepo.setNewLocation(person, person.getHouse().getRandomBlock());
    }

    private void goOutside(Action a, boolean randomTime) {
        String id = a.getId();
        ActionRepository ar = ActionRepository.getInstance();
        if(id.equals("activité bénévole")){
            Random rand = new Random();
            int randomLine = rand.nextInt(GameConfiguration.LINE_COUNT);
            int randomCol = rand.nextInt(GameConfiguration.COLUMN_COUNT);
            personRepo.setNewLocation(person,new Block(randomLine, randomCol));
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("apprentissage")){
            personRepo.setNewLocation(person, InfrastructureRepository.getInstance().get("bibliothèque").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("activité culturelle")){
            personRepo.setNewLocation(person,InfrastructureRepository.getInstance().get("musée").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("arts créatifs")){
            personRepo.setNewLocation(person,InfrastructureRepository.getInstance().get("Cinéma").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("jeux d'équipe")){
            personRepo.setNewLocation(person,InfrastructureRepository.getInstance().get("Stade").getRandomBlock());
            setActionTime(a, randomTime);
            person.setCurrentAction(a);
        }
        if(id.equals("sport intense")){
            personRepo.setNewLocation(person,InfrastructureRepository.getInstance().get("Salle").getRandomBlock());
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

    private void refreshLocation() {
        if(person.isSleeping()){
            return;
        }
        Random rand = new Random();
        int randomNumber = rand.nextInt(5);
        if(randomNumber == 4){
            InfrastructureRepository iRepo = InfrastructureRepository.getInstance();
            Infrastructure actualPlace = iRepo.get(person.getLocation());
            personRepo.setNewLocation(person,actualPlace.getRandomBlock());
        }
    }

    private void goSleep() {
        personRepo.setNewLocation(person,person.getHouse().getRandomBlock());
        PersonState ps = person.getPersonState();
        Time start = ps.getSleep().getSleepTime();
        Time end = ps.getSleep().getWakeUpTime();
        Action a = new Action("dormir",false,start,end);
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

    private void goHome() {
        personRepo.setNewLocation(person,person.getHouse().getRandomBlock());
        setNewActionInside();
    }

    private void goHome(String id){
        personRepo.setNewLocation(person,person.getHouse().getRandomBlock());
        Time start = Clock.getInstance().getActualTime();
        Time end = ActionRepository.getInstance().getRandomTimer(person, id);
        Action a = new Action(id, false,start, end);
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

    private void goWork() {
        person.getPersonState().getSleep().setSleeping(false);
        personRepo.setNewLocation(person,person.getSocialState().getInfrastructure().getRandomBlock());
        Action a;
        if(person.isWorker()){
            Worker ss = (Worker) person.getSocialState();
            Time start = ss.getStartTime();
            Time end = ss.getEndTime();
            a = new Action("travail", true,start, end);
        }
        else{
            Pupil ss = (Pupil) person.getSocialState();
            Time start = ss.getStartTime();
            Time end = ss.getEndTime();
            a = new Action("travail", true,start, end);
        }
        person.setCurrentAction(a);
        Reaction react = new Reaction(person);
        react.changeState(a);
    }

}
