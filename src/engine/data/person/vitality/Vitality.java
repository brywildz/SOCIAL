package engine.data.person.vitality;

/**
 * Classe abstraite définissant les differents état d'un individu
 *
 * @version 0.1
 * @author Dylan Manseri, Amadou Bawol
 */
public abstract class Vitality {
    private String Id;
    private int niveau;

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Vitality(int niveau) {
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
        return "Vitality{" +
                "niveau=" + niveau +
                '}';
    }

    public void add(int s){
        niveau += s;
        if(niveau > 10){
            niveau = 10;
        }
    }
}
