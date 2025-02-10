package individu;

public class Individu {
    private String nom;
    private int age;
    private String statutSocial;
    private Personnalite perso;
    private Etat etat;
    private Relation relation;

    public Individu(String nom, int age, String statutSocial, Personnalite perso, Etat etat, Relation relation) {
        this.nom = nom;
        this.age = age;
        this.statutSocial = statutSocial;
        this.perso = perso;
        this.etat = etat;
        this.relation = relation;
    }
}
