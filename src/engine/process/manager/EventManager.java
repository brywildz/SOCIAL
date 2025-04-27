package engine.process.manager;

import engine.data.event.*;
import engine.data.map.Map;
import engine.data.map.Clock;
import engine.data.map.Time;
import engine.data.person.personalityTraits.Extraversion;
import engine.data.person.personalityTraits.Neuroticism;
import engine.data.person.personalityTraits.PersonalityTrait;
import engine.process.builder.EventBuilder;
import engine.process.repository.EventRepository;
import engine.data.person.Person;
import engine.process.repository.PersonRepository;

import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.utils.LifeUtilities.goSleep;


/**
 * Classe qui gère la création et le traitement des différents événements.
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class EventManager {
    private Map map = Map.getInstance();
    private PersonRepository indRepo = PersonRepository.getInstance();
    private EventRepository eventRepo = EventRepository.getInstance();

    public EventManager() {

    }

    /**
     * Crée un potentiel événement selon l'aléatoire.
     * Il peut être social ou personnel.
     * La possibilité d'instanciation de l'événement dépend de la personnalité de la personne.
     * Les événements personnels sont prioritaires aux événements sociaux.
     * Les événements sociaux sont prioritaires aux hobbies qui ne sont pas les préférés de la personne dont il est sujet.
     */
    public void refresh(Person person) {
        Event event = person.getEvent();
        if(event == null) {
            newEvent(person);
        }
        else if(event instanceof SocialEvent) {
            if(((SocialEvent) event).isFinished()){
                person.setEvent(null);
            }
        }
    }


    public void newEvent(Person person) {
        int eventIndex = random(0, 3);
        if (eventIndex == 0) { //Événement personnel
            PersonalEvent pEvent = new PersonalEvent();
            EventBuilder eventBuilder = new EventBuilder(person, pEvent);
            eventBuilder.build();
        }
        if (eventIndex == 1) {
            if (!person.isPreferred() && !person.isWorking()) {
                PersonalityTrait maxPerso = person.getPersonality().getMaxPerso();
                PersonalityTrait minPerso = person.getPersonality().getMinPerso();
                int probaIndex = random(0, 10);
                if (maxPerso instanceof Neuroticism || minPerso instanceof Extraversion) {
                    if (probaIndex <= 3) { //30% de chance
                        SocialEvent sEvent = new SocialEvent();
                        EventBuilder eventBuilder = new EventBuilder(person, sEvent);
                        eventBuilder.build();
                    }
                } else {
                    if (probaIndex <= 6) { //60% de chance
                        SocialEvent sEvent = new SocialEvent();
                        EventBuilder eventBuilder = new EventBuilder(person, sEvent);
                        eventBuilder.build();
                    }
                }
            }
        }
    }


            /**
             * Gère certains cas particuliers d'événement.
             */
    public void reset(Person person){
        String id = person.getEvent().getId();
        Hobby hobby = person.getHobby();
        if(id.equals("meet") && hobby.hasStart()){ //Dés qu'il change de lieu, l'événement de rencontre s'arrête
            person.setEvent(null);
        }
        else if(Clock.getInstance().getTime().getHour() > 5 && person.getEvent().getId().equals("party")){ //Après 5h le night club ferme et les gens vont dormir
            person.setEvent(null);
            goSleep(person, Clock.isWeekend());
        }
    }

    /**
     * Modifie la météo si la dernière est terminée.
     * Le choix de la météo depend de l'aléatoire.
     */
    public void refreshWeather() {
        int nbr = random(8);
        Time start = Clock.getInstance().getActualTime();
        Time end = Clock.getInstance().getActualTime();
        if(map.getWeather().isFinish()){
            if(nbr < 5){
                end.addHour(random(1,3));
                map.setWeather(new WeatherEvent("normal", start, end, "Le temps est normal actuellement."));
            }
            else if(nbr == 5){
                end.addHour(random(1,4));
                map.setWeather(new WeatherEvent("snow", start, end, "Il neige, c'est beau."));
            }
            else{
                end.addHour(random(1,2));
                map.setWeather(new WeatherEvent("rain", start, end, "Il pleut abondement"));
            }
        }
    }


}

