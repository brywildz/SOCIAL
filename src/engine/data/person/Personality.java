package engine.data.person;
import engine.data.person.personalityTraits.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe de donnée regroupant les informations de la personnalité d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Personality {
    private Openness openness;
    private Conscientiousness conscientiousness;
    private Agreeableness agreeableness;
    private Neuroticism neuroticism;
    private Extraversion extraversion;
    private ArrayList<PersonalityTrait> personalities;

    public Personality(int agr, int cons, int extra, int neuro, int ouvert) {
        Agreeableness agreeableness = new Agreeableness(agr);
        Conscientiousness conscientiousness = new Conscientiousness(cons);
        Extraversion extraversion = new Extraversion(extra);
        Neuroticism neuroticism = new Neuroticism(neuro);
        Openness openness = new Openness(ouvert);
        this.extraversion = extraversion;
        this.neuroticism = neuroticism;
        this.agreeableness = agreeableness;
        this.conscientiousness = conscientiousness;
        this.openness = openness;
        personalities = new ArrayList<>();
        personalities.add(agreeableness); personalities.add(conscientiousness); personalities.add(extraversion);
        personalities.add(neuroticism); personalities.add(openness);
    }

    public PersonalityTrait getMaxPerso(){
        Iterator<PersonalityTrait> it = personalities.iterator();
        PersonalityTrait cMax = it.next();
        while(it.hasNext()){
            PersonalityTrait c = it.next();
            if (c.getLevel() > cMax.getLevel()){
                cMax = c;
            }
        }
        return cMax;
    }

    public PersonalityTrait getMinPerso(){
        Iterator<PersonalityTrait> it = personalities.iterator();
        PersonalityTrait cMin = it.next();
        while(it.hasNext()){
            PersonalityTrait c = it.next();
            if (c.getLevel() < cMin.getLevel()){
                cMin = c;
            }
        }
        return cMin;
    }


    public Openness getOuverture() {
        return openness;
    }

    public Conscientiousness getConscienciosite() {
        return conscientiousness;
    }

    public Agreeableness getAgreabilite() {
        return agreeableness;
    }

    public Neuroticism getNeuroticisme() {
        return neuroticism;
    }

    public Extraversion getExtraversion() {
        return extraversion;
    }
}
