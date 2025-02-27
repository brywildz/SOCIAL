package engine.data.individu;
import engine.data.individu.caractere.*;

/**
 * Classe de donnée regroupant les informations de la personnalité d'un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Personnalite {
    private String type;
    private Ouverture ouverture;
    private Conscienciosite conscienciosite;
    private Agreabilite agreabilite;
    private Neuroticisme neuroticisme;
    private Extraversion extraversion;

    public Personnalite(String type, Extraversion extraversion, Neuroticisme neuroticisme, Agreabilite agreabilite, Conscienciosite conscienciosite, Ouverture ouverture) {
        this.extraversion = extraversion;
        this.neuroticisme = neuroticisme;
        this.agreabilite = agreabilite;
        this.conscienciosite = conscienciosite;
        this.ouverture = ouverture;

    }

    public String getType() {
        return type;
    }
}
