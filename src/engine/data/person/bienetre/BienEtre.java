package engine.data.person.bienetre;

/**
 * Classe abstraite définissant les differents état d'un individu
 *
 * @version 0.1
 * @author Dylan Manseri, Amadou Bawol
 */
public abstract class BienEtre {
    private String Id;
    private int niveau;

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public BienEtre(int niveau) {
        this.niveau = niveau;
    }

    public String getId() {
        return Id;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public String toString() {
        return "BienEtre{" +
                "niveau=" + niveau +
                '}';
    }
}
