package engine.data.individu;

import engine.data.carte.Block;
import engine.data.carte.Infrastructure;
import engine.data.evenement.Evenement;
import engine.data.individu.bienetre.BienEtre;

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
    private HashMap<String, Integer> personalite;
    private Etat etat;
    private Relation relation;
    private Block location;
    private Evenement currentEvent = null;
    private Infrastructure maison;

    public Individu(String nom, int age, String statutSocial, Etat etat, Relation relation, Block location, int agr, int cons, int extra, int neuro, int ouvert) {
        this.nom = nom;
        this.age = age;
        this.statutSocial = statutSocial;
        this.etat = etat;
        this.relation = relation;
        this.location = location;
        personalite = new HashMap<>();
        personalite.put("agreabilite", agr);
        personalite.put("conscienciosite", cons);
        personalite.put("extraversion", extra);
        personalite.put("neuroticisme", neuro);
        personalite.put("ouverture", ouvert);
    }

    // <editor-fold> desc="getter&setter"
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

    public HashMap<String, Integer> getPersonalite() {
        return personalite;
    }

    public void setPersonalite(HashMap<String, Integer> personalite) {
        this.personalite = personalite;
    }

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public void setCurrentEvent(Evenement currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void setEtat(HashMap<String, BienEtre> etat){
        this.etat = new Etat(etat);
    }

    public Evenement getCurrentEvent() {
        return currentEvent;
    }
    // </editor-fold>

    //<editor-fold> desc="personnalite"
    public String getMaxPerso(){
        Iterator<String> it = personalite.keySet().iterator();
        String maxKey = it.next();
        Integer max = personalite.get(maxKey);
        while(it.hasNext()){
            String persoKey = it.next();
            Integer perso = personalite.get(persoKey);
            if (perso > max){
                max = perso;
                maxKey = persoKey;
            }
        }
        return maxKey;
    }

    public String getMinPerso(){
        Iterator<String> it = personalite.keySet().iterator();
        String minKey = it.next();
        Integer min = personalite.get(minKey);
        while(it.hasNext()){
            String persoKey = it.next();
            Integer perso = personalite.get(persoKey);
            if (perso < min){
                min = perso;
                minKey = persoKey;
            }
        }
        return minKey;
    }

    public int getAgreabilite(){
        return this.personalite.get("agreabilite");
    }

    public int getConscienciosite(){
        return this.personalite.get("conscienciosite");
    }

    public int getExtraversion(){
        return this.personalite.get("extraversion");
    }

    public int getNeuroticisme(){
        return this.personalite.get("neuroticisme");
    }

    public int getOuverture(){
        return this.personalite.get("ouverture");
    }
    //</editor-fold>

    public boolean isOpen(){
        //verifier si ce trait de caractere est majoritaire
        return false;
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

    @Override
    public String toString() {
        return "Individu{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", statutSocial='" + statutSocial + '\'' +
                ", personalite=" + personalite +
                ", etat=" + etat +
                ", relation=" + relation +
                ", location=" + location +
                ", currentEvent=" + currentEvent +
                '}';
    }

}
