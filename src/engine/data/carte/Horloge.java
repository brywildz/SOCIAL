package engine.data.carte;

/**
 * Classe de donnée gérant l'horloge du jeu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Horloge {
    private static Horloge instance = new Horloge();
    private int heure;
    private int minute;
    private int seconde;
    //classe date

    private Horloge(){
    }

    public static Horloge getInstance(){return instance;};

    public void newSecond(){
        seconde++;
    }
}
