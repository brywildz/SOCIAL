package engine.data.map;

/**
 * Classe de donnée gérant l'horloge du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Clock {
    private Time h;
    private Clock(){h=new Time(0,0,0, new Date(0,0,0));}
    private static Clock instance = new Clock();

    public static Clock getInstance(){return instance;};

    public void newSecond(){
        h.newSecond();
    }

    public Time getHoraire(){
        return this.h;
    }

    @Override
    public String toString() {
        return h.toString();
    }
}
