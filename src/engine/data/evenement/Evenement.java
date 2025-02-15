package engine.data.evenement;

import engine.data.individu.bienetre.BienEtre;

import java.util.ArrayList;

public abstract class Evenement {
    private String nom;
    private int duree;
    private boolean actif;
    private ArrayList<BienEtre> bienEtre;
    private String description;


    public Evenement(String nom, String description, int duree) {
        this.nom = nom;
        this.duree = duree;
        this.bienEtre = new ArrayList<>();
        this.description = description;


    }
    public Evenement(String type) {
        this.nom = type;
    }
    public String getNom() {
        return nom;
    }
    public String description() {
        return description;
    }
    public boolean isActif() {
        return actif;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void decrementerDuree() {
        if(duree > 0) {
            duree--;
        }
        
    }
    public boolean endEvent() {
        return duree==0;
    }
    public ArrayList<BienEtre> getBienEtre() {
        return bienEtre;
    }
    public abstract void afficherEffet();


}
