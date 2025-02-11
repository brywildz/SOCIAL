package engine.data.carte;

public class Horloge {
    private static Horloge instance = new Horloge();
    private int heure;
    private int minute;
    private int seconde;
    //classe date

    private Horloge(){
    }

    private static Horloge getInstance(){return instance;};
}
