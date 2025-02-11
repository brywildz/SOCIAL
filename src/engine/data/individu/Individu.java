package engine.data.individu;

import engine.data.carte.Block;

public class Individu {
    private String nom;
    private int age;
    private String statutSocial;
    private Personnalite perso;
    private Etat etat;
    private Relation relation;
    private Block location;

    public Individu(String nom, int age, String statutSocial, Personnalite perso, Etat etat, Relation relation) {
        this.nom = nom;
        this.age = age;
        this.statutSocial = statutSocial;
        this.perso = perso;
        this.etat = etat;
        this.relation = relation;
    }

    public String getStatutSocial() {
        return statutSocial;
    }

    public void setStatutSocial(String statutSocial) {
        this.statutSocial = statutSocial;
    }

    public Block getLocation() {
        return location;
    }

    public void setLocation(Block location) {
        this.location = location;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Personnalite getPerso() {
        return perso;
    }

    public void setPerso(Personnalite perso) {
        this.perso = perso;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
