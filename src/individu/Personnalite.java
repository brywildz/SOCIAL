package individu;
import individu.caractere.*;

public class Personnalite {
    private String type;
    private Ouverture ouverture;
    private Conscienciosite conscienciosite;
    private Agreabilite agreabilite;
    private Neuroticisme neuroticisme;
    private Extraversion extraversion;

    public Personnalite(String type, Extraversion extraversion, Neuroticisme neuroticisme, Agreabilite agreabilite, Conscienciosite conscienciosite, Ouverture ouverture) {
        this.type = type;
        this.extraversion = extraversion;
        this.neuroticisme = neuroticisme;
        this.agreabilite = agreabilite;
        this.conscienciosite = conscienciosite;
        this.ouverture = ouverture;
    }
}
