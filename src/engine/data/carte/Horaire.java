package engine.data.carte;

/**
 * Classe de donnée stockant les informations liée à l'horaire , instanciable dans les où l'on manipule des heures
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Horaire {
    private int heure;
    private int minute;
    private int seconde;

    public Horaire(int heure, int seconde, int minute) {
        this.heure = heure;
        this.seconde = seconde;
        this.minute = minute;
    }
    public void newSecond(){
        seconde++;
    }

    @Override
    public boolean equals(Object obj){
        Horaire h = (Horaire) obj;
        return this.heure == h.heure && this.minute == h.minute && this.seconde == h.seconde;
    }
}
