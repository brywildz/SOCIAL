package engine.data.person;

import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.event.Event;
import engine.data.person.bienetre.BienEtre;

import java.util.HashMap;
import java.util.Iterator;
/**
 * Classe de donnée stockant l'entierté des information liée à un individu
 *
 * @author Dylan Manseri, Amadou Bawol
 * @version 0.1
 */
public class Person {
    private String nom;
    private int age;
    private String statutSocial;
    private HashMap<String, Integer> personalite;
    private PersonState personState;
    private PersonRelationships personRelationships;
    private Block location;
    private Event currentEvent = null;
    private Infrastructure maison;

    public Person(String nom, int age, String statutSocial, PersonState personState, PersonRelationships personRelationships, Block location, int agr, int cons, int extra, int neuro, int ouvert) {
        this.nom = nom;
        this.age = age;
        this.statutSocial = statutSocial;
        this.personState = personState;
        this.personRelationships = personRelationships;
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

    public PersonRelationships getRelation() {
        return personRelationships;
    }

    public void setRelation(PersonRelationships personRelationships) {
        this.personRelationships = personRelationships;
    }

    public PersonState getEtat() {
        return personState;
    }

    public void setEtat(PersonState personState) {
        this.personState = personState;
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

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void setEtat(HashMap<String, BienEtre> etat){
        this.personState = new PersonState(etat);
    }

    public Event getCurrentEvent() {
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
     * @param personState l'etat donnée par EventManager apres le changement en fonction du caractere
     */
    public void refreshEtat(PersonState personState){
        HashMap<String, BienEtre> etatAttendu = personState.getList();
        HashMap<String, BienEtre> etatActuel = this.personState.getList();
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
                ", etat=" + personState +
                ", relation=" + personRelationships +
                ", location=" + location +
                ", currentEvent=" + currentEvent +
                '}';
    }

}
