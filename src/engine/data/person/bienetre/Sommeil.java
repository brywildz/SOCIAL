package engine.data.person.bienetre;

/**
 * Classe de donnée stockant les différente information liée au sommeil d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Sommeil extends BienEtre{
    private Boolean isSleeping;

    public Sommeil(int niveau, Boolean isSleeping) {
        super(niveau);
        this.isSleeping = isSleeping;
    }

    public Boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(Boolean sleeping) {
        isSleeping = sleeping;
    }
}
