package engine.process;

import engine.data.event.*;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;
import engine.data.person.vitality.*;
import engine.process.repository.HobbyRepository;

import static engine.process.manager.utils.LifeUtilities.goHome;

/**
 * Classe de traitement traitant les différents reaction d'un individu dépendamment de son caractère
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Reaction {
    private Person person;
    private PersonState state;
    private Personality personality;

    public Reaction(Person person){
        this.person = person;
        this.state = person.getPersonState();
        this.personality = person.getPersonality();
    }

    public static boolean weatherReact(Person p, WeatherEvent w){
        PersonalityTrait maxPerso = p.getPersonality().getMaxPerso();
        if(w.getId().equals("pluie")){
            if(p.getNeuroticism().isLow()){
                if(p.getOpenness().isHigh()){
                    return true;
                }
            }
        }
        if(w.getId().equals("soleil")){
            if(p.getNeuroticism().isHigh()){
                if(p.getExtraversion().isLow()){
                    if(p.getOpenness().isLow()){
                        if(p.getAgreeableness().isLow()){
                            if(p.getConscientiousness().isLow()){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        if(w.getId().equals("neige")){
            if(p.getNeuroticism().isHigh() || p.getConscientiousness().isLow()){
                return false;
            }
            if(p.getExtraversion().isHigh() || p.getOpenness().isHigh()){
                return true;
            }
            return true;
        }
        if(w.getId().equals("nuageux")){
            if(p.getNeuroticism().isHigh() && p.getExtraversion().isLow()){
                return false;
            }
        }
        return true;
    }

    public static boolean lifeStyleReact(Person p){
        HobbyRepository actionRepo = HobbyRepository.getInstance();
        Hobby prefHobby = actionRepo.getPreferredHobby(p.getPersonality().getMaxPerso());
        if(p.isWorker() || p.isPupil()){
            if(p.isWorking()){
                return false;
            }
        }
        if(p.isSleeping()){
            return false;
        }
        if(p.getHobby().getId().equals(prefHobby.getId())){
            return false;
        }
        return true;
    }

    public void changeState(Hobby hobby) {
        Health health = state.getHealth(); Hunger hunger = state.getHunger(); Mood mood = state.getMood(); Sleep sleep = state.getSleep();
        if(hobby.getId().equals("devoirs")){
            if(personality.getConscienciosite().isHigh()){
                state.getMood().add(1);
            }
            if(personality.getConscienciosite().isLow()){
                state.getMood().add(-1);
            }
            if(personality.getOuverture().isLow()){
                state.getMood().add(-1);
            }
            if(personality.getNeuroticisme().isHigh()){
                state.getMood().add(-0.5);
                state.getHunger().add(+0.5);
            }
            if(personality.getExtraversion().isHigh()){
                state.getMood().add(-0.5);
            }
            if(personality.getExtraversion().isLow()){
                state.getHunger().add(+0.5);
            }
            sleep.add(-0.5);
        }
        if(hobby.getId().equals("team_game")){
            if(personality.getExtraversion().isHigh()){
                state.getMood().add(+1);
            }
            if(personality.getExtraversion().isLow()){
                state.getMood().add(-1);
            }
            if(personality.getAgreabilite().isLow()){
                state.getMood().add(-1);
            }
            if(personality.getNeuroticisme().isHigh()){
                state.getMood().add(-1);
                state.getHunger().add(+0.5);
            }
            if(personality.getConscienciosite().isHigh()){
                state.getHealth().add(+0.5);
            }
            if(personality.getConscienciosite().isLow()){
                state.getMood().add(-0.5);
            }
        }
        if(hobby.getId().equals("sport")){
            if(personality.getExtraversion().isHigh()){
                state.getMood().add(+1);
            }
            if(personality.getNeuroticisme().isHigh()){
                state.getMood().add(+1);
                state.getHunger().add(-1);
            }
            if(personality.getNeuroticisme().isLow()){
                state.getHealth().add(+0.5);
            }
            if(personality.getConscienciosite().isHigh()){
                state.getHealth().add(+0.5);
                state.getMood().add(+0.5);
            }
            if(personality.getOuverture().isLow()){
                state.getMood().add(-0.5);
            }
            if(personality.getOuverture().isHigh()){
                state.getMood().add(+0.5);
            }
            health.add(+0.5);
            sleep.add(-0.5);
        }
        if(hobby.getId().equals("art")){
            if(personality.getOuverture().isHigh()){
                state.getMood().add(+1);
            }
            if(personality.getOuverture().isLow()){
                state.getMood().add(-0.5);
            }
            if(personality.getNeuroticisme().isHigh()){
                state.getMood().add(-0.5);
                state.getHunger().add(+0.5);
            }
            if(personality.getConscienciosite().isHigh()){
                mood.add(+0.5);
            }
            if(personality.getExtraversion().isLow()){
                mood.add(+0.5);
            }
        }
        if(hobby.getId().equals("volunteering")){
            if(personality.getAgreabilite().isHigh()){
                mood.add(+1);
            }
            if(personality.getAgreabilite().isLow()){
                mood.add(-1);
            }
            if(personality.getExtraversion().isHigh()){
                mood.add(+0.5);
            }
            if(personality.getNeuroticisme().isHigh()){
                mood.add(-0.5);
            }
        }
        if(hobby.getId().equals("learning")){
            if(personality.getOuverture().isHigh()){
                mood.add(+1);
            }
            if(personality.getOuverture().isLow()){
                mood.add(-1);
            }
            if(personality.getNeuroticisme().isHigh()){
                hunger.add(+0.5);
            }
            if(personality.getConscienciosite().isHigh()){
                mood.add(+0.5);
            }
            if(personality.getExtraversion().isLow()){
                mood.add(+0.5);
            }
        }
        if(hobby.getId().equals("work")){
            if(state.getSleep().isSleeping()){
                state.getSleep().setSleeping(false);
            }
            if(personality.getConscienciosite().isHigh()){
                mood.add(+1);
            }
            if(personality.getConscienciosite().isLow()){
                mood.add(-1);
            }
            if(personality.getNeuroticisme().isHigh()){
                hunger.add(+0.5);
                mood.add(-0.5);
            }
            if(personality.getExtraversion().isLow()){
                mood.add(-0.5);
            }
            if(personality.getNeuroticisme().isHigh()){
                mood.add(+0.5);
            }
            health.add(-0.5);
            sleep.add(-0.5);
        }
        if(hobby.getId().equals("sleep")){
            if(personality.getNeuroticisme().isHigh()){
                mood.add(+1);
            }
            if(personality.getConscienciosite().isLow()){
                mood.add(+0.5);
            }
            if(personality.getExtraversion().isLow()){
                mood.add(+0.5);
            }
            if(personality.getOuverture().isHigh()){
                hunger.add(+0.5);
            }
            health.add(+0.5);
            sleep.add(+1);
            state.getSleep().setSleeping(true);
        }
    }

    public void sickReact() {
        if(state.getHealth().getNiveau() >= 5){
            person.setSick(false);
            goHome(person);
            person.setEvent(null);
        }
        else{
            state.getHealth().add(0.25);
            state.getMood().add(-0.5);
            state.getHunger().add(+0.5);
            state.getSleep().add(+0.75);
        }
    }

    public void eventReact(String id) {
        switch(id){
            case "sick": sickReact(); break;
            case "success": successReact(); break;
        }
    }

    private void successReact() {
        PersonalityTrait maxPerso = personality.getMaxPerso();
        Health health = state.getHealth();
        Mood mood = state.getMood();
        Sleep sleep = state.getSleep();
        Hunger hunger = state.getHunger();
        if(maxPerso instanceof Openness){
            health.add(+0.5);
            mood.add(+3);
            sleep.add(+1);
            hunger.add(-0.5);
        }
        else if(maxPerso instanceof Conscientiousness){
            health.add(+1);
            mood.add(+2.5);
            sleep.add(-0.5);
            hunger.add(+0.5);
        }
        else if(maxPerso instanceof Extraversion){
            health.add(+1);
            mood.add(+3.5);
            sleep.add(+1.5);
            hunger.add(+1);
        }
        else if(maxPerso instanceof Neuroticism){
            health.add(-0.5);
            mood.add(+2);
            sleep.add(+1.5);
            hunger.add(-1);
        }
        else if(maxPerso instanceof Agreeableness){
            health.add(+1.5);
            mood.add(+3);
            sleep.add(-1);
            hunger.add(+0.5);
        }
    }
}
