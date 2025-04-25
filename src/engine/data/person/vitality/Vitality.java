package engine.data.person.vitality;

/**
 * Classe abstraite définissant les differents état d'un individu
 *
 * @version 0.1
 * @author Dylan Manseri, Amadou Bawol
 */
public abstract class Vitality {
    private String Id;
    private double niveau;

    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }

    public Vitality(double niveau) {
        this.niveau = niveau;
    }

    public String getId() {
        return Id;
    }

    public double getNiveau() {
        return niveau;
    }

    @Override
    public String toString() {
        return "Vitality{" +
                "niveau=" + niveau +
                '}';
    }

    public void add(double s){
        niveau += s;
        if(niveau > 10){
            niveau = 10;
        }
        if(niveau < 0){
            niveau = 0;
        }
    }

    public boolean isHigh(){
        return niveau > 6;
    }

    public boolean isLow(){
        return niveau < 4;
    }
}
