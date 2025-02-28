package engine.data.person.caractere;

public abstract class Caractere {
    private int niveau;

    public Caractere(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
