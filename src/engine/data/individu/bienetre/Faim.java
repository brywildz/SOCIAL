package engine.data.individu.bienetre;

public class Faim extends BienEtre{
    private String repasPrefere;
    private String dernierRepas;

    public Faim(String repasPrefere, String dernierRepas) {
        this.repasPrefere = repasPrefere;
        this.dernierRepas = dernierRepas;
    }
}
