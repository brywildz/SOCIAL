package engine.data.individu;

import engine.data.carte.Block;
import engine.data.evenement.Evenement;
import engine.data.individu.bienetre.BienEtre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Classe de donnée stockant l'entierté des information liée à un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Individu {
    private String nom;
    private int age;
    private String statutSocial;
    private Personnalite perso;
    private Etat etat;
    private Relation relation;
    private Block location;
    private Evenement currentEvent = null;

    public Individu(String nom, int age, String statutSocial, Personnalite perso, Etat etat, Relation relation, Block location) {
        this.nom = nom;
        this.age = age;
        this.statutSocial = statutSocial;
        this.perso = perso;
        this.etat = etat;
        this.relation = relation;
        this.location = location;
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

    public void setCurrentEvent(Evenement currentEvent) {
        this.currentEvent = currentEvent;
    }

    @Override
    public String toString() {
        return "Individu{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", statutSocial='" + statutSocial + '\'' +
                ", perso=" + perso +
                ", etat=" + etat +
                ", relation=" + relation +
                ", location=" + location +
                ", currentEvent=" + currentEvent +
                '}';
    }

    public Evenement getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Update l'etat de l'individu en fonction de l'etat en paramètre
     *
     * @param etat l'etat donnée par EventManager apres le changement en fonction du caractere
     */
    public void refreshEtat(Etat etat){
        HashMap<String, BienEtre> etatAttendu = etat.getList();
        HashMap<String, BienEtre> etatActuel = this.etat.getList();
        Iterator<BienEtre> it = etatAttendu.values().iterator();
        int i=0;
        while(it.hasNext()){
            BienEtre be = it.next();
            if(be!=null){
                etatActuel.put( be.getId(), be);
            }
            i++;
        }
    }

    public void setEtat(HashMap<String, BienEtre> etat){
        this.etat = new Etat(etat);
    }
}
