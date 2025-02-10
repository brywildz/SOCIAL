package individu.bienetre;

public class Sante extends BienEtre{
    private boolean estMalade;
    private int derniereConsultation;

    public Sante(int derniereConsultation, boolean estMalade) {
        this.derniereConsultation = derniereConsultation;
        this.estMalade = estMalade;
    }
}
