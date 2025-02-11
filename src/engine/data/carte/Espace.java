package engine.data.carte;

public abstract class Espace {
    private int capaciteMax;
    private int capacitMin;
    private int frequenceEspace;
    private boolean espaceVide; //tres bizarre de crée un espece en sahcant à l'avance qu'il sera vide
    private String nomEspace;
    private int id;
    private Horaire ouverture;
    private Horaire fermeture;

    public Espace(Horaire fermeture, Horaire ouverture, int id, String nomEspace, boolean espaceVide, int frequenceEspace, int capacitMin, int capaciteMax) {
        this.fermeture = fermeture;
        this.ouverture = ouverture;
        this.id = id;
        this.nomEspace = nomEspace;
        this.espaceVide = espaceVide;
        this.frequenceEspace = frequenceEspace;
        this.capacitMin = capacitMin;
        this.capaciteMax = capaciteMax;
    }
    public int getId() {
        return id;
    }

    public Horaire getOuverture() {
        return ouverture;
    }

    public void setOuverture(Horaire ouverture) {
        this.ouverture = ouverture;
    }

    public Horaire getFermeture() {
        return fermeture;
    }

    public void setFermeture(Horaire fermeture) {
        this.fermeture = fermeture;
    }

    public int getCapaciteMax(){
        return capaciteMax;
    }
    public void setCapaciteMax(int capaciteMax){
        this.capaciteMax = capaciteMax;
    }
    public int getCapacitMin(){
        return capacitMin;
    }
    public void setCapacitMin(int capacitMin){
        this.capacitMin = capacitMin;
    }
    public int getFrequenceEspace(){
        return frequenceEspace;
    }
    public void setFrequenceEspace(int frequenceEspace){
        this.frequenceEspace = frequenceEspace;

    }
    public boolean isEspaceVide(){
        return espaceVide;
    }
    public void setEspaceVide(boolean espaceVide){
        this.espaceVide = espaceVide;
    }
    public String getNomEspace(){
        return nomEspace;
    }
    public void setNomEspace(String nomEspace){
        this.nomEspace = nomEspace;
    }

}
