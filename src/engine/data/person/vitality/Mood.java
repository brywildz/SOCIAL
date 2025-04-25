package engine.data.person.vitality;

/**
 * Classe de donnée stockant les différente information liée à la faim d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Mood extends Vitality {
    private String type;

    public Mood(float niveau, String humeur) {
        super(niveau);
        this.type = humeur;
    }
}
