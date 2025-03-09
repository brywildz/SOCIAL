package engine.data.person.vitality;

/**
 * Classe de donnée stockant les différente information liée au sommeil d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Sleep extends Vitality {
    private Boolean isSleeping;

    public Sleep(int niveau, Boolean isSleeping) {
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
