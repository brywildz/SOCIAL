package engine.data.carte;

/**
 * Classe de donnée gérant l'horloge du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Clock {
    private static Clock instance = new Clock();
    private Time h;
    //classe date

    private Clock(){
    }

    public static Clock getInstance(){return instance;};

    public void newSecond(){
        h.newSecond();
    }

    public Time getHoraire(){
        return this.h;
    }
}
