package engine.process;

import engine.data.event.*;
import engine.data.person.PersonState;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.data.person.personalityTraits.*;
import engine.data.person.vitality.*;
import engine.process.repository.HobbyRepository;

/**
 * Classe de traitement traitant les différents reaction d'un individu dépendamment de son caractère
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Reaction {
    private Person person;
    private String action;
    private Event event;

    public Reaction(Person person){
        this.person = person;
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
        Hobby prefHobby = actionRepo.getPreferredAction(p.getPersonality().getMaxPerso());
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
        Personality per = person.getPersonality();
        PersonState ps = person.getPersonState();
        Health health = ps.getHealth(); Hunger hunger = ps.getHunger(); Mood mood = ps.getMood(); Sleep sleep = ps.getSleep();
        if(hobby.getId().equals("devoirs")){
            if(per.getConscienciosite().isHigh()){
                ps.getMood().add(2);
            }
            if(per.getConscienciosite().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-1);
                ps.getHunger().add(+1);
            }
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(-1);
            }
            if(per.getExtraversion().isLow()){
                ps.getHunger().add(+1);
            }
            sleep.add(-1);
        }
        if(hobby.getId().equals("team_game")){
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getExtraversion().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getAgreabilite().isLow()){
                ps.getMood().add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-2);
                ps.getHunger().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                ps.getHealth().add(+1);
            }
            if(per.getConscienciosite().isLow()){
                ps.getMood().add(-1);
            }
        }
        if(hobby.getId().equals("sport")){
            if(per.getExtraversion().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(+2);
                ps.getHunger().add(-2);
            }
            if(per.getNeuroticisme().isLow()){
                ps.getHealth().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                ps.getHealth().add(+1);
                ps.getMood().add(+1);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-1);
            }
            if(per.getOuverture().isHigh()){
                ps.getMood().add(+1);
            }
            health.add(+1);
            sleep.add(-1);
        }
        if(hobby.getId().equals("art")){
            if(per.getOuverture().isHigh()){
                ps.getMood().add(+2);
            }
            if(per.getOuverture().isLow()){
                ps.getMood().add(-1);
            }
            if(per.getNeuroticisme().isHigh()){
                ps.getMood().add(-1);
                ps.getHunger().add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
        }
        if(hobby.getId().equals("volunteering")){
            if(per.getAgreabilite().isHigh()){
                mood.add(+2);
            }
            if(per.getAgreabilite().isLow()){
                mood.add(-2);
            }
            if(per.getExtraversion().isHigh()){
                mood.add(+1);
            }
            if(per.getNeuroticisme().isHigh()){
                mood.add(-1);
            }
        }
        if(hobby.getId().equals("learning")){
            if(per.getOuverture().isHigh()){
                mood.add(+2);
            }
            if(per.getOuverture().isLow()){
                mood.add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                hunger.add(+1);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
        }
        if(hobby.getId().equals("work")){
            if(ps.getSleep().isSleeping()){
                ps.getSleep().setSleeping(false);
            }
            if(per.getConscienciosite().isHigh()){
                mood.add(+2);
            }
            if(per.getConscienciosite().isLow()){
                mood.add(-2);
            }
            if(per.getNeuroticisme().isHigh()){
                hunger.add(+1);
                mood.add(-1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(-1);
            }
            if(per.getNeuroticisme().isHigh()){
                mood.add(+1);
            }
            health.add(-1);
            sleep.add(-1);
        }
        if(hobby.getId().equals("sleep")){
            if(per.getNeuroticisme().isHigh()){
                mood.add(+2);
            }
            if(per.getConscienciosite().isLow()){
                mood.add(+1);
            }
            if(per.getExtraversion().isLow()){
                mood.add(+1);
            }
            if(per.getOuverture().isHigh()){
                hunger.add(+1);
            }
            health.add(+1);
            sleep.add(+2);
            ps.getSleep().setSleeping(true);
        }
    }
}
