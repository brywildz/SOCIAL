package engine.data.person.bienetre;

/**
 * Classe de donnée stockant les différente information liée à la faim d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Faim extends BienEtre{
    private String repasPrefere;
    private String dernierRepas;

    public Faim(int niveau, String repasPrefere, String dernierRepas) {
        super(niveau);
        this.repasPrefere = repasPrefere;
        this.dernierRepas = dernierRepas;
    }
}
