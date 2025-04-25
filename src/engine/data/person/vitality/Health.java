package engine.data.person.vitality;

/**
 * Classe de donnée stockant les différente information liée à la santé d'un indidivu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Health extends Vitality {
    private Boolean estMalade;
    private int derniereConsultation;

    public Health(double niveau, int derniereConsultation, Boolean estMalade) {
        super(niveau);
        this.derniereConsultation = derniereConsultation;
        this.estMalade = estMalade;
    }

    public Boolean isMalade() {
        return estMalade;
    }

    public void setMalade(Boolean estMalade) {
        this.estMalade = estMalade;
    }
}
