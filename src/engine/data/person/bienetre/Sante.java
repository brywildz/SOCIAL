package engine.data.person.bienetre;

/**
 * Classe de donnée stockant les différente information liée à la santé d'un indidivu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Sante extends BienEtre{
    private Boolean estMalade;
    private int derniereConsultation;

    public Sante(int niveau, int derniereConsultation, Boolean estMalade) {
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
