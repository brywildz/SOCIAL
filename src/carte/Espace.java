package carte;

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
}
