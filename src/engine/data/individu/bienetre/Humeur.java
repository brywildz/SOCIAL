package engine.data.individu.bienetre;

/**
 * Classe de donnée stockant les différente information liée à la faim d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */

public class Humeur extends BienEtre{
    private String type;

    public Humeur(int niveau, String humeur) {
        super(niveau);
        this.type = humeur;
    }
}
